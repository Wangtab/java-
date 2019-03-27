package com.lamp.controller;

import com.lamp.service.ScreenService;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  大屏幕数据总览 相关数据
 */
@Controller
@RequestMapping("screen")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    /**
     * 获取总功率
     */
    @RequestMapping("getSumPowers")
    public void getSumPowers(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        Map<String,Object> dataMap =  screenService.getSumPowers(orgCode);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 获取区域总能耗
     * @param response
     * @throws IOException
     */
    @RequestMapping("getAreasPowers")
    public void getAreasPowers(HttpServletResponse response,HttpServletRequest request) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        Map<String,Object> dataMap = screenService.getAreasPowers(orgCode);
        if(null == dataMap){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(dataMap).toString());
        }
    }

    /**
     * 获取能耗分析
     */
    @RequestMapping("getSumEnergyData")
    public void getSumEnergyData(HttpServletResponse response,HttpServletRequest request) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        Map<String, Object> map = screenService.getSumEnergyData(orgCode);
        response.getWriter().print(JSONArray.fromObject(map).toString());
    }

    /**
     *  获取大屏幕单条数据
     */
    @RequestMapping("getSingleData")
    public void getSingleData(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        Map<String,Object> dataMap = screenService.getSingleData(orgCode);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

}
