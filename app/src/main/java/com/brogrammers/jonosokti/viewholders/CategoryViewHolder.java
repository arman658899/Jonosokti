package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView tvServiceName;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tvServiceName = itemView.findViewById(R.id.textView_service_name);
    }
}
