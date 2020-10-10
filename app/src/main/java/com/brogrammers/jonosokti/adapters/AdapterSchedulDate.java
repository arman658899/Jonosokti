package com.brogrammers.jonosokti.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brogrammers.jonosokti.Constants;
import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.Date;
import com.brogrammers.jonosokti.viewholders.DateViewHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

import static com.brogrammers.jonosokti.Constants.TAG;

public class AdapterSchedulDate extends RecyclerView.Adapter<DateViewHolder> {
    private Context context;
    private List<Long> dates;

    public AdapterSchedulDate(Context context, List<Long> dates) {
        this.context = context;
        this.dates = dates;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sampleview_schedule_date,parent,false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dates.get(position));

        holder.tvMonthName.setText(Constants.MONTH_NAME_SHORT[calendar.get(Calendar.MONTH)]);
        holder.tvDay.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        holder.tvDayName.setText(Constants.DAY_NAME[calendar.get(Calendar.DAY_OF_WEEK)-1]);

        Log.d(TAG, "onBindViewHolder: "+calendar.get(Calendar.DAY_OF_WEEK));


    }
    @Override
    public int getItemCount() {
        return dates.size();
    }
}
