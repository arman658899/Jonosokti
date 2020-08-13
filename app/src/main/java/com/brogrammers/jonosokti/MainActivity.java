package com.brogrammers.jonosokti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.WindowManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_main);

        chipNavigationBar=findViewById( R.id.bottom_nav_menu );
        chipNavigationBar.setItemSelected( R.id.bottom_home,true );
        getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,new HomeFragment() ).commit();

        bottomMenu();
    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener( new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment=null;
                switch (i){

                    case R.id.bottom_home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.bottom_order:
                        fragment=new OrderFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,fragment ).commit();
            }
        } );
    }
}
