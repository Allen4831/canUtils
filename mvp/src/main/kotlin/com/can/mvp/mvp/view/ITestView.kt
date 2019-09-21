package com.can.mvp.mvp.view

import com.can.mvp.base.BaseView

/**
 * Created by CAN on 2019/9/21.
 */
interface ITestView : BaseView {
    fun showTestResult(result: String?)
    fun showErrorMsg(msg: String?)
}