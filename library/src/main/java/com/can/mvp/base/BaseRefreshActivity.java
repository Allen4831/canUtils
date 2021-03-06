package com.can.mvp.base;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.can.mvp.R;
import com.can.mvp.adapter.BaseRefreshAdapter;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.mvps.views.BaseView;
import com.can.mvp.views.baseviews.IRecycleView;

import java.util.List;

import okhttp3.ResponseBody;


/**
 * Created by can on 2018/6/11.
 * base下拉刷新Activity
 */

public class BaseRefreshActivity extends BaseActivity implements BaseView, IRecycleView.OnIRecycleListener {

    public IRecycleView irl;

    public int pageIndex = 0;
    private BaseRefreshAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_refresh;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        irl = (IRecycleView) findViewById(R.id.irl);
        irl.setOnIRecycleListener(this);
    }

    @Override
    public void initData(Bundle bundle) {
        super.initData(bundle);
        requestData(getRequestParameters());
    }

    @Override
    public void onError(int type,String error) {
        irl.refreshComlete();
        irl.setState(type,error);
    }

    @Override
    public void onSuccess(ResponseBody success) {
        List list = ReturnNetworkData(success);
        if(adapter==null)
            adapter = (BaseRefreshAdapter) getAdapter();
        if(adapter!=null&&irl.getRecyclerView().getAdapter()==null)
            irl.setAdapter(adapter);
        if(list!=null){
            if(list.size()>=20)
                irl.setCanLoadMore(true);
            else
                irl.setCanLoadMore(false);
            if(pageIndex==0)
                adapter.setList(list);
            else
                adapter.addList(list);
        }
    }

    @Override
    public void onComplete() {
        irl.refreshComlete();
    }


    @Override
    public void onRefresh() {
        pageIndex = 0;
        requestData(getRequestParameters());
    }


    @Override
    public void onLoadMore() {
        pageIndex ++;
        requestData(getRequestParameters());
    }

    @Override
    public BaseRequestBean getRequestParameters() {
        return null;
    }

    @Override
    public List ReturnNetworkData(ResponseBody result) {
        return null;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return null;
    }
}
