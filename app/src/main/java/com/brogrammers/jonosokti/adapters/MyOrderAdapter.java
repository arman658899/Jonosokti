package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.Order;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.viewholders.OrderViewHolder;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private Context context;
    private List<Order> orders;
    private OnItemSelectListener<Order> listener;
    public MyOrderAdapter(Context context, List<Order> orders,OnItemSelectListener<Order> listener) {
        this.context = context;
        this.orders = orders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.tvCategoryName.setText(orders.get(position).getCategoryName());
        holder.tvProviderName.setText(orders.get(position).getProviderName());
        holder.tvCompanyName.setText(orders.get(position).getProviderCompany());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) listener.onItemSelected(orders.get(position));
            }
        });

        String status = "";
        if (orders.get(position).isPlaced()){
            status = "Order is placed to process.";
        }
        if (orders.get(position).isAccepted()){
            status = "Order is accepted.";
        }
        if (orders.get(position).isWorking()){
            status = "Provider is working on it.";
        }
        if (orders.get(position).isCompleted()){
            status = "Order is completed.";
        }
        if (orders.get(position).isCancelledByMe()){
            status = "Order is cancelled.";
        }
        if (orders.get(position).isCancelledByProvider()){
            status = "Order is cancelled by Provider.";
        }

        holder.tvStatus.setText(status);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
