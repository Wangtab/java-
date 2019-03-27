package com.lamp.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TLampCommonMapper {
    List<Map<String, Object>> getAreaNameForSelect(@Param("orgCode") String orgCode);
    List<Map<String, Object>> getRoadNameForSelect(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getRoadLineNameForSelect(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getLampNumForSelect(HashMap<String,Object> dataMap);
    List<Map<String, Object>> queryRoadLineMap(@Param("orgCode") String orgCode, @Param("roadId") Integer roadId);
    List<Map<String, Object>> getGroupNameForSelect(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getPlanContentForSelect();
    List<Map<String, Object>> getMenuBtnByUser(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getDistributionBoxForSelect(@Param("orgCode") String orgCode);
    List<Map<String, Object>> getDimmingModelForSelect();
    List<Map<String, Object>> getControllerKindForSelect();
    List<Map<String, Object>> getConcentratorForSelect(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getMenuDataForSelect();
    List<Map<String, Object>> getRepairPeopleForSelect(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getBuildStandardForSelect(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getDeviceTypeDataForSelect();
    List<Map<String, Object>> getControllerNumDataForSelect(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getLampTypeDataForSelect(HashMap<String,Object> dataMap);

}
