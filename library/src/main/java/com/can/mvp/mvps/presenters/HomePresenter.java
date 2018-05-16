package com.can.mvp.mvps.presenters;

import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBasePresenter;
import com.can.mvp.bean.responseBean.User;
import com.can.mvp.mvps.models.HomeModel;
import com.can.mvp.mvps.views.HomeView;

/**
 * Created by can on 2018/4/4.
 */

public class HomePresenter implements IBasePresenter.BaseHomePresenter, IBaseModel.onGetUserFinishedListener {

    private HomeView view;
    private HomeModel model;

    public HomePresenter(HomeView view, HomeModel model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void getUser(String userName, String userPassword) {
        if(model!=null)
            model.onUser(userName,userPassword,this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onError() {
        if(view!=null)
            view.error();
    }

    @Override
    public void onSuccess(User user) {
        if(view!=null&&user!=null)
            view.setUser(user);
    }
}
