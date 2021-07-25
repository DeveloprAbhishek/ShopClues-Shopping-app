package com.example.shopcluesshoppingapp;

public interface CartItemClickListener {
    void onClickCloseIcon(int Position, CartModel model, String key);
    void onClickQtyButtons(int position, CartModel model);
}
