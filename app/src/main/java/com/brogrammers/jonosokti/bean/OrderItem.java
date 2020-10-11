package com.brogrammers.jonosokti.bean;

public class OrderItem {
    private String subcatName;
    private int quantity;
    private double fee;

    public OrderItem(String subcatName, int quantity, double fee) {
        this.subcatName = subcatName;
        this.quantity = quantity;
        this.fee = fee;
    }

    public String getSubcatName() {
        return subcatName;
    }

    public void setSubcatName(String subcatName) {
        this.subcatName = subcatName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
