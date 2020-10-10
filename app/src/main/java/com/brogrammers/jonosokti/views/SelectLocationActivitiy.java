package com.brogrammers.jonosokti.views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.brogrammers.jonosokti.Constants;
import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.LocationAdapter;
import com.brogrammers.jonosokti.helpers.AppPreferences;
import com.brogrammers.jonosokti.listeners.OnLocationStateListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectLocationActivitiy extends AppCompatActivity implements OnLocationStateListener {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private LocationAdapter adapter;
    private List<String> allLocations = Arrays.asList(Constants.PROVIDER_LOCATIONS);
    private List<String> selectedLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location_activitiy);

        selectedLocations = new ArrayList<>();
        adapter = new LocationAdapter(this,allLocations,this);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Select Your Area/Location");
        toolbar.setNavigationIcon(R.drawable.icon_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onSelected(String location) {
        AppPreferences.setUserLocationName(this,location);
        finish();
    }

    @Override
    public void onDeselected(String location) {
        //selectedLocations.remove(location);
    }
}