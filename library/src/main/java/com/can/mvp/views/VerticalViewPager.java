package com.can.mvp.views;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by can on 2018/5/16.
 * 竖直viewpager+自动滚动+无限滚动+禁止手动滚动
 */

public class VerticalViewPager extends ViewPager {

    //是否可以手动滑动
    private boolean enabled = false;

    private int scroll_duration = 2000; //滑动时长
    private int switch_duration = 1000; //切换时长

    private PagerAdapter adapter;

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setMyAdapter(PagerAdapter adapter){
        if(adapter!=null){
            this.adapter = adapter;
            handler.sendEmptyMessageDelayed(1,scroll_duration);
        }
    }

    public void setSwitchDuration(int duration){
        this.switch_duration = duration;
    }

    public void setScrollDuration(int duration){
        this.scroll_duration = duration;
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    setAdapter(adapter);
                    handler.removeMessages(2);
                    handler.sendEmptyMessageDelayed(2,scroll_duration);
                    break;
                case 2:
                    handler.removeMessages(2);
                    setCurrentItem(getCurrentIndex());
                    handler.sendEmptyMessageDelayed(2,scroll_duration);
                    break;
            }
            return false;
        }
    });

    //获取当前下标
    private int getCurrentIndex(){
        return getCurrentItem()+1>=getAdapter().getCount()?0:getCurrentItem()+1;
    }

    private void init() {
        // 设置pageTransformer为竖直滚动
        setPageTransformer(true, new VerticalPageTransformer());
        // 设置滚动模式
        setOverScrollMode(OVER_SCROLL_NEVER);
        setScrollDuration();
    }

    /**
     * 设置滚动时间
     */
    private void setScrollDuration() {
        try {
            // 通过class文件获取mScroller属性
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            FixedSpeedScroller mScroller = new FixedSpeedScroller(this.getContext(),new AccelerateInterpolator());
            mField.set(this, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换viewpager时，设置它的时长
     */
    public class FixedSpeedScroller extends Scroller {
        public FixedSpeedScroller(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, switch_duration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, switch_duration);
        }
    }

    //竖直方向
    private class VerticalPageTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View view, float position) {
            if (position < -1) {
                view.setAlpha(0);
            } else if (position <= 1) {
                view.setAlpha(1);
                // 抵消默认滑动
                view.setTranslationX(view.getWidth() * -position);
                //设置Y位置从顶部滑入
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);
            } else {
                view.setAlpha(0);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //不拦截触摸事件,由上层处理
        if(enabled)
            return super.onTouchEvent(ev);
        else
            return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //不拦截分发事件，由子控件分发处理
        if(enabled)
            return super.onInterceptTouchEvent(ev);
        else
            return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //分发事件，必须要，否则子view收不到事件
        return super.dispatchTouchEvent(ev);
    }

    //是否可以手动滑动
    public void setCanScroll(boolean enabled){
        this.enabled = enabled;
    }

}

