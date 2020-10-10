package com.brogrammers.jonosokti.repositories;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.brogrammers.jonosokti.Constants;
import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.bean.ServiceAndProvider;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.helpers.ApplicationHelper;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DefaultRepository {
    private static DefaultRepository repository;
    FirebaseFirestore db;
    private Context context;
    private CollectionReference COLL_CATEGORIES,COLL_SUB_CATEGORIES,COLL_SERVICES_PROVIDERS;
    public DefaultRepository(Context context){
        this.context = context;
        db = ApplicationHelper.getDatabase().getDb();
        COLL_CATEGORIES = db.collection(Constants.DB_CATEGORIES);
        COLL_SERVICES_PROVIDERS = db.collection(Constants.DB_SERVICES_PROVIDERS);
        COLL_SUB_CATEGORIES = db.collection(Constants.DB_SUB_CATEGORIES);
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

    public void getFivePopularCategories(final OnDataDownloadListener<Category> listener) {
        COLL_CATEGORIES.orderBy("viewed", Query.Direction.DESCENDING)
                .limit(5)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        listener.onStarted();
                        for (DocumentSnapshot ds: queryDocumentSnapshots){
                            try{
                                Category category = ds.toObject(Category.class);
                                listener.onDownloaded(category);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        listener.onFinish();
                    }
                });
    }

    public void getSubCategoriesByCategoryId(String categoryId,final OnDataDownloadListener<SubCategory> listener){
        List<SubCategory> subCategories = new ArrayList<>();
        COLL_SUB_CATEGORIES.whereEqualTo("categoryId",categoryId)
                .limit(5)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        listener.onStarted();
                        subCategories.clear();
                        for (DocumentSnapshot ds: queryDocumentSnapshots){
                            try{
                                SubCategory category = ds.toObject(SubCategory.class);
                                subCategories.add(category);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        listener.onDownloaded(subCategories);
                        listener.onFinish();
                    }
                });
    }

    public void getAllSubCategoriesByCategoryId(String categoryId,final OnDataDownloadListener<SubCategory> listener){
        COLL_SUB_CATEGORIES.whereEqualTo("categoryId",categoryId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        listener.onStarted();
                        for (DocumentSnapshot ds: queryDocumentSnapshots){
                            try{
                                SubCategory category = ds.toObject(SubCategory.class);
                                listener.onDownloaded(category);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        listener.onFinish();
                    }
                });
    }

    public void getAllServiceProvidersBySubCatAndLocation(String subCategoryId, String location,final OnDataDownloadListener<ServiceAndProvider> listener){
        List<String> locations = new ArrayList<>();
        locations.add(location);
        COLL_SERVICES_PROVIDERS
                .whereEqualTo("subCatId",subCategoryId)
                .whereArrayContainsAny("locations",locations)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        listener.onStarted();
                        for (DocumentSnapshot ds: queryDocumentSnapshots){
                            try{
                                ServiceAndProvider category = ds.toObject(ServiceAndProvider.class);
                                listener.onDownloaded(category);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        listener.onFinish();
                    }
                });
    }

}
