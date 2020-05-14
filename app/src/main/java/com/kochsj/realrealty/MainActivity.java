package com.kochsj.realrealty;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // intent is to render a map...
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
