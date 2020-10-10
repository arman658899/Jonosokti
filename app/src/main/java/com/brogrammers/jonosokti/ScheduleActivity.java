package com.brogrammers.jonosokti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.brogrammers.jonosokti.adapters.AdapterSchedulDate;
import com.brogrammers.jonosokti.adapters.AdapterSchedulTime;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener2;
import com.brogrammers.jonosokti.views.FilupAddressActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements OnItemSelectListener<String>, OnItemSelectListener2<Long> {
    RecyclerView recyclerViewTime,recyclerViewDate;
    AdapterSchedulTime adapterSchedulTime;
    AdapterSchedulDate adapterSchedulDate;
    private List<Long> dates;
    private List<String> times = Arrays.asList(Constants.WORKING_TIMES);
    private long currentTime,aDay=86400000;

    private String selectedTime = "";
    private long selectedDate = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        currentTime = Calendar.getInstance().getTimeInMillis();
        dates = new ArrayList<>();
        for (int i=0; i<7; i++){
            dates.add(currentTime+(aDay*i));
        }

        adapterSchedulTime = new AdapterSchedulTime(this,times,this);
        adapterSchedulDate =new AdapterSchedulDate(this,dates);
        recyclerViewDate = findViewById(R.id.recyclerview_date);
        recyclerViewDate.setHasFixedSize(true);
        recyclerViewDate.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        recyclerViewDate.setAdapter(adapterSchedulDate);

        recyclerViewTime =findViewById(R.id.recyclerview_time);
        recyclerViewTime.setHasFixedSize(true);
        recyclerViewTime.setLayoutManager(new GridLayoutManager(this,2));
        recyclerViewTime.setAdapter(adapterSchedulTime);

        //continue button
        findViewById(R.id.button_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDate!=-1 && !selectedTime.isEmpty()){
                    Intent intent = new Intent(ScheduleActivity.this, FilupAddressActivity.class);
                    intent.putExtra("date",selectedDate);
                    intent.putExtra("time",selectedTime);
                    startActivity(intent);
                }else Toast.makeText(ScheduleActivity.this, "Please select date & time.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemSelected(String s) {
        selectedTime = s;
    }

    @Override
    public void onItemSelected2(Long aLong) {
        selectedDate = aLong;
    }
}