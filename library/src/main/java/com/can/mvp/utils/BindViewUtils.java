package com.can.mvp.utils;

import android.app.Activity;
import android.view.View;

import com.can.mvp.views.BindView;


/**
 * Created by CAN on 18-8-22.
 */

public class BindViewUtils {

    private static final String SUFFIX = "$$BindView";

    public static void bindView(Activity activity) {
    }

    public static void bindView(Object object, View view) {
        BindView proxyActivity = findProxyActivity(object);
    }

    private static BindView findProxyActivity(Object activity) {
        try {
            Class clazz = activity.getClass();
            Class bindViewClaszz = Class.forName(clazz.getName() + SUFFIX);
            return (BindView) bindViewClaszz.newInstance();
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
