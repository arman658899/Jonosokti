package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView tvServiceName;
    public CardView container;
    public ImageView imageView;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tvServiceName = itemView.findViewById(R.id.textView_service_name);
        container = itemView.findViewById(R.id.cardview_product_container);
        imageView = itemView.findViewById(R.id.imageView_service);
    }
}
