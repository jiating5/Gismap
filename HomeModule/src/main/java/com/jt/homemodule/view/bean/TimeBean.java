package com.jt.homemodule.view.bean;

/**
 * @author 贾婷
 * @date 2019/1/2.
 * GitHub：https://github.com/jiating5
 * description：地图
 */

public class TimeBean {
    private String time;
    private String activity;
    private String address;

    @Override
    public String toString() {
        return "TimeBean{" +
                "time='" + time + '\'' +
                ", activity='" + activity + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public TimeBean(String time, String activity, String address) {
        this.time = time;
        this.activity = activity;
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
