package com.brogrammers.jonosokti.bean;

import java.io.Serializable;

public class SubCategory implements Serializable {
    String subCatName,categoryName,categoryId,photoLink,documentId;
    String minRange,maxRange;

    public SubCategory() {
    }

    public SubCategory(String subCatName, String categoryName, String categoryId, String photoLink, String documentId, String minRange, String maxRange) {
        this.subCatName = subCatName;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.photoLink = photoLink;
        this.documentId = documentId;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getMinRange() {
        return minRange;
    }

    public void setMinRange(String minRange) {
        this.minRange = minRange;
    }

    public String getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(String maxRange) {
        this.maxRange = maxRange;
    }
}
