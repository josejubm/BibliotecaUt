package com.biblotecaut.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.biblotecaut.R;
import com.biblotecaut.adapters.OnboardingAdapter;
import com.biblotecaut.models.ItemOnboarding;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {


    private ViewPager2 vpOnboarding;
    private MaterialButton btnNext;
    private MaterialButton btnStart;

    private List<ItemOnboarding> listOnboarding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        setView();
        getListOnboarding();
        setAdapter();
        setListeners();
    }

    private void setListeners() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpOnboarding.setCurrentItem(vpOnboarding.getCurrentItem() + 1);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnboardingActivity.this,LoginActivity.class));
                finish();
            }
        });

        vpOnboarding.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                // Lógica a ejecutar cuando cambia la página
                // `position` es la posición actual de la página seleccionada
                super.onPageSelected(position);
                if(position == listOnboarding.size() -1) {
                    btnNext.setVisibility(View.GONE);
                    btnStart.setVisibility(View.VISIBLE);
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                    btnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Lógica a ejecutar cuando se desplaza entre páginas
                // Puedes utilizar estos métodos según tus necesidades
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Lógica a ejecutar cuando cambia el estado de desplazamiento
                // Puedes utilizar estos métodos según tus necesidades
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void getListOnboarding() {
        listOnboarding = getOnboardingItems();
    }

    //enlazamos el adapter a nuestro viewpager2
    private void setAdapter() {
        OnboardingAdapter adapter = new OnboardingAdapter(this, listOnboarding);
        vpOnboarding.setAdapter(adapter);
    }

    //enlazamos las views con su variable en codigo
    private void setView() {
        vpOnboarding = findViewById(R.id.vp_onboarding);
        btnNext = findViewById(R.id.btn_next);
        btnStart = findViewById(R.id.btn_start);
    }

    //obtenemos los valores de la lista de la presentacion
    public static List<ItemOnboarding> getOnboardingItems() {
        List<ItemOnboarding> onboardingItems = new ArrayList<>();

        onboardingItems.add(new ItemOnboarding(
                R.drawable.copy01,
                "Lee libros online en cualquier momento y en cualquier lugar."
        ));

        onboardingItems.add(new ItemOnboarding(
                R.drawable.onboarding_2,
                "Busca el libro perfecto y descubre algo nuevo de interés para ti."
        ));

        onboardingItems.add(new ItemOnboarding(
                R.drawable.onboarding_3,
                "Disfruta de cientos de horas de aprendizaje, ¿estás preparado para comenzar?"
        ));

        return onboardingItems;
    }
}