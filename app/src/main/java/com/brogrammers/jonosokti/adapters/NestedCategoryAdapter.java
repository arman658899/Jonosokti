package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.NestedCategory;
import com.brogrammers.jonosokti.viewholders.NestedCategoryViewHolder;

import java.util.List;

public class NestedCategoryAdapter extends RecyclerView.Adapter<NestedCategoryViewHolder> {
    private Context context;
    private List<NestedCategory> nestedCategories;
    public NestedCategoryAdapter(Context context, List<NestedCategory> nestedCategories) {
        this.context = context;
        this.nestedCategories = nestedCategories;
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
        SubCategoryHorizontalAdapter adapter = new SubCategoryHorizontalAdapter(context,nestedCategories.get(position).getCategories());
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return nestedCategories.size();
    }
}
