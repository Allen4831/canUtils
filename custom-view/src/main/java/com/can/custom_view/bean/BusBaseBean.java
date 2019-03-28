package com.can.custom_view.bean;

/**
 * Created by CAN on 19-3-21.
 * 公交数据基类
 */

public class BusBaseBean {

    private BusStationBean busStationBean;
    private BusCarBean busCarBean;

    public BusStationBean getBusStationBean() {
        return busStationBean;
    }

    public void setBusStationBean(BusStationBean busStationBean) {
        this.busStationBean = busStationBean;
    }

    public BusCarBean getBusCarBean() {
        return busCarBean;
    }

    public void setBusCarBean(BusCarBean busCarBean) {
        this.busCarBean = busCarBean;
    }
}
