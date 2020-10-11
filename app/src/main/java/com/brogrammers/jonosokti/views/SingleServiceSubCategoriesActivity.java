package com.brogrammers.jonosokti.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.SubCategoryVerticalAdapter;
import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.viewmodels.SingleServiceSubCategoryViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class SingleServiceSubCategoriesActivity extends AppCompatActivity implements OnItemSelectListener<SubCategory> {
    private RecyclerView recyclerView;
    private TextView tvFoundNothing;
    private List<SubCategory> subCategories;
    private SubCategoryVerticalAdapter adapter;
    private SingleServiceSubCategoryViewModel viewModel;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    private ImageView imageView;
    private ProgressBar progressBar,progressBarMain;

    private Category mCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_service_sub_categories);

        mCategory = (Category) getIntent().getSerializableExtra("category");

        subCategories = new ArrayList<>();
        adapter = new SubCategoryVerticalAdapter(this,this);
        initUI();

        viewModel = new ViewModelProvider(this).get(SingleServiceSubCategoryViewModel.class);

        if (mCategory!=null){
            Glide.with(this)
                    .load(mCategory.getImageLink())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);
            viewModel.getAllSubCategoriesByCategoryId(mCategory.getCategoryId());
            viewModel.incrementViewsOnCategory(mCategory.getCategoryId());
        }
    }

    private void initUI() {

        tvFoundNothing = findViewById(R.id.textview_found_nothing);
        imageView = findViewById(R.id.imageview_category_icon);
        progressBar = findViewById(R.id.progress_bar);
        progressBarMain = findViewById(R.id.progress_bar_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.submitList(subCategories);

        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        toolbar = findViewById(R.id.toolbar);

        if (mCategory!=null) collapsingToolbar.setTitle(mCategory.getCategoryName());
        toolbar.setNavigationIcon(R.drawable.icon_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private Observer<List<SubCategory>> subCategoryObserver = new Observer<List<SubCategory>>() {
        @Override
        public void onChanged(List<SubCategory> updatedData) {
            subCategories.clear();
            subCategories.addAll(updatedData);
            adapter.notifyDataSetChanged();
            progressBarMain.setVisibility(View.GONE);
            if (subCategories.size()<=0){
                tvFoundNothing.setVisibility(View.VISIBLE);
            }else tvFoundNothing.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getSubCategoriesLiveData().removeObserver(subCategoryObserver);
        viewModel.getSubCategoriesLiveData().observe(this,subCategoryObserver);


    }

    @Override
    public void onItemSelected(SubCategory subCategory) {
        Intent intent = new Intent(this,SelectServiceAndProviderActivity.class);
        intent.putExtra("sub_cat",subCategory);
        startActivity(intent);
    }
}
