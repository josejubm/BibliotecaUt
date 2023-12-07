package com.biblotecaut.views;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.biblotecaut.R;
import com.biblotecaut.models.BookPdf;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.google.android.material.card.MaterialCardView;

import java.io.InputStream;

public class PdfViewerActivity extends AppCompatActivity {

    private ImageView btnBack;
    private ImageView btnNext;
    private TextView textActualPage;
    private TextView textTotalPages;
    private ImageView btnFullPage;
    private BookPdf pdf;
    private PDFView pdfView;
    private MaterialCardView containerSettings;

    private Boolean isFullScreen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        getPdfFile();
        setView();
        setPdfInView();
        setListeners();
    }

    private void setListeners() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdfView.jumpTo(pdfView.getCurrentPage() + 1);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdfView.jumpTo(pdfView.getCurrentPage() - 1);
            }
        });

        btnFullPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFullscreenMode(true);
            }
        });
    }

    private void setPdfInView() {
        InputStream inputStream = getResources().openRawResource(pdf.getPdf());
        pdfView.fromStream(inputStream)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        textActualPage.setText(String.valueOf(page + 1));
                    }
                })
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        textTotalPages.setText(String.valueOf(nbPages));
                    }
                })
                .load();
    }

    private void setView() {
        pdfView = findViewById(R.id.pdfView);
        btnBack = findViewById(R.id.btn_back_page);
        btnNext = findViewById(R.id.btn_next);
        textActualPage = findViewById(R.id.edt_page_current);
        textTotalPages = findViewById(R.id.text_count_total);
        btnFullPage = findViewById(R.id.btn_full);
        containerSettings = findViewById(R.id.container_settings);
    }

    private void getPdfFile() {
        pdf = (BookPdf) getIntent().getSerializableExtra("pdf");
    }

    private void setFullscreenMode(boolean fullscreen) {
        Window window = getWindow();
        View decorView = window.getDecorView();

        isFullScreen = fullscreen;
        if (fullscreen) {
            containerSettings.setVisibility(View.GONE);
            // Ocultar la barra de navegación y la barra de estado
            int flags = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                flags |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            decorView.setSystemUiVisibility(flags);

            // Ocultar la barra de acción si está presente
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

            // Opcional: si quieres que la barra de estado también sea transparente
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            }
        } else {
            containerSettings.setVisibility(View.VISIBLE);

            // Mostrar la barra de navegación y la barra de estado
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

            // Mostrar la barra de acción si estaba oculta
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }

            // Restaurar el color original de la barra de estado si se cambió
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.red));
            } else {

            }
        }
    }

    @Override
    public void onBackPressed() {

        if(isFullScreen) {
            setFullscreenMode(false);
        } else {
            super.onBackPressed();
        }
    }
}