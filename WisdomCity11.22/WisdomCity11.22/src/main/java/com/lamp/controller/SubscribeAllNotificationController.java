package com.lamp.controller;

import com.huawei.utils.Constant;
import com.huawei.utils.HttpsUtil;
import com.huawei.utils.HuaWeiMethod;
import com.huawei.utils.JsonUtil;
import com.lamp.common.NotifyType;
import com.lamp.common.SimpleHttpServer;
import com.lamp.dao.TMonitorServiceMapper;
import com.lamp.dao.TPlatformSettingMapper;
import com.lamp.model.TDianXiIot;
import com.lamp.model.Tsysuser;
import com.lamp.utils.PlatformUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 平台订阅
 * Created by lenovo on 2018-06-29
 */
@Controller
@RequestMapping(value = "subscribe")
public class SubscribeAllNotificationController {

    @Autowired
    private TPlatformSettingMapper tPlatformSettingMapper;

    @Autowired
    private TMonitorServiceMapper tMonitorServiceMapper;

    /**
     * 订阅平台发送执行命令
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "sendSubscribeData")
    public @ResponseBody String sendSubscribeData(HttpServletRequest request,String id){
        try{
            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION; //订阅平台接口

            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            //开启服务信息
            SimpleHttpServer.startServer(8082);
            //获取用户的ID
            Tsysuser user =  PlatformUtils.getLoginUser(request);
            //查出IOT授权
            Map<String,Object> map = tPlatformSettingMapper.getPlatData(user.getId());
            //获取token
            String accessToken = HuaWeiMethod.getAccessToken(map);
            if(accessToken != null){
                //更新授权密钥
                TDianXiIot tDianXiIot   = new TDianXiIot();
                tDianXiIot.setId(Integer.parseInt(map.get("id").toString()));
                tDianXiIot.setAccessToken(accessToken);
                tDianXiIot.setTokenTime(new Date());
                tPlatformSettingMapper.updateDianXiIotDataById(tDianXiIot);
            }else {
                accessToken = map.get("accessToken").toString();
            }
            //获取回调接口
            Map<String,Object> map1 = tPlatformSettingMapper.telecomManage(Integer.parseInt(id));
            //接口函数
            String notifyType = map1.get("telecom_back_url").toString()+"?id="+map.get("id").toString();

            String callbackurl = map1.get("telecom_url").toString();
            //初始化接口
            Map<String, Object> paramSubscribe = new HashMap<>();
            paramSubscribe.put("notifyType", notifyType);
            paramSubscribe.put("callbackurl", callbackurl);

            String jsonRequest = JsonUtil.jsonObj2Sting(paramSubscribe);

            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

            HttpResponse httpResponse = httpsUtil.doPostJson(urlSubscribe, header, jsonRequest);

            String bodySubscribe = httpsUtil.getHttpResponseBody(httpResponse);

            System.out.println(bodySubscribe);
            //更新开启
            if (!bodySubscribe.isEmpty()){
                tPlatformSettingMapper.updateTelecomManage(Integer.parseInt(id));
            }

            return bodySubscribe;

        }catch (Exception e){

        }
        return  null;
    }

    /**
     * 通用方法
     * @param id
     * @return
     */
    public Map<String,Object> commonMethod(String id){
        //查出IOT授权
        Map<String,Object> map = tPlatformSettingMapper.getPlatDataByID(Integer.parseInt(id));
        //获取token
        String accessToken = HuaWeiMethod.getAccessToken(map);
        if(accessToken != null){
            //更新授权密钥
            TDianXiIot tDianXiIot   = new TDianXiIot();
            tDianXiIot.setId(Integer.parseInt(map.get("id").toString()));
            tDianXiIot.setAccessToken(accessToken);
            tDianXiIot.setTokenTime(new Date());
            tPlatformSettingMapper.updateDianXiIotDataById(tDianXiIot);
        }else {
            accessToken = map.get("accessToken").toString();
        }
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("appId",map.get("appId"));
        map1.put("accessToken",map.get("accessToken"));
        return map1;
    }

