package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView productImage;
    private TextView productName;
    private TextView description;
    private TextView price;
    private TextView actualPrice;
    private TextView discount;
    private ImageView favoriteIcon;
    private ImageView shareIcon;
    private TextView BuyButton;
    private TextView CartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Intent intent = getIntent();
        initViews();
//        setProductDetailsView(intent);

    }

    private void initViews() {
        productName = findViewById(R.id.detail_name);
        description = findViewById(R.id.product_desc);
        price = findViewById(R.id.detail_price);
        actualPrice = findViewById(R.id.detail_actual_price);
        discount = findViewById(R.id.detail_discount);
        favoriteIcon = findViewById(R.id.favorite_icon);
        shareIcon = findViewById(R.id.share_icon);
        BuyButton = findViewById(R.id.ButtonBuy);
        CartButton = findViewById(R.id.ButtonAddToCart);

        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteIcon.setImageResource(R.drawable.favorite_icon);
                Toast.makeText(ProductDetailActivity.this, "Item Added to Favorite", Toast.LENGTH_SHORT).show();
            }
        });
        shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareProduct = new Intent(android.content.Intent.ACTION_SEND);
                shareProduct.setType("txt/plain");
                startActivity(Intent.createChooser(shareProduct, "Share Product Via"));
            }
        });

        CartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetailActivity.this, "Item Added to the Cart", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setProductDetailsView(Intent intent) {
        productName.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("desc"));
        price.setText(intent.getStringExtra("price"));
        actualPrice.setText(intent.getStringExtra("actualPrice"));
        discount.setText(intent.getStringExtra("discount"));
    }
}