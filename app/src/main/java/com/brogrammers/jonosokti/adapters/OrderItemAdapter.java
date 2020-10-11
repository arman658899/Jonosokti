package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.OrderItem;
import com.brogrammers.jonosokti.viewholders.OrderItemViewHolder;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemViewHolder> {
    private Context context;
    private List<OrderItem> orderItemList;

    public OrderItemAdapter(Context context, List<OrderItem> orderItemList) {
        this.context = context;
        this.orderItemList = orderItemList;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderItemViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_cart_service_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        holder.tvSubCategoryName.setText(orderItemList.get(position).getSubcatName());
        holder.tvQuantity.setText(String.valueOf(orderItemList.get(position).getQuantity()));
        holder.tvRate.setText(String.valueOf(orderItemList.get(position).getFee()));
        holder.tvAmount.setText(String.valueOf(orderItemList.get(position).getFee() * orderItemList.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }
}
