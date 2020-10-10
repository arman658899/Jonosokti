package com.brogrammers.jonosokti.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.helpers.AppPreferences;
import com.brogrammers.jonosokti.helpers.ApplicationHelper;
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

    @Override
    protected void onStart() {
        super.onStart();

        if (!AppPreferences.Login.isFirstTimeLogin(this)){
            Intent intent = new Intent(this, IntroSliderActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_in);
        }
        else if (ApplicationHelper.getDatabase().getAuth().getCurrentUser()==null){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_in);

        }else if (ApplicationHelper.getDatabase().getAuth().getCurrentUser()!=null){
            if (AppPreferences.UserInfo.getUserName(this).isEmpty()){
                Intent intent = new Intent(this, RegistrationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_in);
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

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
