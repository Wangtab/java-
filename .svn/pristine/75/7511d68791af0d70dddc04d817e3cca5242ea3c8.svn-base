package com.huawei.utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lamp.common.HuaWeiCommon;
import org.apache.http.HttpResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 华为接口
 */
public class HuaWeiMethod {

    /**
     * 计算时间差
     * @param dateTime
     * @return
     */
    public static Integer timeNum(Date dateTime){
        //现在时间
        Date date = new Date();
        long  sDate =  date.getTime();
        long eDate = dateTime.getTime();
        int num = (int)((sDate - eDate)/1000); //多少秒
        return num;
    }

    /**
     * 获取IOT-NB平台授权accessToken
     * @param map
     * @return
     */
    public static String getAccessToken(Map<String,Object> map){
        try {
            Date tokenTime = (Date)map.get("tokenTime");
            String accessToken = null;
            //查看密钥是否失效
            int dateNum = HuaWeiMethod.timeNum(tokenTime);
            //获取注册链接
            String urlLogin = Constant.APP_AUTH;
            if(dateNum > 3600){
                Map<String,String> map1 = new HashMap<String,String>();
                map1.put("appId", map.get("appid").toString());
                map1.put("secret", map.get("secret").toString());
                //实例化HttpsUtil协议
                HttpsUtil httpsUtil = new HttpsUtil();
                StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(urlLogin, map1);
                Map<String, String> data = new HashMap<>();
                data = JsonUtil.jsonString2SimpleObj(responseLogin.getContent(), data.getClass());
                return data.get("accessToken");
            }

        }catch (Exception e){

        }
        return null;
    }

    /**
     * 注册设备
     * @param map
     * @return
     */
    public static Map<String, String> RegisterDirectlyConnectedDevice(Map<String,Object> map){
        try {
            String urlReg = Constant.REGISTER_DEVICE;
            //实例化HttpsUtil协议
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            //封装参数
            Map<String,Object> paramReg = new HashMap<String,Object>();
            paramReg.put("verifyCode", map.get("verifyCode").toString().toUpperCase());
            paramReg.put("nodeId", map.get("nodeId").toString().toUpperCase());
            paramReg.put("timeout", Integer.parseInt(map.get("timeout").toString()));
            paramReg.put("name", map.get("name").toString());
            paramReg.put("manufacturerId", map.get("manufacturerId").toString());
            paramReg.put("manufacturerName", map.get("manufacturerName").toString());
            paramReg.put("deviceType",  map.get("deviceType").toString());
            paramReg.put("model", map.get("model").toString());
            paramReg.put("protocolType",map.get("protocolType").toString());

            String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);

            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            StreamClosedHttpResponse responseReg = httpsUtil.doPostJsonGetStatusLine(urlReg, header, jsonRequest);

            Map<String, String> data = new HashMap<>();

            return JsonUtil.jsonString2SimpleObj(responseReg.getContent(), data.getClass());
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 删除设备
     * @param map
     * @return
     */
    public static String DeleteDirectlyConnectedDevice(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlDelete = Constant.DELETE_DEVICE + "/" +map.get("deviceId").toString();

            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            StreamClosedHttpResponse responseDelete = httpsUtil.doDeleteGetStatusLine(urlDelete, header);

            System.out.println(responseDelete.getContent());

            return responseDelete.getContent();
        }catch (Exception e){

        }
        return null;
    }
    /**
     * 间接的删除删除设备
     * @param map
     * @return
     */
    public static Map<String,Object> RemoveNonDirectlyConnectedDevice(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String path = Constant.REMOVE_INDIRECT_DEVICE;
            path =String.format(path, map.get("deviceId").toString(), map.get("serviceId").toString());

            Map<String, String> commandNA2CloudHeader = new HashMap<>();
            commandNA2CloudHeader.put("mode", map.get("mode").toString());
            commandNA2CloudHeader.put("method", map.get("method").toString());
            commandNA2CloudHeader.put("toType", map.get("toType").toString());
            commandNA2CloudHeader.put("callbackURL", map.get("callbackURL").toString());

            Map<String, String> commandNA2CloudBody = new HashMap<>();
            commandNA2CloudBody.put("cmdBody", "remove indirect device cmd body content.");

            Map<String, Object> paramCommandNA2Cloud = new HashMap<>();
            paramCommandNA2Cloud.put("header", commandNA2CloudHeader);
            paramCommandNA2Cloud.put("body", commandNA2CloudBody);

            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            String jsonRequest = JsonUtil.jsonObj2Sting(paramCommandNA2Cloud);

            StreamClosedHttpResponse responseData = httpsUtil.doPostJsonGetStatusLine(path, header, jsonRequest);
            Map<String, Object> data = new HashMap<>();
            return JsonUtil.jsonString2SimpleObj(responseData.getContent(), data.getClass());
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 查询设备工作状态
     * @param map
     * @return
     */
    public static String QueryDeviceActivationStatus(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlDeviceActivationStatus = Constant.QUERY_DEVICE_ACTIVATION_STATUS + "/" + map.get("deviceId").toString();

            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            StreamClosedHttpResponse bodyDeviceActivationStatus = httpsUtil.doGetWithParasGetStatusLine(
                    urlDeviceActivationStatus, null, header);

            System.out.println(bodyDeviceActivationStatus.getContent());

            return bodyDeviceActivationStatus.getContent();
        }catch (Exception e){

        }
        return null;
    }
    /**
     * 查询单个设备信息
     * @param map
     * @return
     */
    public static HashMap<String,Object> QueryDeviceData(Map<String,Object> map){
        try {
            //协议
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            //参数
            String urlQueryDeviceData = Constant.QUERY_DEVICE_DATA + "/" + map.get("deviceId").toString();

            Map<String, String> paramQueryDeviceData = new HashMap<>();
            paramQueryDeviceData.put("appId", map.get("appId").toString());

            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            StreamClosedHttpResponse bodyQueryDeviceData = httpsUtil.doGetWithParasGetStatusLine(urlQueryDeviceData,
                    paramQueryDeviceData, header);

            HashMap<String, Object> data = new HashMap<>();

            return JsonUtil.jsonString2SimpleObj(bodyQueryDeviceData.toString(), data.getClass());
        }catch (Exception e){
            System.out.println("有异常");
        }
        return null;
    }

