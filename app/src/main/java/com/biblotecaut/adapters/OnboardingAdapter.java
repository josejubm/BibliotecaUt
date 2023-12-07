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
import com.biblotecaut.models.ItemOnboarding;


import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private List<ItemOnboarding> onboardingItems;
    private Context context;

    public OnboardingAdapter(Context context, List<ItemOnboarding> onboardingItems) {
        this.context = context;
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_onboarding, parent, false);
        return new OnboardingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        ItemOnboarding item = onboardingItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    public class OnboardingViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView descriptionTextView;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }

        public void bind(ItemOnboarding item) {
            // Cargar la imagen con Glide (puedes usar tu propia lógica de carga de imagen)
            imageView.setImageResource(item.getImage());
            descriptionTextView.setText(item.getDescription());
        }
    }
}
