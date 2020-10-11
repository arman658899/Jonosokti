package com.brogrammers.jonosokti.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.OrderItemAdapter;
import com.brogrammers.jonosokti.bean.Order;
import com.brogrammers.jonosokti.bean.OrderItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusActivity extends AppCompatActivity {
    private TextView tvOrderId,tvTotal,tvSubTotal,tvProviderName,tvRating,tvViewProfile,tvDate,tvTime;
    private TextView tvPlacedTime,tvAcceptedTime,tvWorkingTime,tvCompletedTime,tvCancel,tvHelpLine;
    private RecyclerView recyclerView;
    private CircularImageView cvPic;

    private CheckBox cbPlaced,cbAccepted,cbWorked,cbCompleted;
    private OrderItemAdapter adapter;
    private List<OrderItem> orderItemList;

    private Order mOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        mOrder = (Order) getIntent().getSerializableExtra("order");

        orderItemList = new ArrayList<>();
        adapter = new OrderItemAdapter(this,orderItemList);

        tvOrderId = findViewById(R.id.textview_order_id);
        tvTotal = findViewById(R.id.textview_total);
        tvSubTotal = findViewById(R.id.textview_subtotal_amount);
        tvProviderName = findViewById(R.id.textview_provider_name);
        tvRating = findViewById(R.id.textview_rating);
        tvViewProfile = findViewById(R.id.textview_view_profile);
        tvCancel = findViewById(R.id.textview_cancel);
        tvHelpLine = findViewById(R.id.textview_helpline);
        tvDate = findViewById(R.id.textview_date);
        tvTime = findViewById(R.id.textview_time);

        tvPlacedTime = findViewById(R.id.textview_time_placed);
        tvAcceptedTime = findViewById(R.id.textview_time_accepted);
        tvWorkingTime = findViewById(R.id.textview_time_started);
        tvCompletedTime = findViewById(R.id.textview_time_complete);

        cbPlaced = findViewById(R.id.checkbox_placed);
        cbAccepted = findViewById(R.id.checkbox_accepted);
        cbWorked = findViewById(R.id.checkbox_started);
        cbCompleted = findViewById(R.id.checkbox_completed);

        cvPic = findViewById(R.id.circularImageView_provider);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //back button
        findViewById(R.id.imageView4).setOnClickListener(v -> onBackPressed());
        tvHelpLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderStatusActivity.this, "Helpline number will be added.", Toast.LENGTH_SHORT).show();
            }
        });
        tvViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderStatusActivity.this, "On developing.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        try{
            tvOrderId.setText(mOrder.getOrderId());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            tvProviderName.setText(mOrder.getProviderName());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            tvRating.setText(mOrder.getProviderCompany());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            tvOrderId.setText(mOrder.getOrderId());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            tvDate.setText(mOrder.getSelectedDate());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            tvTime.setText(mOrder.getSelectedTime());
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            orderItemList.clear();
            double total = 0;
            String[] splitedItems = mOrder.getOrderDetails().split("\\$");
            for (String item: splitedItems){
                String[] itemAttributes = item.split("###");
                if (itemAttributes.length == 3 ){
                    OrderItem orderItem = new OrderItem(itemAttributes[0],Integer.parseInt(itemAttributes[1]),Double.parseDouble(itemAttributes[2]));
                    orderItemList.add(orderItem);
                    total += Integer.parseInt(itemAttributes[1])*Double.parseDouble(itemAttributes[2]);
                }
            }
            adapter.notifyDataSetChanged();
            tvTotal.setText(String.valueOf(total));
            tvSubTotal.setText(String.valueOf(total));

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            Glide.with(this)
                    .load(mOrder.getProviderPhoto())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(cvPic);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            if (mOrder.isPlaced()){
                cbPlaced.setChecked(true);
            }
            if (mOrder.isAccepted()){
                cbAccepted.setChecked(true);
            }
            if (mOrder.isWorking()){
                cbWorked.setChecked(true);
                tvCancel.setVisibility(View.GONE);
            }
            if (mOrder.isCompleted()){
                cbCompleted.setChecked(true);
                tvCancel.setVisibility(View.GONE);
            }
            if (mOrder.isCancelledByMe()){
                tvCancel.setText("You cancelled order.");
                tvCancel.setEnabled(false);
            }
            if (mOrder.isCancelledByProvider()){
                tvCancel.setText("Provider cancelled order.");
                tvCancel.setEnabled(false);
            }
        }catch (Exception e){

        }


    }
}