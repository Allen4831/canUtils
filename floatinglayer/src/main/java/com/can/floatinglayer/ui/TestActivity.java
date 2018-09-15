package com.can.floatinglayer.ui;

import android.view.View;
import android.widget.Button;

import com.can.floatinglayer.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.views.BindView;

/**
 * Created by CAN on 18-8-28.
 */

public class TestActivity extends BaseActivity implements FloatingLayerView.mItemClick {

    @BindView(id = R.id.btn_text,click = true)
    private Button btn_text;
    @BindView(id = R.id.btn_circle,click = true)
    private Button btn_circle;
    @BindView(id = R.id.btn_round,click = true)
    private Button btn_round;


    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
    }

    @Override
    public void setClick(View view) {
        switch (view.getId()){
            case R.id.btn_circle://正圆

                break;
            case R.id.btn_round://椭圆

                break;
        }
    }


    @Override
    public void onFloatingClick(Enum type, View view, boolean isBackClick) {

    }
}
