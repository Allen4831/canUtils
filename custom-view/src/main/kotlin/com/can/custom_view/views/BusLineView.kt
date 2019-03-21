package com.can.custom_view.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.can.custom_view.R
import com.can.custom_view.bean.BusBaseBean
import com.can.custom_view.bean.BusStationBean
import com.can.custom_view.bean.BusStationState
import com.can.custom_view.utils.dp2px


/**
 * Created by CAN on 19-3-21.
 * 公交 中间层线的view
 */

class BusLineView : View {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private var mDataList = ArrayList<BusBaseBean>()//数据集合
    private var mViewHeight: Float = 0.toFloat() //控件高度
    private var mItemWidth: Float = 0.toFloat() //item宽度

    private var mCurrentCircleX = 0.toFloat() //当前圆x位置
    private var mCurrentLineX = 0.toFloat() //当前线x位置
    private val mPaint = Paint() //画笔
    private val mTextPaint = TextPaint() //文字画笔

    //初始化
    @SuppressLint("Recycle")
    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BusLineView)
        mViewHeight = dp2px(context, typedArray.getDimension(R.styleable.BusLineView_viewHeight, 2.toFloat())).toFloat()
        mItemWidth = dp2px(context, typedArray.getDimension(R.styleable.BusLineView_itemWidth, 10.toFloat())).toFloat()
        mTextPaint.textSize = 32.toFloat()
        mTextPaint.color = Color.WHITE
    }

    //更新view
    fun updateView(list: ArrayList<BusBaseBean>) {
        this.mDataList = list
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mDataList.isEmpty())
            return

        mCurrentCircleX = 10.toFloat()
        mCurrentLineX = mCurrentCircleX + mViewHeight / 2

        for (i in 0 until mDataList.size) {
            drawItem(canvas, mDataList[i].busStationBean, i)
        }

    }

    //绘制某个item
    private fun drawItem(canvas: Canvas, bean: BusStationBean, position: Int) {

        //画文字
        val text = bean.busStationName
        var textWidth = 0
        for (i in 0 until text.length) {
            val mSingText = text[i]
            val width = mTextPaint.measureText(mSingText.toString())
            if (width > textWidth)
                textWidth = width.toInt()
        }
        val sl = StaticLayout(text, mTextPaint, textWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false)
        canvas.save()
        canvas.translate(mCurrentCircleX + mViewHeight / 2 - textWidth / 2, 2 * mViewHeight)
        sl.draw(canvas)
        canvas.restore()

        when (position) {
            0 -> { //起始站点
                //画线
                when (bean.busStationState) {
                    BusStationState.RED.status -> mPaint.color = BusStationState.RED.color
                    BusStationState.ORANGE.status -> mPaint.color = BusStationState.ORANGE.color
                    BusStationState.GREEN.status -> mPaint.color = BusStationState.GREEN.color
                }
                val startX = mCurrentLineX
                val startY = 0.toFloat()
                val stopX = mCurrentLineX + mItemWidth
                val stopY = mViewHeight

                canvas.drawRect(startX, startY, stopX, stopY, mPaint)

                mCurrentLineX += mItemWidth

                //画圆
                mPaint.color = Color.WHITE
                val cx = mCurrentCircleX + mViewHeight / 2
                val cy = mViewHeight / 2
                val radius = mViewHeight / 2
                canvas.drawCircle(cx, cy, radius, mPaint)

                mCurrentCircleX += mItemWidth
            }
            mDataList.size - 1 -> { //最后站点
                //画圆
                mPaint.color = Color.WHITE
                val cx = mCurrentCircleX + mViewHeight / 2
                val cy = mViewHeight / 2
                val radius = mViewHeight / 2
                canvas.drawCircle(cx, cy, radius, mPaint)
            }
            else -> { //中间站点
                //画线
                when (bean.busStationState) {
                    BusStationState.RED.status -> mPaint.color = BusStationState.RED.color
                    BusStationState.ORANGE.status -> mPaint.color = BusStationState.ORANGE.color
                    BusStationState.GREEN.status -> mPaint.color = BusStationState.GREEN.color
                }
                val startX = mCurrentLineX
                val startY = 0.toFloat()
                val stopX = mCurrentLineX + mItemWidth
                val stopY = mViewHeight
                canvas.drawRect(startX, startY, stopX, stopY, mPaint)

                mCurrentLineX += mItemWidth

                //画圆
                mPaint.color = Color.WHITE
                val cx = mCurrentCircleX + mViewHeight / 2
                val cy = mViewHeight / 2
                val radius = mViewHeight / 2
                canvas.drawCircle(cx, cy, radius, mPaint)

                mCurrentCircleX += mItemWidth
            }
        }


    }


}
