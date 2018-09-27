package com.can.floatinglayer.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.can.floatinglayer.R;

import java.util.WeakHashMap;


/**
 * Created by CAN on 18-8-28.
 * 浮层View
 */

@SuppressLint("ViewConstructor")
public class FloatingLayerView extends FrameLayout {

    interface mItemClick{
        void onFloatingClick(Enum type,View view,boolean isBackClick);
    }

    //点击回调
    private mItemClick mItemClick;

    //绘制圈的类型
    public enum DRAW_TYPE {
        TYPE_CIRCLE, //圆
        TYPE_ROUND,//椭圆
    }

    //我知道了位置
    public enum KNOW_POSITION_TYPE{
        TYPE_BOTTOM,//底部
        TYPE_TOP//顶部
    }

    //背景类型
    public enum BG_TYPE{
        TYPE_1,
        TYPE_2,
        TYPE_3,
        TYPE_4,
        TYPE_5,
    }

    private Paint mPaintIn; //内圈画笔
    private Paint mPaintInOut; //外圈画笔

    private Enum mDrawType ; //绘制类型
    private Enum mBgType ;//背景类型
    private Enum mKnowPositionType ;//我知道了位置

    private View mLightView;//高亮的view


    private Region mRegionLight = new Region(); //高亮的点击区域
    private Region mRegionKnow = new Region() ; //我知道了的点击区域

    private static Bitmap mBgBitmap ; //描述背景图片

    private static Bitmap mKnowBitmap ; //我知道了按钮图片

    public FloatingLayerView(Context context, View view, Enum drawType, Enum bgType, Enum knowPositionType, mItemClick clickListener) {
        super(context);
        this.mLightView = view;
        this.mDrawType = drawType;
        this.mBgType = bgType;
        this.mKnowPositionType = knowPositionType;
        this.mItemClick = clickListener;
        init();
    }

