package com.can.mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;

import com.can.mvp.base.BaseActivity;

import java.util.Iterator;
import java.util.Stack;

/**
 * Activity管理工具类
 */
public class ActivityManagerUtils {
    private static Stack<BaseActivity> activityStack;
    private static ActivityManagerUtils instance;

    private ActivityManagerUtils() {
    }

    public static ActivityManagerUtils getInstance() {
        if(instance == null) {
            instance = new ActivityManagerUtils();
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
                BaseActivity activity = (BaseActivity)var1.next();
                if(activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }

        return null;
    }

    //添加Activity
    public void addActivty(BaseActivity activity) {
        activityStack.add(activity);
    }

    //当前Activity
    public BaseActivity currentActivity() {
        BaseActivity activity = activityStack.lastElement();
        return activity;
    }

    //结束Activity
    public void finishActivity() {
        BaseActivity activity = activityStack.lastElement();
        this.finishActivity(activity);
    }

    //结束Activity
    public void finishActivity(BaseActivity activity) {
        if(activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    //移除Activity
    public void removeActivity(BaseActivity activity) {
        if(activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
        }

    }

    //结束Activity
    public void finishActivity(Class<?> cls) {
        Iterator var2 = activityStack.iterator();

        while(var2.hasNext()) {
            BaseActivity activity = (BaseActivity)var2.next();
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
                this.finishActivity(activityStack.get(i));
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


    //打开activity
    public static void openActivity(Context context, Class<?> tClass, Bundle bundle, int requestCode){
        Intent intent = new Intent(context,tClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        if(requestCode!=0){
            ((Activity)context).startActivityForResult(intent,requestCode);
        }else {
            context.startActivity(intent);
        }
    }

    //打开activity
    public static void openActivity(Context context, Class<?> tClass){
        openActivity(context,tClass,null,0);
    }

    //打开activity
    public static void openActivity(Context context, Class<?> tClass,Bundle bundle){
        openActivity(context,tClass,bundle,0);
    }


}
