package com.can.mvp.utils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.can.mvp.R;

/**
 * Created by CAN on 18-8-17.
 * 弹窗辅助类
 */

public class WindowUtils {

    private static View mView = null;
    private static WindowManager mWindowManger = null;
    private static Context mContext = null;
    private static Boolean mIsShown = false;

    public static void showSuspensionWindow(Context context){
        if(mIsShown)return;
        mContext =  context.getApplicationContext();
        mView = setUpView(mContext);
        mWindowManger = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.format = PixelFormat.TRANSLUCENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.LEFT;
        mWindowManger.addView(mView,params);
        mIsShown = true;
    }

    public static void hideSuspensionWindow(){
        if(mView!=null&&mIsShown){
            mWindowManger.removeView(mView);
            mIsShown = false;
        }
    }

    private static View setUpView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_suspension_window,null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSuspensionWindow();
            }
        });
        return view;
    }



}
