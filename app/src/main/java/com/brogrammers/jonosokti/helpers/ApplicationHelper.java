package com.brogrammers.jonosokti.helpers;

import android.app.Application;

public class ApplicationHelper {
    public static DatabaseInitializer database;
    private static UtilsHelper utilsHelper;
    public static void initialize(Application application){
        database = DatabaseInitializer.getInstance(application);
        database.init();
        utilsHelper = UtilsHelper.getInstance(application);

    }

    public static DatabaseInitializer getDatabase(){
        return database;
    }

    public static UtilsHelper getUtilsHelper(){
        return utilsHelper;
    }
}
