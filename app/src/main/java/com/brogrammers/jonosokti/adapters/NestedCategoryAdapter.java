package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.bean.NestedCategory;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener2;
import com.brogrammers.jonosokti.viewholders.NestedCategoryViewHolder;

import java.util.List;

public class NestedCategoryAdapter extends RecyclerView.Adapter<NestedCategoryViewHolder> {
    private Context context;
    private List<NestedCategory> nestedCategories;
    private OnItemSelectListener2<SubCategory> listener2;
    public NestedCategoryAdapter(Context context, List<NestedCategory> nestedCategories,OnItemSelectListener2<SubCategory> listener2) {
        this.context = context;
        this.nestedCategories = nestedCategories;
        this.listener2 = listener2;
    }

   /* private static final DiffUtil.ItemCallback<NestedCategory> diffCallback = new DiffUtil.ItemCallback<NestedCategory>() {
        @Override
        public boolean areItemsTheSame(@NonNull NestedCategory oldItem, @NonNull NestedCategory newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull NestedCategory oldItem, @NonNull NestedCategory newItem) {
            return false;
        }
    };*/

    @NonNull
    @Override
    public NestedCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NestedCategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_nested_category_recyclerview_and_tittle,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NestedCategoryViewHolder holder, int position) {
        holder.tvCategoryName.setText(nestedCategories.get(position).getPopularCategory());
        SubCategoryHorizontalAdapter adapter = new SubCategoryHorizontalAdapter(context,nestedCategories.get(position).getCategories(),listener2);
        holder.recyclerView.setAdapter(adapter);

        holder.tvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category = new Category();
                category.setCategoryId(nestedCategories.get(position).getCategories().get(0).getCategoryId());
                category.setCategoryName(nestedCategories.get(position).getCategories().get(0).getCategoryName());
                category.setImageLink(nestedCategories.get(position).getCategories().get(0).getPhotoLink());
                if (listener2!=null) listener2.onItemSelected2(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nestedCategories.size();
    }
}
