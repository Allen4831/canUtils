package com.can.aidl.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.can.aidl.Book
import com.can.aidl.BookInterface
import com.can.aidl.R
import com.can.mvp.base.BaseActivity

/**
 * Created by CAN on 18-10-8.
 */
class AidlActivity : BaseActivity() {

    private var mBookInterface : BookInterface? = null
    private var mIsBound = false
    private var mBooksList = mutableListOf<Book>()

    override fun getLayoutId(): Int {
        return R.layout.activity_aidl
    }

    override fun onStart() {
        super.onStart()
        if(!mIsBound){
            attemptToBindService()
        }
    }

    /***
     * 尝试与服务器连接
     */
    private fun attemptToBindService(){
        val intent = Intent()
        intent.action = "com.can.aidl"
        intent.`package` = "com.can.aidl"
        bindService(intent,mServiceConnection,Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if(mIsBound){
            unbindService(mServiceConnection)
            mIsBound =false
        }
    }

    private val mServiceConnection = object : ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            Log.e(localClassName,"service connected")
            mBookInterface = BookInterface.Stub.asInterface(p1)
            mIsBound = true
            if(mBookInterface!=null){
                mBooksList = mBookInterface!!.books
                Log.e(localClassName,mBooksList.toString())
                val book = Book()
                book.name = "连接后添加的"
                book.price = 0.01
                mBookInterface!!.addBook(book)
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.e(localClassName,"service disconnected")
            mIsBound = false
        }

    }
}