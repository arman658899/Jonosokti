package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public ConstraintLayout container;
    public SubCategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.textview_sub_category_name);
        container = itemView.findViewById(R.id.constraintlayout_container);
    }
}
