package com.can.mvp.mvp.presenter

import com.can.mvp.base.BasePresenter
import com.can.mvp.mvp.model.ITestModel
import com.can.mvp.mvp.view.ITestView

/**
 * Created by CAN on 2019/9/21.
 */
class TestPresenter(mView : ITestView) : BasePresenter<ITestView>(), ITestModel {


    override fun showErrorMsg(msg: String?) {

    }

    override fun showTestResult(result: String?) {

    }
}