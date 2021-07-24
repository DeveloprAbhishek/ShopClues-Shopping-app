package com.example.shopcluesshoppingapp;

public class OffersModel {
    String image, title;
    int price, maxPrice, offer;
    double rating;

    public OffersModel() {
    }

    public OffersModel(String image, String title, int price, int maxPrice, int offer, double rating) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.maxPrice = maxPrice;
        this.offer = offer;
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getOffer() {
        return offer;
    }

    public double getRating() {
        return rating;
    }
}
