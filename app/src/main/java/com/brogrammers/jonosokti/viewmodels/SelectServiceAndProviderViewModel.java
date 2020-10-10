package com.brogrammers.jonosokti.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.brogrammers.jonosokti.bean.ServiceAndProvider;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.bean.model.Cart;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.brogrammers.jonosokti.repositories.CartRepository;
import com.brogrammers.jonosokti.repositories.DefaultRepository;

import java.util.ArrayList;
import java.util.List;

public class SelectServiceAndProviderViewModel extends AndroidViewModel {
    private DefaultRepository defaultRepository;
    private CartRepository cartRepository;
    private List<ServiceAndProvider> serviceAndProviders;
    private MutableLiveData<List<ServiceAndProvider>> mlServiceAndProviders;
    public SelectServiceAndProviderViewModel(@NonNull Application application) {
        super(application);
        cartRepository = new CartRepository(application);
        defaultRepository = DefaultRepository.getInstance(application);
        serviceAndProviders = new ArrayList<>();
        mlServiceAndProviders = new MutableLiveData<>();
    }

    //cart items
    public LiveData<List<Cart>> getAllCarts(){
        return cartRepository.getAllCarts();
    }

    public void insertToCart(final Cart cart){
        cartRepository.insert(cart);
    }

    public void deleteAllCart() {
        cartRepository.deleteAll();
    }

    public void getAllServiceProvidersBySubCatAndLocation(String subCatId, String location){
        defaultRepository.getAllServiceProvidersBySubCatAndLocation(subCatId,location, new OnDataDownloadListener<ServiceAndProvider>() {
            @Override
            public void onStarted() {
                serviceAndProviders.clear();
            }

            @Override
            public void onDownloaded(ServiceAndProvider serviceAndProvider) {
                serviceAndProviders.add(serviceAndProvider);

            }

            @Override
            public void onDownloaded(List<ServiceAndProvider> list) {

            }

            @Override
            public void onFinish() {
                mlServiceAndProviders.postValue(serviceAndProviders);
            }
        });
    }

    public LiveData<List<ServiceAndProvider>> getLiveDataServiceAndProviders(){
        return mlServiceAndProviders;
    }


}
