package com.can.custom_view.bean;

/**
 * Created by CAN on 19-3-21.
 * 公交车站类
 */

public class BusStationBean {

    public BusStationBean(int id, String busStationName, int busStationState, boolean isCurrentPosition) {
        this.id = id;
        this.busStationName = busStationName;
        this.busStationState = busStationState;
        this.isCurrentPosition = isCurrentPosition;
    }

    public BusStationBean() {
    }

    public BusStationBean(int busStationState, String busStationName) {
        this.busStationState = busStationState;
        this.busStationName = busStationName;
    }

    //站点id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //站点名称
    private String busStationName;

    //站点状态
    private int busStationState;

    //是否为当前站点位置
    private boolean isCurrentPosition;

    public String getBusStationName() {
        return busStationName;
    }

    public void setBusStationName(String busStationName) {
        this.busStationName = busStationName;
    }

    public int getBusStationState() {
        return busStationState;
    }

    public void setBusStationState(int busStationState) {
        this.busStationState = busStationState;
    }

    public boolean isCurrentPosition() {
        return isCurrentPosition;
    }

    public void setCurrentPosition(boolean currentPosition) {
        isCurrentPosition = currentPosition;
    }
}