    private void init() {
        setBackgroundColor(0xb3000000);//设置整个浮层背景颜色(这里为透明度60%)
        int color = 0x80ffffff;
        mPaintIn = new Paint();
        mPaintIn.setColor(color);
        mPaintIn.setAntiAlias(true);
        //图像模式为清除图像模式
        mPaintIn.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        //设置画笔遮罩滤镜,可以传入BlurMaskFilter或EmbossMaskFilter，前者为模糊遮罩滤镜而后者为浮雕遮罩滤镜
        //这个方法已经被标注为过时的方法了，如果你的应用启用了硬件加速，你是看不到任何阴影效果的
        mPaintIn.setMaskFilter(new BlurMaskFilter(1, BlurMaskFilter.Blur.SOLID));
        //关闭当前view的硬件加速
        setLayerType(LAYER_TYPE_HARDWARE, null);

        //保证调用onDraw()
        setWillNotDraw(false);

        mPaintInOut = new Paint();
        mPaintInOut.setColor(0xffffff);
        mPaintInOut.setStyle(Paint.Style.STROKE);
        mPaintInOut.setStrokeWidth(3);
        mPaintInOut.setAntiAlias(true);
        mPaintInOut.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        if(mBgType== BG_TYPE.TYPE_1){
            mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_floatingflayer_type_2);
        }else if(mBgType== BG_TYPE.TYPE_2){
            mBgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_floatingflayer_type_1);
        }else if(mBgType== BG_TYPE.TYPE_3){
            mBgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_floatingflayer_type_5);
        }else if(mBgType== BG_TYPE.TYPE_4){
            mBgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_floatingflayer_type_3);
        }else if(mBgType== BG_TYPE.TYPE_5){
            mBgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_floatingflayer_type_4);
        }
        mKnowBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_floatingflayer_know);

        //绘制虚线
        dashPathEffect = new DashPathEffect(new float[]{10, 3}, 1);
        mPaintInOut.setPathEffect(dashPathEffect);
        paint = new Paint();
        rectKnow = new Rect();
    }

    private HighlightView highlightView;
    private RectF rectFOut;
    private RectF rectIn;
    private PathEffect dashPathEffect;
    private Paint paint;
    private Rect rectKnow;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (mRegionLight.contains(x,y)||mRegionKnow.contains(x,y)){
                    mItemClick.onFloatingClick(mBgType,mLightView,false);//点击高亮区域 或者 '我知道了'按钮
                }else{
                    mItemClick.onFloatingClick(mBgType,mLightView,true);//点击背景区域
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        updateData();

        if (mDrawType == DRAW_TYPE.TYPE_CIRCLE) {//圆
            canvas.drawCircle((rectIn.right-rectIn.left)/2+rectIn.left, (rectIn.bottom-rectIn.top)/2+rectIn.top, Math.max(rectIn.width()/2,rectIn.height()/2), mPaintIn);
            canvas.drawCircle((rectFOut.right- rectFOut.left)/2+ rectFOut.left, (rectFOut.bottom- rectFOut.top)/2+ rectFOut.top, Math.max(rectFOut.width()/2, rectFOut.height()/2), mPaintInOut);
        } else if (mDrawType == DRAW_TYPE.TYPE_ROUND) {//椭圆
            canvas.drawRoundRect(rectIn, highlightView.getRadius(), highlightView.getRadius(), mPaintIn);
            canvas.drawRoundRect(rectFOut, highlightView.getRadius(), highlightView.getRadius(), mPaintInOut);
        }

        mRegionLight.set(rectF2Rect(rectFOut));

        if(mKnowPositionType== KNOW_POSITION_TYPE.TYPE_TOP){ //我知道了-按钮在上方
            canvas.drawBitmap(mBgBitmap,(getWidth()-mBgBitmap.getWidth())/2,rectFOut.top-mBgBitmap.getHeight(),paint);
            canvas.drawBitmap(mKnowBitmap,(getWidth()-mKnowBitmap.getWidth())/2,rectFOut.top-mBgBitmap.getHeight()-mKnowBitmap.getHeight()*2,paint);
            rectKnow.left =  (getWidth()-mKnowBitmap.getWidth())/2;
            rectKnow.right = (getWidth()-mKnowBitmap.getWidth())/2 + mKnowBitmap.getWidth();
            rectKnow.top = (int) (rectFOut.top-mBgBitmap.getHeight()-mKnowBitmap.getHeight()*2);
            rectKnow.bottom = (int) (rectFOut.top-mBgBitmap.getHeight()-mKnowBitmap.getHeight());
        }else if(mKnowPositionType== KNOW_POSITION_TYPE.TYPE_BOTTOM){//我知道了-按钮在下方
            canvas.drawBitmap(mBgBitmap,(getWidth()-mBgBitmap.getWidth())/2,rectFOut.bottom,paint);
            canvas.drawBitmap(mKnowBitmap,(getWidth()-mKnowBitmap.getWidth())/2,rectFOut.bottom+mBgBitmap.getHeight()+mKnowBitmap.getHeight(),paint);
            rectKnow.left =  (getWidth()-mKnowBitmap.getWidth())/2;
            rectKnow.right = (getWidth()-mKnowBitmap.getWidth())/2 + mKnowBitmap.getWidth();
            rectKnow.top = (int) (rectFOut.bottom+mBgBitmap.getHeight()+mKnowBitmap.getHeight());
            rectKnow.bottom = (int) (rectFOut.bottom+mBgBitmap.getHeight()+mKnowBitmap.getHeight()*2);
        }
        mRegionKnow.set(rectKnow);

        //recycleBitmap();
    }

    //更新数据
    private void updateData() {
        highlightView = new HighlightView(mLightView);
        int rect_padding = 10;//间距
        rectIn = highlightView.getRectF(rect_padding);//内圈RectF
        int padding = 10;//外圈与内圈的距离
        rectFOut = new RectF(rectIn.left - padding, rectIn.top - padding,
                rectIn.right + padding, rectIn.bottom + padding);//外圈RectF
    }

    //释放bitmap
    public static void recycleBitmap(){
        if(mBgBitmap!=null&&!mBgBitmap.isRecycled()){
            mBgBitmap.recycle();
            mBgBitmap = null;
        }
        if(mKnowBitmap!=null&&!mKnowBitmap.isRecycled()){
            mKnowBitmap.recycle();
            mKnowBitmap = null;
        }
    }

    private Rect rectF2Rect(RectF rectF){
        if(rectF==null){
            throw new IllegalArgumentException("the rectF is null!");
        }
        return new Rect((int) rectF.left,(int) rectF.top,(int) rectF.right,(int) rectF.bottom);
    }


    static class FloatingLayer {

        private static WeakHashMap<Activity,FloatingLayerView> weakHashMap = new WeakHashMap<>();

        //添加浮层
        static void add2Activity(Activity activity, View view, Enum drawType, Enum bgType, Enum knowPositionType, mItemClick clickListener) {
            if(activity==null||view==null||drawType==null||bgType==null||knowPositionType==null)
                return;
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            FloatingLayerView frameLayout = new FloatingLayerView(activity, view,drawType, bgType, knowPositionType,clickListener);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.setLayoutParams(layoutParams);
            viewGroup.addView(frameLayout);

            weakHashMap.put(activity,frameLayout);
        }

        //是否有浮层
        static boolean hasFloatingView(Activity activity){
            return weakHashMap.containsKey(activity);
        }

        //移除浮层
        static void removeFloatingView(Activity activity){
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            FloatingLayerView frameLayout = weakHashMap.get(activity);
            if(viewGroup!=null&&frameLayout!=null){
                recycleBitmap();
                viewGroup.removeView(frameLayout);
                weakHashMap.remove(activity);
            }
        }
    }
}
