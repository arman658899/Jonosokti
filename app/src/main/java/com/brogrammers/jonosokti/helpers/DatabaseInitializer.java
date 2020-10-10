package com.brogrammers.jonosokti.helpers;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DatabaseInitializer {
    public static DatabaseInitializer instance;
    public  FirebaseFirestore db;
    public  StorageReference storage;
    public FirebaseAuth mAuth;
    private Context context;
    public DatabaseInitializer(Application application){
        this.context = application;
    }

    public static synchronized DatabaseInitializer getInstance(Application application){
        if (instance==null) instance = new DatabaseInitializer(application);
        return instance;
    }

    public void init(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
                .build();
        db = FirebaseFirestore.getInstance();
        db.setFirestoreSettings(settings);

        storage = FirebaseStorage.getInstance().getReference();
    }

    public FirebaseFirestore getDb(){
        return db;
    }
    public StorageReference getStorage(){
        return storage;
    }
    public FirebaseAuth getAuth(){
        return mAuth;
    }
}
