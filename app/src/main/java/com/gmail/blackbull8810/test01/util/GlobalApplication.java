package com.gmail.blackbull8810.test01.util;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class GlobalApplication extends MultiDexApplication {
    private static volatile GlobalApplication instance = null;
    private static volatile Activity currentActivity = null;

    public static GlobalApplication get(Context context){
        return (GlobalApplication) context.getApplicationContext();
    }

    public static void setCurrentActivity(Activity currentActivity) {
        if(currentActivity!=null)
            Logger.log("setting CurrentActivity name -> " + currentActivity.getClass().getName());
        GlobalApplication.currentActivity = currentActivity;
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public static GlobalApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */
    @Override
    public void onTerminate() {
        Logger.log("onTerminate");
        super.onTerminate();
        instance = null;
    }
}
