package com.kochsj.realrealty.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kochsj.realrealty.R;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        User user = new User("Stephen", "Koch");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // intent is to render a map...
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);
    }
}
