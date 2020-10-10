package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.model.Cart;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.viewholders.CartViewHolder;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private Context context;
    private List<Cart> cartList;
    private OnItemSelectListener<Cart> listener;
    public CartAdapter(Context context, List<Cart> cartList,OnItemSelectListener<Cart> listener) {
        this.context = context;
        this.cartList = cartList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.tvAmount.setText(String.valueOf(cartList.get(position).getServiceFee()*cartList.get(position).getQuantity()));
        holder.tvQuantity.setText(String.valueOf(cartList.get(position).getQuantity()));
        holder.tvRate.setText(String.valueOf(cartList.get(position).getServiceFee()));
        holder.tvSubCategoryName.setText(cartList.get(position).getSubCatName());
        holder.tvCategoryName.setText(cartList.get(position).getCategoryName());
        holder.tvProviderInfo.setText("Provider:- "+cartList.get(position).getProviderName());

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) listener.onItemSelected(cartList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
