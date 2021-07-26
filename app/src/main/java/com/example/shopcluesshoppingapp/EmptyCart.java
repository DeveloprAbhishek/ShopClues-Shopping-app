package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EmptyCart extends AppCompatActivity {
    private Button continueShoppingFromEmptyCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_cart);
        continueShoppingFromEmptyCart = findViewById(R.id.continueShopping_emptyCart);
        continueShoppingFromEmptyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmptyCart.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}