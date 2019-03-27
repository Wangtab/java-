package com.lamp.service;
import com.lamp.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MaintainService {

    Map<String,Object> getBuildingInfoData(HashMap<String,Object> dataMap);
    List<Map<String,Object>> exportBuildingInfoData(HashMap<String,Object> dataMap);
    List<Map<String,Object>> getRepairPeopleAndNumberData(String orgCode);
    HashMap<String,Object> getBuildingInfoDataById(Integer id);
    int delBuildingInfoData(Integer id);
    int addBuildingInfoData(Tbuildinfo bild);
    int updateBuildingInfoData(Tbuildinfo bild);

    Map<String,Object> queryLogUserList(HashMap<String,Object> dataMap);

    Map<String,Object> getOperationLog(HashMap<String,Object> dataMap);

    Map<String,Object> getOperationLogData(HashMap<String,Object> dataMap);
    List<Map<String, Object>> exportOperationLampData(HashMap<String,Object> dataMap);

    Map<String,Object> getRoutingData(HashMap<String,Object> dataMap);
    HashMap<String,Object> getRoutingDataById(Integer id);
    int delRoutingDataById(Integer id);
    int addRoutingData(TroutingInspection t);
    int updateRoutingData(TroutingInspection t);

    Map<String, Object> getStockData(HashMap<String,Object> dataMap);
    HashMap<String,Object> getStockDataById(Integer id);
    int delStockDataById(Integer id);

    int addStockData(Tstock tstock);
    int updateStockData(Tstock tstock);

    Map<String, Object> getPlatformDianSumPrice(HashMap<String, Object> dataMap);

    Map<String, Object> getRepairData(HashMap<String,Object> dataMap);
    HashMap<String,Object> getRepairDataById(Integer id);
    int deleteRepById(Integer id);
    int saveRepData(TrepairPeople t);
    int updateRepData(TrepairPeople t);
    int checkRepairNum(String orgCode,String number);

    Map<String, Object> getBuildStandard(HashMap<String,Object> dataMap);
    HashMap<String,Object> getBuildStandardById(Integer id);
    int delBuildStandardById(Integer id);
    int addBuildStandard(Tbuildstandard t);
    int updateBuildStandard(Tbuildstandard t);

}
