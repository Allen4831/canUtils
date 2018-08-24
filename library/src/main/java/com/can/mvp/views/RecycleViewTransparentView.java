package com.can.mvp.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.can.mvp.R;

/**
 * Created by CAN on 18-8-24.
 * RecycleView can transparent at left or right
 */

public class RecycleViewTransparentView extends RelativeLayout{


    public RecycleViewTransparentView(Context context) {
        super(context);
        init(context);
    }

    public RecycleViewTransparentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RecycleViewTransparentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public RecyclerView recyclerView;
    private View view_left;
    private View view_right;
    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_recycleview_transparent,this);
        recyclerView = this.findViewById(R.id.recyclerview);
        view_left = this.findViewById(R.id.view_transparent_left);
        view_right = this.findViewById(R.id.view_transparent_right);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        recyclerView.onTouchEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }
}
