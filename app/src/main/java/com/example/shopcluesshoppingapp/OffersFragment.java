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
    private ArrayList<OffersModel> modelList;
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
        //progressBar.setVisibility(View.GONE);

    }

//    private void buildList() {
//
//        modelList = new ArrayList<>();
//
//        modelList.add(new OffersModel(R.drawable.product1, "Pause solid blue Causal Shirt", 78, 1099, 5));
//        modelList.add(new OffersModel(R.drawable.product2, "Craftwell Blue Double Bedsheet", 45, 452, 4));
//        modelList.add(new OffersModel(R.drawable.product3, "Bihari JI Pure Ghe 1Ltr.", 62, 784, 2.2));
//        modelList.add(new OffersModel(R.drawable.product4, "Lycra V-Neck Men's T-Shirt", 87, 125, 5));
//        modelList.add(new OffersModel(R.drawable.product5, "leather Brown Men's Wallet", 89, 1245, 3));
//        modelList.add(new OffersModel(R.drawable.product6, "Liddu TG 113 Speaker", 45, 345, 5));
//        modelList.add(new OffersModel(R.drawable.product7, "Palco M1101 USB Bluetooth", 23, 451, 2));
//        modelList.add(new OffersModel(R.drawable.product8, "Trendyz Men Black Hooded Tshirt", 56, 899, 4));
//        modelList.add(new OffersModel(R.drawable.product9, "Men Red Solid High Neck T-Shirt", 12, 1099, 3));
//        modelList.add(new OffersModel(R.drawable.product11, "Krishna Multicolor Wall Stickers", 14, 599, 2));
//        modelList.add(new OffersModel(R.drawable.product12, "Clymb Men Sport Shoe", 45, 23, 4));
//        modelList.add(new OffersModel(R.drawable.product13, "Voorkoms body Tempoary Tattoo", 23, 4526, 7));
//        modelList.add(new OffersModel(R.drawable.product1, "Pause solid blue Causal Shirt", 78, 1099, 5));
//        modelList.add(new OffersModel(R.drawable.product2, "Craftwell Blue Double Bedsheet", 45, 789, 4));
//        modelList.add(new OffersModel(R.drawable.product3, "Bihari JI Pure Ghe 1Ltr.", 62, 784, 2.2));
//        modelList.add(new OffersModel(R.drawable.product4, "Lycra V-Neck Men's T-Shirt", 87, 125, 5));
//        modelList.add(new OffersModel(R.drawable.product5, "leather Brown Men's Wallet", 89, 1245, 3));
//        modelList.add(new OffersModel(R.drawable.product6, "Liddu TG 113 Speaker", 45, 345, 5));
//        modelList.add(new OffersModel(R.drawable.product7, "Palco M1101 USB Bluetooth", 23, 451, 2));
//        modelList.add(new OffersModel(R.drawable.product8, "Trendyz Men Black Hooded Tshirt", 56, 899, 4));
//        modelList.add(new OffersModel(R.drawable.product9, "Men Red Solid High Neck T-Shirt", 12, 1099, 3));
//        modelList.add(new OffersModel(R.drawable.product11, "Krishna Multicolor Wall Stickers", 14, 599, 2));
//        modelList.add(new OffersModel(R.drawable.product12, "Clymb Men Sport Shoe", 45, 23, 4));
//        modelList.add(new OffersModel(R.drawable.product13, "Voorkoms body Tempoary Tattoo", 23, 4526, 7));
//
//    }


//    private void setRecyclerView() {
//        OffersAdapter offersAdapter = new OffersAdapter(modelList, this);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        recyclerView.setAdapter(offersAdapter);
//    }

    @Override
    public void onOfferClick(OffersModel model) {
        Intent goToOfferDetailPage = new Intent(getContext(), ProductDetail.class);
        goToOfferDetailPage.putExtra("title", model.getTitle().toString());
        goToOfferDetailPage.putExtra("image", model.getImage());
        startActivity(goToOfferDetailPage);
    }
}