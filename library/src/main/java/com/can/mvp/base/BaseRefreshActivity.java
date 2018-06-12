package com.can.mvp.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.can.mvp.R;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.mvps.models.BaseModel;
import com.can.mvp.mvps.presenters.BasePresenter;
import com.can.mvp.mvps.views.BaseView;
import com.can.mvp.views.baseviews.IRecycleView;

import java.util.List;


/**
 * Created by can on 2018/6/11.
 * base下拉刷新Activity
 */

public class BaseRefreshActivity extends BaseActivity implements BaseView, IRecycleView.OnIRecycleListener {

    public IRecycleView irl;
    public BasePresenter basePresenter;

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
    public void initData() {
        super.initData();
        basePresenter = new BasePresenter(this,new BaseModel(mCompositeSubscription));
        basePresenter.getData(getRequestParameters(),getRequestParameters()==null?null:getRequestParameters().getObservable());
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onSuccess(Object success) {
        ReturnNetworkData(success);
    }

    @Override
    public void onComplete() {
        irl.refreshComlete();
    }


    @Override
    public void onRefresh() {
        basePresenter.getData(getRequestParameters(),getRequestParameters()==null?null:getRequestParameters().getObservable());
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public BaseRequestBean getRequestParameters() {
        return null;
    }

    @Override
    public List<Object> ReturnNetworkData(Object result) {
        return null;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return null;
    }
}
