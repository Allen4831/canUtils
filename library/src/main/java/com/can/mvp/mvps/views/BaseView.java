package com.can.mvp.mvps.views;

import com.can.mvp.base.mvp.IBaseView;

import okhttp3.ResponseBody;

/**
 * Created by can on 2018/4/4.
 */

public interface BaseView extends IBaseView {

    void onError(int type,String error);
    void onSuccess(ResponseBody success);
    void onComplete();

}
