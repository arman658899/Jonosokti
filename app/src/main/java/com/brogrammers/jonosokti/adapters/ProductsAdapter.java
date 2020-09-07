package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.Product;
import com.brogrammers.jonosokti.viewholders.ProductsViewHolder;
import com.bumptech.glide.Glide;

public class ProductsAdapter extends ListAdapter<Product, ProductsViewHolder> {
    private Context context;
    public ProductsAdapter(Context context) {
        super(DIFF_UTIL);
        this.context = context;
    }
    private static final DiffUtil.ItemCallback<Product> DIFF_UTIL = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return false;
        }
    };

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        holder.tvProductName.setText(getItem(position).getProductName());
        Glide.with(context)
                .load(R.drawable.service1)
                .into(holder.imageView);
    }
}
