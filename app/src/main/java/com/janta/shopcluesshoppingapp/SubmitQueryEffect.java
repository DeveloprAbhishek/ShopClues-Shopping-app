package com.janta.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.janta.shopcluesshoppingapp.R;

public class SubmitQueryEffect extends AppCompatActivity {
    private Button queryReceiveOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_query_effect);
        queryReceiveOk = findViewById(R.id.queryReceiveOkBtn);
        queryReceiveOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmitQueryEffect.this,MessageSentSuccessful.class);
                startActivity(intent);
            }
        });
    }
}