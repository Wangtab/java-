package com.lamp.model;

public class Tlamptype {
    private Integer id;

    private String lamptypename;

    private String lamptypedes;

    private String imgurl;

    private Integer power;

    private Integer factoryid;

    private String dimmingmode;

    private String operator;

    private String opertime;

    private Integer delflag;

    private Integer spower; //太阳能板功率

    private Integer bpower; //蓄电池板功率

    private String lampModel; //灯具型号

    private Integer createby; //创建人ID

    private String createTime; //创建时间

    private String lampFactory; //灯具工厂

    public String getLampFactory() {
        return lampFactory;
    }

    public void setLampFactory(String lampFactory) {
        this.lampFactory = lampFactory;
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

    public String getLampModel() {
        return lampModel;
    }

    public void setLampModel(String lampModel) {
        this.lampModel = lampModel;
    }

    public Integer getSpower() {
        return spower;
    }

    public void setSpower(Integer spower) {
        this.spower = spower;
    }

    public Integer getBpower() {
        return bpower;
    }

    public void setBpower(Integer bpower) {
        this.bpower = bpower;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLamptypename() {
        return lamptypename;
    }

    public void setLamptypename(String lamptypename) {
        this.lamptypename = lamptypename == null ? null : lamptypename.trim();
    }

    public String getLamptypedes() {
        return lamptypedes;
    }

    public void setLamptypedes(String lamptypedes) {
        this.lamptypedes = lamptypedes == null ? null : lamptypedes.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(Integer factoryid) {
        this.factoryid = factoryid;
    }

    public String getDimmingmode() {
        return dimmingmode;
    }

    public void setDimmingmode(String dimmingmode) {
        this.dimmingmode = dimmingmode == null ? null : dimmingmode.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime == null ? null : opertime.trim();
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }
}