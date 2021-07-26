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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class CartLayout extends AppCompatActivity implements CartItemClickListener, View.OnClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CartAdapter cartAdapter;
    private ImageView mIvBackButton;
    private TextView mTvPlaceOrderButton, mTvGrandTotal;
    private FirebaseAuth mAuth;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_layout);
        initViews();
        getDataFromFirebase();
    }

    private void initViews() {
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getUid();
        checkIfCartEmpty();

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
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("cart").child(userId);
        FirebaseRecyclerOptions<CartModel> options =
                new FirebaseRecyclerOptions.Builder<CartModel>()
                        .setQuery(myRef, CartModel.class)
                        .build();
        cartAdapter = new CartAdapter(options, this, progressBar);
        recyclerView.setAdapter(cartAdapter);
        getUserCartGrandTotal();
    }

    void getUserCartGrandTotal() {
        DatabaseReference cartTotalRef = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("cartTotal");
        cartTotalRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                int cartTotal = dataSnapshot.getValue(int.class);
                mTvGrandTotal.setText(cartTotal+"");
            }
        });
    }

    @Override
    public void onClickCloseIcon(CartModel model, int items) {
        int price = model.getPrice()*items;
        DatabaseReference cartTotalRef = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("cartTotal");

        cartTotalRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                int cartTotal = dataSnapshot.getValue(int.class);
                int remainingPrice = cartTotal-price;
                cartTotalRef.setValue(remainingPrice);
                mTvGrandTotal.setText("₹"+remainingPrice);
            }
        });
        DatabaseReference cartItemTotalRef = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("totalItem");
        cartItemTotalRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                int cartTotalItems = dataSnapshot.getValue(int.class);
                int remainsItems = cartTotalItems - items;
                cartItemTotalRef.setValue(remainsItems);
            }
        });
        FirebaseDatabase.getInstance().getReference().child("cart").child(userId).child(model.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });
    }

    @Override
    public void onClickQtyButtons(int cartTotal, CartModel model) {
        mTvGrandTotal.setText("₹"+cartTotal);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivBackButton) {
            onBackPressed();
        } else if(v.getId() == R.id.tvPlaceOrderButton) {
            orderPlaced();
        }

    }

    private void orderPlaced() {
        startActivity(new Intent(CartLayout.this, EditAddress.class));
        DatabaseReference cartDb = FirebaseDatabase.getInstance().getReference("cart").child(userId);
        cartDb.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                DatabaseReference orderDb = FirebaseDatabase.getInstance().getReference("order").child(userId);
                orderDb.setValue(map);

                FirebaseDatabase.getInstance().getReference("cart").child(userId).removeValue();
                FirebaseDatabase.getInstance().getReference("Users").child(userId).child("totalItem").setValue(0);
                FirebaseDatabase.getInstance().getReference("Users").child(userId).child("cartTotal").setValue(0);
            }
        });
    }

    private void checkIfCartEmpty() {
        FirebaseDatabase.getInstance().getReference("cart").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                Log.d("TAG", "Value is: " + map);
                if(map == null) {
                    startActivity(new Intent(CartLayout.this, EmptyCart.class));
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}