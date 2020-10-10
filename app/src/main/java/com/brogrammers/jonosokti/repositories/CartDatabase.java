package com.brogrammers.jonosokti.repositories;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.brogrammers.jonosokti.bean.model.Cart;
import com.brogrammers.jonosokti.bean.model.CartDAO;


@Database(entities = Cart.class,version = 2,exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {
    private static CartDatabase instance;
    public abstract CartDAO cartDAO();

    public static synchronized CartDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    CartDatabase.class,
                    "cart_database"
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
