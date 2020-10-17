package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.Order;
import com.brogrammers.jonosokti.bean.OrderItem;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.viewholders.OrderViewHolder;

import java.util.ArrayList;
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

        holder.tvCategoryName.setText(orders.get(position).getProviderName()+"\nCompany: "+orders.get(position).getProviderCompany());
        holder.tvProviderName.setText(orders.get(position).getCategoryName());
        //holder.tvCompanyName.setText(orders.get(position).getProviderCompany());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) listener.onItemSelected(orders.get(position));
            }
        });

        holder.tvStatus.setText(orders.get(position).getSelectedDate()+"--"+orders.get(position).getSelectedTime());

        if (orders.get(position).isAccepted() || orders.get(position).isCancelledByProvider()) holder.tvCancel.setVisibility(View.GONE);
        else holder.tvCancel.setVisibility(View.VISIBLE);
        holder.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cancel order
                if (listener!=null) listener.onCancel(orders.get(position));
            }
        });

        if (orders.get(position).isCancelledByProvider()){
            holder.linearLayoutDeleteContainer.setVisibility(View.VISIBLE);
        }else holder.linearLayoutDeleteContainer.setVisibility(View.GONE);
        holder.tvDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete order
                if (listener!=null) listener.onDelete(orders.get(position));
            }
        });


        try{
            List<OrderItem> orderItemList = new ArrayList<>();
            orderItemList.clear();
            OrderItemAdapter adapter = new OrderItemAdapter(context,orderItemList);
            holder.recyclerView.setAdapter(adapter);

            double total = 0;
            String[] splitedItems = orders.get(position).getOrderDetails().split("\\$");
            for (String item: splitedItems){
                String[] itemAttributes = item.split("###");
                if (itemAttributes.length == 3 ){
                    OrderItem orderItem = new OrderItem(itemAttributes[0],Integer.parseInt(itemAttributes[1]),Double.parseDouble(itemAttributes[2]));
                    orderItemList.add(orderItem);
                    total += Integer.parseInt(itemAttributes[1])*Double.parseDouble(itemAttributes[2]);
                }
            }
            adapter.notifyDataSetChanged();

            holder.tvCompanyName.setText("Total: "+total);

        }catch (Exception e){
            e.printStackTrace();
        }

       /* String status = "";
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
        }*/
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
