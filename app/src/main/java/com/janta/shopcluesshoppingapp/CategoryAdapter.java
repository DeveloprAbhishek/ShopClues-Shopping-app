package com.janta.shopcluesshoppingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.janta.shopcluesshoppingapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    ArrayList<CategoryModel> categoryModelsList;
    CategoryClickListener categoryClickListener;
    public CategoryAdapter(ArrayList<CategoryModel> categoryModelsList, CategoryClickListener categoryClickListener) {
        this.categoryModelsList = categoryModelsList;
        this.categoryClickListener = categoryClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout_recyclerview, parent, false);
        return new CategoryViewHolder(view, categoryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull  CategoryViewHolder holder, int position) {
        CategoryModel model = categoryModelsList.get(position);
        holder.setData(model);
    }

    @Override
    public int getItemCount() {
        return categoryModelsList.size();
    }
}
