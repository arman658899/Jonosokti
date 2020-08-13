package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.viewholders.CategoryViewHolder;

public class CategoriesAdapter extends ListAdapter<Category,CategoryViewHolder> {
    private Context context;
    public CategoriesAdapter(Context context) {
        super(DIFF_UTIL);
        this.context = context;
    }
    private static final DiffUtil.ItemCallback<Category> DIFF_UTIL = new DiffUtil.ItemCallback<Category>() {
        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return false;
        }
    };

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.tvServiceName.setText(getItem(position).getName());
    }



}
