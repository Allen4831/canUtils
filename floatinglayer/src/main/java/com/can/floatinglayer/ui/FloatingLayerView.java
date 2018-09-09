package com.can.floatinglayer.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by CAN on 18-8-28.
 * 浮层View
 */

public class FloatingLayerView extends FrameLayout {

    private Paint mPaint;
    private Paint mPaint2;

    private final int padding = 10;
    private final int color = 0x80ffffff;

    private View mHeightView;
    public FloatingLayerView(Context context,int imgRes,View view){
        super(context);
        mHeightView = view;
        init(context,imgRes);
    }

    private void init(Context context, int imgRes) {
        setBackgroundResource(imgRes);
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        //设置画笔遮罩滤镜,可以传入BlurMaskFilter或EmbossMaskFilter，前者为模糊遮罩滤镜而后者为浮雕遮罩滤镜
        //这个方法已经被标注为过时的方法了，如果你的应用启用了硬件加速，你是看不到任何阴影效果的
        mPaint.setMaskFilter(new BlurMaskFilter(1, BlurMaskFilter.Blur.INNER));
        //关闭当前view的硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        //ViewGroup默认设定为true，会使onDraw方法不执行，如果复写了onDraw(Canvas)方法，需要清除此标记
        setWillNotDraw(false);



        mPaint2 = new Paint();
        mPaint2.setColor(0xffffff);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(3);
        mPaint2.setAntiAlias(true);
        mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        HighlightView highlightView = new HighlightView(mHeightView);
        RectF rectF = highlightView.getRectF((ViewGroup) getParent());
        RectF rectF1 = new RectF(rectF.left-padding,rectF.top-padding,
                rectF.right+padding,rectF.bottom+padding);

        canvas.drawRoundRect(rectF,highlightView.getRadius(),highlightView.getRadius(),mPaint);
        PathEffect dashPathEffect = new DashPathEffect(new float[]{10,3},1);
        mPaint2.setPathEffect(dashPathEffect);
        canvas.drawRoundRect(rectF1,highlightView.getRadius(),highlightView.getRadius(), mPaint2);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    public FloatingLayerView(@NonNull Context context) {
        super(context);
    }

    public FloatingLayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingLayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    static class FloatingLayer{
        static void add2Activity(Activity activity, int imgRes, int viewId){
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            ((Button)viewGroup.findViewById(viewId)).setText("你好吗");
            FrameLayout fb = new FloatingLayerView(activity,imgRes,viewGroup.findViewById(viewId));
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            fb.setLayoutParams(layoutParams);
            viewGroup.addView(fb);
        }
    }
}
