package com.can.mvp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;

/**
 * Created by can on 2018/6/13.
 * Gson解析数据工具类
 */

public class GsonUtils {

    private static GsonUtils gsonUtils;
    private static Gson gson = (new GsonBuilder()).disableHtmlEscaping().create();

    public static GsonUtils getInstance(){
        if(gsonUtils==null)
            gsonUtils = new GsonUtils();
        return gsonUtils;
    }

    //json字符串转bean对象
    public static <T> T jsonStrToBean(String jsonStr, Class<T> cls) {
        Object t = gson.fromJson(jsonStr, cls);
        return (T) t;
    }

    //bean对象转json字符串
    public static String beanTojsonStr(Object obj) {
        return gson.toJson(obj);
    }

    //解析数据
    public static <T> T parseResponseBody(ResponseBody responseBody, Class<T> tClass){
        if(responseBody!=null&&tClass!=null){
            //这里不try catch无法将ResponseBody转为字符串
            try {
                String result = responseBody.string().trim();
                if (!StringUtils.isEmpty(result)) {
                    return jsonStrToBean(result,tClass);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
