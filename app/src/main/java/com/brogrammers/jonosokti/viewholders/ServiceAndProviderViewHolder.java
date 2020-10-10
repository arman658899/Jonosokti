package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class ServiceAndProviderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvCompanyName,tvServiceName,tvServicePrice,tvProviderName;
    public CircularImageView circularImage;
    public Button buttonSelect;
    public ServiceAndProviderViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCompanyName = itemView.findViewById(R.id.textView_company_name);
        tvServiceName = itemView.findViewById(R.id.textView_service_name);
        tvServicePrice = itemView.findViewById(R.id.textView_price);
        tvProviderName = itemView.findViewById(R.id.textView_provider_name);
        circularImage = itemView.findViewById(R.id.circularImageView);
        buttonSelect = itemView.findViewById(R.id.button_add);

    }
}
