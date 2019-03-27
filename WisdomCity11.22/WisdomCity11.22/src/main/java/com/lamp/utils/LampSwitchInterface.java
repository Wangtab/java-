package com.lamp.utils;

import com.lamp.common.MonitorCommon;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 灯具开关接口
 */
public class LampSwitchInterface {


    /**
     *  IOT-NB平台参数的问题
     * @param map
     * @return
     */
    public static HashMap<String,Object> getIOTNBByData(Map<String,Object> map){
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("record_time",GetLocalTimes.getNowTime());
        hashMap.put("eventTime",(Date)map.get("dateTime"));      //上传时间
        hashMap.put("signal",map.get("signal"));              //NB信号强度
        hashMap.put("state",map.get("state"));               //控制器状态
        hashMap.put("record_dimming",map.get("level"));               //调光
        hashMap.put("record_ele",map.get("current"));             //电流
        hashMap.put("record_vol",map.get("voltage"));             //电压
        hashMap.put("record_power",map.get("active_power"));        //功率
        hashMap.put("pf",map.get("pf"));                  //功率因数
        hashMap.put("energy",map.get("energy"));              //总电量
        hashMap.put("working_hours",map.get("work_time"));           //工作时间
        hashMap.put("luminance",map.get("luminance"));           //控制的照度
        hashMap.put("ICCID",map.get("ICCID"));               //SIM卡的设备标签
        hashMap.put("IMEI",map.get("IMEI"));                //NB模块的设备号
        hashMap.put("IMSI",map.get("IMSI"));                //SIM卡的设备标示
        hashMap.put("GPS",map.get("GPS"));                 //表示GPS数据
        hashMap.put("version",map.get("version"));             //固件版本号
        hashMap.put("log",map.get("log"));                 //日志信息
        hashMap.put("conSate",MonitorCommon.NB_LAMP_CONNECT_STATE_ONlINE);
        return hashMap;
    }

