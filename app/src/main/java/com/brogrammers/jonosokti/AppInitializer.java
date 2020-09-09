package com.brogrammers.jonosokti;

import android.app.Application;

import com.brogrammers.jonosokti.helpers.ApplicationHelper;

public class AppInitializer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationHelper.initialize(this);

    }
}
