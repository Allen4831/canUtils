package com.can.mvp.base;

import android.view.View;

import com.can.mvp.R;
import com.can.mvp.adapter.BaseRefreshAdapter;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.mvps.models.BaseModel;
import com.can.mvp.mvps.presenters.BasePresenter;
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
    public BasePresenter basePresenter;

    public int pageIndex = 0;

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
        irl.refreshComlete();
    }

    @Override
    public void onSuccess(ResponseBody success) {
        List list = ReturnNetworkData(success);
        BaseRefreshAdapter adapter = getAdapter();
        if(adapter!=null&&irl.getRecyclerView().getAdapter()==null)
            irl.setAdapter(adapter);
        if(list!=null){
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
    public List ReturnNetworkData(ResponseBody result) {
        return null;
    }

    @Override
    public BaseRefreshAdapter getAdapter() {
        return null;
    }
}
