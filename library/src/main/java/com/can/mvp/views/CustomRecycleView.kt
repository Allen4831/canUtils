package com.can.mvp.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by CAN on 2020/1/16.
 */
class CustomRecycleView(context: Context, attrs: AttributeSet?, defStyle: Int) : RecyclerView(context, attrs, defStyle) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

}