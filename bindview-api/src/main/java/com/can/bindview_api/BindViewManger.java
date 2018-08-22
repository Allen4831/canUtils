package com.can.bindview_api;

import android.app.Activity;


/**
 * Created by CAN on 18-8-22.
 */

public class BindViewManger {

    private static final String SUFFIX = "$$BindViewInject";

    public static void bindView(Activity activity) {
        BindViewInject proxyActivity = findProxyActivity(activity);
        proxyActivity.bindview(activity, activity);
    }

    private static BindViewInject findProxyActivity(Object activity) {
        try {
            Class clazz = activity.getClass();
            Class bindViewClaszz = Class.forName(clazz.getName() + SUFFIX);
            return (BindViewInject) bindViewClaszz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException(String.format("can not find %s , something when compiler.", activity.getClass().getSimpleName() + SUFFIX));
    }

}
