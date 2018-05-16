package com.can.mvp.base.mvp;

import android.graphics.Bitmap;

import com.can.mvp.bean.responseBean.User;

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
        void onSuccess(String result);
    }

    interface onQRCodeListener{
        void onDataError(String error);
        void onSuccess(Bitmap bitmap);
    }


}
