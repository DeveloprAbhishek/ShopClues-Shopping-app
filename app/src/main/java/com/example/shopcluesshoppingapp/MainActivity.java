package com.example.shopcluesshoppingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private ArrayList<OfferModel> offer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        buildList();
        setRecyclerView();

    }


    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void buildList() {
        offer = new ArrayList<>();
        offer.add(new OfferModel(R.drawable.offer_1));
        offer.add(new OfferModel(R.drawable.offer_2));
        offer.add(new OfferModel(R.drawable.offer_3));
        offer.add(new OfferModel(R.drawable.offer_4));
        offer.add(new OfferModel(R.drawable.offer_5));
        offer.add(new OfferModel(R.drawable.offer_1));
    }

    private void setRecyclerView() {
        OfferAdapter offerAdapter = new OfferAdapter(offer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(offerAdapter);
    }
}
