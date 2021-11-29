package com.janta.shopcluesshoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.janta.shopcluesshoppingapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CategoryDataShowingActivity extends AppCompatActivity implements CategoryDataShowCllickListener{
    private TextView mTvPriceSort;
    private RecyclerView recyclerView;
    private OffersAdapter offersAdapter;
    private ProgressBar progressBar;
    String mainCategory, category, subCategory;
    private CategoryDataShowAdapter adapter;
    private boolean sortData = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_data_showing);
        initViews();
        getDataFromFirebase();
    }

    private void initViews() {
        mainCategory = getIntent().getStringExtra("mainCategory");
        category = getIntent().getStringExtra("category");
        subCategory = getIntent().getStringExtra("subCategory");
        mTvPriceSort = findViewById(R.id.tvPriceSort);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        mTvPriceSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sortData) {
                    mTvPriceSort.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_downward, 0, 0, 0);

                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("category").child(mainCategory).child(category).child(subCategory);
                    Query query = myRef.orderByChild("price").endAt(1);
                    FirebaseRecyclerOptions<OffersModel> options =
                            new FirebaseRecyclerOptions.Builder<OffersModel>()
                                    .setQuery(query, OffersModel.class)
                                    .build();
                    adapter = new CategoryDataShowAdapter(options, CategoryDataShowingActivity.this, progressBar);
                    recyclerView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();


                    sortData = false;
                } else {

                    mTvPriceSort.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_upward, 0, 0, 0);

                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("category").child(mainCategory).child(category).child(subCategory);
                    Query query = myRef.orderByChild("price").limitToFirst(1);
                    FirebaseRecyclerOptions<OffersModel> options =
                            new FirebaseRecyclerOptions.Builder<OffersModel>()
                                    .setQuery(query, OffersModel.class)
                                    .build();
                    adapter = new CategoryDataShowAdapter(options, CategoryDataShowingActivity.this, progressBar);                    recyclerView.setAdapter(adapter);
                    recyclerView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                    sortData = true;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    void getDataFromFirebase() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("category").child(mainCategory).child(category).child(subCategory);

        FirebaseRecyclerOptions<OffersModel> options =
                new FirebaseRecyclerOptions.Builder<OffersModel>()
                        .setQuery(myRef, OffersModel.class)
                        .build();
        adapter = new CategoryDataShowAdapter(options, this, progressBar);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void categoryShowDataClicked(OffersModel model) {
        Intent goToOfferDetailPage = new Intent(this, ProductDetail.class);
        goToOfferDetailPage.putExtra("title", model.getTitle().toString());
        goToOfferDetailPage.putExtra("image", model.getImage());
        goToOfferDetailPage.putExtra("price", model.getPrice());
        goToOfferDetailPage.putExtra("offer", model.getOffer());
        goToOfferDetailPage.putExtra("rating", model.getRating());
        startActivity(goToOfferDetailPage);
    }
}