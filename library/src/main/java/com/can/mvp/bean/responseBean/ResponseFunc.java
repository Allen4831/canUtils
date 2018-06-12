package com.can.mvp.bean.responseBean;

import rx.functions.Func1;

/**
 * Created by can on 2018/06/12.
 */

public class ResponseFunc<T> implements Func1<BaseResponseBean<T>,T> {

    private static final String CODE = "0";

    @Override
    public T call(BaseResponseBean<T> tBaseResponseBean) {
        if (!tBaseResponseBean.getErrorCode().equals(CODE)) {
            // 在此处抛出异常，subscribe的onError方法中会收到异常。
            // 抛出的异常不能是会使系统崩溃的检查异常，如OOM
            throw new IllegalStateException(tBaseResponseBean.getErrorMsg());
        }
        return tBaseResponseBean.getData();
    }
}
