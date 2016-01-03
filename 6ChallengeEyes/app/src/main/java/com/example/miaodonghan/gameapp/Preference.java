package com.example.miaodonghan.gameapp;

import android.content.Context;
import android.content.SharedPreferences;


public class Preference {
    private static Preference pre;
    private SharedPreferences sharedPreferences;

    public static Preference getInstance(Context context) {
        if (pre == null) {
            pre = new Preference(context);
        }
        return pre;
    }

    private Preference(Context context) {
        sharedPreferences = context.getSharedPreferences("record",Context.MODE_PRIVATE);
    }

    public void saveData(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor .putString(key, value);
        prefsEditor.commit();
    }

    public String getData(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }
}
