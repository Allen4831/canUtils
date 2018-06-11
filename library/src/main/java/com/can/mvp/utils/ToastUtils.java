package com.can.mvp.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.can.mvp.R;

/**
 * Created by can on 2018/6/11.
 * 吐司工具类
 */

public class ToastUtils {

    public static ToastUtils toastUtils;

    private Toast toast;
    private static Context context;

    public static ToastUtils getInstance(Context mContext){
        if(toastUtils==null)
            toastUtils = new ToastUtils();
        if(context==null)
            context = mContext;
        return toastUtils;
    }

    /**
     * 吐司纯文本
     * @param showText 文本
     * @return
     */
    public  boolean showText(String showText){
        boolean isEmpty = true;
            if(!StringUtils.isEmpty(showText)&&context!=null){
                View view = LayoutInflater.from(context).inflate(R.layout.layout_toast_text, null);
                TextView tv =  view.findViewById(R.id.tv_toast);
                tv.setText(showText);
                if(toast!=null)
                    toast.cancel();
                toast = new Toast(context);
                toast.setGravity(Gravity.BOTTOM, 0, 35); // Toast显示的位置
                toast.setDuration(Toast.LENGTH_SHORT); // Toast显示的时间
                toast.setView(view);
                toast.show();
            }
        return isEmpty;
    }

}
