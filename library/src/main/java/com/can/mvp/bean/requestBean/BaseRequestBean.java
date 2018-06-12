package com.can.mvp.bean.requestBean;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by can on 2018/4/4.
 * 请求参数类
 */

public class BaseRequestBean {

    private String request_url;
    private Observable<ResponseBody> observable;

    private String userName;
    private String password;
    private int page;

    public Observable<ResponseBody> getObservable() {
        return observable;
    }

    public void setObservable(Observable<ResponseBody> observable) {
        this.observable = observable;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
