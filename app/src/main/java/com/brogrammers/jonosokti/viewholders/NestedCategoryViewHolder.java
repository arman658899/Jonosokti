package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class NestedCategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView tvCategoryName;
    public RecyclerView recyclerView;
    public NestedCategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        tvCategoryName = itemView.findViewById(R.id.popularTextView);
        recyclerView = itemView.findViewById(R.id.recyclerview_PopularView_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 1,RecyclerView.HORIZONTAL,false));
    }
}
