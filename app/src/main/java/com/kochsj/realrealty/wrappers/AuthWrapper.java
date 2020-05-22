package com.kochsj.realrealty.wrappers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.kochsj.realrealty.MainApplication;
import com.kochsj.realrealty.activities.AuthActivity;
import com.kochsj.realrealty.activities.MainActivity;

public class AuthWrapper extends AppCompatActivity {
    /*
    If a user is Auth, show the main activity, otherwise show the auth activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (MainApplication.getUID() == null) {
            startAuthActivity();
        } else {
            startMainActivity();
        }

        // clears back stack
        finishAffinity();
    }


    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void startAuthActivity() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }
}
