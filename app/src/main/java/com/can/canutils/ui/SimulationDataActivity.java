package com.can.canutils.ui;

import android.view.View;

import com.can.canutils.R;
import com.can.canutils.adapter.HomeArticleListAdapter;
import com.can.canutils.bean.HomeArticleListBean;
import com.can.mvp.adapter.BaseRefreshAdapter;
import com.can.mvp.base.BaseRefreshActivity;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.utils.GsonUtils;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by can on 2018/6/11.
 * 模拟请求数据
 */

public class SimulationDataActivity extends BaseRefreshActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_simulation;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
    }

    @Override
    public BaseRequestBean getRequestParameters() {
        BaseRequestBean bean = new BaseRequestBean();
        bean.setRequest_url("http://www.wanandroid.com/");
        bean.setObservable(manager.getHomeArticleList(pageIndex));
        return bean;
    }

    @Override
    public List ReturnNetworkData(ResponseBody result) {
        HomeArticleListBean bean = GsonUtils.parseResponseBody(result,HomeArticleListBean.class);
        if(bean!=null&&bean.getData()!=null)
            return bean.getData().getDatas();
        return super.ReturnNetworkData(result);
    }

    @Override
    public BaseRefreshAdapter getAdapter() {
        HomeArticleListAdapter adapter = new HomeArticleListAdapter(this);
        return adapter;
    }
}
