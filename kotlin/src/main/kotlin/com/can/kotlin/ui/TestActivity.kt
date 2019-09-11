package com.can.kotlin.ui

import android.util.Log
import com.can.kotlin.R
import com.can.kotlin.myClass.Person
import com.can.mvp.base.BaseActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by CAN on 18-9-27.
 */
class TestActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    init {
        test()
        testJobs()
    }


    private fun test() {
        val person = Person()

        GlobalScope.launch {
            delay(1000)
            Log.i("GlobalScope", "delay")
        }

        Log.i("GlobalScope", "test")
        Thread.sleep(2000)

    }

    private fun testJobs() = runBlocking {
        val jobs = List(10_10) {
            GlobalScope.launch {
                delay(1000)
                Log.i("GlobalScope", "testJobs")
            }
        }
        jobs.forEach {
            it.join()
        }
    }

}