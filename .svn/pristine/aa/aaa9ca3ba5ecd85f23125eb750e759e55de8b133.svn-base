package com.lamp.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lenovo on 2017-12-08.
 *
 */
public class LoginUtils {



    /**
     * 保存账号密码(记住我)
     * @param username
     * @param password
     * @param request
     * @param response
     */
    public static void remeberMe(String username, String password,
                                 HttpServletRequest request, HttpServletResponse response){
        //1：创建2个Cookie，分别存放用户名和密码
        //Cookie中不能存放中文
        try {
            username = URLEncoder.encode(username, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Cookie nameCookie = new Cookie("name",username);
        Cookie passwordCookie = new Cookie("password",password);

        //判断是否自动登录
        String autoLogin = request.getParameter("autoLogin");
        Cookie autoCookie = new Cookie("autoLogin","");
        if (autoLogin!=null && autoLogin.equals("yes")){
             autoCookie = new Cookie("autoLogin","checked");
        }
        //2：判断页面中的复选框是否被选中，选中设置，不选中就不设置
        String remeberMe = request.getParameter("saveLogin");

        //设置有效路径
        nameCookie.setPath(request.getContextPath()+"/");
        passwordCookie.setPath(request.getContextPath()+"/");
        //选中
        if(remeberMe!=null && remeberMe.equals("yes")){
            System.out.println("------remeberMe------");
            //设置有效时间
            nameCookie.setMaxAge(7*24*60*60);//1周
            passwordCookie.setMaxAge(7*24*60*60);//1周
            autoCookie.setMaxAge(7*24*60*60);//1周
        }else{
            //清空
            nameCookie.setMaxAge(0);//
            passwordCookie.setMaxAge(0);//
            autoCookie.setMaxAge(0);//
        }
        //3：将Cookie存放到response对象
        response.addCookie(nameCookie);
        response.addCookie(passwordCookie);
        response.addCookie(autoCookie);

    }
}
