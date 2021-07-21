package com.example.shopcluesshoppingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferViewHolder> {
    private ArrayList<OfferModel> offerModels;

    public OfferAdapter(ArrayList<OfferModel> offerModels) {
        this.offerModels = offerModels;
    }

    @NonNull

    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View offerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_layout, parent, false);
        return new OfferViewHolder(offerView);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        OfferModel offerModel = offerModels.get(position);
        holder.setOfferData(offerModel);
    }

    @Override
    public int getItemCount() {
        return offerModels.size();
    }
}
