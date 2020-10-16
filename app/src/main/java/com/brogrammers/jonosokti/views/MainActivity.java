package com.brogrammers.jonosokti.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.brogrammers.jonosokti.AppInitializer;
import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.helpers.AppPreferences;
import com.brogrammers.jonosokti.helpers.ApplicationHelper;
import com.brogrammers.jonosokti.listeners.OnMainActivityCallback;
import com.brogrammers.jonosokti.views.fragments.CartFragment;
import com.brogrammers.jonosokti.views.fragments.HomeFragment;
import com.brogrammers.jonosokti.views.fragments.ProfileFragment;
import com.brogrammers.jonosokti.views.fragments.OrderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements OnMainActivityCallback, NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;

    //drawer layout
    private static DrawerLayout drawerLayout;
    private static NavigationView navigationView;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById( R.id.drawerlayout );
        navigationView = findViewById( R.id.admin_nav_view );
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        mToggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_home) {
                    replaceFragment(new HomeFragment(MainActivity.this));
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

        if (savedInstanceState==null) replaceFragment(new HomeFragment(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.privacy_policy){
            //privacy policy
            closeDrawer();
        }
        if (id == R.id.provider){
            //provider app link
            closeDrawer();
        }
        if (id == R.id.share_app){
            //share app
            closeDrawer();
        }

        if (id==R.id.rate_us){
            //rate us
            closeDrawer();

        }
        if (id == R.id.about_us){
            //about us
            closeDrawer();

        }

        if (id == R.id.developer_option){
            //about developer
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://brotechit.com/home")));
            closeDrawer();
        }

        if(id==R.id.logout){
            ApplicationHelper.getDatabase().getAuth().signOut();
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return false;
    }

    public static void openDrawer(){
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public static void closeDrawer(){
        drawerLayout.closeDrawer(Gravity.LEFT);
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

    @Override
    public void onOpenDrawer() {
        openDrawer();
    }

    @Override
    public void onCloseDrawer() {
        closeDrawer();
    }

    @Override
    public void onReplaceFragment(Fragment fragment) {

    }
}
