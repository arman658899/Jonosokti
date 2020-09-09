package com.brogrammers.jonosokti.bean;

import java.io.Serializable;

public class Category implements Serializable {
    String categoryName,imageLink,categoryId;

    public Category() {
    }

    public Category(String categoryName, String imageLink, String categoryId) {
        this.categoryName = categoryName;
        this.imageLink = imageLink;
        this.categoryId = categoryId;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
