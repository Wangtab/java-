package com.lamp.thread;

import com.lamp.common.MonitorCommon;
import com.lamp.dao.TBaseDeviceMapper;
import com.lamp.utils.LampSwitchInterface;

import java.util.HashMap;

public class UpdateLampThread implements  Runnable {

    private TBaseDeviceMapper tBaseDeviceMapper;

    private HashMap<String,Object> paramMap;

    @Override
    public void run() {
        addRealDataByNbLot();
    }

    //添加实时状态
    private void addRealDataByNbLot(){
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        Integer protocol = Integer.parseInt(paramMap.get("protocol").toString());
        String deviceId = paramMap.get("deviceId").toString();
        HashMap<String,Object> dataMap = lampSwitchInterface.getLampDataByNbId(protocol,deviceId);
        HashMap<String,Object> sqlMap = new HashMap<>();
        //通用信息
        sqlMap.put("nb_device",deviceId);
        sqlMap.put("networking_state","已联网");
        sqlMap.put("vol",dataMap.get("record_vol").toString());
        sqlMap.put("ele",dataMap.get("record_ele").toString());
        sqlMap.put("power",dataMap.get("record_power").toString());
        sqlMap.put("dimming",0);//调光值为零
        sqlMap.put("onOff", MonitorCommon.LAMP_CLOSE);//关灯
        sqlMap.put("conn_state",MonitorCommon.NB_LAMP_CONNECT_STATE_ONlINE);//在线
        sqlMap.put("hour",0);
        sqlMap.put("signal",0);
        sqlMap.put("nbId",paramMap.get("nbManageId").toString());
        sqlMap.put("record_time",dataMap.get("eventTime").toString());
        sqlMap.put("pVol",0);
        sqlMap.put("pele",0);
        sqlMap.put("bVol",0);
        sqlMap.put("temp",0);

        //UDP协议 获取蓄电池相关信息
        if(MonitorCommon.NB_UDP == protocol){
            sqlMap.put("pVol",dataMap.get("record_pvol").toString());
            sqlMap.put("pele",dataMap.get("record_pele").toString());
            sqlMap.put("bVol",dataMap.get("record_bvol").toString());
            sqlMap.put("temp",dataMap.get("record_temp").toString());
        }
        tBaseDeviceMapper.addRealNBData(dataMap);
    }

    public TBaseDeviceMapper gettBaseDeviceMapper() {
        return tBaseDeviceMapper;
    }

    public void settBaseDeviceMapper(TBaseDeviceMapper tBaseDeviceMapper) {
        this.tBaseDeviceMapper = tBaseDeviceMapper;
    }

    public HashMap<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(HashMap<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
