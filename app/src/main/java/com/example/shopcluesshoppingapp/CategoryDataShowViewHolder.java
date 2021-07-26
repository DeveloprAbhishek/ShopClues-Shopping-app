package com.example.shopcluesshoppingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

public class CategoryDataShowViewHolder extends RecyclerView.ViewHolder {
    private CategoryDataShowCllickListener listener;

    private ImageView mIvOfferImage;
    private TextView mTvOfferTitle, mTvOfferPrice, mTvOfferOff;
    private RatingBar mRbOfferRating;
    private CardView mCvOfferCardView;

    public CategoryDataShowViewHolder(@NonNull View itemView, CategoryDataShowCllickListener listener) {
        super(itemView);
        initViews(itemView);
        this.listener = listener;
    }

    private void initViews(View itemView) {
        mIvOfferImage = itemView.findViewById(R.id.ivOfferImage);
        mTvOfferTitle = itemView.findViewById(R.id.tvOffersTitle);
        mTvOfferPrice = itemView.findViewById(R.id.tvOffersPrice);
        mTvOfferOff = itemView.findViewById(R.id.tvOffersOff);
        mRbOfferRating = itemView.findViewById(R.id.rbOfferRating);
        mCvOfferCardView = itemView.findViewById(R.id.offerCardView);
    }
    public void setOffersData(OffersModel model) {
        mTvOfferTitle.setText(model.getTitle());
        Glide.with(mIvOfferImage.getContext()).load(model.getImage()).into(mIvOfferImage);
        mTvOfferPrice.setText("Rs."+model.getPrice());
        mTvOfferOff.setText(model.getOffer()+"% off");
        mRbOfferRating.setRating((float) model.getRating());
        mCvOfferCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.categoryShowDataClicked(model);

            }
        });
    }
}
