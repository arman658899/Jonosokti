package com.brogrammers.jonosokti.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.brogrammers.jonosokti.bean.model.Cart;
import com.brogrammers.jonosokti.repositories.CartRepository;
import com.brogrammers.jonosokti.repositories.DefaultRepository;

import java.util.List;

public class ConfirmOderViewModel extends AndroidViewModel {
    private CartRepository cartRepository;
    private DefaultRepository defaultRepository;
    public ConfirmOderViewModel(@NonNull Application application) {
        super(application);

        cartRepository = new CartRepository(application);
        defaultRepository = DefaultRepository.getInstance(application);
    }

    public LiveData<List<Cart>> getAllCarts(){
        return cartRepository.getAllCarts();
    }

    public void deleteAllCart(){
        cartRepository.deleteAll();
    }
}
