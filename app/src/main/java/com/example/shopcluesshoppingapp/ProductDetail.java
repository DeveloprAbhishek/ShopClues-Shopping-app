package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ProductDetail extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvProductImage, mIvCartBtn, favoriteIcon, shareIcon;
    private TextView mTvProductName, mTvPrice, mTvActualPrice, mTvProductDiscount, mTvBuyBtn, mTvAddCartBtn;
    private RatingBar mRbRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initViews();
        setIntentData();

    }

    private void initViews() {
        mTvProductName = findViewById(R.id.tvProductTitle);
        mIvProductImage = findViewById(R.id.ivProductImage);
        mTvPrice = findViewById(R.id.tvProductPrice);
        mRbRatingBar = findViewById(R.id.rbOfferRating);
        mIvCartBtn = findViewById(R.id.ivCartBtn);
        favoriteIcon = findViewById(R.id.favorite_icon);
        shareIcon = findViewById(R.id.share_icon);
        mTvActualPrice = findViewById(R.id.ivProductActualPrice);
        mTvProductDiscount = findViewById(R.id.tvProductDiscount);

        mTvAddCartBtn = findViewById(R.id.tvAddCartBtn);
        mTvBuyBtn = findViewById(R.id.tvBuyBtn);
        mIvCartBtn.setOnClickListener(this);


//        favoriteIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                favoriteIcon.setImageResource(R.drawable.favorite_icon);
//                Toast.makeText(ProductDetail.this, "Item Added to Favorite", Toast.LENGTH_SHORT).show();
//            }
//        });
//        shareIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent shareProduct = new Intent(android.content.Intent.ACTION_SEND);
//                shareProduct.setType("txt/plain");
//                startActivity(Intent.createChooser(shareProduct, "Share Product Via"));
//            }
//        });
//

    }

    private void setIntentData() {
        mTvProductName.setText(getIntent().getStringExtra("title"));
        Glide.with(mIvProductImage).load(getIntent().getStringExtra("image")).into(mIvProductImage);
//        productImage.setImageResource(getIntent().getIntExtra("img", 0));
//        productName.setText(getIntent().getStringExtra("name"));
//        description.setText(getIntent().getStringExtra("desc"));
//        price.setText(getIntent().getStringExtra("price"));
//        actualPrice.setText(getIntent().getStringExtra("actualPrice"));
//        discount.setText(getIntent().getStringExtra("discount"));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvAddCartBtn) {

        } else if (v.getId() == R.id.tvBuyBtn) {

        } else if (v.getId() == R.id.ivCartBtn) {
            Intent intent = new Intent(ProductDetail.this, CartLayout.class);
            Toast.makeText(ProductDetail.this, "Item Added to the Cart", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }
}