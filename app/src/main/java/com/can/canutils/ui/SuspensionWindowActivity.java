package com.can.canutils.ui;

import android.view.View;
import android.widget.Button;

import com.can.canutils.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.utils.WindowUtils;
import com.can.mvp.views.BindView;

/**
 * Created by CAN on 18-8-17.
 * 悬浮窗
 */

public class SuspensionWindowActivity extends BaseActivity{

    @BindView(id = R.id.btn_show_suspension_window,click = true)
    private Button btn_show_suspension_window;

    @Override
    public int getLayoutId() {
        return R.layout.activity_suspension_window;
    }

    @Override
    public void setClick(View view) {
        super.setClick(view);
        switch (view.getId()){
            case R.id.btn_show_suspension_window:
                WindowUtils.showSuspensionWindow(SuspensionWindowActivity.this);
                break;
        }
    }
}
