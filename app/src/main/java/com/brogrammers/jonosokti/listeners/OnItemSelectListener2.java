package com.brogrammers.jonosokti.listeners;

import com.brogrammers.jonosokti.bean.Category;

import org.jetbrains.annotations.NotNull;

public interface OnItemSelectListener2<T> {
    void onItemSelected2(T t, int index);
    void onItemSelected2(@NotNull Category category);
}
