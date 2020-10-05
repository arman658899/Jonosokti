package com.brogrammers.jonosokti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.brogrammers.jonosokti.adapters.AdapterSchedulDate;
import com.brogrammers.jonosokti.adapters.AdapterSchedulTime;

public class Schedule extends AppCompatActivity {

    RecyclerView recyclerViewTime,recyclerViewDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        recyclerViewDate=findViewById(R.id.recyclerview_date);
        recyclerViewTime =findViewById(R.id.recyclerview_time);
        AdapterSchedulTime adapterSchedulTime=new AdapterSchedulTime();
        recyclerViewTime.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewTime.setAdapter(adapterSchedulTime);
        AdapterSchedulDate adapterSchedulDate=new AdapterSchedulDate();
        recyclerViewDate.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
        recyclerViewDate.setAdapter(adapterSchedulDate);
    }
}