package com.brogrammers.jonosokti.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.brogrammers.jonosokti.bean.model.Cart;
import com.brogrammers.jonosokti.repositories.CartRepository;

import java.util.List;

public class CartFragmentViewModel extends AndroidViewModel {
    private CartRepository cartRepository;
    public CartFragmentViewModel(@NonNull Application application) {
        super(application);

        cartRepository = new CartRepository(application);

    }

    public LiveData<List<Cart>> getAllCarts(){
        return cartRepository.getAllCarts();
    }

    public void deleteCartItem(Cart cart) {
        cartRepository.delete(cart);
    }
}
