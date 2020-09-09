package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.listeners.OnCategorySelectListener;
import com.brogrammers.jonosokti.viewholders.CategoryViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class CategoriesAdapter extends ListAdapter<Category,CategoryViewHolder> {
    private Context context;
    private OnCategorySelectListener listener;

    @Override
    public void submitList(@Nullable List<Category> list) {
        super.submitList(list);
        if (list.size()>0) notifyDataSetChanged();
    }

    public CategoriesAdapter(Context context, OnCategorySelectListener listener) {
        super(DIFF_UTIL);
        this.context = context;
        this.listener = listener;
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
        holder.tvServiceName.setText(getItem(position).getCategoryName());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onCategorySelected(getItem(position).getCategoryName(),position);
            }
        });

        Glide.with(context)
                .load(""+getItem(position).getImageLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }



}
