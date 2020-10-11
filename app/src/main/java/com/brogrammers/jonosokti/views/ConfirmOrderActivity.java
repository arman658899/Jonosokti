package com.brogrammers.jonosokti.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.OrderItemAdapter;
import com.brogrammers.jonosokti.bean.Order;
import com.brogrammers.jonosokti.bean.OrderItem;
import com.brogrammers.jonosokti.bean.model.Cart;
import com.brogrammers.jonosokti.helpers.AppPreferences;
import com.brogrammers.jonosokti.helpers.ApplicationHelper;
import com.brogrammers.jonosokti.listeners.OnUploadListener;
import com.brogrammers.jonosokti.repositories.DefaultRepository;
import com.brogrammers.jonosokti.viewmodels.ConfirmOderViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTime,tvDate,tvProviderName,tvSubTotal,tvTotal,tvCustomerDetails;
    private EditText etInstructions;
    private RecyclerView recyclerView;
    private List<Cart> cartList;
    private List<OrderItem> orderItemList;
    private ConfirmOderViewModel viewModel;
    private OrderItemAdapter adapter;
    private DefaultRepository defaultRepository;

    private Dialog dialog;
    private double total = 0;
    private String providerName = "",providerUid = "",selectedDate = "", selectedTime = "",address="",categoryName="",
            providerComapany="",providerPic="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        dialog = ApplicationHelper.getUtilsHelper().getLoadingDialog(this);
        dialog.setCancelable(false);

        cartList = new ArrayList<>();
        orderItemList = new ArrayList<>();
        adapter = new OrderItemAdapter(this,orderItemList);
        viewModel = new ViewModelProvider(this).get(ConfirmOderViewModel.class);
        defaultRepository = DefaultRepository.getInstance(this);

        tvTime = findViewById(R.id.textview_time);
        tvDate = findViewById(R.id.textview_date);
        tvProviderName = findViewById(R.id.textview_provider_name);
        tvSubTotal = findViewById(R.id.textview_subtotal_amount);
        tvTotal = findViewById(R.id.textview_total);
        tvCustomerDetails = findViewById(R.id.textview_client_name);

        etInstructions = findViewById(R.id.edittext_instructions);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //change time button
        findViewById(R.id.button_date_time_change).setOnClickListener(this);
        //change order detils
        findViewById(R.id.button_details_change).setOnClickListener(this);
        //confirm button
        findViewById(R.id.button_confirm_order).setOnClickListener(this);
        //backbutton
        findViewById(R.id.imageView4).setOnClickListener(v -> onBackPressed());

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.button_date_time_change){
            //start schedule activity
            Intent intent = new Intent(ConfirmOrderActivity.this,ScheduleActivity.class);
            startActivity(intent);
        }

        if (id == R.id.button_details_change){
            //start filupaddressactivity
            Intent intent = new Intent(ConfirmOrderActivity.this,FilupAddressActivity.class);
            startActivity(intent);
        }

        if (id == R.id.button_confirm_order){
            //confirm order -> upload to server

            if (cartList.size()<=0){
                Toast.makeText(this, "No service is found. Please select service and try again.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (ApplicationHelper.getDatabase().getAuth().getCurrentUser()==null){
                Toast.makeText(this, "You are not logged in. Please login and try again.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!ApplicationHelper.getUtilsHelper().isConnected()){
                Toast.makeText(this, "No internet connection.", Toast.LENGTH_LONG).show();
                return;
            }

            String documentId = ""+defaultRepository.getOrderDocumentId();
            if (documentId.isEmpty()){
                Toast.makeText(this, "No internet connection.", Toast.LENGTH_LONG).show();
                return;
            }

            dialog.show();

            String orderId = createOrderId(documentId);
            String instructions = ""+etInstructions.getText().toString();

            long currentTime = Calendar.getInstance().getTimeInMillis();
            String placedTime = getFormatedTime(currentTime);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<cartList.size(); i++){
                stringBuilder.append(cartList.get(i).getSubCatName())
                        .append("###")
                        .append(cartList.get(i).getQuantity())
                        .append("###")
                        .append(cartList.get(i).getServiceFee());

                if (i == cartList.size()-1) break;
                stringBuilder.append("$");

            }

            Order order = new Order(ApplicationHelper.getDatabase().getAuth().getCurrentUser().getUid(),AppPreferences.UserInfo.getUserName(ConfirmOrderActivity.this),address,instructions,selectedDate,selectedTime,categoryName,stringBuilder.toString(),providerName,providerComapany,providerPic,providerUid,orderId,documentId,placedTime,"","","",true,false,false,false,false,false, Calendar.getInstance().getTimeInMillis());
            defaultRepository.createNewOrder(order, new OnUploadListener() {
                @Override
                public void onUploaded() {
                    dialog.dismiss();
                    viewModel.deleteAllCart();
                    Toast.makeText(ConfirmOrderActivity.this, "Order is placed. You will be notified soon.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailed() {
                    dialog.dismiss();
                    Toast.makeText(ConfirmOrderActivity.this, "Order can't be placed now. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private String getFormatedTime(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        return DateFormat.format("dd-MM-yyyy hh:mm aa",calendar).toString();
    }

    private String createOrderId(String documentId) {
        char[] chars = documentId.toCharArray();
        int count = 0;
        for (int i=0; i<chars.length; i++){
            count+=chars[i]*(i+1);
        }
        return "Order-"+count;
    }

    private Observer<List<Cart>> cartObserver = new Observer<List<Cart>>() {
        @Override
        public void onChanged(List<Cart> carts) {
            cartList.clear();
            cartList.addAll(carts);

            total = 0;
            orderItemList.clear();
            for (Cart cart: cartList){
                orderItemList.add(new OrderItem(cart.getSubCatName(),cart.getQuantity(),cart.getServiceFee()));
                total += cart.getServiceFee()*cart.getQuantity();
            }
            adapter.notifyDataSetChanged();
            tvSubTotal.setText(String.valueOf(total));
            tvTotal.setText(String.valueOf(total));

            if (cartList.size()>0){
                providerName = cartList.get(0).getProviderName();
                providerUid = cartList.get(0).getProviderUid();
                providerComapany = cartList.get(0).getProviderCompany();
                providerPic = cartList.get(0).getProviderPic();
                categoryName = cartList.get(0).getCategoryName();
            }
            tvProviderName.setText(providerName);


        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllCarts().removeObserver(cartObserver);
        viewModel.getAllCarts().observe(this,cartObserver);

        //initializing ui
        address = AppPreferences.UserInfo.getUserName(this) +
                "\n" +
                "Mobile No: " + AppPreferences.UserInfo.getUserMob(this) +
                "\n" +
                "Address: " +
                AppPreferences.UserInfo.getUserAddressType(this) +
                " " +
                AppPreferences.UserInfo.getUserFlat(this) +
                "," +
                AppPreferences.UserInfo.getUserAddress(this);

        tvCustomerDetails.setText(address);

        selectedDate = AppPreferences.getLastOrderDate(this);
        selectedTime = AppPreferences.getLastOrderTime(this);

        tvDate.setText(selectedDate);
        tvTime.setText(selectedTime);

    }
}