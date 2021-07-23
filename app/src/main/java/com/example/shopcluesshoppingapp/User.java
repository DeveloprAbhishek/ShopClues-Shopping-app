package com.example.shopcluesshoppingapp;

public class User {
    String Email, password, mobileNumber;

    public User(String email, String password, String mobileNumber) {
        Email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
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
}
