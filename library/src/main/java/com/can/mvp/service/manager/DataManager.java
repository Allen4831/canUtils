package com.can.mvp.service.manager;

import android.content.Context;

import com.can.mvp.service.RetrofitHelper;
import com.can.mvp.service.RetrofitService;

import rx.Observable;


/**
 * Created by can on 2018/6/12.
 * 数据管理类
 */

public class DataManager {

    private RetrofitService mRetrofitService;

    public DataManager(Context context,String url){
        this.mRetrofitService = RetrofitHelper.getInstance(context,url).getServer();
    }

    //获取首页banner
    public Observable<Object> getHomeBanner(){
        return mRetrofitService.getHomeBanner();
    }

    public Observable<Object> getHomeArticleList(int page){
        return mRetrofitService.getHomeArticleList(page);
    }

}
