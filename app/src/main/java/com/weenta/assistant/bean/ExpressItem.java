package com.weenta.assistant.bean;

/**
 * 物流查询item
 */
public class ExpressItem {
    // 时间
    private String time;
    // 轨迹
    private String desc;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ExpressItem{" +
                "time='" + time + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
