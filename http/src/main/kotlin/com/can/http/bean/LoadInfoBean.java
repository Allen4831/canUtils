package com.can.http.bean;

/**
 * Created by CAN on 2020/6/9.
 */
public class LoadInfoBean {

    private int totalLength;
    private int downLoadLength;
    private String downLoadFilePath;
    private String downLoadUrl;
    private int downLoadStartLocation;
    private int downLoadEndLocation;

    public int getDownLoadStartLocation() {
        return downLoadStartLocation;
    }

    public void setDownLoadStartLocation(int downLoadStartLocation) {
        this.downLoadStartLocation = downLoadStartLocation;
    }

    public int getDownLoadEndLocation() {
        if (downLoadEndLocation == 0)
            return totalLength;
        return downLoadEndLocation;
    }

    public void setDownLoadEndLocation(int downLoadEndLocation) {
        this.downLoadEndLocation = downLoadEndLocation;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public int getDownLoadLength() {
        return downLoadLength;
    }

    public void setDownLoadLength(int downLoadLength) {
        this.downLoadLength = downLoadLength;
    }

    public String getDownLoadFilePath() {
        return downLoadFilePath;
    }

    public void setDownLoadFilePath(String downLoadFilePath) {
        this.downLoadFilePath = downLoadFilePath;
    }

    public String getDownLoadUrl() {
        return downLoadUrl;
    }

    public void setDownLoadUrl(String downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
    }
}
