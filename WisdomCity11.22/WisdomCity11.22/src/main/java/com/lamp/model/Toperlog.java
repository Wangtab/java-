package com.lamp.model;

public class Toperlog {
    private Integer id;

    private String username;//废弃

    private String operkind;

    private String opermodule;//废弃

    private String operdes;

    private String opertime;

    private Integer createby;//废弃

    private String createTime;//废弃

    private Integer userid;

    private Integer menuid;

    private Integer kind_id;


    public Integer getKind_id() {
        return kind_id;
    }

    public void setKind_id(Integer kind_id) {
        this.kind_id = kind_id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getOperkind() {
        return operkind;
    }

    public void setOperkind(String operkind) {
        this.operkind = operkind == null ? null : operkind.trim();
    }

    public String getOpermodule() {
        return opermodule;
    }

    public void setOpermodule(String opermodule) {
        this.opermodule = opermodule == null ? null : opermodule.trim();
    }

    public String getOperdes() {
        return operdes;
    }

    public void setOperdes(String operdes) {
        this.operdes = operdes == null ? null : operdes.trim();
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime == null ? null : opertime.trim();
    }
}