package com.can.eventbus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.can.eventbus.R;
import com.can.eventbus.utils.UpdateUIEvent;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.views.BindView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by CAN on 18-9-17.
 */

public class TestActivity extends BaseActivity {


    @BindView(id = R.id.btn_receiver,click = true)
    private Button btn_receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void onUpdateUIEvent(UpdateUIEvent updateUIEvent){
        btn_receiver.setText("我不傻");
    }

    @Override
    public void setClick(View view) {
        super.setClick(view);
        if(view.getId()==R.id.btn_receiver){
            startActivity(new Intent(this,SecondActivity.class));
        }
    }
}
