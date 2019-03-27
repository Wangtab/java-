package com.lamp.model;

/**
 * Created by lenovo on 2017/11/27.
 * 库存
 */
public class Tstock {
    private Integer id;
    private Integer catId;//设备类型id
    private String equipName;//类别id(不清晰)
    private String totime;//入库时间
    private Integer stocknum;//库存数量
    private Integer changenum;//该变量
    private String changetime;//变化量
    private String endopertime;//最后一次操作时间
    private String node;//备注
    private Integer operid;//操作人id
    private String opertime;//操作时间
    private Integer state;//0有1无

    private String realName; //用户名

    private Integer createby; //创建ID
    private String  createTime; //创建时间

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

    public Integer getChangenum() {
        return changenum;
    }

    public void setChangenum(Integer changenum) {
        this.changenum = changenum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public Integer getStocknum() {
        return stocknum;
    }

    public void setStocknum(Integer stocknum) {
        this.stocknum = stocknum;
    }

    public String getChangetime() {
        return changetime;
    }

    public void setChangetime(String changetime) {
        this.changetime = changetime;
    }

    public String getEndopertime() {
        return endopertime;
    }

    public void setEndopertime(String endopertime) {
        this.endopertime = endopertime;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Integer getOperid() {
        return operid;
    }

    public void setOperid(Integer operid) {
        this.operid = operid;
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Tstock{" +
                "id=" + id +
                ", catId=" + catId +
                ", equipName='" + equipName + '\'' +
                ", totime='" + totime + '\'' +
                ", stocknum=" + stocknum +
                ", changenum=" + changenum +
                ", changetime='" + changetime + '\'' +
                ", endopertime='" + endopertime + '\'' +
                ", node='" + node + '\'' +
                ", operid=" + operid +
                ", opertime='" + opertime + '\'' +
                ", state=" + state +
                ", realName='" + realName + '\'' +
                ", createby=" + createby +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
