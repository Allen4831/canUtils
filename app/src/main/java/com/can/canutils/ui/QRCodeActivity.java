package com.can.canutils.ui;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.can.canutils.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.mvps.models.BaseModel;
import com.can.mvp.mvps.presenters.QRCodePresenter;
import com.can.mvp.mvps.views.QRCodeView;
import com.can.mvp.utils.ToastUtils;
import com.can.mvp.views.BindView;

/**
 * Created by can on 2018/4/9.
 * 生成二维码
 */

public class QRCodeActivity extends BaseActivity implements QRCodeView {

    @BindView(id = com.can.mvp.R.id.et_content)
    private EditText et_content;
    @BindView(id = R.id.ll_container)
    private LinearLayout ll_container;
    @BindView(id = R.id.sv)
    private ScrollView sv;
    @BindView(id = R.id.ll_content)
    private LinearLayout ll_content;


    private QRCodePresenter presenter;

    @Override
    public int getLayoutId() {
        return com.can.mvp.R.layout.activity_qrcode;
    }

    @Override
    public void initData(Bundle bundle) {
        super.initData(bundle);
        presenter = new QRCodePresenter(this,new BaseModel(mCompositeSubscription));
    }

    private boolean flag;
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
                if(sc==null){
                    sc = new int[2];
                    ll_content.getLocationInWindow(sc);
                }
                int screenHeight = ll_container.getRootView().getHeight();
                int softHeight = screenHeight - rect.bottom;
                if(softHeight>150){
                    scrollHeight = sc[1]-softHeight-ll_content.getHeight();
                    if(ll_container.getScrollY()<scrollHeight){
                        ll_container.scrollTo(0,scrollHeight);
                    }
                    flag = true;
                }else{
                    if(ll_container.getScrollY()!=0){
                        ll_container.scrollTo(0,0);
                    }
                    flag = false;
                }
            }
        });

        sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(flag)
                    return true;
                else
                   return false;
            }
        });

        et_content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    sv.requestDisallowInterceptTouchEvent(false);
                } else {
                    sv.requestDisallowInterceptTouchEvent(true);
                }
                return false;
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

    }


}
