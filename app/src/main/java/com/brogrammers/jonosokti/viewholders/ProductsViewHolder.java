package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class ProductsViewHolder extends RecyclerView.ViewHolder {
    public TextView tvProductName;
    public ImageView imageView;
    public CardView container;
    public ProductsViewHolder(@NonNull View itemView) {
        super(itemView);
        tvProductName = itemView.findViewById(R.id.textview_product_name);
        imageView = itemView.findViewById(R.id.imageView_product);

        container = itemView.findViewById(R.id.cardview_product_container);
    }
}
