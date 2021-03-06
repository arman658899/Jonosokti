package com.brogrammers.jonosokti.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.CategoriesAdapter;
import com.brogrammers.jonosokti.adapters.NestedCategoryAdapter;
import com.brogrammers.jonosokti.bean.Banner;
import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.bean.NestedCategory;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.helpers.AppPreferences;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener2;
import com.brogrammers.jonosokti.listeners.OnMainActivityCallback;
import com.brogrammers.jonosokti.viewmodels.HomeFragmentViewModel;
import com.brogrammers.jonosokti.views.SearchActivity;
import com.brogrammers.jonosokti.views.SelectLocationActivitiy;
import com.brogrammers.jonosokti.views.SelectServiceAndProviderActivity;
import com.brogrammers.jonosokti.views.SingleServiceSubCategoriesActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnItemSelectListener<Category>, OnItemSelectListener2<SubCategory> {
    private TextView tvLocation;
    private ImageButton imageButtonMenu;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerViewCategories, recyclerViewPopularCategories;
    private List<Category> categories;
    private List<NestedCategory> nestedCategories;
    private CategoriesAdapter categoriesAdapter;
    private NestedCategoryAdapter popularAdapter;

    //viewmodel
    private HomeFragmentViewModel viewModel;

    private OnMainActivityCallback mainActivityCallback;
    public HomeFragment(OnMainActivityCallback mainActivityCallback) {
        // Required empty public constructor
        this.mainActivityCallback = mainActivityCallback;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categories = new ArrayList<>();
        nestedCategories = new ArrayList<>();
        categoriesAdapter = new CategoriesAdapter(requireActivity(), this);
        popularAdapter = new NestedCategoryAdapter(requireActivity(),nestedCategories,this);
        categoriesAdapter.submitList(categories);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeFragmentViewModel.class);


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

        imageButtonMenu = view.findViewById(R.id.imageButton);
        imageButtonMenu.setOnClickListener(v -> {
            if (mainActivityCallback!=null) mainActivityCallback.onOpenDrawer();
        });
        tvLocation = view.findViewById(R.id.textview_location);
        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), SelectLocationActivitiy.class);
                startActivity(intent);
            }
        });

        //searchview
        view.findViewById(R.id.searchView).setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), SearchActivity.class);
            startActivity(intent);
        });

        viewFlipper = view.findViewById(R.id.viewFlipper);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
        //intiViewFlipper();
        recyclerViewCategories = view.findViewById(R.id.recyclerview_CategoryView_home);
        recyclerViewCategories.setHasFixedSize(true);
        recyclerViewCategories.setLayoutManager(new GridLayoutManager(requireActivity(), 3));
        recyclerViewCategories.setAdapter(categoriesAdapter);

        recyclerViewPopularCategories = view.findViewById(R.id.recyclerview_PopularView_home);
        recyclerViewPopularCategories.setHasFixedSize(true);
        recyclerViewPopularCategories.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerViewPopularCategories.setAdapter(popularAdapter);


        viewModel.getCategoriesLiveData().removeObserver(categoryObserver);
        viewModel.getCategoriesLiveData().observe(requireActivity(), categoryObserver);
        viewModel.getPopularNestedCategories().removeObserver(nestedCategoyObserver);
        viewModel.getPopularNestedCategories().observe(requireActivity(),nestedCategoyObserver);


    }

    private Observer<List<Category>> categoryObserver = new Observer<List<Category>>() {
        @Override
        public void onChanged(List<Category> categoriesLive) {
            categories.clear();
            categories.addAll(categoriesLive);
            categoriesAdapter.submitList(categories);
        }
    };

    private Observer<List<NestedCategory>> nestedCategoyObserver = new Observer<List<NestedCategory>>() {
        @Override
        public void onChanged(List<NestedCategory> updatedList) {
            nestedCategories.clear();
            nestedCategories.addAll(updatedList);
            popularAdapter.notifyDataSetChanged();
        }
    };

    private Observer<List<Banner>> bannerObserver = new Observer<List<Banner>>() {
        @Override
        public void onChanged(List<Banner> banners) {
            if (banners.size()>0) updateViewFlipper(banners);
        }
    };

    private void updateViewFlipper(List<Banner> banners) {
        viewFlipper.setVisibility(View.VISIBLE);
        for (Banner banner: banners){
            ImageView imageView = new ImageView(getActivity());
            Glide.with(requireActivity())
                    .load(banner.getLink())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            //imageView1.setBackgroundResource(images[0]);
            viewFlipper.addView(imageView);
        }
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

        viewModel.getLiveDataBanners().removeObserver(bannerObserver);
        viewModel.getLiveDataBanners().observe(requireActivity(),bannerObserver);
        viewFlipper.startFlipping();

        if (AppPreferences.getUserLocationName(requireActivity()).isEmpty()){
            tvLocation.setText("Please select your location.");
        }else tvLocation.setText(AppPreferences.getUserLocationName(requireActivity()));

    }

    @Override
    public void onItemSelected(Category category) {
        Intent intent = new Intent(requireActivity(), SingleServiceSubCategoriesActivity.class);
        intent.putExtra("category",category);
        startActivity(intent);
    }

    @Override
    public void onDelete(Category category) {

    }

    @Override
    public void onCancel(Category category) {

    }

    @Override
    public void onApprove(Category category) {

    }

    @Override
    public void onItemSelected2(SubCategory subCategory, int index) {
        Intent intent = new Intent(requireActivity(), SelectServiceAndProviderActivity.class);
        intent.putExtra("sub_cat",subCategory);
        startActivity(intent);
    }

    @Override
    public void onItemSelected2(@NotNull Category category) {
        Intent intent = new Intent(requireActivity(), SingleServiceSubCategoriesActivity.class);
        intent.putExtra("category",category);
        startActivity(intent);
    }
}