    /**
     * IOT-NB平台设备调光、开关灯
     * @param level
     * @return
     */
    public static Map<String,Object> dimmingParam(Integer level)throws Exception{
        //参数当level为0的时候为关灯不为0的时候为调光
        String serviceId = "Dimming";
        String method = "DIMMING_LEVL";
        ObjectNode paras = JsonUtil.convertObject2ObjectNode("{ \"mode\": 1, \"level\": "+level+", \"channel\": 1 }");
        Map<String, Object> paramCommand = new HashMap<>();
        paramCommand.put("serviceId", serviceId);
        paramCommand.put("method", method);
        paramCommand.put("paras", paras);
        return paramCommand;
    }
    /**
     * IOT-NB平台设备控制
     * @param map
     * @return
     */
    public static String IOTNBSwitchLightAndDimming(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            //更新设备地址
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;

            String callbackUrl = Constant.DEVICE_ADDED_CALLBACK_URL;
            //调光开关灯参数
            Map<String,Object> param = dimmingParam(Integer.parseInt(map.get("level").toString()));
            //设备参数
            Map<String,Object> paramPost = new HashMap<String,Object>();
            paramPost.put("deviceId", map.get("deviceId"));
            paramPost.put("command", param);
            paramPost.put("callbackUrl", callbackUrl);
            String jsonRequest = JsonUtil.jsonObj2Sting(paramPost);
            //发送获取数据
            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd, header, jsonRequest);

            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);

            return responseBody;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 修改设备信息
     */
    public String ModifyDeviceInfo(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay(HuaWeiCommon.PROJECT_PATH);

            String urlModifyDeviceInfo = Constant.MODIFY_DEVICE_INFO + "/" + map.get("deviceId").toString();

            Map<String, Object> paramModifyDeviceInfo = new HashMap<>();
            paramModifyDeviceInfo.put("manufacturerId", map.get("manufacturerId").toString());
            paramModifyDeviceInfo.put("manufacturerName", map.get("manufacturerName").toString());
            paramModifyDeviceInfo.put("deviceType", map.get("deviceType").toString());
            paramModifyDeviceInfo.put("model", map.get("model").toString());
            paramModifyDeviceInfo.put("protocolType", map.get("protocolType").toString());

            String jsonRequest = JsonUtil.jsonObj2Sting(paramModifyDeviceInfo);

            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());
            StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo,
                    header, jsonRequest);

            return responseModifyDeviceInfo.getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量查询设备
     * @param map
     * @return
     */
    public String QueryDevices(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlQueryDevices = Constant.QUERY_DEVICES;

            Map<String, String> paramQueryDevices = new HashMap<>();
            paramQueryDevices.put("appId", map.get("appId").toString());
            paramQueryDevices.put("pageNo", map.get("pageNo").toString());
            paramQueryDevices.put("pageSize", map.get("pageSize").toString());

            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            StreamClosedHttpResponse bodyQueryDevices = httpsUtil.doGetWithParasGetStatusLine(urlQueryDevices,
                    paramQueryDevices, header);

            System.out.println(bodyQueryDevices.getContent());

            return bodyQueryDevices.getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





    /**电信IOT平台订阅功能*/

    /**
     * 添加设备信息
     * @param map
     * @return
     */
    public String addDevice(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;

            String callbackurl_deviceAdded = Constant.DEVICE_ADDED_CALLBACK_URL;
            String notifyType_deviceAdded = Constant.DEVICE_ADDED;

            Map<String, Object> paramSubscribe = new HashMap<>();
            paramSubscribe.put("notifyType", notifyType_deviceAdded);
            paramSubscribe.put("callbackurl", callbackurl_deviceAdded);

            String jsonRequest = JsonUtil.jsonObj2Sting(paramSubscribe);

            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse httpResponse = httpsUtil.doPostJson(urlSubscribe, header, jsonRequest);

            String bodySubscribe = httpsUtil.getHttpResponseBody(httpResponse);

            return bodySubscribe;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 修改设备信息
     * @param map
     * @return
     */
    public String updateDevice(Map<String,Object> map){
        try{
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;

            String callbackurl_deviceInfoChanged = Constant.DEVICE_INFO_CHANGED_CALLBACK_URL;
            String notifyType_deviceInfoChanged = Constant.DEVICE_INFO_CHANGED;

            Map<String, Object> paramSubscribe_deviceInfoChanged = new HashMap<>();
            paramSubscribe_deviceInfoChanged.put("notifyType", notifyType_deviceInfoChanged);
            paramSubscribe_deviceInfoChanged.put("callbackurl", callbackurl_deviceInfoChanged);

            String jsonRequest_deviceInfoChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceInfoChanged);

            Map<String, String> header_deviceInfoChanged = new HashMap<>();
            header_deviceInfoChanged.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header_deviceInfoChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse httpResponse_deviceInfoChanged = httpsUtil.doPostJson(urlSubscribe, header_deviceInfoChanged, jsonRequest_deviceInfoChanged);

            String bodySubscribe_deviceInfoChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceInfoChanged);

            System.out.println(bodySubscribe_deviceInfoChanged);

            return bodySubscribe_deviceInfoChanged;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 设备数据变化
     * @param map
     * @return
     */
    public String deviceDataChanged(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;
            String callbackurl_deviceDataChanged = Constant.DEVICE_DATA_CHANGED_CALLBACK_URL;
            String notifyType_deviceDataChanged = Constant.DEVICE_DATA_CHANGED;

            Map<String, Object> paramSubscribe_deviceDataChanged = new HashMap<>();
            paramSubscribe_deviceDataChanged.put("notifyType", notifyType_deviceDataChanged);
            paramSubscribe_deviceDataChanged.put("callbackurl", callbackurl_deviceDataChanged);

            String jsonRequest_deviceDataChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDataChanged);

            Map<String, String> header_deviceDataChanged = new HashMap<>();
            header_deviceDataChanged.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header_deviceDataChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse httpResponse_deviceDataChanged = httpsUtil.doPostJson(urlSubscribe, header_deviceDataChanged, jsonRequest_deviceDataChanged);

            String bodySubscribe_deviceDataChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDataChanged);

            System.out.println(bodySubscribe_deviceDataChanged);


            return bodySubscribe_deviceDataChanged;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 订阅平台删除设备
     * @param map
     * @return
     */
    public String deviceDeleted(Map<String,Object> map){
        try{
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;
            String callbackurl_deviceDeleted = Constant.DEVICE_DELETED_CALLBACK_URL;
            String notifyType_deviceDeleted = Constant.DEVICE_DELETED;

            Map<String, Object> paramSubscribe_deviceDeleted = new HashMap<>();
            paramSubscribe_deviceDeleted.put("notifyType", notifyType_deviceDeleted);
            paramSubscribe_deviceDeleted.put("callbackurl", callbackurl_deviceDeleted);

            String jsonRequest_deviceDeleted = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDeleted);

            Map<String, String> header_deviceDeleted = new HashMap<>();
            header_deviceDeleted.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header_deviceDeleted.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse httpResponse_deviceDeleted = httpsUtil.doPostJson(urlSubscribe, header_deviceDeleted, jsonRequest_deviceDeleted);

            String bodySubscribe_deviceDeleted = httpsUtil.getHttpResponseBody(httpResponse_deviceDeleted);

            System.out.println(bodySubscribe_deviceDeleted);

            return bodySubscribe_deviceDeleted;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 消息确认
     * @param map
     * @return
     */
    public String subscribeMessageConfirm(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;

            String callbackurl_messageConfirm = Constant.MESSAGE_CONFIRM_CALLBACK_URL;
            String notifyType_messageConfirm = Constant.MESSAGE_CONFIRM;

            Map<String, Object> paramSubscribe_messageConfirm = new HashMap<>();
            paramSubscribe_messageConfirm.put("notifyType", notifyType_messageConfirm);
            paramSubscribe_messageConfirm.put("callbackurl", callbackurl_messageConfirm);

            String jsonRequest_messageConfirm = JsonUtil.jsonObj2Sting(paramSubscribe_messageConfirm);

            Map<String, String> header_messageConfirm = new HashMap<>();
            header_messageConfirm.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header_messageConfirm.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse httpResponse_messageConfirm = httpsUtil.doPostJson(urlSubscribe, header_messageConfirm, jsonRequest_messageConfirm);

            String bodySubscribe_messageConfirm = httpsUtil.getHttpResponseBody(httpResponse_messageConfirm);

            System.out.println(bodySubscribe_messageConfirm);

            return bodySubscribe_messageConfirm;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 描述设备信息
     * @param map
     * @return
     */
    public String serviceInfoChanged(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;
            String callbackurl_serviceInfoChanged = Constant.SERVICE_INFO_CHANGED_CALLBACK_URL;
            String notifyType_serviceInfoChanged = Constant.SERVICE_INFO_CHANGED;

            Map<String, Object> paramSubscribeserviceInfoChanged = new HashMap<>();
            paramSubscribeserviceInfoChanged.put("notifyType", notifyType_serviceInfoChanged);
            paramSubscribeserviceInfoChanged.put("callbackurl", callbackurl_serviceInfoChanged);

            String jsonRequestserviceInfoChanged = JsonUtil.jsonObj2Sting(paramSubscribeserviceInfoChanged);

            Map<String, String> headerserviceInfoChanged = new HashMap<>();
            headerserviceInfoChanged.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            headerserviceInfoChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse httpResponseserviceInfoChanged = httpsUtil.doPostJson(urlSubscribe, headerserviceInfoChanged, jsonRequestserviceInfoChanged);

            String bodySubscribeserviceInfoChanged = httpsUtil.getHttpResponseBody(httpResponseserviceInfoChanged);

            System.out.println(bodySubscribeserviceInfoChanged);

            return bodySubscribeserviceInfoChanged;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 设备响应信息
     * @param map
     * @return
     */
    public String commandRsp(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;
            String callbackurl_commandRsp = Constant.COMMAND_RSP_CALLBACK_URL;
            String notifyType_commandRsp = Constant.COMMAND_RSP;

            Map<String, Object> paramSubscribecommandRsp = new HashMap<>();
            paramSubscribecommandRsp.put("notifyType", notifyType_commandRsp);
            paramSubscribecommandRsp.put("callbackurl", callbackurl_commandRsp);

            String jsonRequestcommandRsp = JsonUtil.jsonObj2Sting(paramSubscribecommandRsp);

            Map<String, String> headercommandRsp = new HashMap<>();
            headercommandRsp.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            headercommandRsp.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse httpResponsecommandRsp = httpsUtil.doPostJson(urlSubscribe, headercommandRsp, jsonRequestcommandRsp);

            String bodySubscribecommandRsp = httpsUtil.getHttpResponseBody(httpResponsecommandRsp);

            System.out.println(bodySubscribecommandRsp);

            return bodySubscribecommandRsp;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 设备事件
     * @param map
     * @return
     */
    public String deviceEvent(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;
            String callbackurl_deviceEvent = Constant.DEVICE_EVENT_CALLBACK_URL;
            String notifyType_deviceEvent = Constant.DEVICE_EVENT;

            Map<String, Object> paramSubscribe_deviceEvent = new HashMap<>();
            paramSubscribe_deviceEvent.put("notifyType", notifyType_deviceEvent);
            paramSubscribe_deviceEvent.put("callbackurl", callbackurl_deviceEvent);

            String jsonRequest_deviceEvent = JsonUtil.jsonObj2Sting(paramSubscribe_deviceEvent);

            Map<String, String> header_deviceEvent = new HashMap<>();
            header_deviceEvent.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header_deviceEvent.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse httpResponse_deviceEvent = httpsUtil.doPostJson(urlSubscribe, header_deviceEvent, jsonRequest_deviceEvent);

            String bodySubscribe_deviceEvent = httpsUtil.getHttpResponseBody(httpResponse_deviceEvent);

            System.out.println(bodySubscribe_deviceEvent);

            return bodySubscribe_deviceEvent;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 设备规则事件
     * @param map
     * @return
     */
    public String ruleEvent(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;
            String callbackurl_ruleEvent = Constant.RULE_EVENT_CALLBACK_URL;
            String notifyType_ruleEvent = Constant.RULE_EVENT;

            Map<String, Object> paramSubscribe_ruleEvent = new HashMap<>();
            paramSubscribe_ruleEvent.put("notifyType", notifyType_ruleEvent);
            paramSubscribe_ruleEvent.put("callbackurl", callbackurl_ruleEvent);

            String jsonRequest_ruleEvent = JsonUtil.jsonObj2Sting(paramSubscribe_ruleEvent);

            Map<String, String> header_ruleEvent = new HashMap<>();
            header_ruleEvent.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header_ruleEvent.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse httpResponse_ruleEvent = httpsUtil.doPostJson(urlSubscribe, header_ruleEvent, jsonRequest_ruleEvent);

            String bodySubscribe_ruleEvent = httpsUtil.getHttpResponseBody(httpResponse_ruleEvent);

            System.out.println(bodySubscribe_ruleEvent);

            return bodySubscribe_ruleEvent;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 设备数据变化集合
     * @param map
     * @return
     */
    public String deviceDatasChanged(Map<String,Object> map){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();

            String urlSubscribe = Constant.SUBSCRIBE_NOTIFYCATION;
            String callbackurl_deviceDatasChanged = Constant.DEVICE_DATAS_CHANGED_CALLBACK_URL;
            String notifyType_deviceDatasChanged = Constant.DEVICE_DATAS_CHANGED;

            Map<String, Object> paramSubscribe_deviceDatasChanged = new HashMap<>();
            paramSubscribe_deviceDatasChanged.put("notifyType", notifyType_deviceDatasChanged);
            paramSubscribe_deviceDatasChanged.put("callbackurl", callbackurl_deviceDatasChanged);

            String jsonRequest_deviceDatasChanged = JsonUtil.jsonObj2Sting(paramSubscribe_deviceDatasChanged);

            Map<String, String> header_deviceDatasChanged = new HashMap<>();
            header_deviceDatasChanged.put(Constant.HEADER_APP_KEY, map.get("appId").toString());
            header_deviceDatasChanged.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + map.get("accessToken").toString());

            HttpResponse httpResponse_deviceDatasChanged = httpsUtil.doPostJson(urlSubscribe, header_deviceDatasChanged, jsonRequest_deviceDatasChanged);

            String bodySubscribe_deviceDatasChanged = httpsUtil.getHttpResponseBody(httpResponse_deviceDatasChanged);

            System.out.println(bodySubscribe_deviceDatasChanged);
            return bodySubscribe_deviceDatasChanged;
        }catch (Exception e){

        }
        return null;
    }


    /**
     * 发送命令
     * @param deviceId 设备ID号
     * @param dimming 调光率
     */
    public String sendCommand(String deviceId,String dimming){
        try {
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay(HuaWeiCommon.PROJECT_PATH);
            String accessToken = HuaWeiCommon.DX_ACCESS_TOKEN_VALUE;
            String appId = Constant.APPID;
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;
            String callbackUrl = Constant.DEVICE_ADDED_CALLBACK_URL;

            String serviceId = "Dimming";
            String method = "DIMMING_LEVL";
            String jsonInfo = "{ \"mode\": 1, \"level\": "+dimming+", \"channel\": 1 }";
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(jsonInfo);
            Map<String, Object> paramCommand = new HashMap<>();
            paramCommand.put("serviceId", serviceId);
            paramCommand.put("method", method);
            paramCommand.put("paras", paras);

            Map<String, Object> paramPostAsynCmd = new HashMap<>();
            paramPostAsynCmd.put("deviceId", deviceId);
            paramPostAsynCmd.put("command", paramCommand);
            paramPostAsynCmd.put("callbackUrl", callbackUrl);

            String jsonRequest = JsonUtil.jsonObj2Sting(paramPostAsynCmd);
            Map<String, String> header = new HashMap<>();
            header.put(Constant.HEADER_APP_KEY, appId);
            header.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);

            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd, header, jsonRequest);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            System.out.println(responsePostAsynCmd.getStatusLine());
            System.out.println(responseBody);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
