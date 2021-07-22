package com.example.shopcluesshoppingapp;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SmallBannerViewHolder extends RecyclerView.ViewHolder {
    ImageView smallBanner;

    public SmallBannerViewHolder(@NonNull View itemView) {
        super(itemView);
        smallBanner = itemView.findViewById(R.id.small_banner);
    }

    public void setSmallBanner(SmallBannerModel smallBannerModel) {
        smallBanner.setImageResource(smallBannerModel.getSmallBannerImage());
    }


    public void setSmallBannerData(SmallBannerModel smallBannerModel) {
        smallBanner.setImageResource(smallBannerModel.getSmallBannerImage());
    }
}
