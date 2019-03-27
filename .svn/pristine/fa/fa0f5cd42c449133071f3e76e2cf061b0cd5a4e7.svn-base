package com.lamp.dao;

import com.lamp.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TPlatformSettingMapper {

    List<Map<String,Object>> telecomManageList();

    Integer updateTelecomManage(@Param("id") Integer id);

    Map<String,Object> telecomManage(@Param("id") Integer id);

    Tcontroller getTcontroller(String code);

    Map<String,Object> getPlatDataByID(Integer id);

    Map<String,Object> getPlatData(Integer userId);

    List<Map<String, Object>> getOrganizationList(String orgCode);

    int saveDianXiIotData(TDianXiIot tDianXiIot);

    int delPlatFomSettingById(Integer id);

    List<Map<String, Object>> getDianXiIotData(@Param("id") Integer id,@Param("orgId") Integer orgId);
    List<Map<String, Object>> getDianXiIotDataById(Integer id);
    int updateDianXiIotDataById(TDianXiIot tDianXiIot);

    List<Map<String, Object>> getChanYeYuanIot(HashMap<String,Object> dataMap);
    HashMap<String,Object> getChanYeYuanIotById(Integer id);
    int updateChanYeYuanIot(HashMap<String,Object> dataMap);

    List<Map<String,Object>> getPowerRateList(HashMap<String,Object> map);
    int getPowerRateCount(HashMap<String,Object> map);
    List<Map<String,Object>> getPowerRateById(Integer id);
    int updatePowerRate(TPowerRate TPowerRate);
    int insertPowerrate(TPowerRate t);

    int countGetLogoInfo(HashMap<String,Object> map);
    List<Map<String,Object>> getLogoInfo(HashMap<String,Object> map);
    int updateLogoInfo(Tlogo l);
    HashMap<String,Object> getLogoInfoById(Integer id);

    int getCityDataNum(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getCityData(HashMap<String,Object> dataMap);
    HashMap<String,Object> getCityDataById(Integer id);
    int updateCityData(TCitySetting t);


}
