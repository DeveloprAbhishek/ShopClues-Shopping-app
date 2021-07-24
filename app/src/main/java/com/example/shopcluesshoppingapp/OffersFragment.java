package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OffersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OffersFragment extends Fragment implements OffersClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private OffersAdapter offersAdapter;
    private ProgressBar progressBar;

    public OffersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OffersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OffersFragment newInstance(String param1, String param2) {
        OffersFragment fragment = new OffersFragment();
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
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        initViews(view);
        getDataFromFirebase();
        //buildList();
        //setRecyclerView();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        offersAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        offersAdapter.stopListening();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    void getDataFromFirebase() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("offers");

        FirebaseRecyclerOptions<OffersModel> options =
                new FirebaseRecyclerOptions.Builder<OffersModel>()
                        .setQuery(myRef, OffersModel.class)
                        .build();
        offersAdapter = new OffersAdapter(options, this, progressBar);
        recyclerView.setAdapter(offersAdapter);
    }


    @Override
    public void onOfferClick(OffersModel model) {
        Intent goToOfferDetailPage = new Intent(getContext(), ProductDetail.class);
        goToOfferDetailPage.putExtra("title", model.getTitle().toString());
        goToOfferDetailPage.putExtra("image", model.getImage());
        goToOfferDetailPage.putExtra("price", model.getPrice());
        goToOfferDetailPage.putExtra("offer", model.getOffer());
        goToOfferDetailPage.putExtra("rating", model.getRating());
        startActivity(goToOfferDetailPage);
    }
}