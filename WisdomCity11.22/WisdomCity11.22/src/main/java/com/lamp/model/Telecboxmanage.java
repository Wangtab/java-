package com.lamp.model;

public class Telecboxmanage {
    private Integer id;

    private String name;

    private Double longitude;

    private Double latitude;

    private Integer operId;

    private String operTime;

    private Integer delFlag;

    private Integer createby;

    private String createTime;

    private Integer roadId;

    public Integer getCreateby() { return createby; }

    public void setCreateby(Integer createby) { this.createby = createby; }

    public String getCreateTime() { return createTime; }

    public void setCreateTime(String createTime) { this.createTime = createTime; }

    public Integer getRoadId() { return roadId; }

    public void setRoadId(Integer roadId) { this.roadId = roadId; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getOperId() {
        return operId;
    }

    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime == null ? null : operTime.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Telecboxmanage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", operId=" + operId +
                ", operTime='" + operTime + '\'' +
                ", delFlag=" + delFlag +
                ", createby=" + createby +
                ", createTime='" + createTime + '\'' +
                ", roadId=" + roadId +
                '}';
    }
}