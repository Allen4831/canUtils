package com.can.canutils.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.can.mvp.base.BaseActivity;
import com.can.mvp.mvps.models.BaseModel;
import com.can.mvp.mvps.presenters.LoginPresenter;
import com.can.mvp.mvps.views.LoginView;
import com.can.mvp.views.BindView;

/**
 * Created by can on 2018/4/3.
 */

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(id = com.can.mvp.R.id.et_name)
    private EditText et_name;
    @BindView(id = com.can.mvp.R.id.et_password)
    private EditText et_password;
    @BindView(id = com.can.mvp.R.id.btn_submit,click = true)
    private Button btn_submit;

    LoginPresenter presenter;

    @Override
    public int getLayoutId() {
        return com.can.mvp.R.layout.activity_login;
    }

    @Override
    public void initData() {
        super.initData();
        presenter = new LoginPresenter(this,new BaseModel());
    }

    @Override
    public void setClick(View view) {
        super.setClick(view);
        switch (view.getId()){
            case com.can.mvp.R.id.btn_submit:
                presenter.login(et_name.getText().toString().trim(),et_password.getText().toString().trim());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setUsernameError() {
        Toast.makeText(this,"姓名不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError() {
        Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
