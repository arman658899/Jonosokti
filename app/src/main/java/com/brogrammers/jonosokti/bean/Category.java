package com.brogrammers.jonosokti.bean;

import java.io.Serializable;

public class Category implements Serializable {
    String categoryName,imageLink,categoryId;
    int viewed;

    public Category() {
    }

    public Category(String categoryName, String imageLink, String categoryId, int viewed) {
        this.categoryName = categoryName;
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.viewed = viewed;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
