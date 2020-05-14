package com.kochsj.realrealty;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // intent is to render a map...
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);
    }

    ViewPager searchView = findViewById(R.id.center_view);


}
