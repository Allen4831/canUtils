package com.can.canutils.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.can.bindview.BindView;
import com.can.bindview_api.BindViewManger;
import com.can.canutils.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.mvps.models.BaseModel;
import com.can.mvp.mvps.presenters.LoginPresenter;
import com.can.mvp.mvps.views.LoginView;
import com.can.mvp.utils.ActivityManagerUtils;



/**
 * Created by can on 2018/4/3.
 */

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_submit)
    Button btn_submit;

    LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindViewManger.bindView(this);
    }

    @Override
    public int getLayoutId() {
        return com.can.mvp.R.layout.activity_login;
    }


    @Override
    public void initData(Bundle bundle) {
        presenter = new LoginPresenter(this,new BaseModel(mCompositeSubscription));
    }


    @Override
    public void setClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:
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
        ActivityManagerUtils.getInstance().openActivity(this,SimulationDataActivity.class);
    }
}
