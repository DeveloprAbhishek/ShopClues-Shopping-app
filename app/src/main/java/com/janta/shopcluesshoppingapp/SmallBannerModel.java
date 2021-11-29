package com.janta.shopcluesshoppingapp;

public class SmallBannerModel extends LayoutBaseModel {
    public static final int TYPE_SMALL_BANNER = 0;
    private int smallBannerImage;


    public SmallBannerModel(int smallBannerImage) {
        this.smallBannerImage = smallBannerImage;

    }

    public int getSmallBannerImage() {
        return smallBannerImage;
    }

    @Override
    int getViewType() {
        return TYPE_SMALL_BANNER;
    }
}
