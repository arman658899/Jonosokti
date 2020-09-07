package com.brogrammers.jonosokti.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.ServiceViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import static com.brogrammers.jonosokti.Constants.CATEGORIES;


public class AllServicesActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_services);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager2);

        toolbar.setTitle("All Categories");
        toolbar.setNavigationIcon(R.drawable.ef_ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        viewPager.setAdapter(new ServiceViewPagerAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0: {
                    tab.setText(CATEGORIES[0]);
                    break;
                }
                default: tab.setText(CATEGORIES[position]);
            }
        });
        tabLayoutMediator.attach();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int tabSelectedIconColor = ContextCompat.getColor(AllServicesActivity.this, R.color.colorAccent);
                int tabUnSelectedIconColor = ContextCompat.getColor(AllServicesActivity.this, R.color.colorWhite);

                /*for (int i = 0; i < tabLayout.getTabCount(); i++) {

                    if (i == position) {
                        tabLayout.getTabAt(position).getIcon().setColorFilter(tabSelectedIconColor, PorterDuff.Mode.SRC_IN);
                        continue;
                    }
                    tabLayout.getTabAt(i).getIcon().setColorFilter(tabUnSelectedIconColor, PorterDuff.Mode.SRC_IN);
                }*/

            }

        });

    }
}
