package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartLayout extends AppCompatActivity implements CartItemClickListener, View.OnClickListener {
    private TextView quantity;
    private TextView minus;
    private TextView plush;
    private ImageView removeButton;
    private TextView continueToShoppingButton;
    private TextView cartTotalPrice;
    private TextView cartTotalOrderValue;
    private TextView cartTotalDiscount;
    private TextView shippingCharge;
    private TextView cartGrandTotal;
    private RelativeLayout cartCard;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CartAdapter cartAdapter;
    private ImageView mIvBackButton;
    private TextView mTvPlaceOrderButton, mTvGrandTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_layout);
        initViews();
        getDataFromFirebase();
        //setCartData();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        mIvBackButton = findViewById(R.id.ivBackButton);
        mTvPlaceOrderButton = findViewById(R.id.tvPlaceOrderButton);
        mTvGrandTotal = findViewById(R.id.tvGrandTotal);

        mTvPlaceOrderButton.setOnClickListener(this);
        mIvBackButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        cartAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        cartAdapter.stopListening();
    }

    void getDataFromFirebase() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cart");

        FirebaseRecyclerOptions<CartModel> options =
                new FirebaseRecyclerOptions.Builder<CartModel>()
                        .setQuery(myRef, CartModel.class)
                        .build();
        cartAdapter = new CartAdapter(options, this, progressBar);
        recyclerView.setAdapter(cartAdapter);
        getUserCartGrandTotal();
    }

    void getUserCartGrandTotal() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("cartTotalAmount");

        ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                int cartGTotalPrice = dataSnapshot.getValue(int.class);
                mTvGrandTotal.setText(cartGTotalPrice+"");
            }
        });
    }

    @Override
    public void onClickCloseIcon(int position, CartModel model, String key) {
        int price = model.getPrice();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("cartTotalAmount");

        ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                int cartGTotalPrice = dataSnapshot.getValue(int.class);
                int remainingPrice = cartGTotalPrice-price;
                ref.setValue(remainingPrice);
                mTvGrandTotal.setText("₹"+remainingPrice);
            }
        });

        FirebaseDatabase.getInstance().getReference().child("cart").child(key).removeValue();

    }

    @Override
    public void onClickQtyButtons(int position, CartModel model) {
        //mTvGrandTotal.setText("₹"+position);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivBackButton) {
            onBackPressed();
        } else if(v.getId() == R.id.tvPlaceOrderButton) {

        }

    }
}