package com.can.kotlin.ui

import android.util.Log
import com.can.kotlin.R
import com.can.kotlin.myClass.Person
import com.can.mvp.base.BaseActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by CAN on 18-9-27.
 */
class TestActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    init {
        test()
    }


    private fun test(){
        val person = Person()

        GlobalScope.launch {
            delay(1000)
            Log.i("TestActivity","delay")
        }

        Log.i("TestActivity","test")

    }

}