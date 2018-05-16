package com.can.canutils.application;

import android.app.Application;

import com.can.mvp.utils.ActivityManagerUtil;

/**
 * Created by can on 2018/3/2.
 * Application
 */

public class MyApplication extends Application {

    private static com.can.mvp.application.MyApplication mInstance;
    private static ActivityManagerUtil activityManager = null;

    public MyApplication() {

    }

    public static Application getInstance(){
        if(mInstance == null){
            mInstance = new com.can.mvp.application.MyApplication();
        }
        return mInstance;
    }

    public static ActivityManagerUtil getActivityManager() {
        if(activityManager==null)
            activityManager = ActivityManagerUtil.getInstance();
        return activityManager;
    }

    public static void exit() {
        activityManager.finishAllActivity();
    }

}
