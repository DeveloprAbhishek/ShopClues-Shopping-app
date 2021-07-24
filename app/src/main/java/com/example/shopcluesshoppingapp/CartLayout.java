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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class CartLayout extends AppCompatActivity implements CartItemClickListener{
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

        cartTotalOrderValue = findViewById(R.id.total_value_cart);
        cartTotalDiscount = findViewById(R.id.total_discount_cart);
        shippingCharge = findViewById(R.id.shipping_charge_cart);
        cartGrandTotal = findViewById(R.id.grand_total_cart);
        cartCard = findViewById(R.id.cart_card);
        continueToShoppingButton = findViewById(R.id.cart_continue_shopping);
//

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
        cartAdapter = new CartAdapter(options, this);
        recyclerView.setAdapter(cartAdapter);
    }

    public void setCartData() {
        int deliveryCharge = 0;
        int totalPrice = Integer.parseInt(quantity.getText().toString()) * Integer.parseInt(cartTotalPrice.getText().toString());
        int totalOrderValue = Integer.parseInt(quantity.getText().toString()) * Integer.parseInt(cartTotalOrderValue.getText().toString());
        int discountValue = Integer.parseInt(quantity.getText().toString()) * Integer.parseInt(cartTotalDiscount.getText().toString());
        String shippingValue = shippingCharge.getText().toString();
        if (shippingValue.endsWith("Free")) {
            shippingCharge.setText("Free");
        } else {
            int charge = Integer.parseInt(shippingValue) * Integer.parseInt(quantity.getText().toString());
            deliveryCharge = charge;
            shippingCharge.setText(charge);
        }
        int grandTotal = (totalOrderValue - discountValue) + deliveryCharge;
        cartTotalPrice.setText(totalPrice + " Rs");
        cartTotalOrderValue.setText(totalOrderValue + " Rs");
        cartTotalDiscount.setText(discountValue + " Rs");
        cartGrandTotal.setText(grandTotal + " Rs");

    }

    @Override
    public void onClickCloseIcon(int position) {
        //Toast.makeText(this, ""+FirebaseDatabase.getInstance().getReference().child("cart").getRef().getKey(), Toast.LENGTH_SHORT).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cart");
        FirebaseRecyclerOptions<CartModel> options =
                new FirebaseRecyclerOptions.Builder<CartModel>()
                        .setQuery(myRef, CartModel.class)
                        .build();

        Toast.makeText(this, "z"+options, Toast.LENGTH_SHORT).show();
        FirebaseDatabase.getInstance().getReference().child("cart").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    String key = ds.getKey();
                    //Toast.makeText(CartLayout.this, ""+position, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClickQtyButtons(int position, CartModel model) {

    }


    //removeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(CartLayout.this, "Item has been removed from Cart", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        continueToShoppingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CartLayout.this, HomeActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Integer.parseInt(quantity.getText().toString()) > 1) {
//                    int value = Integer.parseInt(quantity.getText().toString()) - 1;
//                    quantity.setText(value + "");
//
//                }
//            }
//        });
//        plush.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int value = Integer.parseInt(quantity.getText().toString()) + 1;
//                quantity.setText(value + "");
//
//            }
//        });
}