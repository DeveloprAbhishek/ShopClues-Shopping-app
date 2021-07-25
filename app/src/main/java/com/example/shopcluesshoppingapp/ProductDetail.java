package com.example.shopcluesshoppingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class ProductDetail extends AppCompatActivity implements View.OnClickListener, PaymentResultListener {
    private ImageView mIvProductImage, mIvCartBtn, favoriteIcon, shareIcon;
    private TextView mTvProductName, mTvPrice, mTvActualPrice, mTvProductDiscount, mTvBuyBtn, mTvAddCartBtn, mTvDescription;
    private RatingBar mRbRatingBar;
    private TextView mTvShopMore, mTvGoToCart;

    private String productTitle, productImage,productDescription;
    private int productPrice, productOffer;
    private float productRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Checkout.preload(getApplicationContext());
        initViews();
        getIntentData();
        setIntentData();

    }

    private void initViews() {
        mTvProductName = findViewById(R.id.tvProductTitle);
        mIvProductImage = findViewById(R.id.ivProductImage);
        mTvPrice = findViewById(R.id.tvProductPrice);
        mRbRatingBar = findViewById(R.id.rbOfferRating);
        mIvCartBtn = findViewById(R.id.ivCartBtn);
        mTvActualPrice = findViewById(R.id.ivProductActualPrice);
        mTvProductDiscount = findViewById(R.id.tvProductDiscount);
        mTvDescription=findViewById(R.id.description);

        favoriteIcon = findViewById(R.id.favorite_icon);
        shareIcon = findViewById(R.id.share_icon);
        mTvAddCartBtn = findViewById(R.id.tvAddCartBtn);
        mTvBuyBtn = findViewById(R.id.tvBuyBtn);
        mTvShopMore = findViewById(R.id.tvShopMore);
        mTvGoToCart = findViewById(R.id.tvGoToCart);

        mIvCartBtn.setOnClickListener(this);
        mTvBuyBtn.setOnClickListener(this);
        mTvAddCartBtn.setOnClickListener(this);
        mTvShopMore.setOnClickListener(this);
        mTvGoToCart.setOnClickListener(this);
        shareIcon.setOnClickListener(this);
        favoriteIcon.setOnClickListener(this);
    }

    private void getIntentData() {
        productTitle = getIntent().getStringExtra("title");
        productImage = getIntent().getStringExtra("image");
        productDescription = getIntent().getStringExtra("desc");
        productPrice = getIntent().getIntExtra("price", -1);
        productOffer = getIntent().getIntExtra("offer", 1);
        productRating = (float) getIntent().getDoubleExtra("rating", -1);
    }

    private void setIntentData() {
        mTvProductName.setText(productTitle);
        Glide.with(mIvProductImage).load(productImage).into(mIvProductImage);
        mTvPrice.setText("Rs." + productPrice);
        mRbRatingBar.setRating(productRating);
        mTvProductDiscount.setText(productOffer + "% off");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvAddCartBtn) {
            addProductToCart();
        } else if (v.getId() == R.id.tvBuyBtn) {
            startPayment();
        } else if (v.getId() == R.id.ivCartBtn) {
            Intent intent = new Intent(ProductDetail.this, CartLayout.class);
            Toast.makeText(ProductDetail.this, "Item Added to the Cart", Toast.LENGTH_LONG).show();
            startActivity(intent);
        } else if (v.getId() == R.id.share_icon) {
            Intent shareProduct = new Intent(android.content.Intent.ACTION_SEND);
            shareProduct.setType("txt/plain");
            startActivity(Intent.createChooser(shareProduct, "Share Product Via"));
        } else if (v.getId() == R.id.favorite_icon) {
            favoriteIcon.setImageResource(R.drawable.favorite_icon);
            Toast.makeText(ProductDetail.this, "Item Added to Favorite", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.tvShopMore) {
            startActivity(new Intent(ProductDetail.this, HomeFragment.class));
        } else if (v.getId() == R.id.tvGoToCart) {
            startActivity(new Intent(ProductDetail.this, CartLayout.class));
        }
    }

    private void addProductToCart() {

        CartModel object = new CartModel(productTitle, productImage, productPrice);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("cart").push()
                .setValue(object)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ProductDetail.this, "Added to cart", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(ProductDetail.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
        mTvBuyBtn.setVisibility(View.GONE);
        mTvAddCartBtn.setVisibility(View.GONE);
        mTvShopMore.setVisibility(View.VISIBLE);
        mTvGoToCart.setVisibility(View.VISIBLE);
    }

    private void startPayment() {
        String amtStr = Integer.toString(productPrice * 100);
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_MVo8XLbqFup11L");
        checkout.setImage(R.drawable.rzp_logo);
        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Abhishek");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amtStr);//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact", "7777011329");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch (Exception e) {
            Log.e("TAG Payment", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful, PaymentId: " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed! Due to " + s, Toast.LENGTH_SHORT).show();
    }
}