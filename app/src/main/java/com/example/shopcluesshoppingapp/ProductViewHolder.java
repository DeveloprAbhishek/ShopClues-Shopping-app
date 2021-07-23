package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private ImageView product_image;
    private TextView product_name;
    private TextView product_price;
    private TextView product_actualPrice;
    private TextView product_discount;
    private TextView description;
    private ProductClickListener productClickListener;
    private RelativeLayout productView;

    public ProductViewHolder(@NonNull View itemView, ProductClickListener productClickListener) {
        super(itemView);
        this.productClickListener = productClickListener;
        initView(itemView);
    }

    private void initView(View itemView) {
        product_image = itemView.findViewById(R.id.product_pic);
        product_name = itemView.findViewById(R.id.product_name);
        product_price = itemView.findViewById(R.id.price);
        product_actualPrice = itemView.findViewById(R.id.actual_price);
        product_discount = itemView.findViewById(R.id.discount);
        description = itemView.findViewById(R.id.product_desc);
        productView = itemView.findViewById(R.id.product_card);

    }

    public void setProductData(ProductModel product) {
        product_image.setImageResource(product.getImage());
        product_name.setText(product.getName());
        product_price.setText(product.getPrice());
        product_actualPrice.setText(product.getActualPrice());
        product_discount.setText(product.getDiscount());

        productView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productClickListener.onProductClick(product, getAdapterPosition());

            }
        });
    }
}
