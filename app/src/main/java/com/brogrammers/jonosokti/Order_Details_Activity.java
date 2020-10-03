package com.brogrammers.jonosokti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Order_Details_Activity extends AppCompatActivity {

    private TextView service_Name,service_provider_name,order_faq,need_help,order_id,provider_profile,cancel_request,order_placed,order_accepted,work_started,work_completed,time_placed,time_accepted,time_stared,time_completed;
    private ImageView provider_ImageView,back_arrow_Iv,call_Iv;
    private CheckBox order_placed_CB,order_accepted_CB,work_stared_CB,work_completed_CB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_order__details_ );

        service_Name=findViewById( R.id.service_name );
        service_provider_name=findViewById( R.id.textview_provider_name );
        order_faq=findViewById( R.id.order_faq );
        need_help=findViewById( R.id.need_help );
        order_id=findViewById( R.id.textview_order_id );
        provider_profile=findViewById( R.id.provider_profile_id );
        cancel_request=findViewById( R.id.textview_cancel );
        order_placed=findViewById( R.id.textview_order_placed );
        order_accepted=findViewById( R.id.textview_order_accepted );
        work_started=findViewById( R.id.textview_work_started );
        work_completed=findViewById( R.id.textview_completed );
        time_placed=findViewById( R.id.textview_time_placed );
        time_accepted=findViewById( R.id.textview_time_accepted );
        time_stared=findViewById( R.id.textview_time_started );
        time_completed=findViewById( R.id.textview_time_complete );

        provider_ImageView=findViewById( R.id.ImageView_image );
        call_Iv=findViewById( R.id.call_Iv );
        back_arrow_Iv=findViewById( R.id.back_btn );

        order_placed_CB=findViewById( R.id.checkbox_placed);
        order_accepted_CB=findViewById( R.id.checkbox_accepted );
        work_stared_CB=findViewById( R.id.checkbox_started );
        work_completed_CB=findViewById( R.id.checkbox_completed );




    }
}