package com.example.shopcluesshoppingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class OffersViewHolder extends RecyclerView.ViewHolder {
    private ImageView mIvOfferImage;
    private TextView mTvOfferTitle, mTvOfferPrice, mTvOfferOff;
    private RatingBar mRbOfferRating;

    public OffersViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        initViews(itemView);
    }


    private void initViews(View itemView) {
        mIvOfferImage = itemView.findViewById(R.id.ivOfferImage);
        mTvOfferTitle = itemView.findViewById(R.id.tvOffersTitle);
        mTvOfferPrice = itemView.findViewById(R.id.tvOffersPrice);
        mTvOfferOff = itemView.findViewById(R.id.tvOffersOff);
        mRbOfferRating = itemView.findViewById(R.id.rbOfferRating);
    }

    void setOffersData(OffersModel model) {
        mTvOfferTitle.setText(model.getOfferTitle());
        mIvOfferImage.setImageResource(model.getImageUrl());
    }


}
