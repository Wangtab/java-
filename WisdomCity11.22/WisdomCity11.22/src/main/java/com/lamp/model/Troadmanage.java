package com.lamp.model;

import java.math.BigDecimal;

public class Troadmanage {
    private Integer id;

    private String roadName;

    private String roadDes;

    private Integer areaId;

    private Integer operId;

    private String operTime;

    private String roadimg;

    private String points;

    private Integer createby;

    private String createTime;

    private BigDecimal cuton;

    public BigDecimal getCuton() {
        return cuton;
    }

    public void setCuton(BigDecimal cuton) {
        this.cuton = cuton;
    }

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

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getRoadimg() {
        return roadimg;
    }

    public void setRoadimg(String roadimg) {
        this.roadimg = roadimg;
    }

    private Byte delFlag;

    private Integer dimming;

    public Integer getDimming() {
        return dimming;
    }

    public void setDimming(Integer dimming) {
        this.dimming = dimming;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName == null ? null : roadName.trim();
    }

    public String getRoadDes() {
        return roadDes;
    }

    public void setRoadDes(String roadDes) {
        this.roadDes = roadDes == null ? null : roadDes.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }
}