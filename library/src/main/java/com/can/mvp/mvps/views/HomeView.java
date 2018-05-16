package com.can.mvp.mvps.views;

import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.bean.responseBean.User;

/**
 * Created by can on 2018/4/4.
 */

public interface HomeView extends IBaseView{

    void setUser(User user);
    void error();

}
