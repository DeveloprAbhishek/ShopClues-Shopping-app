package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView productImage;
    private TextView productName;
    private TextView description;
    private TextView price;
    private TextView actualPrice;
    private TextView discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Intent intent = getIntent();
        initViews();
//        setProductView(intent);

    }

    private void initViews() {
        productName = findViewById(R.id.product_name);
        description = findViewById(R.id.product_desc);
        price = findViewById(R.id.price);
        actualPrice = findViewById(R.id.actual_price);
        discount = findViewById(R.id.discount);
    }

    private void setProductView(Intent intent) {
        productName.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("desc"));
        price.setText(intent.getStringExtra("price"));
        actualPrice.setText(intent.getStringExtra("actualPrice"));
        discount.setText(intent.getStringExtra("discount"));
    }
}