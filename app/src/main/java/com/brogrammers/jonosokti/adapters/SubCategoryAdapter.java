package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.viewholders.SubCategoryViewHolder;

public class SubCategoryAdapter extends ListAdapter<String, SubCategoryViewHolder> {
    private Context context;
    public SubCategoryAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<String> diffCallback = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return false;
        }
    };

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubCategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_sub_category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        holder.tvName.setText(getItem(position));
    }
}
