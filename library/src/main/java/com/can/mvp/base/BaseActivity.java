package com.can.mvp.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.can.mvp.application.BaseApplication;
import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.utils.AnnotationUtils;

import java.util.List;

import static com.can.mvp.application.BaseApplication.getActivityManager;

/**
 * Created by can on 2018/3/2.
 * BaseAcitivty
 *  包括： 1.Activity统一管理
 *         2.设置布局 getLayoutId
 *         3.注解初始化 AnnotationUtils.initBindView
 *         4.view初始化 initView
 *         5.数据初始化 initData
 *         6.点击事件初始化 setClick
 *         7.onActivityResult : Fragment回调
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
        BaseApplication.getInstance().getActivityManager().addActivty(this);
        int contentId = getLayoutId();
        if (contentId != 0) {
            setContentView(contentId);
            AnnotationUtils.initBindView(this);
            initView(getWindow().getDecorView());
            initData();
            initEvent();
            requestData();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityManager().removeActivity(this);
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
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取Fragment管理类
        FragmentManager manager=getSupportFragmentManager();
        //遍历Fragments
        for(int i=0;i<manager.getFragments().size();i++) {
            Fragment fragment=manager.getFragments().get(i);
            //fragment不为空就去调用onActivityResult()
            if(fragment!=null)
                callActivityResult(fragment,requestCode,resultCode,data);
        }
    }
    /**
     * 递归调用，对所有的子Fragment生效
     */
    private void callActivityResult(Fragment fragment,int requestCode,int resultCode,Intent data) {
        //调用每个Fragment的onActivityResult()
        fragment.onActivityResult(requestCode, resultCode, data);
        //获取子Fragment
        List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments();
        if(childFragment!=null)
            for(Fragment f:childFragment)
                if(f!=null)
                    callActivityResult(f, requestCode, resultCode, data);
    }

    public static final int REQUEST_STORAGE_PERMISSION = 10111;

    /**
     * 是否有请求读写权限
     */
    public boolean checkReadPermission() {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
        }
        return flag;
    }

}
