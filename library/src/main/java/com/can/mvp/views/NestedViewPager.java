package com.can.mvp.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.can.mvp.R;


public class NestedViewPager extends ViewPager {

    private boolean mIsScrollable;

    public NestedViewPager(Context context) {
        this(context, null);
    }

    public NestedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NestedViewPager);
        mIsScrollable = a.getBoolean(R.styleable.NestedViewPager_scrollable, true);

        a.recycle();
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mIsScrollable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.mIsScrollable && super.onInterceptTouchEvent(ev);
    }

}