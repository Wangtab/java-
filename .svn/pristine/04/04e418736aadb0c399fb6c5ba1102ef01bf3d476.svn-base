package com.lamp.service;

import com.lamp.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IBaseDeviceService {

    Map<String,Object> getAreaManageData(HashMap<String,Object> sqlMap);

    List<Map<String, Object>> getOrganizationList();

    int saveAreaMangeData(Tareamanage ta);

    int updateAreaManageData(Tareamanage ta);

    List<Map<String, Object>> getAreaManageDataById(Integer areaId);

    int delteAreaMangeDataById(Integer areaId);

    Map<String,Object> getRoadManageData(String roadName,Integer num,Integer showNum);

    Map<String,Object> getRoadManageDataByOrgCode(HashMap<String,Object> paramMap);

    int saveRoadNameData(Troadmanage td);

    int updateRoadNameData(Troadmanage td);

    List<Map<String, Object>> getRoadNameManageById(Integer id);

    int deleteRoadManageDataById(Integer id);

    HashMap<String,String> saveData(Tcontroller tt,HttpServletRequest request);

    List<Map<String, Object>> getCOntrollerDataById(Integer id);

    int deleteControllerById(HttpServletRequest requests,Integer id);

    Map<String,Object> getControllerData(HashMap<String,Object> paramMap);

    int saveConcentratorData(Tconcentrator tt,HttpServletRequest request);

    List<Map<String, Object>> getConcentratorDataById(Integer id);

    int deleteConcentratorData(Integer id);

    Map<String,Object> getConcentratorListData(HashMap<String,Object> paramMap);

    String ImportControllerData(String data,Integer userId);

    String ImportConcentratorData(String data,String orgCode);

    Map<String,Object> getEleBoxData(HashMap<String,Object> dataMap);

    int getCounteleBoxData(String cname);

    int addeleBoxData(Telecboxmanage tb);

    int updateeleBoxData(Telecboxmanage tb);

    List<Map<String, Object>> geteleBoxDataById(Integer id);

    int delEleBoxDataById(Integer id);

    int checkDelAreaById(Integer id);

    int checkDelRoadById(Integer id);

    Map<String,Object> getLampWarnningData(Integer showNum, Integer curpage,Integer areaId,Integer roadId,Integer lineId,Integer lampId,
                                           String startDate,String endDate,String orgCode,String orderBy,String sort);

    List<Map<String, Object>> getExportWarnData(Map<String,Object> dataMap);

    List<Map<String, Object>> getSendBuildingDataByLampId(Integer id);

    int updateWarnLampDataByOrderNum(String ordernum,String nb_device);

    int saveRoadLineManageData(TRoadLineManage tRoadLineManage);

    int updateRoadLineManageData(TRoadLineManage tRoadLineManage);

    Map<String,Object> queryRoadLineManage(HashMap<String,Object> dataMap);

    HashMap<String,Object> queryRoadLineManageById(Integer id);

    int deleteRoadLineManageById(Integer id);

    HashMap<String,Object> getPlatformTemperatureInfo(String orgCode);

    String ImportDeviceData(String data,Integer userId);

    Map<String,Object> getAmmeterList(HashMap<String,Object> dataMap);
    int insertAmmeter(Tammeter t);
    int updateAmmeterSelective(Tammeter t);
    Map<String,Object> getAmmeterDataById(Integer id);
    int deleteAmmeterDataById(Integer id);

    Map<String, Object> getLampTypeList(HashMap<String,Object> dataMap);
    HashMap<String,Object> getLampTypeDataById(Integer id);
    int delLampTypeDataById(Integer id);
    int addLampTypeData(Tlamptype t);
    int updateLampTypeData(Tlamptype t);
    int checkLampTypeModel(HashMap<String,Object> dataMap);

    Map<String, Object> getLampManageData(HashMap<String,Object> dataMap);
    HashMap<String,Object> getLampManageDataById(Integer id);
    int delLampManageDataById(Integer id);
    int addLampManageData(Tlamp t);
    int updateLampManageData(Tlamp t);
}
