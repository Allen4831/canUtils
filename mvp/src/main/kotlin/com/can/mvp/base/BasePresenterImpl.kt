package com.can.mvp.base

/**
 * Created by CAN on 2019/9/21.
 */
open class BasePresenterImpl<T : BaseView> : BasePresenter<T> {

    private var mRootView: T? = null

    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null
    }

    private val mIsViewAttach: Boolean
        get() = mRootView != null

}