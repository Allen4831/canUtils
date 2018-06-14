package com.can.mvp.service;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by can on 2018/6/12.
 * 网络请求封装
 */

public class RetrofitHelper {

    private Context context;//上下文
    private OkHttpClient okHttpClient = new OkHttpClient();//okHttp
    private static GsonConverterFactory gsonConverterFactory ;//Gson工厂类

    private static Retrofit retrofit;//Retroift
    private String baseUrl;//网址

    //单例化
    private static RetrofitHelper retrofitHelper ;
    public static RetrofitHelper getInstance(Context context,String url,GsonConverterFactory factory){
        if(retrofitHelper==null){
            gsonConverterFactory = factory;
            retrofitHelper = new RetrofitHelper(context,url);
        }
        return retrofitHelper;
    }

    private RetrofitHelper(Context context,String url){
        this.context = context;
        this.baseUrl = url;
        init();
    }

    //初始化
    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        ;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

}
