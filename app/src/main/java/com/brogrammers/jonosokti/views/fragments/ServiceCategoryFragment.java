package com.brogrammers.jonosokti.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.SubCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class ServiceCategoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private SubCategoryAdapter adapter;
    private List<String> categories;

    private String categoryName;
    private int positon;
    public ServiceCategoryFragment(String categoryName,int positon) {
        // Required empty public constructor
        this.categoryName = categoryName;
        this.positon = positon;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categories = new ArrayList<>();
        adapter = new SubCategoryAdapter(requireActivity());

        for (int i=0; i<positon; i++){
            categories.add("Sub-Category "+(i+1));
        }
        adapter.submitList(categories);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapter);

    }
}
