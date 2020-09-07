package com.brogrammers.jonosokti.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.brogrammers.jonosokti.views.fragments.OngoingOrderFragment;
import com.brogrammers.jonosokti.views.fragments.OrderHistoryFragment;

public class TabAdapter extends FragmentStatePagerAdapter {
    String[] tabText = {"Ongoing","History"};
    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                return new OngoingOrderFragment();
            }
            case 1:{
                return new OrderHistoryFragment();
            }
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabText.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabText[position];
    }
}
