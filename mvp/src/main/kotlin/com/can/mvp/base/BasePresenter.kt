package com.can.mvp.base

/**
 * Created by CAN on 2019/9/21.
 */
interface BasePresenter<in V : BaseView> {
    fun attachView(mRootView: V)
    fun detachView()
}