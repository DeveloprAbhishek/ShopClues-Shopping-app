package com.janta.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Wishlist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wishlist extends Fragment implements onWishlistClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private WishlistAdapter wishlistAdapter;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private String userId;

    public Wishlist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Wishlist.
     */
    // TODO: Rename and change types and number of parameters
    public static Wishlist newInstance(String param1, String param2) {
        Wishlist fragment = new Wishlist();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        initViews(view);
        getDataFromFirebase();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        wishlistAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        wishlistAdapter.stopListening();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getUid();
        checkWishlistData();
    }

    void getDataFromFirebase() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("wishlist").child(userId);
        FirebaseRecyclerOptions<OffersModel> options =
                new FirebaseRecyclerOptions.Builder<OffersModel>()
                        .setQuery(myRef, OffersModel.class)
                        .build();
        wishlistAdapter = new WishlistAdapter(options, this, progressBar);
        recyclerView.setAdapter(wishlistAdapter);
    }

    @Override
    public void onWishlistItemClicked(int Position, OffersModel model, String key) {
        Intent goToOfferDetailPage = new Intent(getContext(), ProductDetail.class);
        goToOfferDetailPage.putExtra("title", model.getTitle().toString());
        goToOfferDetailPage.putExtra("image", model.getImage());
        goToOfferDetailPage.putExtra("price", model.getPrice());
        goToOfferDetailPage.putExtra("offer", model.getOffer());
        goToOfferDetailPage.putExtra("rating", model.getRating());
        goToOfferDetailPage.putExtra("key", model.getKey());
        startActivity(goToOfferDetailPage);
    }

    @Override
    public void onClickBuyButton(int Position, OffersModel model, String key) {
        startActivity(new Intent(getContext(), EditAddress.class));
    }

    @Override
    public void onClickHeartIcon(int Position, OffersModel model, String key) {
        FirebaseDatabase.getInstance().getReference().child("wishlist").child(userId).child(key).removeValue();
        checkWishlistData();
    }

    void checkWishlistData() {
        FirebaseDatabase.getInstance().getReference("wishlist").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                Log.d("TAG", "Value is: " + map);
                if (map == null) {
                    startActivity(new Intent(getContext(), EmptyWishList.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}