package com.can.canutils.ui;

import android.os.Bundle;
import android.view.View;

import com.can.canutils.R;
import com.can.canutils.adapter.VerticalScrollPagerAdapter;
import com.can.canutils.bean.HomeArticleListBean;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.service.manager.DataManager;
import com.can.mvp.utils.GsonUtils;
import com.can.mvp.views.BindView;
import com.can.mvp.views.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by can on 2018/6/15.
 * 竖直ViewPager
 */

public class VerticalViewPagerActivity extends BaseActivity {

    @BindView(id = R.id.vertical_viewpager)
    private VerticalViewPager verticalViewPager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_vertical_viewpager;
    }

    @Override
    public void initData(Bundle bundle) {
        BaseRequestBean bean = new BaseRequestBean();
        bean.setRequest_url("http://www.wanandroid.com/");
        bean.setObservable(manager!=null?((DataManager)manager).getHomeArticleList(0):null);
        requestData(bean);
    }

    @Override
    public void onSuccess(ResponseBody success) {
        HomeArticleListBean bean = GsonUtils.parseResponseBody(success,HomeArticleListBean.class);
        if(bean!=null&&bean.getData()!=null&&bean.getData().getDatas()!=null){
            List<HomeArticleListBean.DataBean.DatasBean> list = bean.getData().getDatas();
            List<String> string_list = new ArrayList<>();
            for(int i =0;i<list.size();i++){
                string_list.add(list.get(i).getTitle());
            }
            string_list = initList(string_list);
            View[]views = new View[list.size()];
            VerticalScrollPagerAdapter adapter = new VerticalScrollPagerAdapter(this,string_list,views);
            verticalViewPager.setMyAdapter(adapter);
        }

    }

    //集合数量不能小于4
    private List<String> initList(List<String> list) {
        if(list.size()>0&&list.size()<4){
            list.addAll(list);
            initList(list);
        }
        return list;
    }
}
