package com.lamp.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TScreenMapper {

    String getSumPowers(String orgCode);

    List<Map<String, Object>> getAreasPower(@Param("orgCode") String orgCode);

    List<Map<String, Object>> getFourDaysSumPowerData(@Param("orgCode") String orgCode);

    Double getTodayPowerData(@Param("orgCode") String orgCode);

    void dealTodayRecordData();

    List<Map<String, Object>> getSumEnergyData(@Param("orgCode") String orgCode);

    HashMap<String,Object> getSingleData(@Param("orgCode") String orgCode);

}
