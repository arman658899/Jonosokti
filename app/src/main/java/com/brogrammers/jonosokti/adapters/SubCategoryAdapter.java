package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.viewholders.SubCategoryViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryViewHolder> {
    private Context context;
    private List<SubCategory> categories;
    private OnItemSelectListener<SubCategory> listener;

    public SubCategoryAdapter(Context context, List<SubCategory> categories, OnItemSelectListener<SubCategory> listener) {
        this.context = context;
        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubCategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_sub_category_item,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        //holder.tvSubCatName.setText(getItem(position));
        holder.tvSubCatName.setText(categories.get(position).getSubCatName());
        holder.tvPrice.setText("Service Fee: "+categories.get(position).getMinRange()+"-"+categories.get(position).getMaxRange());

        Glide.with(context)
                .load(categories.get(position).getPhotoLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivSubCat);

        holder.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) listener.onItemSelected(categories.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
