package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public TextView tvCategoryName,tvProviderName,tvCompanyName,tvStatus,tvCancel,tvDeleteOrder;
    public LinearLayout linearLayoutDeleteContainer;
    public RecyclerView recyclerView;
    public CardView cardView;
    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCategoryName = itemView.findViewById(R.id.textView_name);
        tvProviderName = itemView.findViewById(R.id.textView_service_name);
        tvCompanyName = itemView.findViewById(R.id.textView_company_name);
        tvStatus = itemView.findViewById(R.id.textView_time);
        tvCancel = itemView.findViewById(R.id.textview_cancel);

        recyclerView = itemView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

        linearLayoutDeleteContainer = itemView.findViewById(R.id.linearLayout_cancelled);
        tvDeleteOrder = itemView.findViewById(R.id.textview_delete_order);

        cardView = itemView.findViewById(R.id.cardview_container);

    }

}
