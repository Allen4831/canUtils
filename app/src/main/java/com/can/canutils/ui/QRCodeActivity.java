package com.can.canutils.ui;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.can.canutils.R;
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
    @BindView(id = R.id.sv)
    private ScrollView sv;
    @BindView(id = R.id.ll_container)
    private LinearLayout ll_container;



    private QRCodePresenter presenter;
    private Bitmap mBitmap ;
    private SureOrCancleDialog dialog;

    @Override
    public int getLayoutId() {
        return com.can.mvp.R.layout.activity_qrcode;
    }

    @Override
    public void initData(Bundle bundle) {
        super.initData(bundle);
        presenter = new QRCodePresenter(this,new BaseModel(mCompositeSubscription));
        dialog = new SureOrCancleDialog(this, com.can.mvp.R.style.style_sureOrCancleDialog,this);
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        ll_container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private int [] sc;
            private int scrollHeight;

            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                ll_container.getWindowVisibleDisplayFrame(rect);
                sc = new int[2];
                btn_qrcode.getLocationOnScreen(sc);
                int screenHeight = ll_container.getRootView().getHeight();
                int softHeight = screenHeight - rect.bottom;
                if(softHeight>140){
                    scrollHeight = sc[1]-softHeight-btn_qrcode.getHeight();
                    if(ll_container.getScrollY()!=scrollHeight&&scrollHeight>0){
//                        ll_container.scrollTo(0,scrollHeight);
                        scrollToPos(0,scrollHeight);
                    }
                }else{
                    if(ll_container.getScrollY()!=0){
//                        ll_container.scrollTo(scrollHeight,0);
                        scrollToPos(scrollHeight,0);
                    }
                }
            }
        });

    }

    private void scrollToPos(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(250);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ll_container.scrollTo(0, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
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
