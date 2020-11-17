package com.lenovo.smarttraffic.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.lenovo.smarttraffic.InitApp;
import com.lenovo.smarttraffic.bean.UrlBean;
import com.lenovo.smarttraffic.bean.User;

public class Util {
    private String urlhttp;
    private String porthttp;

    public static void saveSetting(String urlhttp, String porthttp) {
        SharedPreferences.Editor editor = InitApp.getInstance().getSharedPreferences("setting", Context.MODE_PRIVATE).edit();
        editor.putString("urlhttp", urlhttp);
        editor.putString("porthttp", porthttp);
        editor.apply();
    }

    public static UrlBean getSetting() {
        SharedPreferences preferences = InitApp.getInstance().getSharedPreferences("setting", Context.MODE_PRIVATE);
        return new UrlBean(preferences.getString("urlhttp", "172.20.10.2"), preferences.getString("porthttp", "8088"));
    }

    public static void setUser(String userName, String userRole) {
        SharedPreferences.Editor editor = InitApp.getInstance().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
        editor.putString("userName", userName);
        editor.putString("userRole", userRole);
        editor.apply();
    }

    public static User getUser() {
        SharedPreferences preferences = InitApp.getInstance().getSharedPreferences("user", Context.MODE_PRIVATE);
        return new User(preferences.getString("userName", "user1"), preferences.getString("userRole", "R02"));
    }


}
