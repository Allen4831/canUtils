package com.can.http.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.can.http.R
import com.can.http.services.StaticBroadcastReceiver
import com.can.mvp.base.BaseActivity

/**
 * Created by CAN on 2019/12/30.
 */
class UpdateAppActivity : BaseActivity() {

    private var mBtnCall: Button? = null
    private var mDynamicBroadcastReceiver: BroadcastReceiver? = null
    private var mIntentFilter: IntentFilter? = null

    private val mIsDynamic = false

    override fun getLayoutId(): Int {
        return R.layout.activity_activity_update_app
    }

    override fun initView(view: View?) {
        mBtnCall = findViewById(R.id.btn_call)

    }

    override fun initEvent() {
        mBtnCall?.setOnClickListener {
            register()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mIsDynamic) {
            mIntentFilter = IntentFilter()
            mIntentFilter?.addAction("动态广播")
            mDynamicBroadcastReceiver = DynamicBroadcastReceiver()
            registerReceiver(mDynamicBroadcastReceiver, mIntentFilter)
        }
    }

    //注册
    private fun register() {
        if (mIsDynamic) {
            val intent = Intent()
            intent.action = "动态广播"
            intent.putExtra("动态广播", "注册成功")
            sendBroadcast(intent)
        }else{
            val intent = Intent()
            intent.action = "call"
            intent.putExtra("静态广播", "注册成功")
            intent.`package` = packageName
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mIsDynamic)
            unregisterReceiver(mDynamicBroadcastReceiver)
    }

    private class DynamicBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i("BroadcastReceiver", "动态广播注册:${intent?.getStringExtra("动态广播")}")
        }

    }
}