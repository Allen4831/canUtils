package com.can.floatinglayer.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by CAN on 18-8-28.
 * 浮层View
 */

public class FloatingLayerView extends FrameLayout {

    public interface mItemClick{
        void onFloatingClick(int type,View view,boolean isBackClick);
    }

    private mItemClick mItemClick;

    public enum TYPE {
        TYPE_CIRCLE,
        TYPE_ROUND,
    }

    private Paint mPaint; //内圈画笔
    private Paint mPaint2; //外圈画笔

    private final int padding = 10; //内圈和外圈间距
    private final int color = 0x80ffffff; //整个圈背景色

    private int type = 0; //绘制类型

    private View mLightView;

    private RectF rectF1 ;

    public FloatingLayerView(Context context, int imgRes, View view, int type,mItemClick clickListener) {
        super(context);
        this.mLightView = view;
        this.type = type;
        this.mItemClick = clickListener;
        init(context, imgRes);
    }

    private void init(Context context, int imgRes) {
        setBackgroundResource(imgRes);
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        //图像模式为清除图像模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        //设置画笔遮罩滤镜,可以传入BlurMaskFilter或EmbossMaskFilter，前者为模糊遮罩滤镜而后者为浮雕遮罩滤镜
        //这个方法已经被标注为过时的方法了，如果你的应用启用了硬件加速，你是看不到任何阴影效果的
        mPaint.setMaskFilter(new BlurMaskFilter(1, BlurMaskFilter.Blur.SOLID));
        //关闭当前view的硬件加速
        setLayerType(LAYER_TYPE_HARDWARE, null);

        mPaint2 = new Paint();
        mPaint2.setColor(0xffffff);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(3);
        mPaint2.setAntiAlias(true);
        mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (region.contains(x,y)){
                    mItemClick.onFloatingClick(type,mLightView,false);//点击高亮区域
                }else{
                    mItemClick.onFloatingClick(type,mLightView,true);//点击背景区域
                }
                break;
        }
        return true;
    }

    Region region = new Region();

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        HighlightView highlightView = new HighlightView(mLightView);
        RectF rectF = highlightView.getRectF((ViewGroup) getParent());
        rectF1 = new RectF(rectF.left - padding, rectF.top - padding,
                rectF.right + padding, rectF.bottom + padding);

        PathEffect dashPathEffect = new DashPathEffect(new float[]{10, 3}, 1);
        mPaint2.setPathEffect(dashPathEffect);

        if (type == TYPE.TYPE_CIRCLE.ordinal()) {
            canvas.drawCircle((rectF.right-rectF.left)/2+rectF.left, (rectF.bottom-rectF.top)/2+rectF.top, Math.max(rectF.width()/2,rectF.height()/2), mPaint);
            canvas.drawCircle((rectF1.right-rectF1.left)/2+rectF1.left, (rectF1.bottom-rectF1.top)/2+rectF1.top,Math.max(rectF1.width()/2,rectF1.height()/2), mPaint2);
        } else if (type == TYPE.TYPE_ROUND.ordinal()) {
            canvas.drawRoundRect(rectF, highlightView.getRadius(), highlightView.getRadius(), mPaint);
            canvas.drawRoundRect(rectF1, highlightView.getRadius(), highlightView.getRadius(), mPaint2);
        }
        Rect rect = new Rect((int) rectF1.left,(int) rectF1.top,(int) rectF1.right,(int) rectF1.bottom);
        region.set(rect);
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

    private static int mIndex = 0; //下标View


    static class FloatingLayer {
        static void add2Activity(Activity activity, int imgRes, int viewId, final int type, final mItemClick clickListener) {
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            View lightView = viewGroup.findViewById(viewId);
            FrameLayout fb = new FloatingLayerView(activity, imgRes, lightView, type,clickListener);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            fb.setLayoutParams(layoutParams);
            mIndex = viewGroup.getChildCount();
            viewGroup.addView(fb,mIndex);
        }
        static void removeFloatingView(Activity activity){
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            if(viewGroup!=null&&viewGroup.getChildCount()==mIndex+1){
                viewGroup.removeViewAt(mIndex);
            }
        }
    }
}
