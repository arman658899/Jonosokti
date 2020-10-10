package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.Time;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.brogrammers.jonosokti.Constants.TAG;

public class AdapterSchedulTime extends RecyclerView.Adapter<AdapterSchedulTime.ViewholderTime> {
    private Context context;
    private List<Time> times;
    private OnItemSelectListener2<Time> listener;
    public AdapterSchedulTime(Context context, List<Time> times,OnItemSelectListener2<Time> listener) {
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
        holder.tvTime.setText(times.get(position).getTime());

        if (!times.get(position).isClicked()) holder.container.setBackground(context.getResources().getDrawable(R.drawable.shape_background_schedule_white));
        else holder.container.setBackground(context.getResources().getDrawable(R.drawable.shape_background_schedule));

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.onItemSelected2(times.get(position),position);
                    Log.d(TAG, "onClick: "+times.get(position));
                }Log.d(TAG, "onClick: "+times.get(position));
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
            container.setFocusable(true);
        }
    }
}
