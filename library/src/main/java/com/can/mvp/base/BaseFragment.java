package com.can.mvp.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.utils.AnnotationUtils;
import com.can.mvp.utils.FragmentManagerUtil;

/**
 * Created by can on 2018/3/6.
 */

public class BaseFragment extends Fragment implements IBaseView,View.OnClickListener{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentManagerUtil.getInstance().pushFragment(this);
        int contentId = getLayoutId();
        if (contentId != 0) {
            View view = inflater.inflate(contentId,null);
            AnnotationUtils.initBindView(this,view);
            initView(view);
            initData();
            initEvent();
            requestData();
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentManagerUtil.getInstance().removeFragment(this);
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
