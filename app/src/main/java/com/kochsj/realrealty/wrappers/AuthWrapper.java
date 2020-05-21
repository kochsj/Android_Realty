package com.kochsj.realrealty.wrappers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.activities.EmailPasswordActivity;
import com.kochsj.realrealty.activities.SignedInMainActivity;

public class AuthWrapper extends AppCompatActivity {
    /*
    If a user is Auth, show the main activity, otherwise show the auth activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (MainApplication.getUserData() == null) {
            startAuthActivity();
        } else {
            startMainActivity();
        }

    }


    private void startMainActivity() {
        Log.d("Wrapper", "startMainActivity: xxxxxxxxxxx");
        Intent intent = new Intent(this, SignedInMainActivity.class);
        startActivity(intent);

    }

    private void startAuthActivity() {
        Intent intent = new Intent(this, EmailPasswordActivity.class);
        startActivity(intent);
    }
}