package com.example.shopcluesshoppingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private ArrayList<ProductModel> productModelsList;
    private ProductClickListener productClickListener;

    public ProductAdapter(ArrayList<ProductModel> productModelsList, ProductClickListener productClickListener) {
        this.productModelsList = productModelsList;
        this.productClickListener = productClickListener;
    }

    @NonNull

    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View productView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
        return new ProductViewHolder(productView, productClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel model = productModelsList.get(position);
        holder.setProductData(model);


    }

    @Override
    public int getItemCount() {
        return productModelsList.size();
    }

}
