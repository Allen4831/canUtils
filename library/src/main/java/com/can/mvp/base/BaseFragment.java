package com.can.mvp.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.can.mvp.base.mvp.IBaseView;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.utils.AnnotationUtils;
import com.can.mvp.utils.FragmentManagerUtils;

/**
 * Created by can on 2018/3/6.
 */

public abstract class BaseFragment extends Fragment implements IBaseView,View.OnClickListener{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentManagerUtils.getInstance().pushFragment(this);
        int contentId = getLayoutId();
        if (contentId != 0) {
            View view = inflater.inflate(contentId,null);
            AnnotationUtils.initBindView(this,view);
            Bundle bundle = getArguments();
            initView(view);
            initData(bundle);
            initEvent();
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentManagerUtils.getInstance().removeFragment(this);
    }

    public abstract int getLayoutId();

    @Override
    public void initView(View view) {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData(Bundle bundle) {

    }

    public void requestData(BaseRequestBean bean) {

    }


    @Override
    public void setClick(View view) {

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
