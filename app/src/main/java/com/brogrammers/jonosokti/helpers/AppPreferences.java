package com.brogrammers.jonosokti.helpers;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    /*private Context context;
    private static AppPreferences instance;

    private AppPreferences(Context context){
        this.context = context;
    }

    public static AppPreferences getInstance(Application application){
        if (instance==null) instance = new AppPreferences(application);
        return instance;
    }*/

    public static final String PREFERENCE_DATABASE = "jonosokti_client";
    public static final String FIELD_LOCATION = "client_location";

    public static void setUserLocationName(Context context, String location){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(FIELD_LOCATION,location);
        editor.apply();
    }

    public static String getUserLocationName(Context context){
        return context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE).getString(FIELD_LOCATION,"");
    }


    public static class UserInfo{
        public static final String USER_NAME = "user_name";
        public static final String USER_MOB = "user_mob";
        public static final String USER_ADDRESS = "user_address";
        public static final String USER_FLAT = "user_flat";
        public static final String USER_ADDRESS_TYPE = "user_address_type";

        public static void setUserName(Context context, String userName){
            SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(USER_NAME,userName);
            editor.apply();
        }
        public static void setUserMob(Context context, String userName){
            SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(USER_MOB,userName);
            editor.apply();
        }
        public static void setUserAddress(Context context, String userName){
            SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(USER_ADDRESS,userName);
            editor.apply();
        }
        public static void setUserFlat(Context context, String userName){
            SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(USER_FLAT,userName);
            editor.apply();
        }
        public static void setUserAddressType(Context context, String userName){
            SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(USER_ADDRESS_TYPE,userName);
            editor.apply();
        }

        public static String getUserName(Context context){
            return context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE).getString(USER_NAME,"");
        }
        public static String getUserMob(Context context){
            return context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE).getString(USER_MOB,"");
        }
        public static String getUserAddress(Context context){
            return context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE).getString(USER_ADDRESS,"");
        }
        public static String getUserFlat(Context context){
            return context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE).getString(USER_FLAT,"");
        }
        public static String getUserAddressType(Context context){
            return context.getSharedPreferences(PREFERENCE_DATABASE,Context.MODE_PRIVATE).getString(USER_ADDRESS_TYPE,"");
        }

    }


}
