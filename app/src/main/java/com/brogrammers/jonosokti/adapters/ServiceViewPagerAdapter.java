package com.brogrammers.jonosokti.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.brogrammers.jonosokti.Constants;
import com.brogrammers.jonosokti.views.fragments.ServiceCategoryFragment;

public class ServiceViewPagerAdapter extends FragmentStateAdapter {


    public ServiceViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return new ServiceCategoryFragment("Category",10);
    }

    @Override
    public int getItemCount() {
        return Constants.CATEGORIES.length;
    }
}
