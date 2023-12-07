package com.biblotecaut.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biblotecaut.R;
import com.biblotecaut.interfaces.OnImageClickListener;
import com.biblotecaut.models.BookPdf;

import java.util.List;

public class AdapterThumbs extends RecyclerView.Adapter<AdapterThumbs.PdfViewHolder> {

    private Context context;
    private List<BookPdf> pdfList;
    private OnImageClickListener onImageClickListener;
    public AdapterThumbs(Context context, List<BookPdf> pdfList) {
        this.context = context;
        this.pdfList = pdfList;
    }

    public void setOnImageClickListener(OnImageClickListener listener) {
        this.onImageClickListener = listener;
    }

    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thumb_pdf, parent, false);
        return new PdfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewHolder holder, int position) {
        BookPdf pdf = pdfList.get(position);
        holder.bind(pdf, position);
    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }

    public void updateList(List<BookPdf> newList) {
        pdfList = newList;
        notifyDataSetChanged();
    }

    public class PdfViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgThumb;
        private TextView textName;
        private TextView textIsbn;

        public PdfViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_thumb);
            textName = itemView.findViewById(R.id.text_name);
            textIsbn = itemView.findViewById(R.id.text_isbn);


        }

        public void bind(BookPdf pdf, int position) {
            imgThumb.setImageResource(pdf.getImage());
            textName.setText(pdf.getName());
            textIsbn.setText(pdf.getIsbn());

            imgThumb.setOnClickListener(view -> {
                if (position != RecyclerView.NO_POSITION && onImageClickListener != null) {
                    onImageClickListener.onImageClick(pdfList.get(position));
                }
            });
        }
    }
}