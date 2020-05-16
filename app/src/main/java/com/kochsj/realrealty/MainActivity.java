package com.kochsj.realrealty;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.kochsj.realrealty.adapters.MapViewAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        User user = new User("Baba", "San");

//         intent is to render a map...
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);

//        ViewPager searchView = findViewById(R.id.center_view);
//        ProfilePageAdapter adapter = new ProfilePageAdapter(user, this);
//
//        searchView.setAdapter(adapter);
    }

    public void showMap(View view) {
        ViewPager centerView = findViewById(R.id.center_view);
        MapViewAdapter adapter = new MapViewAdapter(this);

        centerView.setAdapter(adapter);

//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);
    }

}