    /**
     * 订阅平台增加设备信息
     * @param id    电信ID
     */
    @RequestMapping(value = "deviceAdded")
    public void deviceAdded(String id){
        //调用工具接口
        Map<String,Object> map = commonMethod(id);
        //调用增加设备信息
        HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
        String bodySubscribe = huaWeiMethod.addDevice(map);
        System.out.println("bodySubscribe:"+bodySubscribe);
    }

    /**
     * 订阅平台设备信息改变
     * @param id 电信IOT平台id
     */
    @RequestMapping(value = "deviceInfoChanged")
    public void deviceInfoChanged(String id){
        //调用工具接口
        Map<String,Object> map = commonMethod(id);
        //调用订阅平台设备
        HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
        String bodySubscribe = huaWeiMethod.updateDevice(map);
    }

    /**
     * 订阅平台设备数据改变
     * @param id 电信IOT平台
     */
    @RequestMapping(value = "deviceDataChanged")
    public void deviceDataChanged(String id){
        //调用工具接口
        Map<String,Object> map = commonMethod(id);
        //调用订阅平台设备
        HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
        String bodySubscribe = huaWeiMethod.deviceDataChanged(map);
    }

    /**
     * 订阅平台设备删除
     * @param id 电信IOT平台
     */
    @RequestMapping(value = "deviceDeleted")
    public void deviceDeleted(String id){
        //调用工具接口
        Map<String,Object> map = commonMethod(id);
        //调用订阅平台设备
        HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
        String bodySubscribe = huaWeiMethod.deviceDeleted(map);
    }

    /**
     * 订阅平台信息响应
     * @param id 电信IOT平台
     */
    @RequestMapping(value = "messageConfirm")
    public void messageConfirm(String id){
        //调用工具接口
        Map<String,Object> map = commonMethod(id);
        //调用订阅平台设备
        HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
        String bodySubscribe = huaWeiMethod.subscribeMessageConfirm(map);
    }

    /**
     * 描述设备
     * @param id 电信IOT平台
     */
    @RequestMapping(value = "serviceInfoChanged")
    public void serviceInfoChanged(String id){
        //调用工具接口
        Map<String,Object> map = commonMethod(id);
        //调用订阅平台设备
        HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
        String bodySubscribe = huaWeiMethod.serviceInfoChanged(map);
    }

    /**
     * 响应命令
     * @param id 电信IOT平台
     */
    @RequestMapping(value = "commandRsp")
    public void commandRsp(String id){
        //调用工具接口
        Map<String,Object> map = commonMethod(id);
        //调用订阅平台设备
        HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
        String bodySubscribe = huaWeiMethod.commandRsp(map);
    }

    /**
     * 设备事件
     * @param id 电信IOT平台
     */
    @RequestMapping(value = "deviceEvent")
    public void deviceEvent(String id){
        //调用工具接口
        Map<String,Object> map = commonMethod(id);
        //调用订阅平台设备
        HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
        String bodySubscribe = huaWeiMethod.deviceEvent(map);
    }

    /**
     * 设备规则事件
     * @param id 电信IOT平台
     */
    @RequestMapping(value = "ruleEvent")
    public void ruleEvent(String id){
        //调用工具接口
        Map<String,Object> map = commonMethod(id);
        //调用订阅平台设备
        HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
        String bodySubscribe = huaWeiMethod.ruleEvent(map);
    }

    /**
     * 设备数据变化集合
     * @param id 电信IOT平台
     */
    @RequestMapping(value = "deviceDatasChanged")
    public void deviceDatasChanged(String id){
        //调用工具接口
        Map<String,Object> map = commonMethod(id);
        //调用订阅平台设备
        HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
        String bodySubscribe = huaWeiMethod.deviceDatasChanged(map);
        System.out.print("bodySubscribe"+bodySubscribe);
    }




}
