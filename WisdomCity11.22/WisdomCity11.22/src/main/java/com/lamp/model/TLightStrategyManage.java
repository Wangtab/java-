package com.lamp.model;

public class TLightStrategyManage {

    private Integer id;

    private String cname;

    private Integer contentId;

    private String exe_time;

    private Integer dimming;

    private Integer lampId;

    private Integer isWork;

    private Integer createby;

    private String createTime;

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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getExe_time() {
        return exe_time;
    }

    public void setExe_time(String exe_time) {
        this.exe_time = exe_time;
    }

    public Integer getDimming() {
        return dimming;
    }

    public void setDimming(Integer dimming) {
        this.dimming = dimming;
    }

    public Integer getLampId() {
        return lampId;
    }

    public void setLampId(Integer lampId) {
        this.lampId = lampId;
    }

    public Integer getIsWork() {
        return isWork;
    }

    public void setIsWork(Integer isWork) {
        this.isWork = isWork;
    }
}
