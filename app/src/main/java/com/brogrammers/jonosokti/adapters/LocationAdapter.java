package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.listeners.OnLocationStateListener;
import com.brogrammers.jonosokti.viewholders.LocationViewHolder;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {
    private Context context;
    private List<String> locations;
    private OnLocationStateListener listener;
    public LocationAdapter(Context context, List<String> locations, OnLocationStateListener listener) {
        this.context = context;
        this.locations = locations;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationViewHolder(LayoutInflater.from(context).inflate(R.layout.sampleview_select_location,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.tvLocationName.setText(locations.get(position));
        holder.buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)  listener.onSelected(locations.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
}
