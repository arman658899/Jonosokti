package com.brogrammers.jonosokti.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.bean.NestedCategory;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.brogrammers.jonosokti.repositories.DefaultRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragmentViewModel extends AndroidViewModel {
    private DefaultRepository repository;

    private List<Category> categories;
    private MutableLiveData<List<Category>> mlCategories;

    private List<NestedCategory> nestedCategoryList;
    private MutableLiveData<List<NestedCategory>> mlNestedCategoryList;
    private Map<String,List<SubCategory>> hashedPopularCategories;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);

        repository = DefaultRepository.getInstance(application);

        categories = new ArrayList<>();
        mlCategories = new MutableLiveData<>();
        hashedPopularCategories = new HashMap<>();
        nestedCategoryList = new ArrayList<>();
        mlNestedCategoryList = new MutableLiveData<>();
        //get values from server
        getAllCategoriesFromDatabase();
        getPopularCategories();
    }

    public void getAllCategoriesFromDatabase(){
        repository.getCategories(new OnDataDownloadListener<Category>() {
            @Override
            public void onStarted() {
                categories.clear();
            }

            @Override
            public void onDownloaded(Category category) {
                categories.add(category);
            }

            @Override
            public void onDownloaded(List<Category> list) {

            }

            @Override
            public void onFinish() {
                mlCategories.postValue(categories);
            }
        });
    }

    public void getPopularCategories(){
        repository.getFivePopularCategories(new OnDataDownloadListener<Category>() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onDownloaded(Category category) {
                repository.getSubCategoriesByCategoryId(category.getCategoryId(), new OnDataDownloadListener<SubCategory>() {
                    @Override
                    public void onStarted() {

                    }

                    @Override
                    public void onDownloaded(SubCategory subCategory) {

                    }

                    @Override
                    public void onDownloaded(List<SubCategory> list) {
                        if (list.size()>0){
                            hashedPopularCategories.put(list.get(0).getCategoryName(),list);
                        }
                    }

                    @Override
                    public void onFinish() {
                        nestedCategoryList.clear();
                        for (String categoryName: hashedPopularCategories.keySet()){
                            nestedCategoryList.add(new NestedCategory(categoryName,hashedPopularCategories.get(categoryName)));
                        }

                        mlNestedCategoryList.postValue(nestedCategoryList);
                    }
                });
            }

            @Override
            public void onDownloaded(List<Category> list) {

            }

            @Override
            public void onFinish() {

            }
        });
    }



    public LiveData<List<Category>> getCategoriesLiveData(){
        return mlCategories;
    }

    public LiveData<List<NestedCategory>> getPopularNestedCategories(){
        return mlNestedCategoryList;
    }

}
