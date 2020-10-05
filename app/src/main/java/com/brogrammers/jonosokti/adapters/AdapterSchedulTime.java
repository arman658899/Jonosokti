package com.brogrammers.jonosokti.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brogrammers.jonosokti.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterSchedulTime extends RecyclerView.Adapter<AdapterSchedulTime.ViewholderTime> {
    @NonNull
    @Override
    public ViewholderTime onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_schedule_time,parent,false);
        return new ViewholderTime(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewholderTime holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewholderTime extends RecyclerView.ViewHolder{

        public ViewholderTime(@NonNull View itemView) {
            super(itemView);
        }
    }
}
