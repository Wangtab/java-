package com.lamp.dao;

import com.lamp.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TBaseDeviceMapper {

    List<Map<String, Object>> getAreaManageData (HashMap<String,Object> sqlMap);

    List<Map<String, Object>> getRoadManageData(@Param("roadName") String roadName,@Param("num") Integer num,@Param("showNum") Integer showNum);

    List<Map<String, Object>> getRoadManageDataByOrgCode(HashMap<String,Object> sqlMap);

    int getCountAreaManageData(HashMap<String,Object> sqlMap);

    List<Map<String, Object>> getOrganizationList();

    int saveAreaMangeData(Tareamanage ta);

    int updateAreaManageData(Tareamanage ta);

    List<Map<String, Object>> getAreaManageDataById(Integer areaId);

    int delteAreaMangeDataById(Integer areaId);

    int getCountRoadManageData(@Param("roadName") String roadName);

    int getCountRoadManageDataByOrgCode(@Param("roadName") String roadName,@Param("orgCode") String orgCode,@Param("areaId") Integer areaId);

    int saveRoadNameData(Troadmanage td);

    int updateRoadNameData(Troadmanage td);

    List<Map<String, Object>> getRoadNameManageById(Integer id);

    int deleteRoadManageDataById(Integer id);

    int addControllerData(Tcontroller tc);

    int updateControllerData(Tcontroller tc);

    List<Map<String, Object>> getCOntrollerDataById(Integer id);

    int deleteControllerById(Integer id);

    List<Map<String, Object>> getControllerData(HashMap<String,Object> paramMap);

    int getCountControllerData(HashMap<String,Object> paramMap);

    List<Map<String, Object>> getConcentratorKindData();

    int addConcentratorData(Tconcentrator tt);

    int updateConcentratorData(Tconcentrator tt);

    int deleteConcentratorData(Integer id);

    List<Map<String, Object>> getConcentratorListData(HashMap<String,Object> sqlMap);

    List<Map<String, Object>> getConcentratorDataById(Integer id);

    int getCountConcentratorNum(Map<String, Object> dataMap);

    List<Map<String, Object>> getLampTypeData();

    List<Map<String, Object>> getUserManageData();

    int importControlerData(List<Map<String, Object>> list);

    int importConcentratorData(List<Map<String, Object>> list);

    List<Map<String, Object>> geteleBoxData(HashMap<String,Object> dataMap);

    int getCountEleBoxData(HashMap<String,Object> dataMap);

    int addeleBoxData(Telecboxmanage tb);

    int updateeleBoxData(Telecboxmanage tb);

    List<Map<String, Object>> geteleBoxDataById(Integer id);

    int delEleBoxDataById(Integer id);

    int checkDelAreaById(Integer id);

    int checkDelRoadById(Integer id);

    List<Map<String, Object>> getLampWarnningData(HashMap<String,Object> sqlMap);

    int getCountLampWarnningData(HashMap<String,Object> sqlMap);

    List<Map<String, Object>> getExportWarnData(Map<String,Object> dataMap);

    List<Map<String, Object>> getSendBuildingDataByLampId(Integer id);

    List<Map<String, Object>> getdealingBuillingInfo();

    int updateWarnLampDataByOrderNum(@Param("ordernum") String ordernum,@Param("nb_device") String nb_device);

    int hasNbCode(String nbCode);

    int addNbLotManageData(Tnbmanage tnbmanage);

    int addRealNBData(HashMap<String,Object> dataMap);

    int saveRoadLineManageData(TRoadLineManage tRoadLineManage);

    int updateRoadLineManageData(TRoadLineManage tRoadLineManage);

    List<Map<String, Object>> queryRoadLineManage(HashMap<String,Object> dataMap);

    int queryCountRoadLineManage(HashMap<String,Object> dataMap);

    HashMap<String,Object> queryRoadLineManageById(Integer id);

    int deleteRoadLineManageById(Integer id);

    HashMap<String,Object> getPlatformTemperatureInfo(@Param("orgCode") String orgCode);

    List<Map<String, Object>> getCitySettingData();

    int updateCitySettingTempData(HashMap<String,String> dataMap);

    HashMap<String,Object> getNbInfoByControllerId(Integer id);

    int deleteNbManageById(Integer id);
    int deleteRealNbDataById(Integer id);

    int ImportDeviceData(List<Map<String, Object>> list);

    List<Map<String, Object>> getAmmeterList(HashMap<String,Object> dataMap);
    int getCountAmmeterNum(HashMap<String,Object> dataMap);
    int insertAmmeter(Tammeter t);
    int updateAmmeterSelective(Tammeter t);
    HashMap<String,Object> getAmmeterDataById(Integer id);
    int deleteAmmeterDataById(Integer id);

    int getCountLampTypeList(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getLampTypeList(HashMap<String,Object> dataMap);
    HashMap<String,Object> getLampTypeDataById(Integer id);
    int delLampTypeDataById(Integer id);
    int addLampTypeData(Tlamptype t);
    int updateLampTypeData(Tlamptype t);
    int checkLampTypeModel(HashMap<String,Object> dataMap);

    int getCountLampManageData(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getLampManageData(HashMap<String,Object> dataMap);
    HashMap<String,Object> getLampManageDataById(Integer id);
    int delLampManageDataById(Integer id);
    int addLampManageData(Tlamp t);
    int updateLampManageData(Tlamp t);
}
