package com.janta.shopcluesshoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.janta.shopcluesshoppingapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class OrderActivity extends AppCompatActivity implements OrderClickListener{
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private OrderAdapter orderAdapter;

    private String userId;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initViews();
        checkIfOrderEmpty();
        getDataFromFirebase();
    }

    private void checkIfOrderEmpty() {
        FirebaseDatabase.getInstance().getReference("order").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                Log.d("TAG", "Value is: " + map);
                if(map == null) {
                    startActivity(new Intent(OrderActivity.this, EmptyOrder.class));
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void initViews() {
        mAuth = FirebaseAuth.getInstance();
        userId =  mAuth.getUid();

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

    }

    @Override
    public void onStart() {
        super.onStart();
        orderAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        orderAdapter.stopListening();
    }

    void getDataFromFirebase() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("order").child(userId);

        FirebaseRecyclerOptions<OrderModel> options =
                new FirebaseRecyclerOptions.Builder<OrderModel>()
                        .setQuery(myRef, OrderModel.class)
                        .build();

        orderAdapter = new OrderAdapter(options, this, progressBar);
        recyclerView.setAdapter(orderAdapter);
    }

    @Override
    public void onCancelButtonClick(OrderModel model) {
        FirebaseDatabase.getInstance().getReference().child("order").child(userId).child(model.getKey()).removeValue();
    }


}