package com.janta.shopcluesshoppingapp;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.janta.shopcluesshoppingapp.R;

public class SmallBannerViewHolder extends RecyclerView.ViewHolder {
    ImageView smallBanner;
    private ProductClickListener productClickListener;

    public SmallBannerViewHolder(@NonNull View itemView, ProductClickListener productClickListener) {
        super(itemView);
        this.productClickListener = productClickListener;
        smallBanner = itemView.findViewById(R.id.small_banner);
    }

    public void setSmallBanner(SmallBannerModel smallBannerModel) {
        smallBanner.setImageResource(smallBannerModel.getSmallBannerImage());
    }


    public void setSmallBannerData(SmallBannerModel smallBannerModel) {
        smallBanner.setImageResource(smallBannerModel.getSmallBannerImage());
    }
}
