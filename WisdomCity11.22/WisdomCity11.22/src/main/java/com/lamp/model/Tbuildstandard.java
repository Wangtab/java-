package com.lamp.model;

/**
 * Created by lenovo on 2017/11/27.
 * 施工标准
 */
public class Tbuildstandard {

    private Integer id;
    private String buildname;//施工类型名称
    private String builddescribe;//施工描述

    private String opertime;//操作时间（添加时间）
    private Integer operid;//操作人

    private Integer createby;

    private String createTime;



    private int state;//0可用1不可用

    private Tsysuser oper;

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

    public Tsysuser getOper() {
        return oper;
    }

    public void setOper(Tsysuser oper) {
        this.oper = oper;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildname() {
        return buildname;
    }

    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    public String getBuilddescribe() {
        return builddescribe;
    }

    public void setBuilddescribe(String builddescribe) {
        this.builddescribe = builddescribe;
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime;
    }

    public Integer getOperid() {
        return operid;
    }

    public void setOperid(Integer operid) {
        this.operid = operid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
