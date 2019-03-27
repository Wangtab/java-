package com.lamp.model;

import java.util.HashMap;

public class Tsysuser {
    private Integer id;

    private String userName;

    private String password;

    private String realName;

    private String telNum;

    private Integer authId;

    private Integer operId;

    private String operTime;

    private Integer pid;

    private Integer orgId;

    private Byte delFlag;

    private Integer createBy;

    private String createTime;

    private Torganization torganization;

    public Torganization getTorganization() {
        return torganization;
    }

    public void setTorganization(Torganization torganization) {
        this.torganization = torganization;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum == null ? null : telNum.trim();
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setHashMap(HashMap<String,Object> dataMap){
        Integer id = Integer.parseInt(dataMap.get("id").toString());
        Integer authId = Integer.parseInt(dataMap.get("auth_id").toString());
        Integer orgId = Integer.parseInt(dataMap.get("org_id").toString());
        this.setId(id);
        this.setUserName(dataMap.get("user_name").toString());
        this.setRealName(dataMap.get("real_name").toString());
        this.setTelNum(dataMap.get("tel_num").toString());
        this.setAuthId(authId);
        this.setOrgId(orgId);
        Torganization torganization = new Torganization();
        torganization.setId(orgId);
        torganization.setOrgName(dataMap.get("org_name").toString());
        torganization.setOrgCode(dataMap.get("org_code").toString());
        this.setTorganization(torganization);

    }

}
