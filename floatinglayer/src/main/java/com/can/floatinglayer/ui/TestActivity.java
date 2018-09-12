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
                FloatingLayerView.FloatingLayer.add2Activity(this,R.drawable.group1,R.id.btn_circle,FloatingLayerView.TYPE.TYPE_CIRCLE.ordinal(),this);
                break;
            case R.id.btn_round://椭圆
                FloatingLayerView.FloatingLayer.add2Activity(this,R.drawable.group1,R.id.btn_round,FloatingLayerView.TYPE.TYPE_ROUND.ordinal(),this);
                break;
        }
    }

    @Override
    public void onFloatingClick(int type, View view,boolean isBackClick) {
        if(isBackClick){ //点击背景
            FloatingLayerView.FloatingLayer.removeFloatingView(this);
        }else{//点击高亮处
            FloatingLayerView.FloatingLayer.removeFloatingView(this);
        }
    }
}
