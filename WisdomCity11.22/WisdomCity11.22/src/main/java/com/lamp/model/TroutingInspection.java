package com.lamp.model;

/**
 * Created by lenovo on 2017-12-04.
 * 巡检管理
 */
public class TroutingInspection {
    private Integer id; //自增ID
    private Integer roadid; //路段ID
    private Integer uid; //人员
    private String  startime; //开始时间
    private String  endtime; //结束时间
    private String checkdescribe; //巡检描述
    private Integer operid; //操作人
    private String opertime;//操作时间
    private Integer state; //删除标识0有1无
    private Integer createby;//创建人id
    private String createTime;

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoadid() {
        return roadid;
    }

    public void setRoadid(Integer roadid) {
        this.roadid = roadid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCheckdescribe() {
        return checkdescribe;
    }

    public void setCheckdescribe(String checkdescribe) {
        this.checkdescribe = checkdescribe;
    }

    public Integer getOperid() {
        return operid;
    }

    public void setOperid(Integer operid) {
        this.operid = operid;
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
