package com.janta.shopcluesshoppingapp;

public interface CartItemClickListener {
    void onClickCloseIcon(CartModel model, int items);
    void onClickQtyButtons(int cartTotal, CartModel model);
}
