package com.example.shopcluesshoppingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private ImageView mSubCategoryImage1, mSubCategoryImage2, mSubCategoryImage3, mSubCategoryImage4, mSubCategoryImage5, mSubCategoryImage6;
    private TextView mTvMainCategoryTitle, mSubCategoryName1, mSubCategoryName2, mSubCategoryName3, mSubCategoryName4, mSubCategoryName5, mSubCategoryName6;
    CategoryClickListener categoryClickListener;

    public CategoryViewHolder(@NonNull View itemView, CategoryClickListener categoryClickListener) {
        super(itemView);
        this.categoryClickListener = categoryClickListener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvMainCategoryTitle = itemView.findViewById(R.id.tvMainTitle);
        mSubCategoryImage1 = itemView.findViewById(R.id.ivCategorySubImage1);
        mSubCategoryImage2 = itemView.findViewById(R.id.ivCategorySubImage2);
        mSubCategoryImage3 = itemView.findViewById(R.id.ivCategorySubImage3);

        mSubCategoryName1 = itemView.findViewById(R.id.tvCategorySubName1);
        mSubCategoryName2 = itemView.findViewById(R.id.tvCategorySubName2);
        mSubCategoryName3 = itemView.findViewById(R.id.tvCategorySubName3);

    }


    public void setData(CategoryModel model) {
        mTvMainCategoryTitle.setText(model.getMainCategoryName());
        mSubCategoryName1.setText(model.getSubCategoryName1());
        mSubCategoryName2.setText(model.getSubCategoryName2());
        mSubCategoryName3.setText(model.getSubCategoryName3());

        mSubCategoryImage1.setImageResource(model.getSubCategoryImage1());
        mSubCategoryImage2.setImageResource(model.getSubCategoryImage2());
        mSubCategoryImage3.setImageResource(model.getSubCategoryImage3());

        mSubCategoryImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickListener.onClickCategory(model, getAdapterPosition());
            }
        });


        mSubCategoryImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickListener.onClickSecondCategory(model, getAdapterPosition());
            }
        });

        mSubCategoryImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickListener.onClickThirdCategory(model, getAdapterPosition());
            }
        });
    }
}
