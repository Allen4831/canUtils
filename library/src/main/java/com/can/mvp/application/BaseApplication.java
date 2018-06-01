package com.can.mvp.application;

import android.app.Application;

import com.can.mvp.utils.ActivityManagerUtils;

/**
 * Created by can on 2018/3/2.
 * Application
 */

public class BaseApplication extends Application {

    private static BaseApplication mInstance;
    private static ActivityManagerUtils activityManager = null;

    public BaseApplication() {

    }

    //Application 单例
    public static BaseApplication getInstance(){
        if(mInstance == null){
            mInstance = new BaseApplication();
        }
        return mInstance;
    }

    // ActivityManagerUtils 单例
    public static ActivityManagerUtils getActivityManager() {
        if(activityManager==null)
            activityManager = ActivityManagerUtils.getInstance();
        return activityManager;
    }

    public static void exit() {
        activityManager.finishAllActivity();
    }

}
