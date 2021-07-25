package com.example.shopcluesshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditAddress extends AppCompatActivity {
    private Button mBtnSaveContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        mBtnSaveContinue=findViewById(R.id.BtnSaveContinue);
        mBtnSaveContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditAddress.this,Wishlist.class);
                startActivity(intent);

            }
        });
    }
}