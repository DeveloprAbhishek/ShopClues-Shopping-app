package com.example.shopcluesshoppingapp;

public class User {
    String Email, password, mobileNumber, userId;
    int cartTotal, totalItem;
    public User(String email, String password, String mobileNumber, String userId, int cartTotal, int totalItem) {
        this.Email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.userId = userId;
        this.cartTotal = cartTotal;
        this.totalItem = totalItem;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getUserId() {
        return userId;
    }

    public int getCartTotal() {
        return cartTotal;
    }

    public int getTotalItem() {
        return totalItem;
    }
}
