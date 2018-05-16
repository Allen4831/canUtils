package com.can.mvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by can on 2018/3/9.
 * 网络工具类
 */

public class NetWorkUtil {

    public static boolean isNetWork(Context context){
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService("connectivity");
        if(manager != null) {
            NetworkInfo network = manager.getActiveNetworkInfo();
            if(network != null && network.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

}
