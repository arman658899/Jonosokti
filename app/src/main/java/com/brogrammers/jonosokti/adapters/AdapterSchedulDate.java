package com.brogrammers.jonosokti.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brogrammers.jonosokti.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterSchedulDate extends RecyclerView.Adapter<AdapterSchedulDate.ViewholderDate> {
    @NonNull
    @Override
    public ViewholderDate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sampleview_schedule_date,parent,false);
        return new ViewholderDate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewholderDate holder, int position) {

    }
    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewholderDate extends RecyclerView.ViewHolder{

        public ViewholderDate(@NonNull View itemView) {
            super(itemView);
        }
    }
}
