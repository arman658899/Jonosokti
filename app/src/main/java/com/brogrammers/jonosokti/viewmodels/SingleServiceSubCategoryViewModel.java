package com.brogrammers.jonosokti.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.brogrammers.jonosokti.repositories.DefaultRepository;

import java.util.ArrayList;
import java.util.List;

public class SingleServiceSubCategoryViewModel extends AndroidViewModel {
    private DefaultRepository defaultRepository;

    private List<SubCategory> subCategories;
    private MutableLiveData<List<SubCategory>> mlSubCategories;
    public SingleServiceSubCategoryViewModel(@NonNull Application application) {
        super(application);

        defaultRepository = DefaultRepository.getInstance(application);
        subCategories = new ArrayList<>();
        mlSubCategories = new MutableLiveData<>();
    }

    public void getAllSubCategoriesByCategoryId(String categoryId){
        defaultRepository.getAllSubCategoriesByCategoryId(categoryId, new OnDataDownloadListener<SubCategory>() {
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
                mlSubCategories.postValue(subCategories);
            }
        });
    }

    public LiveData<List<SubCategory>> getSubCategoriesLiveData(){
        return mlSubCategories;
    }
}
