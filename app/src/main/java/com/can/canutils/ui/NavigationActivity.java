package com.can.canutils.ui;

import android.view.View;
import android.widget.TextView;

import com.can.canutils.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.utils.ActivityManagerUtils;
import com.can.mvp.views.BindView;

/**
 * Created by can on 2018/6/1.
 * 导航Activity
 */

public class NavigationActivity extends BaseActivity {

    @BindView(id = R.id.tv_home,click = true)
    private TextView tv_home;
    @BindView(id = R.id.tv_login,click = true)
    private TextView tv_login;
    @BindView(id = R.id.tv_qrcode,click = true)
    private TextView tv_qrcode;
    @BindView(id = R.id.tv_simulation_data,click = true)
    private TextView tv_simulation_data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    public void setClick(View view) {
        switch (view.getId()){//首页
            case R.id.tv_home:
                ActivityManagerUtils.getInstance().openActivity(this,HomeActivity.class);
                break;
            case R.id.tv_login://登录
                ActivityManagerUtils.getInstance().openActivity(this,LoginActivity.class);
                break;
            case R.id.tv_qrcode://二维码
                ActivityManagerUtils.getInstance().openActivity(this,QRCodeActivity.class);
                break;
            case R.id.tv_simulation_data://模拟请求数据
                ActivityManagerUtils.getInstance().openActivity(this,SimulationDataActivity.class);
                break;
        }
    }
}
