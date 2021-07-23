package com.example.shopcluesshoppingapp;

public class CategoryModel {
    private String mainCategoryName, subCategoryName1, subCategoryName2, subCategoryName3, subCategoryName4, subCategoryName5, subCategoryName6;
    private int subCategoryImage1, subCategoryImage2, subCategoryImage3, subCategoryImage4,subCategoryImage5 ,subCategoryImage6;


    public CategoryModel(String mainCategoryName, String subCategoryName1, String subCategoryName2, String subCategoryName3,
                         String subCategoryName4, String subCategoryName5, String subCategoryName6, int subCategoryImage1,
                         int subCategoryImage2, int subCategoryImage3, int subCategoryImage4, int subCategoryImage5, int subCategoryImage6) {
        this.mainCategoryName = mainCategoryName;
        this.subCategoryName1 = subCategoryName1;
        this.subCategoryName2 = subCategoryName2;
        this.subCategoryName3 = subCategoryName3;
        this.subCategoryName4 = subCategoryName4;
        this.subCategoryName5 = subCategoryName5;
        this.subCategoryName6 = subCategoryName6;
        this.subCategoryImage1 = subCategoryImage1;
        this.subCategoryImage2 = subCategoryImage2;
        this.subCategoryImage3 = subCategoryImage3;
        this.subCategoryImage4 = subCategoryImage4;
        this.subCategoryImage5 = subCategoryImage5;
        this.subCategoryImage6 = subCategoryImage6;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public String getSubCategoryName1() {
        return subCategoryName1;
    }

    public String getSubCategoryName2() {
        return subCategoryName2;
    }

    public String getSubCategoryName3() {
        return subCategoryName3;
    }

    public String getSubCategoryName4() {
        return subCategoryName4;
    }

    public String getSubCategoryName5() {
        return subCategoryName5;
    }

    public String getSubCategoryName6() {
        return subCategoryName6;
    }

    public int getSubCategoryImage1() {
        return subCategoryImage1;
    }

    public int getSubCategoryImage2() {
        return subCategoryImage2;
    }

    public int getSubCategoryImage3() {
        return subCategoryImage3;
    }

    public int getSubCategoryImage4() {
        return subCategoryImage4;
    }

    public int getSubCategoryImage5() {
        return subCategoryImage5;
    }

    public int getSubCategoryImage6() {
        return subCategoryImage6;
    }
}
