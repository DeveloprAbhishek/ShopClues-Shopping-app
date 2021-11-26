package com.example.shopcluesshoppingapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


public class OrderViewHolder extends RecyclerView.ViewHolder{
    private OrderClickListener orderClickListener;
    private ImageView mIvOrderImage;
    private TextView mTvOrderTitle, mTvOrderPrice;
    private Button mBtnCancel;
    public OrderViewHolder(@NonNull View itemView, OrderClickListener orderClickListener) {
        super(itemView);
        this.orderClickListener = orderClickListener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mIvOrderImage = itemView.findViewById(R.id.ivOrderImage);
        mTvOrderPrice = itemView.findViewById(R.id.tvOrderPrice);
        mTvOrderTitle = itemView.findViewById(R.id.tvOrderTitle);
        mBtnCancel = itemView.findViewById(R.id.btnCancel);



    }

    public void setData(OrderModel model) {
        Glide.with(mIvOrderImage).load(model.getImage()).into(mIvOrderImage);
        mTvOrderTitle.setText(model.getTitle());
        mTvOrderPrice.setText("₹" + model.getPrice());

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderClickListener.onCancelButtonClick(model);
            }
        });
    }
}
