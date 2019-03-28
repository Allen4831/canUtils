package com.can.custom_view.utils

import android.content.Context

/**
 * Created by CAN on 19-3-21.
 */
//px转dp
fun px2dp(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

//dp转px
fun dp2px(context: Context, dipValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}