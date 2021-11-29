package com.janta.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment implements CategoryClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    ArrayList<CategoryModel> categoryList = new ArrayList<>();

    public CategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
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
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        initViews(view);
        buildData();
        setAdapterView();
        return view;
    }

    private void setAdapterView() {
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(categoryAdapter);
    }

    private void buildData() {
        categoryList.add(new CategoryModel("Clothing", "TShirts", "Jeans", "Shirts",
                "Sandles", "Slippers", "Sports Class",
                R.drawable.tshirts_navtile,R.drawable.jeans_image,R.drawable.shirts,R.drawable.sandals2,R.drawable.slippers, R.drawable.sportshoes2));

    }

    private void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
    }

    @Override
    public void onClickCategory(CategoryModel model, int position) {
        Intent intent = new Intent(getContext(), CategoryDataShowingActivity.class);
        intent.putExtra("mainCategory", "Men's Fashion");
        intent.putExtra("category", "Clothing");
        intent.putExtra("subCategory", "TShirts");
        startActivity(intent);
    }

    @Override
    public void onClickSecondCategory(CategoryModel model, int position) {
        Intent intent = new Intent(getContext(), CategoryDataShowingActivity.class);
        intent.putExtra("mainCategory", "Men's Fashion");
        intent.putExtra("category", "Clothing");
        intent.putExtra("subCategory", "Jeans");
        startActivity(intent);
    }

    @Override
    public void onClickThirdCategory(CategoryModel model, int position) {
        Intent intent = new Intent(getContext(), CategoryDataShowingActivity.class);
        intent.putExtra("mainCategory", "Men's Fashion");
        intent.putExtra("category", "Clothing");
        intent.putExtra("subCategory", "Shirts");
        startActivity(intent);

    }
}