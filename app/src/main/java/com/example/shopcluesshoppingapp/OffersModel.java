package com.example.shopcluesshoppingapp;

public class OffersModel {
    int imageUrl, off, price;
    String offerTitle;
    double rating;

    public OffersModel(int imageUrl, String offerTitle, int off, int price, double rating) {
        this.imageUrl = imageUrl;
        this.off = off;
        this.price = price;
        this.offerTitle = offerTitle;
        this.rating = rating;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public int getOff() {
        return off;
    }

    public int getPrice() {
        return price;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public double getRating() {
        return rating;
    }
}
