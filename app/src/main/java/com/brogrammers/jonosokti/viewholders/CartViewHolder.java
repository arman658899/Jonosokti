package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class CartViewHolder extends RecyclerView.ViewHolder {
    public TextView tvCategoryName,tvProviderInfo,tvSubCategoryName,tvRate,tvQuantity,tvAmount;
    public ImageView ivDelete;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        tvCategoryName = itemView.findViewById(R.id.textview_category_name);
        tvProviderInfo = itemView.findViewById(R.id.textview_provider_info);
        tvSubCategoryName = itemView.findViewById(R.id.textview_sub_category_name);
        tvRate = itemView.findViewById(R.id.textview_rate);
        tvQuantity = itemView.findViewById(R.id.textview_quantity);
        tvAmount = itemView.findViewById(R.id.textview_amount);

        ivDelete = itemView.findViewById(R.id.imageview_delete);

    }
}
