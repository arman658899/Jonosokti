package com.brogrammers.jonosokti.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.views.fragments.CartFragment;
import com.brogrammers.jonosokti.views.fragments.HomeFragment;
import com.brogrammers.jonosokti.views.fragments.ProfileFragment;
import com.brogrammers.jonosokti.views.fragments.OrderFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipNavigationBar = findViewById( R.id.bottom_nav_menu );
        chipNavigationBar.setItemSelected( R.id.bottom_home,true );

        if(savedInstanceState==null) replaceFragment(new HomeFragment());

        bottomMenu();
    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener( new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                switch (id){
                    case R.id.bottom_order:
                        replaceFragment(new OrderFragment());
                        break;
                    case R.id.bottom_cart:{
                        replaceFragment(new CartFragment());
                        break;
                    }
                    case R.id.bottom_profile:{
                        replaceFragment(new ProfileFragment());
                        break;
                    }
                    default: replaceFragment(new HomeFragment());
                }
            }
        } );
    }

    private void replaceFragment(Fragment fragment){
        String fragmentName = fragment.getClass().getName();

        boolean popped = getSupportFragmentManager().popBackStackImmediate(fragmentName,0);
        if (!popped && getSupportFragmentManager().findFragmentByTag(fragmentName)==null){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fragment_container,fragment,fragmentName);
            ft.addToBackStack(fragmentName);
            ft.commit();
        }
    }
}
