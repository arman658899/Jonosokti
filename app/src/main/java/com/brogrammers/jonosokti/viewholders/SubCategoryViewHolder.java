package com.brogrammers.jonosokti.viewholders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.R;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView tvSubCatName,tvPrice;
    public ImageView ivSubCat;
    public ConstraintLayout container;
    public LinearLayout nextButton;
    public SubCategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        tvSubCatName = itemView.findViewById(R.id.textview_sub_category_name);
        tvPrice = itemView.findViewById(R.id.textview_price);
        ivSubCat = itemView.findViewById(R.id.imageview_sub_category_image);
        container = itemView.findViewById(R.id.constraintlayout_container);

        nextButton = itemView.findViewById(R.id.linearlayout_right_arrow_container);


    }
}
