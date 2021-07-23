package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetail extends AppCompatActivity {
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
        initViews();
//        setProductDetailsView();

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
        productImage = findViewById(R.id.details_image);

        productName.setText(getIntent().getStringExtra("title"));
        productImage.setImageResource(getIntent().getIntExtra("image",-1));
        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteIcon.setImageResource(R.drawable.favorite_icon);
                Toast.makeText(ProductDetail.this, "Item Added to Favorite", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(ProductDetail.this, CartLayout.class);
                Toast.makeText(ProductDetail.this, "Item Added to the Cart", Toast.LENGTH_LONG).show();
                startActivity(intent);

            }
        });
    }

    private void setProductDetailsView() {
        productImage.setImageResource(getIntent().getIntExtra("img", 0));
        productName.setText(getIntent().getStringExtra("name"));
        description.setText(getIntent().getStringExtra("desc"));
        price.setText(getIntent().getStringExtra("price"));
        actualPrice.setText(getIntent().getStringExtra("actualPrice"));
        discount.setText(getIntent().getStringExtra("discount"));
    }
}