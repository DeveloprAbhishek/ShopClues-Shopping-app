package com.example.shopcluesshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderConfirmed extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnTrackOrder, mBtnContinueShopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmed);
        initViews();
    }

    private void initViews() {
        mBtnTrackOrder = findViewById(R.id.btnTrackOrder);
        mBtnContinueShopping = findViewById(R.id.btnContinueShopping);

        mBtnTrackOrder.setOnClickListener(this);
        mBtnContinueShopping.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(R.id.btnContinueShopping == v.getId()) {
            startActivity(new Intent(this, HomeActivity.class));
        } else if(v.getId() == R.id.btnTrackOrder) {
            startActivity(new Intent(this, OrderActivity.class));
        }
    }
}