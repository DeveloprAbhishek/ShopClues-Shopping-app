package com.janta.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.janta.shopcluesshoppingapp.R;

public class EmptyOrder extends AppCompatActivity {
    private Button continueShoppingEmptyOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_order);
        continueShoppingEmptyOrder = findViewById(R.id.continueShopping_emptyOrder);
        continueShoppingEmptyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmptyOrder.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}