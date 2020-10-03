package com.brogrammers.jonosokti.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.views.fragments.CartFragment;
import com.brogrammers.jonosokti.views.fragments.HomeFragment;
import com.brogrammers.jonosokti.views.fragments.ProfileFragment;
import com.brogrammers.jonosokti.views.fragments.OrderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                if (item.getItemId()==R.id.home)
                {
                    fragment=new HomeFragment();
                    item.setChecked(true);
                }
                if (item.getItemId()==R.id.bottom_order)
                {
                    fragment=new OrderFragment();
                    item.setChecked(true);
                }
                if (item.getItemId()==R.id.bottom_profile)
                {
                    fragment=new ProfileFragment();
                    item.setChecked(true);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                return true;
            }
        });

    }
}
