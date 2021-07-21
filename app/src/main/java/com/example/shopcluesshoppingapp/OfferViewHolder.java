package com.example.shopcluesshoppingapp;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OfferViewHolder extends RecyclerView.ViewHolder {
    ImageView offerImage;

    public OfferViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        offerImage = itemView.findViewById(R.id.offer_image);
    }

    public void setOfferData(OfferModel offer) {
        offerImage.setImageResource(offer.getOfferImg());
    }
}
