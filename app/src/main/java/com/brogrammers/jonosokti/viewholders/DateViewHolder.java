package com.brogrammers.jonosokti.viewholders;

import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class DateViewHolder extends RecyclerView.ViewHolder {
    public TextView tvMonthName,tvDay,tvDayName;
    public ConstraintLayout container;
    public DateViewHolder(@NonNull View itemView) {
        super(itemView);

        tvMonthName = itemView.findViewById(R.id.textView_month);
        tvDay = itemView.findViewById(R.id.textView_date);
        tvDayName = itemView.findViewById(R.id.textView_day);
        container = itemView.findViewById(R.id.contratintlayout_container);
    }
}
