package com.brogrammers.jonosokti.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.brogrammers.jonosokti.R;

public class LoginActivity extends AppCompatActivity {
    private EditText etNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNumber = findViewById(R.id.phone_number_et);

        findViewById(R.id.sign_up_btn).setOnClickListener(v -> checkNumber(""+etNumber.getText().toString()));

    }

    private void checkNumber(String s) {
        if (isValidPhoneNumber(s)){
            Intent intent = new Intent(LoginActivity.this, OtpVerificationActivity.class);
            intent.putExtra("mobile_number",s);
            startActivity(intent);
        }else Toast.makeText(this, "Invalid mobile number.", Toast.LENGTH_SHORT).show();
    }

    private boolean isValidPhoneNumber(String mobileNumber){
        if (mobileNumber.isEmpty()) return false;
        else if (mobileNumber.length()!=11) return false;
        else{
            switch (mobileNumber.substring(0,3)){
                case "014":
                case "015":
                case "016":
                case "017":
                case "018":
                case "019": return true;
                default: return false;
            }
        }
    }

}