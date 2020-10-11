package com.brogrammers.jonosokti.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.helpers.AppPreferences;

public class FilupAddressActivity extends AppCompatActivity {
    private EditText etName,etMob,etAddress,etFlat;
    private RadioGroup radioGroup;

    String name="",mob="",address="",flat="",type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filup_address);

        etName = findViewById(R.id.nameEt);
        etMob = findViewById(R.id.mbl_number_Et);
        etAddress = findViewById(R.id.full_address_Et);
        etFlat = findViewById(R.id.flat_id_Et);
        radioGroup = findViewById(R.id.radio_grp);

        etName.setText(AppPreferences.UserInfo.getUserName(FilupAddressActivity.this));
        etMob.setText(AppPreferences.UserInfo.getUserMob(FilupAddressActivity.this));
        etAddress.setText(AppPreferences.UserInfo.getUserAddress(FilupAddressActivity.this));
        etFlat.setText(AppPreferences.UserInfo.getUserFlat(FilupAddressActivity.this));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = findViewById(checkedId);
                type = ""+button.getText().toString();
            }
        });

        //continue button
        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ""+etName.getText().toString();
                mob = ""+etMob.getText().toString();
                address = ""+etAddress.getText().toString();
                flat = ""+etFlat.getText().toString();

                if (name.isEmpty() || name.length()<3){
                    etName.setError("Please write your name.");
                    etName.requestFocus();
                    return;
                }

                if (mob.isEmpty()){
                    etMob.setError("Please write your mobile no.");
                    etMob.requestFocus();
                    return;
                }

                if (address.isEmpty()){
                    etAddress.setError("Please write your proper address.");
                    etAddress.requestFocus();
                    return;
                }

                AppPreferences.UserInfo.setUserName(FilupAddressActivity.this,name);
                AppPreferences.UserInfo.setUserMob(FilupAddressActivity.this,mob);
                AppPreferences.UserInfo.setUserAddress(FilupAddressActivity.this,address);
                AppPreferences.UserInfo.setUserFlat(FilupAddressActivity.this,flat);
                AppPreferences.UserInfo.setUserAddressType(FilupAddressActivity.this,type);

                Intent intent = new Intent(FilupAddressActivity.this,ConfirmOrderActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}