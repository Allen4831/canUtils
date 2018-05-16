package com.can.mvp.mvps.presenters;

import android.content.Context;
import android.graphics.Bitmap;

import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.base.mvp.IBasePresenter;
import com.can.mvp.mvps.models.BaseModel;
import com.can.mvp.mvps.views.QRCodeView;

/**
 * Created by can on 2018/4/9.
 * 二维码
 */

public class QRCodePresenter implements IBasePresenter.BaseQRCodePresenter, IBaseModel.onQRCodeListener {

    private QRCodeView qrCodeView;
    private BaseModel baseModel;

    public QRCodePresenter(QRCodeView qrCodeView,BaseModel baseModel){
        this.baseModel = baseModel;
        this.qrCodeView = qrCodeView;
    }

    @Override
    public void getQRCode(Context context,String content, Bitmap bitmap) {
        if(baseModel!=null)
            baseModel.getQRCode(context,content,bitmap,this);
    }

    @Override
    public void saveImageToGallery(Context context, Bitmap bitmap) {
        if(baseModel!=null)
            baseModel.saveImageToGallery(context,bitmap);
    }

    @Override
    public void onDestroy() {
        qrCodeView = null;
    }

    @Override
    public void onDataError(String error) {
        if(qrCodeView!=null)
            qrCodeView.onError(error);
    }

    @Override
    public void onSuccess(Bitmap bitmap) {
        if(qrCodeView!=null&&bitmap!=null)
            qrCodeView.onSuccess(bitmap);
    }

}
