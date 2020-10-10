package com.brogrammers.jonosokti.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.brogrammers.jonosokti.bean.model.Cart;
import com.brogrammers.jonosokti.bean.model.CartDAO;

import java.util.List;

public class CartRepository {
    private CartDAO cartDAO;
    private LiveData<List<Cart>> allCarts;

    public CartRepository(Application application){
        CartDatabase database = CartDatabase.getInstance(application);

        cartDAO = database.cartDAO();
        allCarts = cartDAO.getAllCartLive();
    }

    public LiveData<List<Cart>> getAllCarts(){
        return allCarts;
    }

    public List<Cart> findCartsByProviderUid(final String uid){
        return cartDAO.findProviderByUid(uid);
    }

    public void insert(Cart cart){
        new InsertTask(cartDAO).execute(cart);
    }

    public void delete(Cart cart){
        new DeleteTask(cartDAO).execute(cart);
    }

    public void update(Cart cart){
        new UpdateTask(cartDAO).execute(cart);
    }

    public void deleteAll(){
        new DeleteAllTask(cartDAO).execute();
    }

    private static class InsertTask extends AsyncTask<Cart, Void,Void>{
        private CartDAO cartDAO;
        private InsertTask(CartDAO cartDAO){
            this.cartDAO = cartDAO;
        }

        @Override
        protected Void doInBackground(Cart... carts) {
            cartDAO.insert(carts[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Cart, Void,Void>{
        private CartDAO cartDAO;
        private DeleteTask(CartDAO cartDAO){
            this.cartDAO = cartDAO;
        }

        @Override
        protected Void doInBackground(Cart... carts) {
            cartDAO.delete(carts[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<Cart, Void,Void>{
        private CartDAO cartDAO;
        private UpdateTask(CartDAO cartDAO){
            this.cartDAO = cartDAO;
        }

        @Override
        protected Void doInBackground(Cart... carts) {
            cartDAO.update(carts[0]);
            return null;
        }
    }

    private static class DeleteAllTask extends AsyncTask<Void, Void,Void>{
        private CartDAO cartDAO;
        private DeleteAllTask(CartDAO cartDAO){
            this.cartDAO = cartDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cartDAO.deleteAllCart();
            return null;
        }
    }

}
