package com.brogrammers.jonosokti.bean.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cart cart);
    @Update
    void update(Cart cart);
    @Delete
    void delete(Cart cart);
    @Query("DELETE FROM cart_table")
    void deleteAllCart();

    @Query("SELECT * FROM cart_table WHERE providerUid LIKE :uid")
    public abstract List<Cart> findProviderByUid(String uid);

    //List<Cart> getAllCarts();
    @Query("SELECT * FROM cart_table")
    LiveData<List<Cart>> getAllCartLive();

}
