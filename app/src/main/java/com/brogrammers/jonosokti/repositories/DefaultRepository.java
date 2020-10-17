package com.brogrammers.jonosokti.repositories;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.brogrammers.jonosokti.Constants;
import com.brogrammers.jonosokti.bean.Banner;
import com.brogrammers.jonosokti.bean.Category;
import com.brogrammers.jonosokti.bean.Order;
import com.brogrammers.jonosokti.bean.Provider;
import com.brogrammers.jonosokti.bean.ServiceAndProvider;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.helpers.ApplicationHelper;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.brogrammers.jonosokti.listeners.OnUploadListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultRepository {
    private static DefaultRepository repository;
    FirebaseFirestore db;
    private Context context;
    private CollectionReference COLL_CATEGORIES,COLL_SUB_CATEGORIES,COLL_SERVICES_PROVIDERS,COLL_ORDERS,COLL_PROVIDERS,COLL_BANNERS;

    private Map<Context, ListenerRegistration> activeListeners;

    public DefaultRepository(Context context){
        this.context = context;
        db = ApplicationHelper.getDatabase().getDb();
        COLL_CATEGORIES = db.collection(Constants.DB_CATEGORIES);
        COLL_SERVICES_PROVIDERS = db.collection(Constants.DB_SERVICES_PROVIDERS);
        COLL_SUB_CATEGORIES = db.collection(Constants.DB_SUB_CATEGORIES);
        COLL_ORDERS = db.collection(Constants.DB_ORDERS);
        COLL_PROVIDERS = db.collection(Constants.DB_PROVIDERS);
        COLL_BANNERS = db.collection(Constants.DB_BANNERS);


        activeListeners = new HashMap<>();
    }
    public static DefaultRepository getInstance(Context context){
        if (repository==null) repository = new DefaultRepository(context);
        return repository;
    }

    public void incrementCategoryViews(final String categoryId){
        db.runTransaction(new Transaction.Function<Void>() {

            @Nullable
            @Override
            public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {

                DocumentSnapshot ds = transaction.get(COLL_CATEGORIES.document(categoryId));
                if (ds.exists()){
                    try{
                        long views = ds.getLong("viewed");
                        transaction.update(COLL_CATEGORIES.document(categoryId),"viewed",views+1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                return null;
            }
        });
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

    //orders
    public String getOrderDocumentId(){
        return COLL_ORDERS.document().getId();
    }

    public void createNewOrder(final Order order, final OnUploadListener listener){
        COLL_ORDERS.document(order.getDocumentId())
                .set(order, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) listener.onUploaded();
                        else listener.onFailed();
                    }
                });
    }

    public void getMyOnGoingOrders(String uid,final OnDataDownloadListener<Order> listener){
        ListenerRegistration listenerRegistration =
                COLL_ORDERS.orderBy("createdTime", Query.Direction.DESCENDING)
                .whereEqualTo("userUid",uid)
                .whereEqualTo("completed",false)
                .whereEqualTo("cancelledByMe",false)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error==null && value!=null){
                            listener.onStarted();
                            for (DocumentSnapshot ds: value){
                                try{
                                    Order order = ds.toObject(Order.class);
                                    listener.onDownloaded(order);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                        listener.onFinish();
                    }
                });

        activeListeners.put(context,listenerRegistration);

    }

    public void getMyOrderHistory(String uid,final OnDataDownloadListener<Order> listener){
        ListenerRegistration listenerRegistration =
                COLL_ORDERS.orderBy("createdTime", Query.Direction.DESCENDING)
                        .whereEqualTo("userUid",uid)
                        .whereEqualTo("completed",true)
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (error==null && value!=null){
                                    listener.onStarted();
                                    for (DocumentSnapshot ds: value){
                                        try{
                                            Order order = ds.toObject(Order.class);
                                            listener.onDownloaded(order);
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                listener.onFinish();
                            }
                        });

        activeListeners.put(context,listenerRegistration);

    }

    public void removeActiveListeners(Context context){
        if (activeListeners.containsValue(context)){
            activeListeners.get(context).remove();
        }
    }

    //order updating

    public void updateCancelByUser(Order order, OnUploadListener listener) {
        COLL_ORDERS.document(order.getDocumentId())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("cancelledByMe",true);
                        if (documentSnapshot.exists()){

                            documentSnapshot.getReference()
                                    .set(hashMap,SetOptions.merge())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) listener.onUploaded();
                                            else listener.onFailed();
                                        }
                                    });
                        }else listener.onFailed();
                    }
                });
    }

    public void deleteOrder(Order order, OnUploadListener listener) {
        COLL_ORDERS.document(order.getDocumentId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) listener.onUploaded();
                        else listener.onFailed();
                    }
                });
    }

    public void getProiderByUid(String mProviderUid, OnDataDownloadListener<Provider> listener) {
        COLL_PROVIDERS.document(mProviderUid)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot!=null){
                            try{
                                Provider provider = documentSnapshot.toObject(Provider.class);
                                listener.onDownloaded(provider);
                            }catch (Exception e){
                                e.printStackTrace();
                                listener.onDownloaded(new Provider());
                            }
                        }

                        listener.onFinish();
                    }
                });
    }


    //banners
    public void getBanners(OnDataDownloadListener<Banner> listener) {
        ListenerRegistration listenerRegistration = COLL_BANNERS.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error==null && value!=null){
                            listener.onStarted();
                            for (DocumentSnapshot ds: value){
                                try{
                                    Banner banner = ds.toObject(Banner.class);
                                    listener.onDownloaded(banner);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }

                        listener.onFinish();
                    }
                });
        activeListeners.put(context,listenerRegistration);
    }

    public void getSubCategoriesByKey(List<String> keys, OnDataDownloadListener<SubCategory> listener) {
        COLL_SUB_CATEGORIES.whereArrayContainsAny("keys",keys)
                .limit(10)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots!=null){
                            listener.onStarted();
                            for (DocumentSnapshot ds: queryDocumentSnapshots){
                                try{
                                    SubCategory subCategory = ds.toObject(SubCategory.class);
                                    listener.onDownloaded(subCategory);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }

                        listener.onFinish();
                    }
                });
    }
}
