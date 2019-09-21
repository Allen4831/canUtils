package com.can.mvp.mvp.presenter

import com.can.mvp.base.BasePresenterImpl
import com.can.mvp.mvp.model.ITestModel
import com.can.mvp.mvp.view.ITestView

/**
 * Created by CAN on 2019/9/21.
 */
class TestPresenter(private val mView: ITestView?) : BasePresenterImpl<ITestView>(), ITestModel {

    override fun showErrorMsg(msg: String?) {
        mView?.showErrorMsg(msg)
    }

    override fun showTestResult(result: String?) {
        mView?.showTestResult(result)
    }
}