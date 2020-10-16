package com.brogrammers.jonosokti.listeners;

public interface OnItemSelectListener<T> {
    void onItemSelected(T t);
    void onDelete(T t);
    void onCancel(T t);
    void onApprove(T t);
}
