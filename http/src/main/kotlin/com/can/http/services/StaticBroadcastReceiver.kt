package com.can.http.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by CAN on 2020/1/14.
 */
class StaticBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("BroadcastReceiver", "静态广播注册:${intent?.getStringExtra("静态广播")}")
    }
}