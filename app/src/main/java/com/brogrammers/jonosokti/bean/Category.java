package com.brogrammers.jonosokti.bean;

import java.io.Serializable;

public class Category implements Serializable {
    String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}