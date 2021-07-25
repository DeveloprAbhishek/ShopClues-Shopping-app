package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MessageSentSuccessful extends AppCompatActivity {
    private final static int TIME_GAP = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_sent_successfulll);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MessageSentSuccessful.this, HomeActivity.class);
                startActivity(intent);
            }
        }, TIME_GAP);
    }
}