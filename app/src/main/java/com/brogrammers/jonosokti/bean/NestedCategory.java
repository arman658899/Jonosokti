package com.brogrammers.jonosokti.bean;

import android.widget.ListAdapter;

import java.util.List;

public class NestedCategory {
    String popularCategory;
    List<Product> categories;

    public NestedCategory() {
    }

    public NestedCategory(String popularCategory, List<Product> categories) {
        this.popularCategory = popularCategory;
        this.categories = categories;
    }

    public String getPopularCategory() {
        return popularCategory;
    }

    public void setPopularCategory(String popularCategory) {
        this.popularCategory = popularCategory;
    }

    public List<Product> getCategories() {
        return categories;
    }

    public void setCategories(List<Product> categories) {
        this.categories = categories;
    }
}
