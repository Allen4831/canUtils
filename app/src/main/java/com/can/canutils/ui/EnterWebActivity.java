package com.can.canutils.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.can.canutils.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.utils.ActivityManagerUtils;
import com.can.mvp.utils.ToastUtils;
import com.can.mvp.views.BindView;

/**
 * Created by CAN on 2019/7/23.
 */
public class EnterWebActivity extends BaseActivity {


    @BindView(id = R.id.btn_enter, click = true)
    private Button mBtnEnter;

    @BindView(id = R.id.et_url)
    private EditText mEtContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter_web;
    }

    @Override
    public void initData(Bundle bundle) {
        mEtContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO) {
                    gotoWeb();
                }
                return false;
            }
        });
    }

    @Override
    public void setClick(View view) {
        if (view.getId() == R.id.btn_enter) {
            gotoWeb();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void gotoWeb() {
        String text = mEtContent.getText() == null ? "" : mEtContent.getText().toString();
        if (!text.equals("")) {
            String url = mEtContent.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            ActivityManagerUtils.openActivity(this, WebViewActivity.class, bundle);
        } else {
            ToastUtils.getInstance(getApplicationContext()).showText("请输入地址");
        }
    }
}
