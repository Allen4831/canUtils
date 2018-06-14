package com.can.mvp.service.manager;

import android.content.Context;

import com.can.mvp.service.RetrofitHelper;
import com.can.mvp.service.RetrofitService;

import okhttp3.ResponseBody;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;


/**
 * Created by can on 2018/6/12.
 * 数据管理类
 */

public class DataManager extends BaseDataManager{

    private RetrofitService mRetrofitService;

    public DataManager(Context context, String url, GsonConverterFactory gsonConverterFactory){
        this.mRetrofitService = RetrofitHelper.getInstance(context,url,gsonConverterFactory).getRetrofit().create(RetrofitService.class);
    }

    //获取首页banner
    public Observable<ResponseBody> getHomeBanner(){
        return mRetrofitService.getHomeBanner();
    }

    public Observable<ResponseBody> getHomeArticleList(int page){
        return mRetrofitService.getHomeArticleList(page);
    }

}
