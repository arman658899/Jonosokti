package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterSchedulTime extends RecyclerView.Adapter<AdapterSchedulTime.ViewholderTime> {
    private Context context;
    private List<String> times;
    private OnItemSelectListener<String> listener;
    public AdapterSchedulTime(Context context, List<String> times,OnItemSelectListener<String> listener) {
        this.context = context;
        this.times = times;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewholderTime onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_schedule_time,parent,false);
        return new ViewholderTime(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewholderTime holder, int position) {
        holder.tvTime.setText(times.get(position));
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) listener.onItemSelected(times.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public class ViewholderTime extends RecyclerView.ViewHolder{
        public TextView tvTime;
        public LinearLayout container;
        public ViewholderTime(@NonNull View itemView) {
            super(itemView);

            tvTime = itemView.findViewById(R.id.textView_time);
            container = itemView.findViewById(R.id.linearLayout_container);
        }
    }
}
