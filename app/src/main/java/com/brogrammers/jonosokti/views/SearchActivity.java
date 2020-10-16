package com.brogrammers.jonosokti.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.SubCategoryAdapter;
import com.brogrammers.jonosokti.adapters.SubCategoryVerticalAdapter;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.helpers.ApplicationHelper;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.repositories.DefaultRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements OnItemSelectListener<SubCategory> {
    private EditText etKey;
    private TextView tvNothingFound;
    private RecyclerView recyclerView;
    private DefaultRepository defaultRepository;
    private List<SubCategory> subCategories;
    private SubCategoryAdapter adapter;
    private Dialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        defaultRepository = DefaultRepository.getInstance(this);
        loadingDialog = ApplicationHelper.getUtilsHelper().getLoadingDialog(this);
        loadingDialog.setCancelable(false);

        etKey = findViewById(R.id.editText);
        tvNothingFound = findViewById(R.id.textview_found_nothing);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapter);


        subCategories = new ArrayList<>();
        adapter = new SubCategoryAdapter(this,subCategories,this);

        //backbutton
        findViewById(R.id.imageButton2).setOnClickListener(v -> onBackPressed());
        //search icon
        findViewById(R.id.imagebutton_search).setOnClickListener(v -> {
            String key = ""+etKey.getText().toString();
            if (key.isEmpty()) return;

            doSearch(key);
        });
    }

    private void doSearch(String key) {
        loadingDialog.show();
        List<String> keys = Arrays.asList(key.toLowerCase().split("[,\\s; -:()^@#!]+"));

        defaultRepository.getSubCategoriesByKey(keys, new OnDataDownloadListener<SubCategory>() {
            @Override
            public void onStarted() {
                subCategories.clear();
            }

            @Override
            public void onDownloaded(SubCategory subCategory) {
                subCategories.add(subCategory);
            }

            @Override
            public void onDownloaded(List<SubCategory> list) {

            }

            @Override
            public void onFinish() {
                adapter.notifyDataSetChanged();
                if (subCategories.size()<=0) tvNothingFound.setVisibility(View.VISIBLE);
                else tvNothingFound.setVisibility(View.GONE);
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    public void onItemSelected(SubCategory subCategory) {
        Intent intent = new Intent(SearchActivity.this, SelectServiceAndProviderActivity.class);
        intent.putExtra("sub_cat",subCategory);
        startActivity(intent);
    }

    @Override
    public void onDelete(SubCategory subCategory) {

    }

    @Override
    public void onCancel(SubCategory subCategory) {

    }

    @Override
    public void onApprove(SubCategory subCategory) {

    }
}