package com.can.mvp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.can.mvp.R;
import com.can.mvp.interfaces.OnWavePlayerListener;
import com.can.mvp.utils.ThreadPool;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by CAN on 18-11-27.
 * 录音时波形图
 */

public class VoiceWaveView extends View implements OnWavePlayerListener {

    public VoiceWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context,attrs);
    }

    public VoiceWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private static final String TAG = VoiceWaveView.class.getSimpleName();

    private Paint mPaint; //画笔
    private int mWaveColor; //波纹颜色
    private float mWaveWidth; //波纹宽度
    private int mTextColor; //文本颜色
    private float mTextSize; //文本大小
    private String mText = DEFAULT_TEXT ; //文本

    private boolean mIsStart = false ; //是否开始

    private Runnable mRunnable ; //线程操作

    private static final float WAVE_WIDTH = 8f ; //默认波纹宽度
    private static final float MIN_WAVE_H = 2*WAVE_WIDTH; //最小波纹高度,是宽度的2倍
    private static final float MAX_WAVE_H = 4*WAVE_WIDTH ; //最大波纹高度，是宽度的4倍
    private static final int DEFAULT_INTERVAL_TIME = 150 ; //更新间隔
    private static final int DEFAULT_WAVE_SIZE = 10 ; //波纹默认数量
    private static final String DEFAULT_TEXT = " 04:30 / 05:00 ";

    private LinkedList<Float> mWaveList = new LinkedList<>(); //波纹集合

    private RectF mRectFRight = new RectF(); //左侧波纹位置，可复用
    private RectF mRectFLeft = new RectF(); //右侧波纹位置，可复用

    //初始化
    private void init(Context context,AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.VoiceWaveView);
        mWaveColor = typedArray.getColor(R.styleable.VoiceWaveView_waveColor, ContextCompat.getColor(context,R.color.colorAccent));
        mWaveWidth = typedArray.getDimension(R.styleable.VoiceWaveView_waveWidth,WAVE_WIDTH);
        mTextColor = typedArray.getColor(R.styleable.VoiceWaveView_textColor,ContextCompat.getColor(context,R.color.color_black));
        mTextSize = typedArray.getDimension(R.styleable.VoiceWaveView_textSize,40);

        typedArray.recycle();

        mPaint = new Paint();
        initWaveList();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                while (mIsStart){
                    refreshWaveList(1);
                    try {
                        Thread.sleep(DEFAULT_INTERVAL_TIME);
                    } catch (InterruptedException e) {
                        Log.e(TAG,"录音波纹刷新出错");
                    }
                    postInvalidate();
                }
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int midWidth = getWidth()/2;
        int midHeight = getHeight()/2;

        //文本更新
        mPaint.setStrokeWidth(0f);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        float mTextWidth = mPaint.measureText(mText);
        canvas.drawText(mText,midWidth-mTextWidth/2,midHeight - (mPaint.ascent() + mPaint.descent())/2,mPaint);

        //更新波纹
        mPaint.setColor(mWaveColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mWaveWidth);
        mPaint.setAntiAlias(true);
        for(int i = 0 ; i<mWaveList.size() ; i++){
            mRectFLeft.left = midWidth - (mTextWidth/2 + 2*(i+1)*mWaveWidth);
            mRectFLeft.top = midHeight - mWaveList.get(i)/2;
            mRectFLeft.right = midWidth - (mTextWidth/2 + (2*i+1)*mWaveWidth);
            mRectFLeft.bottom = midHeight + mWaveList.get(i)/2;

            mRectFRight.left = midWidth + (mTextWidth/2 + (2*i+1)*mWaveWidth);
            mRectFRight.top = midHeight - mWaveList.get(i)/2;
            mRectFRight.right = midWidth + (mTextWidth/2 + 2*(i+1)*mWaveWidth);
            mRectFRight.bottom = midHeight + mWaveList.get(i)/2;

            canvas.drawRoundRect(mRectFLeft,0,0,mPaint);
            canvas.drawRoundRect(mRectFRight,0,0,mPaint);
        }
    }

    private void initWaveList(){
        if(mWaveList==null)
            mWaveList = new LinkedList<>();
        mWaveList.clear();
        for(int i = 0 ; i < DEFAULT_WAVE_SIZE; i++ ){
            float waveHeight = MIN_WAVE_H;
            mWaveList.add(waveHeight);
        }
    }

    /**
     * 设置波纹数据
     * @param maxAmp 最大振幅
     */
    private synchronized void refreshWaveList(int maxAmp){
        Random random = new Random();
        float waveHeight = WAVE_WIDTH*(random.nextInt(4)+1);
        mWaveList.add(0,waveHeight);
        mWaveList.removeLast();
    }

    @Override
    public void startPlayWave() {
        if(!mIsStart){
            if(mRunnable!=null){
                mIsStart = true;
                ThreadPool.getInstance().getCachedPools().execute(mRunnable);
            }else
                Log.e(TAG,"Runnable不能为空...");
        }else{
            Log.e(TAG,"波纹已经在绘制中...");
        }
    }

    @Override
    public void pausePlayWave() {
        if(mIsStart){
            mIsStart = false;
        }
    }

    @Override
    public void stopPlayWave() {
        if(mIsStart){
            mIsStart = false;
            mWaveList.clear();
            mWaveList = null;
            ThreadPool.getInstance().shutdownCachePools();
        }
    }


}
