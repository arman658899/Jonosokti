package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.ServiceAndProvider;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.viewholders.ServiceAndProviderViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class ServiceAndProviderAdapter extends RecyclerView.Adapter<ServiceAndProviderViewHolder> {
    private Context context;
    private List<ServiceAndProvider> serviceAndProviders;
    private OnItemSelectListener<ServiceAndProvider> listener;

    public ServiceAndProviderAdapter(Context context, List<ServiceAndProvider> serviceAndProviders, OnItemSelectListener<ServiceAndProvider> listener) {
        this.context = context;
        this.serviceAndProviders = serviceAndProviders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceAndProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServiceAndProviderViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_serviceandprovider,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAndProviderViewHolder holder, int position) {

        Glide.with(context)
                .load(serviceAndProviders.get(position).getProfilePic())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.circularImage);

        holder.tvProviderName.setText(serviceAndProviders.get(position).getProviderName());
        holder.tvServiceName.setText(serviceAndProviders.get(position).getSubCatName());
        holder.tvServicePrice.setText(serviceAndProviders.get(position).getServiceFee());
        holder.tvCompanyName.setText(serviceAndProviders.get(position).getCompanyName());

        holder.buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) listener.onItemSelected(serviceAndProviders.get(position));
            }
        });

        holder.tvViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) listener.onApprove(serviceAndProviders.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceAndProviders.size();
    }
}
