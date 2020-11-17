package com.lenovo.smarttraffic;


import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author Amoly
 * @date 2019/4/11.
 * email：caoxl@lenovo.com
 * description：
 */
public class InitApp extends MultiDexApplication {

    private static Handler mainHandler;
    private static Context AppContext;
    private static InitApp instance;
    private static RequestQueue queue;
    public static synchronized InitApp getInstance() {
        return instance;
    }

    public static RequestQueue getQueue() {
        return queue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        AppContext = this;
        instance = this;
        mainHandler = new Handler();
        queue = Volley.newRequestQueue(this);

    }

    public static Context getContext(){
        return AppContext;
    }
    public static Handler getHandler(){
        return mainHandler;
    }

}
