package com.can.custom_view.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by CAN on 18-10-22.
 * 自定义饼图
 */

public class PieChartView extends View {

    public PieChartView(Context context) {
        super(context, null);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private RectF rectF;

    private void initView(Context context, AttributeSet attr) {
        mPaint1 = new Paint();
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setAntiAlias(true);
        mPaint1.setColor(Color.RED);
        mPaint1.setStrokeWidth(80);
        mPaint2 = new Paint(mPaint1);
        mPaint2.setColor(Color.BLUE);
        mPaint3 = new Paint(mPaint1);
        mPaint3.setColor(Color.GREEN);

        rectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取中间位置
        float width = getWidth() - getPaddingLeft() - getPaddingRight();
        float height = getHeight() - getPaddingTop() - getPaddingBottom();
        //定位圆心位置
        canvas.translate(width/2, height/2);
        //半径
        float radius = (float) (Math.min(width,height)/2*0.9);
        //更新坐标RectF
        rectF.set(-radius,-radius,radius,radius);
        canvas.drawArc(rectF,0,120,false,mPaint1);
        canvas.drawArc(rectF,120,120,false,mPaint2);
        canvas.drawArc(rectF,240,120,false,mPaint3);
    }
}
