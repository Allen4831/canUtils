package com.can.http.ui

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.view.View
import android.widget.Button
import com.can.http.R
import com.can.http.services.MessengerService
import com.can.http.utils.TestAsyncTask
import com.can.mvp.base.BaseActivity
import java.io.RandomAccessFile

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
            sendMessage()
        }

        val testAsyncTask = TestAsyncTask()
        testAsyncTask.execute("http://www.baidu.com")
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
        } else {
            val intent = Intent()
            intent.action = "call"
            intent.putExtra("静态广播", "注册成功")
            intent.`package` = packageName
            sendBroadcast(intent)
        }
    }

    private var mBound = false
    private var mMessenger: Messenger? = null

    private val mServiceConnected = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
            mMessenger = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mMessenger = Messenger(service)
            mBound = true
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MessengerService::class.java)
        bindService(intent, mServiceConnected, Context.BIND_AUTO_CREATE)
    }

    private fun sendMessage() {
        if (mBound) {
            val msg = Message.obtain()
            mMessenger?.send(msg)
        }
    }

    override fun onStop() {
        super.onStop()
        if (mBound)
            unbindService(mServiceConnected)
        val randomAccessFile = RandomAccessFile("","")
        randomAccessFile.setLength(1)
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