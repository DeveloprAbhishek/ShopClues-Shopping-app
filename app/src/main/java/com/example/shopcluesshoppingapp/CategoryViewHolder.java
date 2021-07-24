package com.example.shopcluesshoppingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private ImageView mSubCategoryImage1, mSubCategoryImage2, mSubCategoryImage3, mSubCategoryImage4, mSubCategoryImage5, mSubCategoryImage6;
    private TextView mTvMainCategoryTitle, mSubCategoryName1, mSubCategoryName2, mSubCategoryName3, mSubCategoryName4, mSubCategoryName5, mSubCategoryName6;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvMainCategoryTitle = itemView.findViewById(R.id.tvMainTitle);
        mSubCategoryImage1 = itemView.findViewById(R.id.ivCategorySubImage1);
        mSubCategoryImage2 = itemView.findViewById(R.id.ivCategorySubImage2);
        mSubCategoryImage3 = itemView.findViewById(R.id.ivCategorySubImage3);
        mSubCategoryImage4 = itemView.findViewById(R.id.ivCategorySubImage4);
        mSubCategoryImage5 = itemView.findViewById(R.id.ivCategorySubImage5);
        mSubCategoryImage6 = itemView.findViewById(R.id.ivCategorySubImage6);

        mSubCategoryName1 = itemView.findViewById(R.id.tvCategorySubName1);
        mSubCategoryName2 = itemView.findViewById(R.id.tvCategorySubName2);
        mSubCategoryName3 = itemView.findViewById(R.id.tvCategorySubName3);
        mSubCategoryName4 = itemView.findViewById(R.id.tvCategorySubName4);
        mSubCategoryName5 = itemView.findViewById(R.id.tvCategorySubName5);
        mSubCategoryName6 = itemView.findViewById(R.id.tvCategorySubName6);

    }


    public void setData(CategoryModel model) {
        mTvMainCategoryTitle.setText(model.getMainCategoryName());
        mSubCategoryName1.setText(model.getSubCategoryName1());
        mSubCategoryName2.setText(model.getSubCategoryName2());
        mSubCategoryName3.setText(model.getSubCategoryName3());
        mSubCategoryName4.setText(model.getSubCategoryName4());
        mSubCategoryName5.setText(model.getSubCategoryName5());
        mSubCategoryName6.setText(model.getSubCategoryName6());


        mSubCategoryImage1.setImageResource(model.getSubCategoryImage1());
        mSubCategoryImage2.setImageResource(model.getSubCategoryImage2());
        mSubCategoryImage3.setImageResource(model.getSubCategoryImage3());
        mSubCategoryImage4.setImageResource(model.getSubCategoryImage4());
        mSubCategoryImage5.setImageResource(model.getSubCategoryImage5());
        mSubCategoryImage6.setImageResource(model.getSubCategoryImage6());
    }
}
