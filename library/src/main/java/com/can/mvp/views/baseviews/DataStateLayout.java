package com.can.mvp.views.baseviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.can.mvp.R;
import com.github.ybq.android.spinkit.style.DoubleBounce;

/**
 * 数据状态显示布局
 */

public class DataStateLayout extends LinearLayout{

    public static final int STATE_NETWORK_ERROR = 1;//网络错误
    public static final int STATE_NETWORK_LOADING = 2;//加载中
    public static final int STATE_NODATA = 3;//数据为空
    public static final int STATE_HIDE_LAYOUT = 4;//隐藏
    public static final int STATE_NO_LOGIN = 5;//未登录

    //各种状态的文字显示
    private String string_no_data ;
    private String string_no_login ;
    private String string_loading;
    private String string_network_error;

    //各种状态下的图片显示
    private int drawable_no_data;
    private int drawable_no_login;
    private int drawable_network_error;

    private ProgressBar animProgress;//加载进度框
    private boolean clickEnable = true;//是否可点击
    private final Context context;//上下文
    public ImageView img;//默认图片
    private TextView tv;//默认文字
    private int mState;//状态
    private LinearLayout ll_after_loading;//加载后的布局
    private LinearLayout ll_loading;//加载中的布局
    private TextView tv_loading; //加载中的文字

    private onDataStateClickListener onDataStateClickListener;//监听器

    //设置监听
    public void setOnDataStateClickListener(onDataStateClickListener listener){
        onDataStateClickListener = listener;
    }

    //点击监听
    public interface onDataStateClickListener{
        void onDateStateClickListener(int state);
    }

    public DataStateLayout(Context context) {
        super(context);
        this.context = context;
        this.init();
    }

    public DataStateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.init();
    }

    //初始化
    private void init() {
        string_no_data = context.getString(R.string.string_no_data);
        string_no_login = context.getString(R.string.string_no_login);
        string_loading = context.getString(R.string.string_loading);
        string_network_error = context.getString(R.string.string_network_error);

        drawable_no_data = R.drawable.img_no_data;
        drawable_no_login = R.drawable.img_no_login;
        drawable_network_error = R.drawable.img_no_net;

        //布局初始化
        View view = View.inflate(this.context, R.layout.layout_data_state, null);
        this.img = view.findViewById(R.id.img_error_layout);
        this.tv = view.findViewById(R.id.tv_error_layout);
        this.ll_loading = view.findViewById(R.id.ll_loading);
        this.ll_after_loading = view.findViewById(R.id.ll_after_loading);
        this.animProgress = view.findViewById(R.id.animProgress);
        this.tv_loading = view.findViewById(R.id.tv_loading);
        this.setProgressStyle(new DoubleBounce());
        this.setBackgroundResource(R.color.color_bg_layout_data);
        this.ll_after_loading.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (DataStateLayout.this.clickEnable && DataStateLayout.this.onDataStateClickListener != null) {
                    onDataStateClickListener.onDateStateClickListener(mState);
                }
            }
        });
        this.addView(view);
    }

    /**
     * 设置状态
     * @param i
     */
    public void setState(int i) {
        this.setVisibility(VISIBLE);
        switch(i) {
            case STATE_NETWORK_ERROR://网络错误
                this.mState = STATE_NETWORK_ERROR;
                this.ll_after_loading.setVisibility(VISIBLE);
                this.ll_loading.setVisibility(GONE);
                this.tv.setText(string_network_error);
                this.img.setBackgroundResource(drawable_network_error);
                this.clickEnable = true;
                break;
            case STATE_NETWORK_LOADING://加载中...
                this.mState = STATE_NETWORK_LOADING;
                this.ll_after_loading.setVisibility(GONE);
                this.ll_loading.setVisibility(VISIBLE);
                this.tv_loading.setText(string_loading);
                this.clickEnable = false;
                break;
            case STATE_NODATA://加载后-没有数据
                this.mState = STATE_NODATA;
                this.ll_after_loading.setVisibility(VISIBLE);
                this.ll_loading.setVisibility(GONE);
                this.img.setBackgroundResource(drawable_no_data);
                this.tv.setText(string_no_data);
                this.clickEnable = true;
                break;
            case STATE_HIDE_LAYOUT://隐藏
                this.setVisibility(GONE);
                break;
            case STATE_NO_LOGIN://用户未登录
                this.mState = STATE_NO_LOGIN;
                this.ll_after_loading.setVisibility(VISIBLE);
                this.ll_loading.setVisibility(GONE);
                this.img.setBackgroundResource(drawable_no_login);
                this.tv.setText(string_no_login);
                this.clickEnable = false;
        }
    }

    //隐藏
    public void dismiss() {
        this.mState = STATE_HIDE_LAYOUT;
        this.setVisibility(GONE);
    }

    //获取状态
    public int getState() {
        return this.mState;
    }

    /**
     * 设置默认文本
     *              setString_no_data
     *              setString_no_login
     *              setString_loading
     *              setString_network_error
     */

    public void setString_no_data(String string_no_data) {
        this.string_no_data = string_no_data;
    }

    public void setString_no_login(String string_no_login) {
        this.string_no_login = string_no_login;
    }

    public void setString_loading(String string_loading) {
        this.string_loading = string_loading;
    }

    public void setString_network_error(String string_network_error) {
        this.string_network_error = string_network_error;
    }

    /**
     * 设置默认图片
     *              setDrawable_no_data
     *              setDrawable_no_login
     *              setDrawable_network_error
     */

    public void setDrawable_no_data(int drawable_no_data) {
        this.drawable_no_data = drawable_no_data;
    }

    public void setDrawable_no_login(int drawable_no_login) {
        this.drawable_no_login = drawable_no_login;
    }

    public void setDrawable_network_error(int drawable_network_error) {
        this.drawable_network_error = drawable_network_error;
    }

    /**
     * 设置加载样式
     * @param drawable
     */
    public void setProgressStyle(Drawable drawable){
        this.animProgress.setIndeterminateDrawable(drawable);
    }

    /**
     * 设置隐藏和显示
     * @param visibility
     */
    public void setVisibility(int visibility) {
        if(visibility == GONE) {
            this.mState = STATE_HIDE_LAYOUT;
        }
        super.setVisibility(visibility);
    }

}
