package com.brogrammers.jonosokti.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.helpers.AppPreferences;


public class ProfileFragment extends Fragment {
    private TextView tvName,tvMobile;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.nameTv);
        tvMobile = view.findViewById(R.id.numberTv);

    }

    @Override
    public void onResume() {
        super.onResume();

        tvName.setText(AppPreferences.UserInfo.getUserName(requireActivity()));
        tvMobile.setText(AppPreferences.UserInfo.getUserMob(requireActivity()));
    }
}
