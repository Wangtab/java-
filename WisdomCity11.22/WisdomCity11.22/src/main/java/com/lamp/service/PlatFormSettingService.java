package com.lamp.service;

import com.lamp.model.TCitySetting;
import com.lamp.model.TDianXiIot;
import com.lamp.model.TPowerRate;
import com.lamp.model.Tlogo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PlatFormSettingService {

    HashMap<String,Object> telecomManageList();

    int saveDianXiIotData(TDianXiIot tDianXiIot);

    List<Map<String,Object>> getOrganizationList(String orgCode);

    int delPlatFomSettingById(Integer id);

    HashMap<String,Object> getDianXiIotData(Integer id,Integer orgId);

    HashMap<String,Object> getDianXiIotDataById(Integer id);
    int updateDianXiIotDataById(TDianXiIot tDianXiIot);

    Map<String, Object> getChanYeYuanIot(HashMap<String,Object> dataMap);
    HashMap<String,Object> getChanYeYuanIotById(Integer id);
    int updateChanYeYuanIot(HashMap<String,Object> dataMap);

    int updatePowerRate(TPowerRate TPowerRate);
    List<Map<String,Object>> getPowerRateById(Integer id);
    Map<String,Object> getPowerRateList(HashMap<String,Object> map);

    Map<String, Object> getLogoInfo(HashMap<String,Object> dataMap);
    int updateLogoInfo(Tlogo l);
    HashMap<String,Object> getLogoInfoById(Integer id);

    Map<String,Object> getCityData(HashMap<String,Object> dataMap);
    HashMap<String,Object> getCityDataById(Integer id);
    int updateCityData(TCitySetting t);

}
