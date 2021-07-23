package com.example.shopcluesshoppingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class OffersViewHolder extends RecyclerView.ViewHolder {
    private ImageView mIvOfferImage;
    private TextView mTvOfferTitle, mTvOfferPrice, mTvOfferOff;
    private RatingBar mRbOfferRating;
    private CardView mCvOfferCardView;

    private OffersClickListener offersClickListener;

    public OffersViewHolder(@NonNull @NotNull View itemView, OffersClickListener offersClickListener) {
        super(itemView);
        this.offersClickListener = offersClickListener;
        initViews(itemView);
    }


    private void initViews(View itemView) {
        mIvOfferImage = itemView.findViewById(R.id.ivOfferImage);
        mTvOfferTitle = itemView.findViewById(R.id.tvOffersTitle);
        mTvOfferPrice = itemView.findViewById(R.id.tvOffersPrice);
        mTvOfferOff = itemView.findViewById(R.id.tvOffersOff);
        mRbOfferRating = itemView.findViewById(R.id.rbOfferRating);
        mCvOfferCardView = itemView.findViewById(R.id.offerCardView);
    }

    void setOffersData(OffersModel model) {
        mTvOfferTitle.setText(model.getOfferTitle());
        mIvOfferImage.setImageResource(model.getImageUrl());

        mCvOfferCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offersClickListener.onOfferClick(model);

            }
        });
    }


}
