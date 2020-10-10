package com.brogrammers.jonosokti.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.brogrammers.jonosokti.Constants;
import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.helpers.AppPreferences;
import com.brogrammers.jonosokti.helpers.ApplicationHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.YELLOW;

public class RegistrationActivity extends AppCompatActivity {
    private Dialog loadingDialog;
    private EditText etName;
    private CollectionReference collRefUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etName = findViewById(R.id.editTextTextPersonName);
        collRefUsers = ApplicationHelper.getDatabase().getDb().collection(Constants.DB_USERS);

        loadingDialog = ApplicationHelper.getUtilsHelper().getLoadingDialog(RegistrationActivity.this);
        loadingDialog.setCancelable(false);

        //button signup
        findViewById(R.id.sign_up_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ""+etName.getText().toString();
                if (name.isEmpty() || name.length()<3){
                    etName.setError("Invalid Name.");
                    etName.requestFocus();
                    return;
                }

                if (ApplicationHelper.getDatabase().getAuth().getCurrentUser()!=null){
                    loadingDialog.show();
                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("uid", ApplicationHelper.getDatabase().getAuth().getCurrentUser().getUid());
                    hashMap.put("name", name);
                    hashMap.put("mobile", AppPreferences.UserInfo.getUserMob(RegistrationActivity.this));

                    collRefUsers.document(ApplicationHelper.getDatabase().getAuth().getCurrentUser().getUid())
                            .set(hashMap, SetOptions.merge())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    loadingDialog.dismiss();
                                    if (task.isSuccessful()){
                                        AppPreferences.UserInfo.setUserName(RegistrationActivity.this,name);
                                        Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }else showSnackbarMessage("Something error happened. Please try again later....");
                                }
                            });

                }else{
                    Toast.makeText(RegistrationActivity.this, "You are not logged in. Please login first.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }


            }
        });

    }

    private void showSnackbarMessage(String s) {
        Snackbar.make(etName,s, BaseTransientBottomBar.LENGTH_LONG)
                .setTextColor(BLACK)
                .setBackgroundTint(YELLOW)
                .show();
    }
}