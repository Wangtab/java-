package com.lamp.model;

public class Tnbmanage {
    private Integer id;

    private Integer roadId;

    private Integer poleId;

    private String nbDevice;

    private String nbCode;

    private String nbName;

    private Integer typeId;

    private String nbSim;

    private Byte regFlag;

    private Integer operId;

    private String operTime;

    private Byte delFlag;

    private String logitude;

    private String latitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoadId() {
        return roadId;
    }

    public void setRoadId(Integer roadId) {
        this.roadId = roadId;
    }

    public Integer getPoleId() {
        return poleId;
    }

    public void setPoleId(Integer poleId) {
        this.poleId = poleId;
    }

    public String getNbDevice() {
        return nbDevice;
    }

    public void setNbDevice(String nbDevice) {
        this.nbDevice = nbDevice == null ? null : nbDevice.trim();
    }

    public String getNbCode() {
        return nbCode;
    }

    public void setNbCode(String nbCode) {
        this.nbCode = nbCode == null ? null : nbCode.trim();
    }

    public String getNbName() {
        return nbName;
    }

    public void setNbName(String nbName) {
        this.nbName = nbName == null ? null : nbName.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getNbSim() {
        return nbSim;
    }

    public void setNbSim(String nbSim) {
        this.nbSim = nbSim == null ? null : nbSim.trim();
    }

    public Byte getRegFlag() {
        return regFlag;
    }

    public void setRegFlag(Byte regFlag) {
        this.regFlag = regFlag;
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

    public String getLogitude() {
        return logitude;
    }

    public void setLogitude(String logitude) {
        this.logitude = logitude == null ? null : logitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }
}