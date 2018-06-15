package com.can.mvp.base.mvp;

import android.os.Bundle;
import android.view.View;

/**
 * Created by can on 2018/3/2.
 * MVP View
 * 对应于Activity，负责View的绘制以及与用户交互
 */

public interface IBaseView {

    /**
     * 初始化控件
     */
     void initView(View view);

    /**
     * 设置事件
     */
     void initEvent();

    /**
     * 初始化数据
     */
    void initData(Bundle bundle);


    /**
     * 点击事件
     */
    void setClick(View view);

}
