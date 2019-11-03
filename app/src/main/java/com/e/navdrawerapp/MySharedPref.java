package com.e.navdrawerapp;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPref {

    private static final String PREF_NAME = "app_shared_pref";
    private SharedPreferences sharedPreferences;

    public MySharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void addString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);

        editor.apply();
    }

    public void addBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);

        editor.apply();
    }
}
