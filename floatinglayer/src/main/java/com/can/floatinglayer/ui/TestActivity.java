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
                FloatingLayerView.FloatingLayer.add2Activity(this, btn_circle, FloatingLayerView.DRAW_TYPE.TYPE_CIRCLE,
                        FloatingLayerView.BG_TYPE.TYPE_5, FloatingLayerView.KNOW_POSITION_TYPE.TYPE_TOP, new FloatingLayerView.mItemClick() {
                            @Override
                            public void onFloatingClick(Enum type, View view, boolean isBackClick) {
                                if(!isBackClick)
                                FloatingLayerView.FloatingLayer.removeFloatingView(TestActivity.this);
                            }
                        });
                break;
            case R.id.btn_round://椭圆
                FloatingLayerView.FloatingLayer.add2Activity(this, btn_round, FloatingLayerView.DRAW_TYPE.TYPE_ROUND,
                        FloatingLayerView.BG_TYPE.TYPE_2, FloatingLayerView.KNOW_POSITION_TYPE.TYPE_BOTTOM, new FloatingLayerView.mItemClick() {
                            @Override
                            public void onFloatingClick(Enum type, View view, boolean isBackClick) {
                                if(!isBackClick)
                                    FloatingLayerView.FloatingLayer.removeFloatingView(TestActivity.this    );
                            }
                        });
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(FloatingLayerView.FloatingLayer.hasFloatingView(this)){
            FloatingLayerView.FloatingLayer.removeFloatingView(this);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onFloatingClick(Enum type, View view, boolean isBackClick) {

    }
}
