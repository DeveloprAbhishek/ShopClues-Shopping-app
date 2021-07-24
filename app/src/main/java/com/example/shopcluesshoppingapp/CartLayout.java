package com.example.shopcluesshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CartLayout extends AppCompatActivity {
    private TextView quantity;
    private TextView minus;
    private TextView plush;
    private ImageView removeButton;
    private TextView continueToShoppingButton;
    private TextView cartTotalPrice;
    private TextView cartTotalOrderValue;
    private TextView cartTotalDiscount;
    private TextView shippingCharge;
    private TextView cartGrandTotal;
    private RelativeLayout cartCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_layout);
        initViews();
        setCartData();
    }

    private void initViews() {
        quantity = findViewById(R.id.quantity);
        minus = findViewById(R.id.quantity_minus);
        plush = findViewById(R.id.quantity_plush);
        removeButton = findViewById(R.id.remove_cart);
        cartTotalPrice = findViewById(R.id.total_price_cart);
        cartTotalOrderValue = findViewById(R.id.total_value_cart);
        cartTotalDiscount = findViewById(R.id.total_discount_cart);
        shippingCharge = findViewById(R.id.shipping_charge_cart);
        cartGrandTotal = findViewById(R.id.grand_total_cart);
        cartCard = findViewById(R.id.cart_card);
        continueToShoppingButton = findViewById(R.id.cart_continue_shopping);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartLayout.this, "Item has been removed from Cart", Toast.LENGTH_SHORT).show();

            }
        });
        continueToShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartLayout.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(quantity.getText().toString()) > 1) {
                    int value = Integer.parseInt(quantity.getText().toString()) - 1;
                    quantity.setText(value + "");

                }
            }
        });
        plush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(quantity.getText().toString()) + 1;
                quantity.setText(value + "");

            }
        });

    }

    public void setCartData() {
        int deliveryCharge = 0;
        int totalPrice = Integer.parseInt(quantity.getText().toString()) * Integer.parseInt(cartTotalPrice.getText().toString());
        int totalOrderValue = Integer.parseInt(quantity.getText().toString()) * Integer.parseInt(cartTotalOrderValue.getText().toString());
        int discountValue = Integer.parseInt(quantity.getText().toString()) * Integer.parseInt(cartTotalDiscount.getText().toString());
        String shippingValue = shippingCharge.getText().toString();
        if (shippingValue.endsWith("Free")) {
            shippingCharge.setText("Free");
        } else {
            int charge = Integer.parseInt(shippingValue) * Integer.parseInt(quantity.getText().toString());
            deliveryCharge = charge;
            shippingCharge.setText(charge);
        }
        int grandTotal = (totalOrderValue - discountValue) + deliveryCharge;
        cartTotalPrice.setText(totalPrice + " Rs");
        cartTotalOrderValue.setText(totalOrderValue + " Rs");
        cartTotalDiscount.setText(discountValue + " Rs");
        cartGrandTotal.setText(grandTotal + " Rs");

    }
}