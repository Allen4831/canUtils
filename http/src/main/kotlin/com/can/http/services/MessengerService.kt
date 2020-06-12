package com.can.http.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import com.can.mvp.utils.ToastUtils

/**
 * Created by CAN on 2020/5/30.
 */
//创建Service
class MessengerService : Service() {

    //创建Handler
    class MessengerHandler(private val mContext: Context) : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    ToastUtils.getInstance(mContext).showText("hello messenger")
                }
            }
        }
    }

    //创建Messenger
    private val mMessenger = Messenger(MessengerHandler(this))

    override fun onBind(intent: Intent?): IBinder? {
        ToastUtils.getInstance(this).showText("onBind")
        return mMessenger.binder
    }

}