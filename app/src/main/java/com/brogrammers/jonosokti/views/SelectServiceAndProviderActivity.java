package com.brogrammers.jonosokti.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.ServiceAndProviderAdapter;
import com.brogrammers.jonosokti.bean.ServiceAndProvider;
import com.brogrammers.jonosokti.bean.SubCategory;
import com.brogrammers.jonosokti.bean.model.Cart;
import com.brogrammers.jonosokti.helpers.AppPreferences;
import com.brogrammers.jonosokti.listeners.OnItemSelectListener;
import com.brogrammers.jonosokti.viewmodels.SelectServiceAndProviderViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectServiceAndProviderActivity extends AppCompatActivity implements OnItemSelectListener<ServiceAndProvider> {
    private RecyclerView recyclerView;
    private List<ServiceAndProvider> serviceAndProviders;
    private List<Cart> cartList;
    //cart items
    private double total;
    private TextView tvTotalFee;
    private ServiceAndProviderAdapter adapter;
    private SelectServiceAndProviderViewModel viewModel;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    private ImageView imageView;
    private ProgressBar progressBar,progressBarMain;

    private SubCategory mSubCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service_andprovider);

        mSubCategory = (SubCategory) getIntent().getSerializableExtra("sub_cat");
        cartList = new ArrayList<>();
        serviceAndProviders = new ArrayList<>();
        adapter = new ServiceAndProviderAdapter(this,serviceAndProviders,this);
        initUI();

        viewModel = new ViewModelProvider(this).get(SelectServiceAndProviderViewModel.class);

        if (mSubCategory !=null){
            Glide.with(this)
                    .load(mSubCategory.getPhotoLink())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);
            viewModel.getAllServiceProvidersBySubCatAndLocation(mSubCategory.getDocumentId(), AppPreferences.getUserLocationName(this));

        }
    }

    private void initUI() {

        tvTotalFee = findViewById(R.id.textview_total_fee);
        imageView = findViewById(R.id.imageview_category_icon);
        progressBar = findViewById(R.id.progress_bar);
        progressBarMain = findViewById(R.id.progress_bar_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        toolbar = findViewById(R.id.toolbar);

        if (mSubCategory !=null) collapsingToolbar.setTitle(mSubCategory.getSubCatName());
        toolbar.setNavigationIcon(R.drawable.icon_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //continue button
        findViewById(R.id.button_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total<=0){
                    Toast.makeText(SelectServiceAndProviderActivity.this, "No service has been selected.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(SelectServiceAndProviderActivity.this, ScheduleActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private Observer<List<ServiceAndProvider>> serviceAndProvidersObserver = new Observer<List<ServiceAndProvider>>() {
        @Override
        public void onChanged(List<ServiceAndProvider> updatedData) {
            serviceAndProviders.clear();
            serviceAndProviders.addAll(updatedData);
            adapter.notifyDataSetChanged();
            progressBarMain.setVisibility(View.GONE);
        }
    };
    private Observer<List<Cart>> cartObserver = new Observer<List<Cart>>() {
        @Override
        public void onChanged(List<Cart> carts) {
            cartList.clear();
            cartList.addAll(carts);

            total = 0;
            for (Cart cart: cartList){
                total+=cart.getServiceFee();
            }

            tvTotalFee.setText(String.format(Locale.ENGLISH,"%.1f",total));

        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getLiveDataServiceAndProviders().removeObserver(serviceAndProvidersObserver);
        viewModel.getLiveDataServiceAndProviders().observe(this,serviceAndProvidersObserver);
        viewModel.getAllCarts().removeObserver(cartObserver);
        viewModel.getAllCarts().observe(this,cartObserver);
    }


    @Override
    public void onItemSelected(ServiceAndProvider serviceAndProvider) {
        Cart cart = new Cart(serviceAndProvider.getSubCatId(),serviceAndProvider.getSubCatName(),serviceAndProvider.getCategoryName(),serviceAndProvider.getCategoryId(),serviceAndProvider.getProviderName(),serviceAndProvider.getCompanyName(),serviceAndProvider.getProfilePic(),serviceAndProvider.getUid(),"1",1,Double.parseDouble(serviceAndProvider.getServiceFee()));
        viewModel.insertToCart(cart);

        if (cartList.size()>0){
            for (Cart cart1: cartList){
                if (!cart1.getProviderUid().equals(cart.getProviderUid())){
                    showConfirmationOfDeleteAllItemsFromCart(cart);
                    break;
                }
            }
        }
        viewModel.insertToCart(cart);
        Toast.makeText(this, "Service is added to cart.", Toast.LENGTH_SHORT).show();

    }

    private void showConfirmationOfDeleteAllItemsFromCart(Cart cart) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("You can only select same provider. Do you want to delete others to add this service?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        viewModel.deleteAllCart();
                        viewModel.insertToCart(cart);
                        Toast.makeText(SelectServiceAndProviderActivity.this, "Service is added to cart.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}