package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class LocationViewHolder extends RecyclerView.ViewHolder {
    public Button buttonSelect;
    public TextView tvLocationName;
    public LocationViewHolder(@NonNull View itemView) {
        super(itemView);
        tvLocationName = itemView.findViewById(R.id.textView_location);
        buttonSelect = itemView.findViewById(R.id.button_update);
    }
}
