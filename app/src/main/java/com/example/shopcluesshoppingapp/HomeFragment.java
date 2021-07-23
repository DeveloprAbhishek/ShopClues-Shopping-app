package com.example.shopcluesshoppingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private ArrayList<LayoutBaseModel> layouts;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        buildList();
        setRecyclerView();

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }


    private void buildList() {

        layouts = new ArrayList<>();

        layouts.add(new ProductModel(R.drawable.product1, "Pause solid blue Causal Shirt", "599", "1099", "70% off"));
        layouts.add(new ProductModel(R.drawable.product2, "Craftwell Blue Double Bedsheet", "228", "599", "74% off"));
        layouts.add(new ProductModel(R.drawable.product3, "Bihari JI Pure Ghe 1Ltr.", "424", "450", "5% off"));
        layouts.add(new ProductModel(R.drawable.product4, "Lycra V-Neck Men's T-Shirt", "178", "1999", "91% off"));
        layouts.add(new ProductModel(R.drawable.product5, "leather Brown Men's Wallet", "142", "1799", "92% off"));
        layouts.add(new ProductModel(R.drawable.product6, "Liddu TG 113 Speaker", "408", "299", "86% off"));
        layouts.add(new ProductModel(R.drawable.product7, "Palco M1101 USB Bluetooth", "931", "2000", "53% off"));
        layouts.add(new ProductModel(R.drawable.product8, "Trendyz Men Black Hooded Tshirt", "475", "899", "47% off"));
        layouts.add(new ProductModel(R.drawable.product9, "Men Red Solid High Neck T-Shirt", "278", "1099", "74% off"));
        layouts.add(new ProductModel(R.drawable.product10, "Ikall TA-777 Portable Bluetooth", "2299", "4999", "54% off"));
        layouts.add(new ProductModel(R.drawable.product11, "Krishna Multicolor Wall Stickers", "95", "599", "84% off"));
        layouts.add(new ProductModel(R.drawable.product12, "Clymb Men Sport Shoe", "497", "999", "50% off"));
        layouts.add(new ProductModel(R.drawable.product13, "Voorkoms body Tempoary Tattoo", "142", "499", "71% off"));
        layouts.add(new ProductModel(R.drawable.product14, "Cute Panda Wall Sticker", "95", "599", "84% off"));
        layouts.add(new ProductModel(R.drawable.product15, "Titan Men Classic Analog Watch", "478", "1999", "76% off"));
        layouts.add(new ProductModel(R.drawable.product16, "Lotus herbals radiant gold", "276", "1010", "72% off"));
        layouts.add(new ProductModel(R.drawable.product17, "BUCIK Black Synthetic Sandals", "361", "1999", "81% off"));
        layouts.add(new ProductModel(R.drawable.product18, "Trackpant With Full Sleeve T-Shirt", "579", "1199", "51% off"));
        layouts.add(new ProductModel(R.drawable.product19, "Kaltron Mini 8 Plug 1 Extension", "199", "599", "66% off"));
        layouts.add(new ProductModel(R.drawable.product20, "Men Black Hooded Tshirt", "398", "1099", "63% off"));
        layouts.add(new ProductModel(R.drawable.product21, "True Choice Men Analog Watch", "218", "1999", "89% off"));
        layouts.add(new ProductModel(R.drawable.product22, "Red Solid Cotton Shrug for Men", "398", "999", "60% off"));
        layouts.add(new ProductModel(R.drawable.product23, "Baby Himalaya Face Cream ", "150", "300", "50% off"));
        layouts.add(new ProductModel(R.drawable.product24, "Wild Stone Body Spray", "199", "249", "40% off"));
        layouts.add(new ProductModel(R.drawable.product25, "Colorblock Cardigan for Men", "478", "1499", "68% off"));
        layouts.add(new ProductModel(R.drawable.product26, "Men's Brown Ethnic Shoe", "399", "699", "50% off"));
        layouts.add(new ProductModel(R.drawable.product27, "Blue Heaven Lipstick", "266", "299", "11% off"));
        layouts.add(new ProductModel(R.drawable.product28, "Kaltron Diagonal Steel", "209", "599", "65% off"));
        layouts.add(new ProductModel(R.drawable.product29, "Meia White Pink Kanchipuram Saree", "730", "2999", "75% off"));
        layouts.add(new ProductModel(R.drawable.product30, "Set of 4 MultiColor Wall Sticker", "190", "499", "57% off"));
//            layouts.add(new SmallBannerModel(R.drawable.small_banner_1));

    }


    private void setRecyclerView() {
        ProductAdapter productAdapter = new ProductAdapter(layouts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(productAdapter);
    }
}