package com.can.mvp.mvps.views;

/**
 * Created by can on 2018/4/4.
 */

public interface LoginView {

    void showProgress();
    void hideProgress();
    void setUsernameError();
    void setPasswordError();
    void navigateToHome();

}
