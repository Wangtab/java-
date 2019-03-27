package com.lamp.model;

import java.io.Serializable;
import java.util.List;

public class TMenu implements Serializable,Comparable<TMenu> {

    private Integer id;

    private String cName;

    private String curl;

    private Integer pid;

    private Integer orderBy;

    private String img;

    private Integer operId;

    private String operTime;

    private List<TMenu> list;

    private List<TMenuBtn> btnList;

    public List<TMenuBtn> getBtnList() {
        return btnList;
    }

    public void setBtnList(List<TMenuBtn> btnList) {
        this.btnList = btnList;
    }

    public Integer getOperid() {
        return operId;
    }

    public void setOperid(Integer operid) {
        this.operId = operid;
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
        this.operTime = operTime;
    }

    public Integer getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<TMenu> getList() {
        return list;
    }

    public void setList(List<TMenu> list) {
        this.list = list;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public int compareTo(TMenu o) {
        return this.getOrderBy() -  o.getOrderBy();
    }
}
