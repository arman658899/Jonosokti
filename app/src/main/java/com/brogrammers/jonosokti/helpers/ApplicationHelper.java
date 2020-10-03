package com.brogrammers.jonosokti.helpers;

import android.app.Application;

public class ApplicationHelper {
    public static DatabaseInitializer database;

    public static void initialize(Application application){
        database = DatabaseInitializer.getInstance(application);
        database.init();
    }

    public static DatabaseInitializer getDatabase(){
        return database;
    }


}
