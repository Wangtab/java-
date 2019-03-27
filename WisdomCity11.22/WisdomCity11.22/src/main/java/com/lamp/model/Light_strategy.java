package com.lamp.model;

public class Light_strategy {
    //光照调光策略实体类
    private Integer id;

    private String cname;//策略名称

    private Integer area_id;//所属区域

    private Integer road_id;//所属道路

    private Integer light_num;//道路照度值

    private Integer diming;//设置调光值

    private Integer is_open;//执行状态 0 开启 1关闭

    private Integer oper_id;//操作者

    private String oper_time;//操作时间

    private Integer createby;//创建人ID

    private String create_time;//创建时间

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

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    public Integer getRoad_id() {
        return road_id;
    }

    public void setRoad_id(Integer road_id) {
        this.road_id = road_id;
    }

    public Integer getLight_num() {
        return light_num;
    }

    public void setLight_num(Integer light_num) {
        this.light_num = light_num;
    }

    public Integer getDiming() {
        return diming;
    }

    public void setDiming(Integer diming) {
        this.diming = diming;
    }

    public Integer getIs_open() {
        return is_open;
    }

    public void setIs_open(Integer is_open) {
        this.is_open = is_open;
    }

    public Integer getOper_id() {
        return oper_id;
    }

    public void setOper_id(Integer oper_id) {
        this.oper_id = oper_id;
    }

    public String getOper_time() {
        return oper_time;
    }

    public void setOper_time(String oper_time) {
        this.oper_time = oper_time;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
