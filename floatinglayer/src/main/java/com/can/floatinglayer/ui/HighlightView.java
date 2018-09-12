package com.can.floatinglayer.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.view.View;

class HighlightView {

    //高亮的view
    private View mView;

    private RectF rectF;

    HighlightView(View mView) {
        this.mView = mView;
    }

    float getRadius() {
        if (mView == null) {
            throw new IllegalArgumentException("the highlight view is null!");
        }
        return Math.max(mView.getWidth() / 2, mView.getHeight() / 2) ;
    }

    RectF getRectF(View target) {
        if (mView == null) {
            throw new IllegalArgumentException("the highlight view is null!");
        }
        if (rectF == null) {
            rectF = new RectF();
            Rect locationInView = getLocationInView(target, mView);
            rectF.left = locationInView.left ;
            rectF.top = locationInView.top ;
            rectF.right = locationInView.right  ;
            rectF.bottom = locationInView.bottom ;
        }
        return rectF;
    }

    private static final String FRAGMENT_CON = "NoSaveStateFrameLayout";

    private static Rect getLocationInView(View parent, View child) {
        if (child == null || parent == null) {
            throw new IllegalArgumentException("parent and child can not be null .");
        }

        View decorView = null;
        Context context = child.getContext();
        if (context instanceof Activity) {
            decorView = ((Activity) context).getWindow().getDecorView();
        }

        Rect result = new Rect();
        Rect tmpRect = new Rect();

        View tmp = child;

        if (child == parent) {
            child.getHitRect(result);
            return result;
        }
        while (tmp != decorView && tmp != parent) {
            tmp.getHitRect(tmpRect);
            if (!tmp.getClass().equals(FRAGMENT_CON)) {
                result.left += tmpRect.left;
                result.top += tmpRect.top;
            }
            tmp = (View) tmp.getParent();
            if (tmp == null) {
                throw new IllegalArgumentException("the view is not showing in the window!");
            }

            if (tmp.getParent() != null && (tmp.getParent() instanceof ViewPager)) {
                tmp = (View) tmp.getParent();
            }
        }
        result.right = result.left + child.getMeasuredWidth();
        result.bottom = result.top + child.getMeasuredHeight();
        return result;
    }


}