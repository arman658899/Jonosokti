package com.brogrammers.jonosokti.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.bean.Provider;
import com.brogrammers.jonosokti.bean.ServiceAndProvider;
import com.brogrammers.jonosokti.listeners.OnDataDownloadListener;
import com.brogrammers.jonosokti.repositories.DefaultRepository;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class ProviderProfileActivity extends AppCompatActivity {

    private TextView tvName,tvMobileNo,tvCompanyName,tvServiceName;
    private CircularImageView cvProfile;
    private String mProviderUid;
    private DefaultRepository defaultRepository;

    private Provider mProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profile);
        defaultRepository = DefaultRepository.getInstance(this);
        mProviderUid =  getIntent().getStringExtra("provider");

        tvName = findViewById(R.id.textView_name);
        tvMobileNo = findViewById(R.id.textView31);
        tvCompanyName = findViewById(R.id.textView_company_name);
        tvServiceName = findViewById(R.id.textView_company_name2);

        cvProfile = findViewById(R.id.circularImageView);

        findViewById(R.id.imageview_editprofile_back).setOnClickListener(v -> onBackPressed());

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mProviderUid!=null){
            defaultRepository.getProiderByUid(mProviderUid, new OnDataDownloadListener<Provider>() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onDownloaded(Provider serviceAndProvider) {
                    mProvider = serviceAndProvider;
                    if (mProvider.getUid()==null) showProviderNotFoundDialog();
                    updateUi();
                }

                @Override
                public void onDownloaded(List<Provider> list) {

                }

                @Override
                public void onFinish() {

                }
            });
        }


    }

    private void showProviderNotFoundDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Not Found")
                .setMessage("Something error occurred. Please try again later.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        onBackPressed();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

    private void updateUi() {
        try{
            tvName.setText(mProvider.getProviderName());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            tvCompanyName.setText(mProvider.getCompanyName());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            tvMobileNo.setText(mProvider.getProviderMobile());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            tvServiceName.setText(mProvider.getCategoryName());
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            Glide.with(this)
                    .load(mProvider.getProfilePic())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(cvProfile);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}