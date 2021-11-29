package com.janta.shopcluesshoppingapp;

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
import com.janta.shopcluesshoppingapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;

import org.jetbrains.annotations.NotNull;

public class ProductDetail extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvProductImage, mIvCartBtn, favoriteIcon, shareIcon;
    private TextView mTvProductName, mTvPrice, mTvActualPrice, mTvProductDiscount, mTvBuyBtn, mTvAddCartBtn;
    private RatingBar mRbRatingBar;
    private TextView mTvShopMore, mTvGoToCart;

    private String productTitle,  productImage, productKey;
    private int productPrice, productOffer;
    private float productRating;
    private boolean isHeartSelected = false;

    private String userId;
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();
        userId =  mAuth.getUid();

        mTvProductName = findViewById(R.id.tvProductTitle);
        mIvProductImage = findViewById(R.id.ivProductImage);
        mTvPrice = findViewById(R.id.tvProductPrice);
        mRbRatingBar = findViewById(R.id.rbOfferRating);
        mIvCartBtn = findViewById(R.id.ivCartBtn);
        mTvActualPrice = findViewById(R.id.ivProductActualPrice);
        mTvProductDiscount = findViewById(R.id.tvProductDiscount);

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
        productPrice = getIntent().getIntExtra("price", -1);
        productOffer = getIntent().getIntExtra("offer", 1);
        productRating = (float) getIntent().getDoubleExtra("rating", -1);
        productKey = getIntent().getStringExtra("key");
        if (productKey != null) {
            isHeartSelected = true;
            favoriteIcon.setImageResource(R.drawable.favorite_icon);
        }

        Log.d("TAGDATA", ""+productTitle+" "+productImage +     " " + productPrice + " "+productOffer+" "+productRating+" "+productKey);

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
            startActivity(new Intent(ProductDetail.this, EditAddress.class));
        } else if (v.getId() == R.id.ivCartBtn) {
            Intent intent = new Intent(ProductDetail.this, CartLayout.class);
            startActivity(intent);
        } else if (v.getId() == R.id.share_icon) {
            Intent shareProduct = new Intent(android.content.Intent.ACTION_SEND);
            shareProduct.setType("txt/plain");
            startActivity(Intent.createChooser(shareProduct, "Share Product Via"));
        } else if(v.getId() == R.id.favorite_icon) {
            wishlistCreate();
        } else if(v.getId() == R.id.tvShopMore) {
            startActivity(new Intent(ProductDetail.this, HomeActivity.class));
        } else if(v.getId() == R.id.tvGoToCart) {
            startActivity(new Intent(ProductDetail.this, CartLayout.class));
        }
    }

    private void wishlistCreate() {
        if (isHeartSelected) {
            removeProductFromWishlist();
            favoriteIcon.setImageResource(R.drawable.favorite_blank_icon);
            Toast.makeText(ProductDetail.this, "Removed from Wishlist", Toast.LENGTH_SHORT).show();
            isHeartSelected = false;
        } else {
            addProductToWishlist();
            favoriteIcon.setImageResource(R.drawable.favorite_icon);
            isHeartSelected = true;
        }
    }

    private void addProductToWishlist() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("wishlist").child(userId);
        String key = ref.push().getKey();
        productKey = key;
        OffersModel object = new OffersModel(productImage, productTitle, productPrice, -1 , productOffer, productRating, key);
        ref.child(key).setValue(object).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ProductDetail.this, "Added to Wishlist", Toast.LENGTH_SHORT).show();
                setUserCartGrandTotal();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(ProductDetail.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void removeProductFromWishlist() {
        FirebaseDatabase.getInstance().getReference().child("wishlist").child(userId).child(productKey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ProductDetail.this, "Removed from wishlist", Toast.LENGTH_SHORT).show();
                //setUserCartGrandTotal();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(ProductDetail.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void addProductToCart() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("cart").child(userId);
        String key = ref.push().getKey();
        CartModel object = new CartModel(productTitle, productImage, productPrice, key);
        ref.child(key).setValue(object).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ProductDetail.this, "Added to cart", Toast.LENGTH_SHORT).show();
                setUserCartGrandTotal();
                setUserCartTotalItem();
            }
        }).addOnFailureListener(new OnFailureListener() {
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



    void setUserCartGrandTotal() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("cartTotal");
        ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                int cartGrandTotalPrice = dataSnapshot.getValue(int.class);
                ref.setValue(cartGrandTotalPrice + productPrice);
            }
        });
    }

    void setUserCartTotalItem() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("totalItem");
        ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                int cartTotalItem = dataSnapshot.getValue(int.class);
                ref.setValue(++cartTotalItem);
            }
        });
    }
}