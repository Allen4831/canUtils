package com.can.canutils.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.can.canutils.fragment.HomeFragment;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.bean.responseBean.User;
import com.can.mvp.mvps.models.BaseModel;
import com.can.mvp.mvps.presenters.HomePresenter;
import com.can.mvp.mvps.views.HomeView;
import com.can.mvp.views.BindView;

/**
 * Created by can on 2018/3/2.
 */

public class HomeActivity extends BaseActivity implements HomeView {

    @BindView(id = com.can.mvp.R.id.tv)
    private TextView tv;
    private LinearLayout ll;

    private HomePresenter presenter;

    @Override
    public int getLayoutId() {
        return com.can.mvp.R.layout.activity_home;
    }

    @Override
    public void initData(Bundle bundle) {
        User user = new User();
        user.setUserAge("18");
        user.setUserId("123456");
        user.setUserPhone("13611414180");
        user.setUserName("多啦B梦");
        user.setUserSex("未知");

        presenter = new HomePresenter(this,new BaseModel(mCompositeSubscription));

        presenter.getUser(user.getUserName(),user.getUserId());
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        ll = (LinearLayout) findViewById(com.can.mvp.R.id.ll_activity);
        changeFragment(com.can.mvp.R.id.ll_activity, HomeFragment.getInstance());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void setUser(User user) {
        tv.setText(user.getUserName());
    }

    @Override
    public void error() {

    }
}
