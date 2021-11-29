package com.janta.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.janta.shopcluesshoppingapp.R;

public class EmptyCart extends AppCompatActivity {
    private Button continueShoppingFromEmptyCart;
    private ImageView emptyCartArrowBack;
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
        emptyCartArrowBack = findViewById(R.id.emptyCartArrowBack);
        emptyCartArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}