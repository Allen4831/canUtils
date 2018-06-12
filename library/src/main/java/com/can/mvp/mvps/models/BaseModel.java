package com.can.mvp.mvps.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.can.mvp.R;
import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.bean.responseBean.User;
import com.can.mvp.utils.BitmapUtils;
import com.can.mvp.utils.QRCodeUtils;
import com.can.mvp.utils.StringUtils;
import com.google.zxing.WriterException;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by can on 2018/4/4.
 */

public class BaseModel {

    private CompositeSubscription mCompositeSubscription ;
    public BaseModel(CompositeSubscription compositeSubscription){
        this.mCompositeSubscription = compositeSubscription;
    }

    //请求网络数据
    public void getData(BaseRequestBean baseRequestBean, Observable<Object> observable,  final IBaseModel.onGetDataFinishedListener listener){
        if(baseRequestBean!=null&&!StringUtils.isEmpty(baseRequestBean.getRequest_url())){
            if(mCompositeSubscription!=null){
                mCompositeSubscription.add(observable
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Object>() {
                            @Override
                            public void onCompleted() {
                                listener.onComplete();
                            }

                            @Override
                            public void onError(Throwable e) {
                                listener.onError("出错了");
                            }

                            @Override
                            public void onNext(Object result) {
                                listener.onSuccess(result);
                            }
                        })
                );
            }
        }else{
            listener.onError("");
        }
    }

    //获取二维码
    public void getQRCode(Context context,String content, Bitmap bitmap, IBaseModel.onQRCodeListener listener){
        if(StringUtils.isEmpty(content)&&bitmap==null)
            listener.onDataError("内容不能为空");
        else
            try {
                if(bitmap==null)
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_app_logo);
                if(bitmap==null)
                    listener.onSuccess(QRCodeUtils.generateStringBitmap(content,400,400));
                else
                    listener.onSuccess(QRCodeUtils.generateLogoBitmap(content,bitmap));
            } catch (WriterException e) {
                e.printStackTrace();
            }
    }

    //保存图片
    public void saveImageToGallery(Context context,Bitmap bitmap){
        BitmapUtils.saveImageToGallery(context,bitmap);
    }

    //登录返回
    public void login(final String username, final String password, final IBaseModel.onLoginFinishedListener listener) {
        if(StringUtils.isEmpty(username)){
            listener.onUsernameError();
            return;
        }
        if(StringUtils.isEmpty(password)){
            listener.onPasswordError();
            return;
        }
        listener.onSuccess();
    }

    //请求登录
    public void onUser(String userName, String userPassword, IBaseModel.onGetUserFinishedListener listener) {
        if(StringUtils.isEmpty(userName)){
            listener.onError();
            return;
        }
        if(StringUtils.isEmpty(userPassword)){
            listener.onError();
            return;
        }
        User user = new User();
        user.setUserName(userName);
        user.setUserId(userPassword);
        listener.onSuccess(user);
    }



}
