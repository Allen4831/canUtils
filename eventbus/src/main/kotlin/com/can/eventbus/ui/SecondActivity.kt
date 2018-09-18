package com.can.eventbus.ui

import android.view.View
import com.can.eventbus.R
import com.can.eventbus.utils.UpdateUIEvent
import com.can.mvp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_second.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by CAN on 18-9-18.
 */
class SecondActivity:BaseActivity() {


    override fun getLayoutId(): Int {
        return R.layout.activity_second
    }

    override fun initView(view: View?) {
        super.initView(view)
        btn_post.setOnClickListener {
            EventBus.getDefault().post(UpdateUIEvent())
            finish()
        }
    }

    init {

    }

}