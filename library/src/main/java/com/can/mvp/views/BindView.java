package com.can.mvp.views;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by can on 2018/3/12.
 * 注解式绑定控件
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    //传入Id
    int id();
    //传入是否可点击
    boolean click() default false;
}
