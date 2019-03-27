package com.lamp.model;

import java.util.Date;
import java.util.List;

public class Torganization {
    private Integer id;

    private String orgName;//组织名字

    private String orgDes;//组织描述

    private String superOrg;//上级组织

    private String manager;

    private Integer operid;

    private String operTime;

    private Integer delFlag;//删除标识

    private List<Torganization> torganizationList;

    private Integer pid;//上级节点

    private String orgCode;//组织代码

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<Torganization> getTorganizationList() {
        return torganizationList;
    }

    public void setTorganizationList(List<Torganization> torganizationList) {
        this.torganizationList = torganizationList;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgDes() {
        return orgDes;
    }

    public void setOrgDes(String orgDes) {
        this.orgDes = orgDes == null ? null : orgDes.trim();
    }

    public String getSuperOrg() {
        return superOrg;
    }

    public void setSuperOrg(String superOrg) {
        this.superOrg = superOrg == null ? null : superOrg.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public Integer getOperid() {
        return operid;
    }

    public void setOperid(Integer operid) {
        this.operid = operid;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}