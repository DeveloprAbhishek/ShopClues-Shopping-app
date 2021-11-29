package com.janta.shopcluesshoppingapp;

public interface onWishlistClickListener {
    void onWishlistItemClicked(int Position, OffersModel model, String key);
    void onClickBuyButton(int Position, OffersModel model, String key);
    void onClickHeartIcon(int Position, OffersModel model, String key);
}
