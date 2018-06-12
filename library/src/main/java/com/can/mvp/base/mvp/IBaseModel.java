package com.can.mvp.base.mvp;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.bean.responseBean.User;

import java.util.List;

/**
 * Created by can on 2018/3/2.
 * MVP Model
 * BaseModel 业务逻辑和数据模型
 */

public interface IBaseModel {

    interface onLoginFinishedListener{
        void onUsernameError();
        void onPasswordError();
        void onSuccess();
    }

    interface onGetUserFinishedListener{
        void onError();
        void onSuccess(User user);
    }

    interface onGetDataFinishedListener{
        void onError(String error);
        void onSuccess(Object result);
        void onComplete();
    }

    interface onQRCodeListener{
        void onDataError(String error);
        void onSuccess(Bitmap bitmap);
    }

    interface IBaseRefreshInterface{
        BaseRequestBean getRequestParameters();
        List ReturnNetworkData(Object result);
        RecyclerView.Adapter getAdapter();
    }
}
