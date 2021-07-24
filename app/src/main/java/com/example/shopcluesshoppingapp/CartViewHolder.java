package com.example.shopcluesshoppingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class CartViewHolder extends RecyclerView.ViewHolder {
    private ImageView mIvProductItemImage;
    private TextView mTvItemTitle, mTvItemPrice;

    private ImageView mIvDeleteItem;
    private TextView mTvDecreaseQty, mTvIncreaseQty, mTvShowQty;
    private CartItemClickListener cartItemClickListener;
    public CartViewHolder(@NonNull View itemView, CartItemClickListener cartItemClickListener) {
        super(itemView);
        this.cartItemClickListener = cartItemClickListener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mIvProductItemImage = itemView.findViewById(R.id.ivProductImage);
        mTvItemTitle = itemView.findViewById(R.id.tvProductTitle);
        mTvItemPrice =  itemView.findViewById(R.id.tvProductPrice);

        mIvDeleteItem = itemView.findViewById(R.id.ivDeCartItem);
        mTvDecreaseQty = itemView.findViewById(R.id.tvDecreaseQty);
        mTvIncreaseQty = itemView.findViewById(R.id.tvIncreaseQty);
        mTvShowQty = itemView.findViewById(R.id.tvShowQty);


    }

    void setData(CartModel model, int position) {
        Glide.with(mIvProductItemImage).load(model.getImage()).into(mIvProductItemImage);
        mTvItemTitle.setText(model.getTitle());;
        mTvItemPrice.setText("₹"+model.getPrice());

        mIvDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItemClickListener.onClickCloseIcon(position);
            }
        });

    }
}
