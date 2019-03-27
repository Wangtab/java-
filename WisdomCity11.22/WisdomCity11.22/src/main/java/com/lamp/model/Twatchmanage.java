package com.lamp.model;

public class Twatchmanage {
    private Integer id;

    private String elecname;

    private String elecboxname;

    private String focusaddr;

    private String circuit;

    private String sign;

    private Double longitude;

    private Double latitude;

    private String operId;

    private String operTime;

    private Integer delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getElecname() {
        return elecname;
    }

    public void setElecname(String elecname) {
        this.elecname = elecname == null ? null : elecname.trim();
    }

    public String getElecboxname() {
        return elecboxname;
    }

    public void setElecboxname(String elecboxname) {
        this.elecboxname = elecboxname == null ? null : elecboxname.trim();
    }

    public String getFocusaddr() {
        return focusaddr;
    }

    public void setFocusaddr(String focusaddr) {
        this.focusaddr = focusaddr == null ? null : focusaddr.trim();
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit == null ? null : circuit.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
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

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
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
}