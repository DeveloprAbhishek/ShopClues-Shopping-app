package com.example.shopcluesshoppingapp;

public interface CartItemClickListener {
    void onClickCloseIcon(int Position);
    void onClickQtyButtons(int position, CartModel model);
}
