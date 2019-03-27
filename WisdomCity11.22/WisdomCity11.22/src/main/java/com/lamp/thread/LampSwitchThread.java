package com.lamp.thread;

import com.lamp.common.MonitorCommon;
import com.lamp.dao.TMonitorServiceMapper;
import com.lamp.utils.LampSwitchInterface;
import com.lamp.utils.PlatformUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LampSwitchThread implements  Runnable {

    private String uuid;

    private TMonitorServiceMapper monitorServiceMapper;

    private List<Map<String, Object>> list;

    @Override
    public void run(){
        commondealSwitchResult();
    }

    public void commondealSwitchResult(){
        //睡眠35秒
        try {
            Thread.currentThread().sleep(MonitorCommon.SLEEP_TIMES * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        //获取记录数
        if(null == list || list.size() == 0){
            return;
        }

        for (Map<String,Object> map : list){
            Integer nbId = Integer.parseInt(map.get("id").toString());//获取设备表ID
            String nbNum = map.get("nb_device").toString();//设备识别码
            Integer protocol = Integer.parseInt(map.get("protocol").toString());//协议
            Float record_power = Float.parseFloat(map.get("power").toString());//获取记录功率
            Integer hopeNum = Integer.parseInt(map.get("hope_num").toString());//获取希望值
            Integer setOnOff = Integer.parseInt(map.get("set_on_off").toString());//获取设置开关状态
            Integer setDimming = Integer.parseInt(map.get("set_dimming").toString());
            //获取现有数据
            HashMap<String, Object> dataMap = lampSwitchInterface.getLampDataByNbId(protocol, nbNum);
            Float now_power = Float.parseFloat(dataMap.get("record_power").toString());//获取记录功率
            boolean isOK = false;
            if(hopeNum == MonitorCommon.HOPE_LESS_THAN){//希望小于当前数值
                //如果当前功率小于记录功率 操作成功 反之操作失败
                isOK = now_power <= record_power ? true : false;
            } else if(hopeNum == MonitorCommon.HOPE_MORE_THAN){//希望大于当前数值
                //如果当前功率大于记录功率 操作成功 反之操作失败
                isOK = now_power >= record_power ? true : false;
            }
            if(isOK){
                HashMap<String,Object> recordMap = new HashMap<>();
                recordMap.put("onOff",setOnOff);
                recordMap.put("dimming",setDimming);
                recordMap.put("nbId",nbId);

                if(MonitorCommon.LAMP_CLOSE == setOnOff){
                    HashMap<String,Object> switchMap = monitorServiceMapper.getLampSwitchRecord(nbId);
                    if(switchMap != null){
                        Date startTime = (Date)switchMap.get("record_time");
                        Date endTime = new Date();
                        Double hour = PlatformUtils.getDatePoor(startTime,new Date());
                        HashMap<String,Object> sqlMap = new HashMap<>();
                        sqlMap.put("startTime",startTime);
                        sqlMap.put("endTime",endTime);
                        sqlMap.put("hour",hour);
                        sqlMap.put("nbId",nbId);
                        monitorServiceMapper.addRecordWorkHour(sqlMap);
                        monitorServiceMapper.addLampSwitchRecord(recordMap);
                    }
                }else{
                    monitorServiceMapper.addLampSwitchRecord(recordMap);
                }
                monitorServiceMapper.controllerLightSwith(setOnOff,nbId,setDimming);

            }
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setMonitorServiceMapper(TMonitorServiceMapper monitorServiceMapper) {
        this.monitorServiceMapper = monitorServiceMapper;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
}
