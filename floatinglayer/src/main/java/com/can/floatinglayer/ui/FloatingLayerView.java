package com.can.floatinglayer.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.can.floatinglayer.R;

/**
 * Created by CAN on 18-8-28.
 * 浮层View
 */

public class FloatingLayerView extends FrameLayout {

    public FloatingLayerView(Context context,int imgRes){
        super(context);
        init(context,imgRes);
    }

    private void init(Context context, int imgRes) {
        LayoutInflater.from(context).inflate(R.layout.layout_floatinglayer, this, true);
        ImageView iv = findViewById(R.id.iv);
        iv.setImageResource(imgRes);
    }

    public FloatingLayerView(@NonNull Context context) {
        super(context);
    }

    public FloatingLayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingLayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    static class FloatingLayer{
        public static void add2Activity(Activity activity,int imgRes){
            FrameLayout fl = activity.findViewById(android.R.id.content);
            FrameLayout fb = new FloatingLayerView(activity,imgRes);
            LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            fb.setLayoutParams(layoutParams);
            fl.addView(fb);
        }
    }
}
