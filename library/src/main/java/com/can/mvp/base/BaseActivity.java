package com.can.mvp.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.can.mvp.application.MyApplication;
import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.utils.AnnotationUtils;

/**
 * Created by can on 2018/3/2.
 *
 */

public class BaseActivity extends AppCompatActivity implements IBaseView,View.OnClickListener{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        MyApplication.getActivityManager().addActivty(this);
        int contentId = getLayoutId();
        if (contentId != 0) {
            setContentView(contentId);
            AnnotationUtils.initBindView(this);
            initView(null);
            initData();
            initEvent();
            requestData();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getActivityManager().removeActivity(this);
    }


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {

    }

    @Override
    public void setClick(View view) {
    }

    @Override
    public void onNetWorkError() {

    }

    @Override
    public void onClick(View view) {
        setClick(view);
    }

    /**
     * 更换Fragment
     * @param id
     * @param fragment
     */
    public void changeFragment(int id,Fragment fragment){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }


}
