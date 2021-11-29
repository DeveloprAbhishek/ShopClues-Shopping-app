package com.janta.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.janta.shopcluesshoppingapp.R;

public class Contact extends AppCompatActivity {
    private Button submitQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        submitQuery = findViewById(R.id.submitMessage);
        submitQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact.this, SubmitQueryEffect.class);
                startActivity(intent);
            }
        });
    }
}