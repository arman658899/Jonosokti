package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class OrderItemViewHolder extends RecyclerView.ViewHolder {
    public TextView tvSubCategoryName,tvRate,tvQuantity,tvAmount;
    public OrderItemViewHolder(@NonNull View itemView) {
        super(itemView);
        tvSubCategoryName = itemView.findViewById(R.id.textview_sub_category_name);
        tvRate = itemView.findViewById(R.id.textview_rate);
        tvQuantity = itemView.findViewById(R.id.textview_quantity);
        tvAmount = itemView.findViewById(R.id.textview_amount);
    }
}
