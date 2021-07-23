package com.example.shopcluesshoppingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OffersAdapter extends RecyclerView.Adapter<OffersViewHolder> {
    ArrayList<OffersModel> offerList;

    public OffersAdapter(ArrayList<OffersModel> offerList) {
        this.offerList = offerList;
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_layout, parent, false);
        return new OffersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {
        OffersModel model = offerList.get(position);
        holder.setOffersData(model);
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }
}
