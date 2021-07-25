package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EmptyWishList extends AppCompatActivity {
    private Button goToExplore;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_wishlist);
        goToExplore=findViewById(R.id.GoToExploreBtn);
        goToExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EmptyWishList.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}