package com.example.shopcluesshoppingapp;

public class CartModel {
    String title, image, key;
    int price;

    public CartModel() {
    }

    public CartModel(String title, String image, int price, String key) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getKey() {
        return key;
    }
}
