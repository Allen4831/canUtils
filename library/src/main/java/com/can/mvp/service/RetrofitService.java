package com.can.mvp.service;

import com.can.mvp.bean.requestBean.BaseRequestBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by can on 2018/6/12.
 * 网络请求类
 */

public interface RetrofitService {

    @POST("String")
    Call<String> getBaseBean(@Body BaseRequestBean bean);

    @GET("{page}/json")
    Call<String> getBaseBean(@Path("page") int page);

    @GET("banner/json")
    Observable<Object> getHomeBanner();

    @GET("article/list/{page}/json")
    Observable<ResponseBody> getHomeArticleList(@Path("page") int page);

}
