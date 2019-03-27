package com.lamp.service;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMonitorService {

    //批量处理灯控
    HashMap<String,Object> batchSwitchOperLamp(HttpServletRequest request,String idArr,Integer onOff,Integer oper,Integer dimming);

    String getPowerList();

    String getLampDetail(HttpServletRequest request,Integer id);

    HashMap<String,Object> getDeviceStatusForWin(Integer id);

    HashMap<String,Object> singleDeviceController(Integer id, Integer onOff,Integer oper,Integer dimming);

    List<Map<String, Object>> getGroupManageByControllerId(Integer id,String orgCode);

    boolean loopSwitch(Integer loop,Integer concentratorId,Integer onOff);

    HashMap<String,Object> RoadSwitchController(HttpServletRequest request,Integer roadId, Integer onOff,Integer oper,Integer dimming);

    HashMap<String,Object> groupSwitchController(Integer groupId, Integer onOff,Integer oper,Integer dimming);

    HashMap<String,Object> loopSwitchController(HttpServletRequest request,Integer concenId, Integer onOff,Integer loop,Integer oper,Integer dimming,String orgCode);

    Map<String,Object> getAllLampDetailData(Integer curpage,Integer showNum,String roadId,Integer groupId);

    HashMap<String,Object> getSingleLampLotData(HttpServletRequest request,Integer id);

    String getManyLampLotData(String roadId);

    HashMap<String,Object> getCityForMap(String orgCode);

    List<Map<String, Object>> getDistributionListForMap(String orgCode);

    List<Map<String, Object>> getLampDataLocation(String orgCode,Integer aid);

    List<Map<String,Object>> compareTodayPower(HashMap<String,Object> dataMap);

    //-----------新
    Map<String,Object> getAllLampDetailData2(HttpServletRequest request,Integer curpage,Integer showNum,String roadId,String areaId,String roadxId);

    Map<String,Object> getAllLampDetailData3(HttpServletRequest request,Integer curpage,Integer showNum,String groupId);

    HashMap<String, Object> getLongitudeAndlatitude(HashMap<String,Object> dataMap);

}
