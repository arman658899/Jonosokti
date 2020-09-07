package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.NestedCategory;
import com.brogrammers.jonosokti.viewholders.NestedCategoryViewHolder;

import java.util.List;

public class NestedCategoryAdapter extends ListAdapter<NestedCategory, NestedCategoryViewHolder> {
    private Context context;
    public NestedCategoryAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<NestedCategory> diffCallback = new DiffUtil.ItemCallback<NestedCategory>() {
        @Override
        public boolean areItemsTheSame(@NonNull NestedCategory oldItem, @NonNull NestedCategory newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull NestedCategory oldItem, @NonNull NestedCategory newItem) {
            return false;
        }
    };

    @NonNull
    @Override
    public NestedCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NestedCategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_nested_category_recyclerview_and_tittle,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NestedCategoryViewHolder holder, int position) {
        holder.tvCategoryName.setText(getItem(position).getPopularCategory());
        ProductsAdapter adapter = new ProductsAdapter(context);
        adapter.submitList(getItem(position).getCategories());
        holder.recyclerView.setAdapter(adapter);

    }
}
