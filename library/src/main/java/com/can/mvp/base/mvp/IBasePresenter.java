package com.can.mvp.base.mvp;

import android.content.Context;
import android.graphics.Bitmap;

import com.can.mvp.bean.requestBean.BaseRequestBean;

/**
 * Created by can on 2018/3/2.
 * MVP Presenter
 * 负责View和Model层之间的交互
 */

public interface IBasePresenter {

    interface BaseHomePresenter{
        void getUser(String userName,String userPassword);
        void onDestroy();
    }

    interface BaseLoginPresenter{
        void login(String username, String password);
        void onDestroy();
    }

    interface BasePresenter{
        void getData(BaseRequestBean baseRequestBean);
        void onDestroy();
    }

    interface BaseQRCodePresenter{
        void getQRCode(Context context, String content, Bitmap bitmp);
        void saveImageToGallery(Context context,Bitmap bitmap);
        void onDestroy();
    }


}
