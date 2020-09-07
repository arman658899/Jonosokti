package com.brogrammers.jonosokti.bean;

import java.io.Serializable;

public class Product implements Serializable {
    String productName;

    public Product() {
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
