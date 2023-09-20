package com.example.javaappversion19.Models;

public class Struct_Categories {
    private int ImageID ;
    private String CategoryName;

    public Struct_Categories(int imageID, String categoryName) {
        ImageID = imageID;
        CategoryName = categoryName;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
