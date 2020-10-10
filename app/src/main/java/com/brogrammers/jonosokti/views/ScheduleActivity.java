package com.brogrammers.jonosokti.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.brogrammers.jonosokti.Constants;
import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.AdapterSchedulDate;
import com.brogrammers.jonosokti.adapters.AdapterSchedulTime;
import com.brogrammers.jonosokti.bean.Date;
import com.brogrammers.jonosokti.bean.Time;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    RecyclerView recyclerViewTime,recyclerViewDate;
    AdapterSchedulTime adapterSchedulTime;
    AdapterSchedulDate adapterSchedulDate;
    private List<Date> dates;
    private List<Time> times;
    private long currentTime,aDay=86400000;

    private String selectedTime = "";
    private long selectedDate = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        currentTime = Calendar.getInstance().getTimeInMillis();
        dates = new ArrayList<>();
        times = new ArrayList<>();
        for (int i=0; i<7; i++){
            dates.add(new Date(false,currentTime+(aDay*i)));
        }

        for (int i=0; i<Constants.WORKING_TIMES.length; i++){
            times.add(new Time(false,Constants.WORKING_TIMES[i]));
        }

        adapterSchedulTime = new AdapterSchedulTime(this, times, new OnItemSelectListener2<Time>() {
            @Override
            public void onItemSelected2(Time time, int index) {
                selectedTime = time.getTime();
                Log.d(Constants.TAG, "onItemSelected2: "+selectedDate+"-"+selectedTime);
                time.setClicked(true);
                for (int i=0; i<times.size(); i++){
                    if (i==index) times.get(i).setClicked(true);
                    else times.get(i).setClicked(false);
                }
                adapterSchedulTime.notifyDataSetChanged();
            }
        });
        adapterSchedulDate =new AdapterSchedulDate(this, dates, new OnItemSelectListener2<Date>() {
            @Override
            public void onItemSelected2(Date aLong, int index) {
                selectedDate = aLong.getTimeInMilliseconds();
                for (int i=0; i<dates.size(); i++){
                    if (i==index) dates.get(i).setClicked(true);
                    else dates.get(i).setClicked(false);
                }
                adapterSchedulDate.notifyDataSetChanged();
            }
        });
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
}