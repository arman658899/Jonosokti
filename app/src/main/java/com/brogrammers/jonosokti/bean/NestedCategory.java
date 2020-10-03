package com.brogrammers.jonosokti.bean;

import android.widget.ListAdapter;

import java.util.List;

public class NestedCategory {
    String popularCategory;
    List<SubCategory> categories;

    public NestedCategory() {
    }

    public NestedCategory(String popularCategory, List<SubCategory> categories) {
        this.popularCategory = popularCategory;
        this.categories = categories;
    }

    public String getPopularCategory() {
        return popularCategory;
    }

    public void setPopularCategory(String popularCategory) {
        this.popularCategory = popularCategory;
    }

    public List<SubCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<SubCategory> categories) {
        this.categories = categories;
    }
}
