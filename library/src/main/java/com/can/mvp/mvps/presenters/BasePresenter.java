package com.can.mvp.mvps.presenters;

import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBasePresenter;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.mvps.models.BaseModel;
import com.can.mvp.mvps.views.BaseView;

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
    public void getData(BaseRequestBean baseRequestBean) {
        if(baseModel!=null)
            baseModel.getData(baseRequestBean,this);
    }

    @Override
    public void onDestroy() {
        baseView = null;
    }

    @Override
    public void onError(String error) {
        if(baseView!=null)
            baseView.onError(error);
    }

    @Override
    public void onSuccess(String result) {
        if(baseView!=null)
            baseView.onSuccess(result);
    }
}
