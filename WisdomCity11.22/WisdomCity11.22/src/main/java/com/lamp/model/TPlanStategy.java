package com.lamp.model;

/**
 * 计划策略表
 */
public class TPlanStategy {

    private Integer id;

    private String cName;

    private Integer areaId;

    private String groupId;

    private Integer planId;

    private String weeks;

    private String startDate;

    private String overDate;

    private String startTime;

    private String endTime;

    private Integer dimming;

    private Integer deFlag;

    private Integer createBy;

    private String createTime;

    private Integer operId;

    private String operTime;

    private Integer senceId;

    public String getOverDate() {
        return overDate;
    }

    public void setOverDate(String overDate) {
        this.overDate = overDate;
    }

    public Integer getSenceId() {
        return senceId;
    }

    public void setSenceId(Integer senceId) {
        this.senceId = senceId;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
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

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getDimming() {
        return dimming;
    }

    public void setDimming(Integer dimming) {
        this.dimming = dimming;
    }

    public Integer getDeFlag() {
        return deFlag;
    }

    public void setDeFlag(Integer deFlag) {
        this.deFlag = deFlag;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
}
