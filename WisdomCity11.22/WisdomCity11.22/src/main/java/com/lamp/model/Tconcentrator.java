package com.lamp.model;

public class Tconcentrator {
    private Integer id;

    private String concentratoraddr;

    private String concentratorname;

    private Integer concentratorkindid;

    private String concentratordes;

    private Integer areaid;

    private Integer ibox;

    private Double la;

    private Double lo;

    private Integer operator;

    private String opertime;

    private Integer roadId;

    private Integer createby;

    private String createTime;

    private String factoryName;

    private String cModel;

    private String cKind;

    public Integer getRoadId() {
        return roadId;
    }

    public void setRoadId(Integer roadId) {
        this.roadId = roadId;
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

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getCModel() {
        return cModel;
    }

    public void setCModel(String cModel) {
        this.cModel = cModel;
    }

    public String getCKind() {
        return cKind;
    }

    public void setCKind(String cKind) {
        this.cKind = cKind;
    }

    public Integer getIbox() {
        return ibox;
    }

    public void setIbox(Integer ibox) {
        this.ibox = ibox;
    }

    public Double getLa() {
        return la;
    }

    public void setLa(Double la) {
        this.la = la;
    }

    public Double getLo() {
        return lo;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public void setLo(Double lo) {
        this.lo = lo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConcentratoraddr() {
        return concentratoraddr;
    }

    public void setConcentratoraddr(String concentratoraddr) {
        this.concentratoraddr = concentratoraddr == null ? null : concentratoraddr.trim();
    }

    public String getConcentratorname() {
        return concentratorname;
    }

    public void setConcentratorname(String concentratorname) {
        this.concentratorname = concentratorname == null ? null : concentratorname.trim();
    }

    public Integer getConcentratorkindid() {
        return concentratorkindid;
    }

    public void setConcentratorkindid(Integer concentratorkindid) {
        this.concentratorkindid = concentratorkindid;
    }

    public String getConcentratordes() {
        return concentratordes;
    }

    public void setConcentratordes(String concentratordes) {
        this.concentratordes = concentratordes == null ? null : concentratordes.trim();
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime == null ? null : opertime.trim();
    }

}

