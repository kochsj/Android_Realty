package com.kochsj.realrealty;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        User user = new User("Baba", "San");
//         intent is to render a map...
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
//        ViewPager searchView = findViewById(R.id.center_view);
//        ProfilePageAdapter adapter = new ProfilePageAdapter(user, this);
//
//        searchView.setAdapter(adapter);
    }





}
