package com.can.floatinglayer.ui;

import android.view.View;
import android.widget.Button;

import com.can.floatinglayer.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.views.BindView;

/**
 * Created by CAN on 18-8-28.
 */

public class TestActivity extends BaseActivity {

    @BindView(id = R.id.btn_text)
    private Button btn_text;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        FloatingLayerView.FloatingLayer.add2Activity(this,R.drawable.group1,R.id.btn_text);
    }
}
