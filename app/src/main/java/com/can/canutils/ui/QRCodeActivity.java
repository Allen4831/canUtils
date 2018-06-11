package com.can.canutils.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.can.canutils.dialogs.SureOrCancleDialog;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.mvps.models.BaseModel;
import com.can.mvp.mvps.presenters.QRCodePresenter;
import com.can.mvp.mvps.views.QRCodeView;
import com.can.mvp.utils.OtherUtils;
import com.can.mvp.utils.ToastUtils;
import com.can.mvp.views.BindView;

/**
 * Created by can on 2018/4/9.
 * 生成二维码
 */

public class QRCodeActivity extends BaseActivity implements QRCodeView, SureOrCancleDialog.onMyClickListener {

    @BindView(id = com.can.mvp.R.id.et_content)
    private EditText et_content;
    @BindView(id = com.can.mvp.R.id.btn_qrcode,click = true)
    private Button btn_qrcode;
    @BindView(id = com.can.mvp.R.id.iv_qrcode,click = true)
    private ImageView iv_qrcode;

    @Override
    public int getLayoutId() {
        return com.can.mvp.R.layout.activity_qrcode;
    }

    private QRCodePresenter presenter;
    private Bitmap mBitmap ;
    private SureOrCancleDialog dialog;

    @Override
    public void initData() {
        super.initData();
        presenter = new QRCodePresenter(this,new BaseModel());
        dialog = new SureOrCancleDialog(this, com.can.mvp.R.style.style_sureOrCancleDialog,this);
    }

    @Override
    public void onError(String error) {
        ToastUtils.getInstance(this).showText(error);
    }

    @Override
    public void onSuccess(Bitmap bitmap) {
        iv_qrcode.setImageBitmap(bitmap);
        mBitmap = bitmap;
        OtherUtils.closeKeyboard(this);
    }

    @Override
    public void setClick(View view) {
        super.setClick(view);
        switch (view.getId()){
            case com.can.mvp.R.id.btn_qrcode://生成二维码
                presenter.getQRCode(this,et_content.getText().toString().trim(),null);
                break;
            case com.can.mvp.R.id.iv_qrcode:
                if(!dialog.isShowing())
                    dialog.show();
                break;
        }
    }

    /**
     * 检查权限后的回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_STORAGE_PERMISSION:
                if (permissions.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {//失败
                    ToastUtils.getInstance(this).showText("请允许存储权限后再试");
                } else {//成功
                    presenter.saveImageToGallery(this,mBitmap);
                }
                break;
        }
    }

    @Override
    public void setMyOnClickListener(View view) {
        if(view.getId()== com.can.mvp.R.id.btn_save) {
            if(checkReadPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,REQUEST_STORAGE_PERMISSION))
            presenter.saveImageToGallery(this,mBitmap);
        }
        dialog.dismiss();
    }
}
