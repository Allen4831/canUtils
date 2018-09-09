package com.can.floatinglayer.ui;

import android.view.View;

import com.can.floatinglayer.R;
import com.can.mvp.base.BaseActivity;

/**
 * Created by CAN on 18-8-28.
 */

public class TestActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        FloatingLayerView.FloatingLayer.add2Activity(this,R.drawable.group1);
    }
}
