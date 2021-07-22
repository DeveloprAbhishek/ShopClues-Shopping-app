package com.example.shopcluesshoppingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<LayoutBaseModel> layoutBaseModelsList;


    public ProductAdapter(ArrayList<LayoutBaseModel> layoutBaseModelsList) {
        this.layoutBaseModelsList = layoutBaseModelsList;
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View smallBannerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_banner_layout, parent, false);
                return new SmallBannerViewHolder(smallBannerView);
            case 1:
                View productView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
                return new ProductViewHolder(productView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = layoutBaseModelsList.get(position).getViewType();
        switch (viewType) {
            case 0:
                if (holder instanceof SmallBannerViewHolder) {
                    SmallBannerModel smallBanner = (SmallBannerModel) layoutBaseModelsList.get(position);
                    ((SmallBannerViewHolder) holder).setSmallBannerData(smallBanner);
                }
                break;
            case 1:
                if (holder instanceof ProductViewHolder) {
                    ProductModel productModel = (ProductModel) layoutBaseModelsList.get(position);
                    ((ProductViewHolder) holder).setProductData(productModel);
                }
                break;
        }

    }

    @Override
    public int getItemCount() {
        return layoutBaseModelsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutBaseModelsList.get(position).getViewType();
    }
}
