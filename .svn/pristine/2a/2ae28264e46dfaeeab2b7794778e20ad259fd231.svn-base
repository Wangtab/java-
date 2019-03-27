package com.lamp.dao;

import com.lamp.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MaintainMapper {
    List<Map<String,Object>> getBuildingInfoData(HashMap<String,Object> dataMap);
    int getCountBuildingInfoData(HashMap<String,Object> dataMap);
    List<Map<String,Object>> exportBuildingInfoData(HashMap<String,Object> dataMap);
    List<Map<String,Object>> getRepairPeopleAndNumberData(@Param("orgCode") String orgCode);
    HashMap<String,Object> getBuildingInfoDataById(Integer id);
    int delBuildingInfoData(Integer id);
    int addBuildingInfoData(Tbuildinfo b);
    int updateBuildingInfoData(Tbuildinfo bild);

    List<Map<String, Object>> getCountRecord_warnByOrderNum(@Param("orderNum") String orderNum);
    int updateLampWarnDataByOrderNum(@Param("orderNum") String orderNum,@Param("nb_device") String nb_device);

    List<Map<String,Object>> queryLogUserList(HashMap<String,Object> dataMap);
    int queryCountLogUserList(HashMap<String,Object> dataMap);

    int getCountOperationData(HashMap<String,Object> dataMap);
    List<Map<String,Object>> getOperationData(HashMap<String,Object> dataMap);

    List<Map<String, Object>> getOperationLogData(HashMap<String,Object> dataMap);
    int countGetOperationLogData(HashMap<String,Object> dataMap);
    List<Map<String, Object>> exportOperationLampData(HashMap<String,Object> dataMap);

    int getCountRoutingData(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getRoutingData(HashMap<String,Object> dataMap);
    HashMap<String,Object> getRoutingDataById(Integer id);
    int delRoutingDataById(Integer id);
    int addRoutingData(TroutingInspection t);
    int updateRoutingData(TroutingInspection t);

    int getCountStockData(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getStockData(HashMap<String,Object> dataMap);
    HashMap<String,Object> getStockDataById(Integer id);
    int delStockDataById(Integer id);
    int addStockData(Tstock tstock);
    int updateStockData(Tstock tstock);

    List<Map<String, Object>> getPlatformDianSumPrice(HashMap<String,Object> dataMap);
    int countGetPlatformDianSumPrice(HashMap<String,Object> dataMap);

    int getCountRepairData(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getRepairData(HashMap<String,Object> dataMap);
    HashMap<String,Object> getRepairDataById(Integer id);
    int deleteRepById(Integer id);
    int saveRepData(TrepairPeople t);
    int updateRepData(TrepairPeople t);
    int checkRepairNum(@Param("orgCode") String orgCode,@Param("number") String number);

    int getCountBuildStandard(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getBuildStandard(HashMap<String,Object> dataMap);
    HashMap<String,Object> getBuildStandardById(Integer id);
    int delBuildStandardById(Integer id);
    int addBuildStandard(Tbuildstandard t);
    int updateBuildStandard(Tbuildstandard t);

}
