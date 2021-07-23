package com.example.shopcluesshoppingapp;

public class ProductModel extends LayoutBaseModel {
    public static final int TYPE_PRODUCT = 1;

    private int image;
    private String name;
    private String description;
    private String price;
    private String actualPrice;
    private String discount;


    public ProductModel(int image, String name, String description, String price, String actualPrice, String discount) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.actualPrice = actualPrice;
        this.discount = discount;

    }


    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getActualPrice() {
        return actualPrice;
    }


    public String getDiscount() {
        return discount;
    }

    @Override
    int getViewType() {
        return TYPE_PRODUCT;
    }

    public String getDescription() {
        return description;
    }
}
