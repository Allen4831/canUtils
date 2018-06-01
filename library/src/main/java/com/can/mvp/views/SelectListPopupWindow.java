package com.can.mvp.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import com.can.mvp.R;

/**
 * Created by can on 2018/5/17.
 * 下拉选择弹窗
 */

public class SelectListPopupWindow extends ListPopupWindow {

    private Drawable drawable ;
    private int width;

    public SelectListPopupWindow(Context context) {
        super(context);
        init(context);
    }

    public SelectListPopupWindow(Context context,Drawable drawable,int width){
        super(context);
        this.width = width;
        this.drawable = drawable;
        init(context);
    }

    private void init(Context context) {
        if(drawable==null)
        drawable = ContextCompat.getDrawable(context, R.drawable.drawable_white_5dp);
        if(width==0&&context instanceof Activity){
            Activity activity = (Activity) context;
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            width = metrics.widthPixels/3;
        }
        setBackgroundDrawable(drawable);
        setWidth(width);
    }

    public SelectListPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }



}
