package com.can.mvp.mvps.views;

import android.graphics.Bitmap;

/**
 * Created by can on 2018/4/9.
 * 二维码
 */

public interface QRCodeView {
    void onError(String error);
    void onSuccess(Bitmap bitmap);
}
