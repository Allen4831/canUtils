package com.can.custom_view.bean;

import android.support.annotation.IntDef;
import android.support.annotation.Keep;

import static com.can.custom_view.bean.BusCarState.ARRIVE;
import static com.can.custom_view.bean.BusCarState.ROAD;

/**
 * Created by CAN on 19-3-21.
 */

@Keep
@IntDef({ARRIVE,ROAD})
public @interface BusCarState {

    //到达站点
    int ARRIVE = 1;
    //还在路上
    int ROAD = 2;

}
