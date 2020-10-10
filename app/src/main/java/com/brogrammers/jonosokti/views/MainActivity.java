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

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_home) {
                    replaceFragment(new HomeFragment());
                    item.setChecked(true);
                }
                if (item.getItemId()==R.id.bottom_order) {
                    replaceFragment(new OrderFragment());
                    item.setChecked(true);
                }
                if (item.getItemId()==R.id.bottom_profile) {
                    replaceFragment(new ProfileFragment());
                    item.setChecked(true);
                }
                if (item.getItemId() == R.id.bottom_cart){
                    replaceFragment(new CartFragment());
                    item.setChecked(true);
                }
                return true;
            }
        });

        if (savedInstanceState==null) replaceFragment(new HomeFragment());

    }

    private void replaceFragment(Fragment fragment){
        String fragmentName = fragment.getClass().getName();

        boolean popped = fragmentManager.popBackStackImmediate(fragmentName,0);
        if (!popped && fragmentManager.findFragmentByTag(fragmentName) == null){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container,fragment,fragmentName);
            ft.addToBackStack(fragmentName);
            ft.commit();
        }
    }
}
