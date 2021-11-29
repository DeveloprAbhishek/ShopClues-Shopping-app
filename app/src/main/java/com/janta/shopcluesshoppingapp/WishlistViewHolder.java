package com.janta.shopcluesshoppingapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class WishlistViewHolder extends RecyclerView.ViewHolder {
    private onWishlistClickListener onWishlistClickListener;
    private ImageView mIvOfferImage;
    private TextView mTvOfferTitle, mTvOfferPrice, mTvOfferOff;
    private RatingBar mRbOfferRating;
    private CardView mCvOfferCardView;

    private Button mBtnBuyNow;
    private ImageView mIvFavoriteIcon;

    public WishlistViewHolder(@NonNull View itemView, onWishlistClickListener onWishlistClickListener) {
        super(itemView);
        this.onWishlistClickListener = onWishlistClickListener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mIvOfferImage = itemView.findViewById(R.id.ivOfferImage);
        mTvOfferTitle = itemView.findViewById(R.id.tvOffersTitle);
        mTvOfferPrice = itemView.findViewById(R.id.tvOffersPrice);
        mTvOfferOff = itemView.findViewById(R.id.tvOffersOff);
        mRbOfferRating = itemView.findViewById(R.id.rbOfferRating);
        mCvOfferCardView = itemView.findViewById(R.id.offerCardView);

        mIvFavoriteIcon = itemView.findViewById(R.id.favorite_icon);
        mBtnBuyNow = itemView.findViewById(R.id.btnBuyNow);
    }

    public void setOffersData(OffersModel model, String key, int position) {
        mTvOfferTitle.setText(model.getTitle());
        Glide.with(mIvOfferImage.getContext()).load(model.getImage()).into(mIvOfferImage);
        mTvOfferPrice.setText("Rs." + model.getPrice());
        mTvOfferOff.setText(model.getOffer() + "% off");


        mCvOfferCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWishlistClickListener.onWishlistItemClicked(position, model, key);
            }
        });

        mIvFavoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWishlistClickListener.onClickHeartIcon(position, model, key);
                mIvFavoriteIcon.setImageResource(R.drawable.favorite_blank_icon);
            }
        });

        mBtnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWishlistClickListener.onClickBuyButton(position, model, key);
            }
        });
    }
}
