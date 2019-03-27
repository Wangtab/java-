package com.lamp.model;

/**
 * Created by lenovo on 2018/2/7.
 */
public enum zxtype {

    ZX1(1,"每月[每月1号]"),Zx2(2,"每周[每周周一]"),Zx3(3,"每天");

    private int id;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private zxtype(int id, String text) {
        this.id = id;
        this.text = text;
    }


}
