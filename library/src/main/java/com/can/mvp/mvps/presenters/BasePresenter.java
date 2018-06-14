package com.can.mvp.mvps.presenters;

import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBasePresenter;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.mvps.models.BaseModel;
import com.can.mvp.mvps.views.BaseView;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by can on 2018/4/4.
 */

public class BasePresenter implements IBasePresenter.BasePresenter, IBaseModel.onGetDataFinishedListener {

    private BaseView baseView;
    private BaseModel baseModel;

    public BasePresenter(BaseView baseView,BaseModel baseModel){
        this.baseModel = baseModel;
        this.baseView = baseView;
    }


    @Override
    public void getData(BaseRequestBean baseRequestBean, Observable<ResponseBody> observable) {
        if(baseModel!=null)
            baseModel.getData(baseRequestBean,observable,this);
    }

    @Override
    public void onDestroy() {
        baseView = null;
    }

    @Override
    public void onError(int type,String error) {
        if(baseView!=null)
            baseView.onError(type,error);
    }

    @Override
    public void onSuccess(ResponseBody result) {
        if(baseView!=null)
            baseView.onSuccess(result);
    }

    @Override
    public void onComplete() {
        if(baseView!=null)
            baseView.onComplete();
    }
}
