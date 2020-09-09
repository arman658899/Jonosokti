package com.brogrammers.jonosokti.listeners;

import java.util.List;

public interface OnDataDownloadListener<T> {
    void onStarted();
    void onDownloaded(T t);
    void onDownloaded(List<T> list);
    void onFinish();
}
