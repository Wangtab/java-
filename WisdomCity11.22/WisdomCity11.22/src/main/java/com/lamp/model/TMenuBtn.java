package com.lamp.model;

public class TMenuBtn {

    private Integer id;

    private Integer menuId;

    private String btnName;

    private String style_css;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getStyle_css() {
        return style_css;
    }

    public void setStyle_css(String style_css) {
        this.style_css = style_css;
    }
}
