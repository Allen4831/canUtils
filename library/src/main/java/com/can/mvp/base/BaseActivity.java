package com.can.mvp.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.can.mvp.application.BaseApplication;
import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.service.manager.DataManager;
import com.can.mvp.utils.AnnotationUtils;
import com.can.mvp.utils.ToastUtils;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

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

public class BaseActivity extends AppCompatActivity implements IBaseModel.IBaseRefreshInterface,IBaseView,View.OnClickListener{

    public DataManager manager;
    public CompositeSubscription mCompositeSubscription;

    public static final String URL = "http://www.wanandroid.com/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new DataManager(BaseApplication.getInstance(),URL);
        mCompositeSubscription = new CompositeSubscription();
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
    protected void onStop() {
        super.onStop();
        if(mCompositeSubscription!=null&&mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
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

    public static final int REQUEST_STORAGE_PERMISSION = 10222;//存储权限
    public static final int REQUEST_CALL_PERMISSION = 10111; //拨号请求码

    /**
     * 判断是否有某项权限
     * @param string_permission 权限
     * @param request_code 请求码
     * @return
     */
    public boolean checkReadPermission(String string_permission,int request_code) {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(this, string_permission) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            ActivityCompat.requestPermissions(this, new String[]{string_permission}, request_code);
        }
        return flag;
    }

    /**
     * 检查权限后的回调
     * @param requestCode 请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PERMISSION: //拨打电话
                if (permissions.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {//失败
                    ToastUtils.getInstance(this).showText("请允许拨号权限后再试");
                } else {//成功
                    call("tel:"+"020-22163668");
                }
                break;
            case REQUEST_STORAGE_PERMISSION: //存储权限
                if (permissions.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {//失败
                    ToastUtils.getInstance(this).showText("请允许存储权限后再试");
                } else {//成功

                }
                break;
        }
    }

    /**
     * 拨打电话（直接拨打）
     * @param telPhone 电话
     */
    public void call(String telPhone){
        if(checkReadPermission(Manifest.permission.CALL_PHONE,REQUEST_CALL_PERMISSION)){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telPhone));
            startActivity(intent);
        }
    }

    @Override
    public BaseRequestBean getRequestParameters() {
        return null;
    }

    @Override
    public void ReturnNetworkData(Object result) {

    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return null;
    }
}
