package com.can.mvp.utils;

import android.app.Activity;
import android.os.Process;

import java.util.Iterator;
import java.util.Stack;

/**
 * Activity管理工具类
 */
public class ActivityManagerUtil {
    private static Stack<Activity> activityStack;
    private static ActivityManagerUtil instance;

    private ActivityManagerUtil() {
    }

    public static ActivityManagerUtil getInstance() {
        if(instance == null) {
            instance = new ActivityManagerUtil();
        }
        if(activityStack == null) {
            activityStack = new Stack();
        }
        return instance;
    }

    //获取Activity
    public static Activity getActivity(Class<?> cls) {
        if(activityStack != null) {
            Iterator var1 = activityStack.iterator();
            while(var1.hasNext()) {
                Activity activity = (Activity)var1.next();
                if(activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }

        return null;
    }

    //添加Activity
    public void addActivty(Activity activity) {
        activityStack.add(activity);
    }

    //当前Activity
    public Activity currentActivity() {
        Activity activity = (Activity)activityStack.lastElement();
        return activity;
    }

    //结束Activity
    public void finishActivity() {
        Activity activity = (Activity)activityStack.lastElement();
        this.finishActivity(activity);
    }

    //结束Activity
    public void finishActivity(Activity activity) {
        if(activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    //移除Activity
    public void removeActivity(Activity activity) {
        if(activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
        }

    }

    //结束Activity
    public void finishActivity(Class<?> cls) {
        Iterator var2 = activityStack.iterator();

        while(var2.hasNext()) {
            Activity activity = (Activity)var2.next();
            if(activity.getClass().equals(cls)) {
                this.finishActivity(activity);
                break;
            }
        }

    }

    //结束所有Activity
    public void finishAllActivity() {
        int i = 0;
        for(int size = activityStack.size(); i < size; ++i) {
            if(null != activityStack.get(i)) {
                this.finishActivity((Activity)activityStack.get(i));
            }
        }
        activityStack.clear();
    }

    //退出App
    public void AppExit() {
        try {
            this.finishAllActivity();
            Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (Exception var2) {
            ;
        }

    }
}