    /**
     * 发送控制请求 到硬件接口
     * @param protocol 协议 0:代表UDP协议 1:代表COAP协议
     * @param dimming 调光数值
     * @param NBNumber NB设备ID
     */
    public boolean sendInfo(Integer protocol,Integer dimming,String NBNumber){
        String httpurl = "";
        try {
            if(MonitorCommon.NB_UDP == protocol){//DUP接口
                httpurl = "http://111.231.132.234:8091/cmd,"+ NBNumber + "," + dealDimming(dimming);
            } else if(MonitorCommon.NB_COAP == protocol){//COAP协定
                httpurl = "http://182.254.141.187:5683/cmd,"+ NBNumber + "," + dealDimming(dimming);
            }
            HttpURLConnection.getHttpResponse(httpurl);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据NB设备ID 获取相应的电流、电压、功率 信息
     * @param protocol 协议 0:代表UDP协议 1:代表COAP协议
     * @param NBNumber
     */
    public HashMap<String,Object> getLampDataByNbId(Integer protocol,String NBNumber){
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("record_temp","");
        dataMap.put("record_pvol","");
        dataMap.put("record_pele","");
        dataMap.put("record_bvol","");
        String httpurl = "";
        if(MonitorCommon.NB_UDP == protocol){//DUP接口
            httpurl = "http://111.231.132.234:8091/getdata," +NBNumber;
        } else if(MonitorCommon.NB_COAP == protocol){//COAP协定
            httpurl = "http://182.254.141.187:5683/getdata," + NBNumber;
        }

        try {
            Thread.sleep(50);
            String message = HttpURLConnection.getHttpResponse(httpurl);
            if(StringUtils.isEmpty(message) || "error".equals(message)){
                return null;
            }
            JSONObject jb = JSONObject.fromObject(message);
            boolean hasKey = jb.containsKey("rawData") && !jb.get("rawData").toString().equals("\"null\"");
            if(hasKey){
                String data =  jb.get("rawData").toString();
                //COAP协议路灯和UDP协议普通路灯返回同样格式
                if(data.startsWith("ACV")){
                    String vol = StringUtils.substringBetween(data,"ACV","ACI");
                    String ele = StringUtils.substringBetween(data,"ACI","ACP");
                    String power = StringUtils.substringBetween(data,"ACP","TG");
                    String dimming = StringUtils.substringAfter(data,"TG");

                    dataMap.put("record_ele",Integer.parseInt(ele)/1000.0);
                    dataMap.put("record_vol",Integer.parseInt(vol));
                    dataMap.put("record_power",Integer.parseInt(power));
                    dataMap.put("record_dimming",Integer.parseInt(dimming));

                    dataMap.put("record_temp",null);
                    dataMap.put("record_pvol",null);
                    dataMap.put("record_pele",null);
                    dataMap.put("record_bvol",null);
                } else if(data.startsWith("T")){ //太阳能路灯格式
                    String ele = StringUtils.substringBetween(data,"OI","OV");
                    String vol =  StringUtils.substringAfter(data,"OV");
                    String temp = StringUtils.substringBetween(data,"T","PV");
                    String pvol = StringUtils.substringBetween(data,"PV","BV");//光伏电压
                    String pele = StringUtils.substringBetween(data,"PI","OI");//光伏电流
                    String bvol = StringUtils.substringBetween(data,"BV","PI");//蓄电池电压
                    Double double_povl = Integer.parseInt(pvol) * 0.1;
                    Double double_bvol = Integer.parseInt(bvol) * 0.1;
                    Double double_pele = Integer.parseInt(pele) /100.0;
                    pvol = String .format("%.2f",double_povl);
                    pele = String .format("%.2f",double_pele);
                    bvol = String .format("%.2f",double_bvol);

                    Double cal_ele = Double.parseDouble(ele);
                    cal_ele = cal_ele/100.0;
                    ele = String .format("%.2f",cal_ele);
                    Double cal_vol = Double.parseDouble(vol);
                    cal_vol = cal_vol/10.0;
                    vol = String .format("%.2f",cal_vol);

                    Double cal_power = cal_ele * cal_vol;
                    String power = String .format("%.2f",cal_power);

                    dataMap.put("record_ele",ele);
                    dataMap.put("record_temp",PlatformUtils.noIntegerBackZero(temp));
                    dataMap.put("record_vol",vol);
                    dataMap.put("record_power",power);
                    dataMap.put("record_dimming",-1);
                    dataMap.put("record_pvol",pvol);
                    dataMap.put("record_pele",pele);
                    dataMap.put("record_bvol",bvol);
                }

                dataMap.put("record_time",GetLocalTimes.getNowTime());
                dataMap.put("eventTime",new TimeUntils().dealIOTTime(jb.get("eventTime").toString()));
                dataMap.put("procotal",protocol);
                dataMap.put("nb_num",NBNumber);
                dataMap.put("is_over",0);
                dataMap.put("error_msg","");
                dataMap.put("conSate",MonitorCommon.NB_LAMP_CONNECT_STATE_ONlINE);
                return dataMap;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取uuid编码
     * @return
     */
    public String getUUID(){
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString();
        uid = uid.replace("-","");
        return uid.toString();
    }

    /**
     * 根据调光值 转换成相应的字符串
     * @param dimming
     * @return
     */
    public String dealDimming(Integer dimming){
        String result = "";
        if(dimming == 0){
            result = "0000";
        }else if(dimming <= 10){
            result = "0001";
        } else if(dimming <= 90 || dimming > 10){
            double d = Math.round(dimming/10);
            int i = (int)d;
            result = "000"+i;
        }else if(dimming ==100){
            result = "000A";
        }else {
            result = "000"+dimming;
        }
        return result;
    }

    /**
     * 将功率由W转换成千瓦 保留3位小数
     */
    public Double dealPowerToKW(String power){
        Double num = Double.parseDouble(power);
        num = num/1000.0;
        power = String.format("%.3f",num);
        return Double.parseDouble(power);
    }

    /**
     * 注册产业院 COAP代码
     * @param simCode
     * @return
     */
    public String registerCoapForIot(String simCode){
        String deviceId = null;
        try {
            String url = "http://182.254.141.187:5683/reg," + simCode;
            String message = HttpURLConnection.getHttpResponse(url);
            if(MonitorCommon.CHAN_YE_YUAN_IOT_ERROR_NBCODE_ERROR.equals(message)){
                return MonitorCommon.CHAN_YE_YUAN_IOT_ERROR_NBCODE_ERROR;
            }
            JSONObject jb = JSONObject.fromObject(message);
            deviceId = jb.get("deviceId").toString();
        } catch (Exception e) {
        }
        return deviceId;
    }

    /**
     * 删除产业院信息
     * @return
     */
    public boolean deleteCoapForIot(String nbDeviceId){
        boolean  result = false;
        try {
            String url = "http://182.254.141.187:5683/reg," + nbDeviceId;
            String message = HttpURLConnection.getHttpResponse(url);
            if("ok".equals(message)){
                result =  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
