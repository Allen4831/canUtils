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

    @BindView(id = R.id.tv_login,click = true)
    private TextView tv_login;
    @BindView(id = R.id.tv_qrcode,click = true)
    private TextView tv_qrcode;
    @BindView(id = R.id.tv_simulation_data,click = true)
    private TextView tv_simulation_data;
    @BindView(id = R.id.tv_vertical_viewpager,click = true)
    private TextView tv_vertical_viewpager;
    @BindView(id = R.id.tv_enter_small_window,click = true)
    private TextView tv_enter_small_window;
    @BindView(id = R.id.tv_recycleview_transparent,click = true)
    private TextView tv_recycleview_transparent;
    @BindView(id = R.id.tv_custom_chartview,click = true)
    private TextView tv_custom_chartview;

    public NavigationActivity() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    public void setClick(View view) {
        switch (view.getId()){
            case R.id.tv_login://登录
                ActivityManagerUtils.openActivity(this,LoginActivity.class);
                break;
            case R.id.tv_qrcode://二维码
                ActivityManagerUtils.openActivity(this,QRCodeActivity.class);
                break;
            case R.id.tv_simulation_data://模拟请求数据
                ActivityManagerUtils.openActivity(this,SimulationDataActivity.class);
                break;
            case R.id.tv_vertical_viewpager://竖直ViewPager
                ActivityManagerUtils.openActivity(this, VerticalViewPagerActivity.class);
                break;
            case R.id.tv_enter_small_window://进入小窗口
                ActivityManagerUtils.openActivity(this,SuspensionWindowActivity.class);
                break;
            case R.id.tv_recycleview_transparent: //RecycleView透明
                ActivityManagerUtils.openActivity(this,RecycleViewTransparentActivity.class);
                break;
            case R.id.tv_custom_chartview://自定义view
                ActivityManagerUtils.openActivity(this,CustomViewActivity.class);
                break;
        }
    }
}
