package com.can.mvp.base

/**
 * Created by CAN on 2019/9/21.
 */
interface IPresenter<in V : IBaseView> {
    fun attachView(mRootView: V)
    fun detachView()
}