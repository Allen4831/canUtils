package com.can.custom_view.bean;

/**
 * Created by CAN on 19-3-21.
 */

public enum BusStationState {

    GREEN(0,0xFF00FF00),
    RED(1,0xFFFF0000),
    ORANGE(2,0xFFFFCC00);

    public int status;
    public int color;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    BusStationState(int status, int color){
        this.status = status;
        this.color = color;
    }



}
