package com.can.mvp.mvp.view

import com.can.mvp.base.IBaseView

/**
 * Created by CAN on 2019/9/21.
 */
interface ITestView : IBaseView {
    fun showTestResult(result: String?)
    fun showErrorMsg(msg: String?)
}