package com.brogrammers.jonosokti.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.brogrammers.jonosokti.repositories.DefaultRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {
    private DefaultRepository repository;

    private List<Category> categories;
    private MutableLiveData<List<Category>> mlCategories;
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);

        repository = DefaultRepository.getInstance(application);

        categories = new ArrayList<>();
        mlCategories = new MutableLiveData<>();

        //get values from server
        getAllCategoriesFromDatabase();
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

    public LiveData<List<Category>> getCategoriesLiveData(){
        return mlCategories;
    }

}
