package com.lamp.controller;

/**
 * Created by lenovo on 2017/11/24.
 */
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;



public class BaseController {
    private static final long serialVersionUID = 6357869213649815390L;
    /**
     * 得到ModelAndView
     */
    public ModelAndView getModelAndView(){
        return new ModelAndView();
    }

    /**
     * 得到request对象
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        return request;
    }





}
