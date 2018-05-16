package com.can.mvp.mvps.presenters;

import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBasePresenter;
import com.can.mvp.mvps.models.LoginModel;
import com.can.mvp.mvps.views.LoginView;

/**
 * Created by can on 2018/4/3.
 */

public class LoginPresenter implements IBasePresenter.BaseLoginPresenter, IBaseModel.onLoginFinishedListener {
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenter(LoginView loginView, LoginModel loginModel) {
        this.loginView = loginView;
        this.loginModel = loginModel;
    }

    @Override public void login(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }
        loginModel.login(username, password, this);
    }

    @Override public void onDestroy() {
        loginView = null;
    }

    @Override public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
