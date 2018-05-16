package com.can.mvp.mvps.views;

import com.can.mvp.base.mvp.IBaseView;

/**
 * Created by can on 2018/4/4.
 */

public interface BaseView extends IBaseView {

    void onError(String error);
    void onSuccess(String success);

}
