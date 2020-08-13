package com.brogrammers.jonosokti.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.CategoriesAdapter;
import com.brogrammers.jonosokti.bean.Category;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewFlipper viewFlipper;
    private RecyclerView recyclerViewCategories, recyclerViewPopularCategories;
    private List<Category> categories;
    private List<Category> products;
    private CategoriesAdapter categoriesAdapter,productsAdapter;

    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categories = new ArrayList<>();
        products = new ArrayList<>();
        categoriesAdapter = new CategoriesAdapter(requireActivity());
        productsAdapter = new CategoriesAdapter(requireActivity());

        for (int i = 0; i < 30; i++) {
            categories.add(new Category("Service " + (i + 1)));
            products.add(new Category("Product " + (i + 1)));
        }
        categoriesAdapter.submitList(categories);
        productsAdapter.submitList(products);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewFlipper = view.findViewById(R.id.viewFlipper);
        intiViewFlipper();
        recyclerViewCategories = view.findViewById(R.id.recyclerview_CategoryView_home);
        recyclerViewCategories.setHasFixedSize(true);
        recyclerViewCategories.setLayoutManager(new GridLayoutManager(requireActivity(), 3));
        recyclerViewCategories.setAdapter(categoriesAdapter);

        recyclerViewPopularCategories = view.findViewById(R.id.recyclerview_PopularView_home);
        recyclerViewPopularCategories.setHasFixedSize(true);
        recyclerViewPopularCategories.setLayoutManager(new GridLayoutManager(requireActivity(), 3));
        recyclerViewPopularCategories.setAdapter(productsAdapter);
    }

    private void intiViewFlipper() {
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(requireActivity())
                .load(R.drawable.service1)
                .into(imageView);
        //imageView1.setBackgroundResource(images[0]);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }

    @Override
    public void onResume() {
        super.onResume();

        viewFlipper.startFlipping();

    }
}