package com.brogrammers.jonosokti.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.brogrammers.jonosokti.R;

public class ConfirmOrderActivity extends AppCompatActivity {

    private TextView textView_Time,textView_Date,textView_Provider_Name,textView_Subtotal_Amount,textView_Client_Number, textView_Client_Location,textView_Client_Name;
    private Button button_Confirm_Order,button_Details_Change,button_date_time_change,button_provider_change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        textView_Time=findViewById( R.id.textview_time );
        textView_Date=findViewById( R.id.textview_date );
        textView_Provider_Name=findViewById( R.id.textview_product_name );
        textView_Subtotal_Amount=findViewById( R.id.textview_subtotal_amount );
        textView_Client_Name=findViewById( R.id.textview_client_name );
        textView_Client_Number=findViewById( R.id.textview_client_number );
        textView_Client_Location=findViewById( R.id.textview_client_location );

        button_Confirm_Order=findViewById( R.id.button_confirm_order );
        button_Details_Change=findViewById( R.id.button_details_change );
        button_provider_change=findViewById( R.id.button_provider_change );
        button_date_time_change=findViewById( R.id.button_date_time_change );



    }
}