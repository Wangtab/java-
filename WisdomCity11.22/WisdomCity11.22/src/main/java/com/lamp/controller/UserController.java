package com.lamp.controller;
import com.lamp.common.CitySettingCommon;
import com.lamp.model.Tsysuser;
import com.lamp.service.TuserService;
import com.lamp.utils.LoginUtils;
import com.lamp.utils.PlatformUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by lc on 2017/11/1.
 */
@Controller
public class UserController {

    @Autowired
    private TuserService tuserService;
    /**
     * @Author:MSI
     * @Description:用户登录验证
     * @Params:[request,response,username,password]
     * @Date:2017/11/6
     */
    @RequestMapping("/login")
    public void login(String username,String password,HttpServletRequest request,HttpServletResponse response) throws Exception {
        try {
            HashMap<String,Object> userMap = tuserService.validedLoginUser(username,password);
            if(userMap == null || userMap.size() == 0){
                response.getWriter().println(0);
                return;
            }
            Tsysuser user = new Tsysuser();
            user.setHashMap(userMap);
            String ip = PlatformUtils.getIpAddress(request);
            tuserService.addUserLog(user,ip); //增加登录日志
            LoginUtils.remeberMe(username, password,request,response); //保存到cookie中
            request.getSession().setAttribute("loginUser", user); //保存用户信息到session中
            dealLogoAndTemperature(request,userMap);
            response.getWriter().println(1);
        } catch (Exception e) {
            System.out.println("ExceptionError:" + e);
            response.getWriter().println(0);
        }
    }

    //保存logo 和天气信息
    private void dealLogoAndTemperature(HttpServletRequest request,HashMap<String,Object> dataMap){
        Object logoName = dataMap.get("name");
        Object logoPic = dataMap.get("imager");
        if(null== logoName){
            logoName = "智慧城市公共设施管理平台";
        }
        if(null == logoPic){
            logoPic = "images/logo.png";
        }

        request.getSession().setAttribute("logoName",logoName);
        request.getSession().setAttribute("logoPic",logoPic);
        Object lowTemp = dataMap.get("low_temperature");
        Object highTemp = dataMap.get("high_temperature");
        Object temDetail = dataMap.get("temperature_detail");
        Object weatherPic = dataMap.get("weather_pic");
        if(null == lowTemp){
            lowTemp = 0;
        }
        if(null == highTemp){
            highTemp = 0;
        }
        if(null == weatherPic){
            weatherPic = CitySettingCommon.WEATHER_DEFAULT_PIC;
        }
        request.getSession().setAttribute("lowTemp",lowTemp);
        request.getSession().setAttribute("heightTemp",highTemp);
        request.getSession().setAttribute("temp_detail",temDetail);
        request.getSession().setAttribute("weatherPic",weatherPic);
    }

    /**
     * 退出系统
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginOut.do")
    public String loginOut(HttpServletRequest request) {
        request.getSession().setAttribute("loginUser",null);
        return "login";
    }

    /**
     * 返回菜单页
     */
    @RequestMapping("/lampModule")
    public String lampModule() {
        return "menu";
    }

}

