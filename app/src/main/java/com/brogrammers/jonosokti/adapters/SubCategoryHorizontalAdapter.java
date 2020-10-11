package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener2;
import com.brogrammers.jonosokti.viewholders.ProductsViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class SubCategoryHorizontalAdapter extends RecyclerView.Adapter<ProductsViewHolder> {
    private Context context;
    private List<SubCategory> subCategories;
    private OnItemSelectListener2<SubCategory> listener2;
    public SubCategoryHorizontalAdapter(Context context, List<SubCategory> subCategories, OnItemSelectListener2<SubCategory> listener2) {
        this.context = context;
        this.subCategories = subCategories;
        this.listener2 = listener2;
    }

    /*@Override
    public void submitList(@Nullable List<SubCategory> list) {
        super.submitList(list);
        if (list.size()>0) notifyDataSetChanged();
    }*/
/*
    private static final DiffUtil.ItemCallback<SubCategory> DIFF_UTIL = new DiffUtil.ItemCallback<SubCategory>() {
        @Override
        public boolean areItemsTheSame(@NonNull SubCategory oldItem, @NonNull SubCategory newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SubCategory oldItem, @NonNull SubCategory newItem) {
            return false;
        }
    };*/

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        holder.tvProductName.setText(subCategories.get(position).getSubCatName());
        Glide.with(context)
                .load(subCategories.get(position).getPhotoLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener2!=null) listener2.onItemSelected2(subCategories.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }
}
