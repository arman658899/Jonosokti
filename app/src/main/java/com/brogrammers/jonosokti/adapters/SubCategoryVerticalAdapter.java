package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.viewholders.SubCategoryViewHolder;
import com.brogrammers.jonosokti.views.AllServicesActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class SubCategoryVerticalAdapter extends ListAdapter<SubCategory, SubCategoryViewHolder> {
    private Context context;
    private OnItemSelectListener<SubCategory> listener;
    public SubCategoryVerticalAdapter(Context context,OnItemSelectListener<SubCategory> listener) {
        super(diffCallback);
        this.context = context;
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<SubCategory> diffCallback = new DiffUtil.ItemCallback<SubCategory>() {
        @Override
        public boolean areItemsTheSame(@NonNull SubCategory oldItem, @NonNull SubCategory newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull SubCategory oldItem, @NonNull SubCategory newItem) {
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
        //holder.tvSubCatName.setText(getItem(position));
        holder.tvSubCatName.setText(getItem(position).getSubCatName());
        holder.tvPrice.setText("Service Fee: "+getItem(position).getMinRange()+"-"+getItem(position).getMaxRange());

        Glide.with(context)
                .load(getItem(position).getPhotoLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivSubCat);

        holder.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) listener.onItemSelected(getItem(position));
            }
        });
    }
}
