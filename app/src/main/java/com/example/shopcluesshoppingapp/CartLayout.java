package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CartLayout extends AppCompatActivity {
    private TextView quantity;
    private TextView minus;
    private TextView plush;
    private ImageView removeButton;
    private TextView continueToShoppingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_layout);
        initViews();
    }

    private void initViews() {
        quantity = findViewById(R.id.quantity);
        minus = findViewById(R.id.quantity_minus);
        plush = findViewById(R.id.quantity_plush);
        removeButton = findViewById(R.id.remove_cart);
        continueToShoppingButton = findViewById(R.id.cart_continue_shopping);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartLayout.this, "Item has been removed from Cart", Toast.LENGTH_SHORT).show();
            }
        });
        continueToShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartLayout.this, MainActivity.class);
                startActivity(intent);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(quantity.getText().toString()) > 1) {
                    int value = Integer.parseInt(quantity.getText().toString()) - 1;
                    quantity.setText(value + "");
                }
            }
        });
        plush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(quantity.getText().toString()) + 1;
                quantity.setText(value + "");
            }
        });
    }
}