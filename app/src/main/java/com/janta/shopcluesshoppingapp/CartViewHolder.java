package com.janta.shopcluesshoppingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.janta.shopcluesshoppingapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartViewHolder extends RecyclerView.ViewHolder {
    private ImageView mIvProductItemImage;
    private TextView mTvItemTitle, mTvItemPrice;

    private ImageView mIvDeleteItem;
    private TextView mTvDecreaseQty, mTvIncreaseQty, mTvShowQty;
    private CartItemClickListener cartItemClickListener;

    private FirebaseAuth mAuth;
    private String userId;
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
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getUid();
    }

    void setData(CartModel model, int position) {
        Glide.with(mIvProductItemImage).load(model.getImage()).into(mIvProductItemImage);
        mTvItemTitle.setText(model.getTitle());;
        mTvItemPrice.setText("₹"+model.getPrice());
        int count = 0;
        mIvDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int items = Integer.parseInt(mTvShowQty.getText().toString());
                cartItemClickListener.onClickCloseIcon(model, items);
            }
        });

        mTvIncreaseQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(mTvShowQty.getText().toString());
                mTvShowQty.setText((qty+1)+"");
                mTvItemPrice.setText("₹"+(qty+model.getPrice()));

                DatabaseReference cartTotalRef = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("cartTotal");
                DatabaseReference cartTotalItemRef = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("totalItem");

                cartTotalRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        int cartTotal = dataSnapshot.getValue(int.class);
                        int totalAMount = cartTotal + model.getPrice();
                        cartTotalRef.setValue(totalAMount);
                        cartItemClickListener.onClickQtyButtons(totalAMount, model);
                    }
                });

                cartTotalItemRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        int cartTotalItem = dataSnapshot.getValue(int.class);
                        int totalItem = cartTotalItem + 1;
                        cartTotalItemRef.setValue(totalItem);
                    }
                });


            }
        });

        mTvDecreaseQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(mTvShowQty.getText().toString());
                if(qty>1) mTvShowQty.setText((qty-1)+"");
                mTvItemPrice.setText("₹"+(qty*model.getPrice()));
            }
        });

    }

    void getUserCartGrandTotal() {


    }
}
