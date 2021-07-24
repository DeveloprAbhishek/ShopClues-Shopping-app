package com.example.shopcluesshoppingapp;

public class CartModel {
    String title, image;
    int price;

    public CartModel() {
    }

    public CartModel(String title, String image, int price) {
        this.title = title;
        this.image = image;
        this.price = price;
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
}
