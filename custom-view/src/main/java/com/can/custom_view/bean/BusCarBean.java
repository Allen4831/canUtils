package com.can.custom_view.bean;

/**
 * Created by CAN on 19-3-21.
 * 公交车
 */

public class BusCarBean {

    //公交车状态
    private int carState;

    //公交车对应的站台id
    private int carStationId ;

    public int getCarStationId() {
        return carStationId;
    }

    public void setCarStationId(int carStationId) {
        this.carStationId = carStationId;
    }

    public int getCarState() {
        return carState;
    }

    public void setCarState(int carState) {
        this.carState = carState;
    }
}
