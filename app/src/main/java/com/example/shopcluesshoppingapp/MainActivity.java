package com.example.shopcluesshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SystemClock.sleep(4000);
        Intent LoginIntent =new Intent(MainActivity.this,LogInActivity.class);
        startActivity(LoginIntent);
        finish();
    }
}