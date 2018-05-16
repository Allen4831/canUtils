package com.can.mvp.mvps.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.can.mvp.R;
import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.utils.BitmapUtils;
import com.can.mvp.utils.QRCodeUtil;
import com.can.mvp.utils.StringUtils;
import com.google.zxing.WriterException;

/**
 * Created by can on 2018/4/4.
 */

public class BaseModel {

    //请求网络数据
    public void getData(BaseRequestBean baseRequestBean, IBaseModel.onGetDataFinishedListener listener){
        
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
                    listener.onSuccess(QRCodeUtil.generateStringBitmap(content,400,400));
                else
                    listener.onSuccess(QRCodeUtil.generateLogoBitmap(content,bitmap));
            } catch (WriterException e) {
                e.printStackTrace();
            }
    }

    //保存图片
    public void saveImageToGallery(Context context,Bitmap bitmap){
        BitmapUtils.saveImageToGallery(context,bitmap);
    }


}
