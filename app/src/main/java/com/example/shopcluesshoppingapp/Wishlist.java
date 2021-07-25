package com.example.shopcluesshoppingapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Wishlist extends AppCompatActivity {
    private Button mBtnPlaceOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        mBtnPlaceOrder=findViewById(R.id.btnPlaceOrder);
        mBtnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Wishlist.this,PaymentGateway.class);
                startActivity(intent);

            }
        });

    }
}
