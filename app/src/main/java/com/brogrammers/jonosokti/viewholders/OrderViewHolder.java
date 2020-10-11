package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvCategoryName,tvProviderName,tvCompanyName,tvStatus;
    public CardView cardView;
    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCategoryName = itemView.findViewById(R.id.textView_name);
        tvProviderName = itemView.findViewById(R.id.textView_service_name);
        tvCompanyName = itemView.findViewById(R.id.textView_company_name);
        tvStatus = itemView.findViewById(R.id.textView_time);

        cardView = itemView.findViewById(R.id.cardview_container);

    }
}
