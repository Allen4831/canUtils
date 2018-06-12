package com.can.canutils.ui;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.can.canutils.R;
import com.can.mvp.base.BaseRefreshActivity;
import com.can.mvp.bean.requestBean.BaseRequestBean;

import java.io.IOException;
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
        bean.setObservable(manager.getHomeArticleList(0));
        return bean;
    }

    @Override
    public List ReturnNetworkData(ResponseBody result) {
        if(result!=null){
            try {
                String bb = result.string().trim();
                Log.d("111", ",\tbody=" + bb);
                if (!TextUtils.isEmpty(bb)) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.ReturnNetworkData(result);
    }
}
