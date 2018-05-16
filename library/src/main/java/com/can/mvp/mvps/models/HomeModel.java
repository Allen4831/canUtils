package com.can.mvp.mvps.models;

import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.bean.responseBean.User;
import com.can.mvp.utils.StringUtils;

/**
 * Created by can on 2018/3/2.
 */

public class HomeModel  {

    public void onUser(String userName, String userPassword, IBaseModel.onGetUserFinishedListener listener) {
        if(StringUtils.isEmpty(userName)){
            listener.onError();
            return;
        }
        if(StringUtils.isEmpty(userPassword)){
            listener.onError();
            return;
        }

        User user = new User();
        user.setUserName(userName);
        user.setUserId(userPassword);
        listener.onSuccess(user);
    }
}
