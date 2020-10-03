package com.brogrammers.jonosokti.repositories;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.brogrammers.jonosokti.Constants;
import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.helpers.ApplicationHelper;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class DefaultRepository {
    private static DefaultRepository repository;
    FirebaseFirestore db;
    private Context context;
    public DefaultRepository(Context context){
        this.context = context;
        db = ApplicationHelper.getDatabase().getDb();
    }
    public static DefaultRepository getInstance(Context context){
        if (repository==null) repository = new DefaultRepository(context);
        return repository;
    }

    public void getCategories(final OnDataDownloadListener<Category> listener){
        db.collection(Constants.DB_CATEGORIES)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots!=null){
                            listener.onStarted();
                            for (DocumentSnapshot ds: queryDocumentSnapshots){
                                try{
                                    Category category = ds.toObject(Category.class);
                                    if (category!=null && category.getCategoryId()!=null && category.getCategoryName()!=null){
                                        listener.onDownloaded(category);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                    Log.d(Constants.TAG, "getCategories onSuccess: "+Constants.DATA_PERSISTENCE_EXCEPTION);
                                }
                            }
                            listener.onFinish();
                        }else listener.onFinish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFinish();
                    }
                });
    }

}
