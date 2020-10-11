package com.brogrammers.jonosokti.bean.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "cart_table")
public class Cart {

    @PrimaryKey
    private @NotNull String  subCatId;
    private String subCatName;
    private String categoryName,categoryId;
    private String providerName,providerCompany,providerPic;
    @ColumnInfo(name = "providerUid")
    private String providerUid;
    private String productSize;
    private int quantity;
    private double serviceFee;

    public Cart(@NotNull String subCatId, String subCatName, String categoryName, String categoryId, String providerName, String providerCompany, String providerPic, String providerUid, String productSize, int quantity, double serviceFee) {
        this.subCatId = subCatId;
        this.subCatName = subCatName;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.providerName = providerName;
        this.providerCompany = providerCompany;
        this.providerPic = providerPic;
        this.providerUid = providerUid;
        this.productSize = productSize;
        this.quantity = quantity;
        this.serviceFee = serviceFee;
    }

    @NotNull
    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(@NotNull String subCatId) {
        this.subCatId = subCatId;
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

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderCompany() {
        return providerCompany;
    }

    public void setProviderCompany(String providerCompany) {
        this.providerCompany = providerCompany;
    }

    public String getProviderPic() {
        return providerPic;
    }

    public void setProviderPic(String providerPic) {
        this.providerPic = providerPic;
    }

    public String getProviderUid() {
        return providerUid;
    }

    public void setProviderUid(String providerUid) {
        this.providerUid = providerUid;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }
}
