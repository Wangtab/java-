package com.lamp.dao;

import com.lamp.model.TCitySetting;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TMonitorServiceMapper {

    HashMap<String,Object> getSingleNBDeviceData();

    HashMap<String,Object> getDeviceStatusForWin(Integer id);

    List<Map<String, Object>> getTodayPower();

    List<Map<String, Object>> getLampDetail(@Param("orgCode") String orgCode,@Param("id") Integer id);

    int controllerLightSwith(@Param("onOff") Integer onOff,@Param("nbId") Integer nbId,@Param("dimming") Integer dimming);

    List<Map<String, Object>> getNbDeviceByControllerId(HashMap<String,Object> dataMap);

    int updateLightOnOffState(@Param("state") Integer state,@Param("id") Integer id,@Param("dimming") Integer dimming,@Param("conn_state") Integer conn_state);

    int updateLampStatusByRoadId(@Param("roadId") Integer roadId,@Param("on_off") Integer on_off);

    List<Map<String, Object>> getLampStatusByRoadId(Integer roadId);

    List<Map<String, Object>> getGroupManageByControllerId(@Param("id") Integer id,@Param("orgCode") String orgCode);

    int loopSwitchByConcentratorId(@Param("on_off") Integer on_off,
                                   @Param("concenId") Integer concenId,@Param("loopNum") Integer loopNum,
                                   @Param("opp_on_off") Integer opp_on_off);

    List<Map<String, Object>> getNbDeviceByRoadId(HashMap<String,Object> dataMap);

    List<Map<String, Object>> getNbDeviceByGroupId(HashMap<String,Object> dataMap);

    List<Map<String, Object>> getNbDeviceByLoopAndConcenId(HashMap<String,Object> dataMap);

    List<Map<String, Object>> lampSwitchDataByUUID(String uuid);

    List<Map<String, Object>> getAllLampDetailData(@Param("roadId")String roadId,@Param("curpage")Integer curpage,@Param("showNum")Integer showNum,@Param("groupId")Integer groupId);

    int getCountAllLampDetailData(@Param("roadId")String roadId,@Param("groupId")Integer groupId);

    HashMap<String,Object> getSingleLampLotData(Integer id);

    int updateSingleLampLotData(HashMap<String,Object> map);

    List<Map<String, Object>> getManyLampLotData(@Param("roadId") String roadId);

    int batchUpdatelampData(List<Map<String, Object>> list);

    HashMap<String,Object> getCityForMap(@Param("orgCode") String orgCode);

    int addCityData(TCitySetting t);

    List<Map<String,Object>> compareTodayPower(HashMap<String,Object> dataMap);

    List<Map<String, Object>> getDistributionListForMap(@Param("orgCode") String orgCode);

    List<Map<String, Object>> getLampDataLocation(@Param("orgCode") String orgCode,@Param("aid") Integer aid);

    int addLampSwitchRecord(HashMap<String,Object> dataMap);

    HashMap<String,Object> getLampSwitchRecord(Integer id);

    int addRecordWorkHour(HashMap<String,Object> dataMap);

    List<Map<String, Object>> batchSwitchOperLamp(HashMap<String,Object> paramMap);


    //----------新增
    List<Map<String, Object>> getAllLampDetailData2(@Param("orgCode") String orgCode,@Param("roadId")String roadId,@Param("curpage")Integer curpage,@Param("showNum")Integer showNum,@Param("areaId")String areaId,@Param("roadxId")String roadxId);

    List<Map<String, Object>> getAllLampDetailData3(@Param("orgCode") String orgCode,@Param("curpage")Integer curpage,@Param("showNum")Integer showNum,@Param("groupId")String groupId);

    List<Map<String, Object>> getNBlOTDevicedata();

    int getCollectDataMaxNum();

    int batchAddLampWarnData(List<Map<String,Object>> warnList);

    int batchAddTodayRecordData(List<Map<String,Object>> dataList);

    HashMap<String,Object> getLongitudeAndlatitude(HashMap<String,Object> dataMap);

    void dealLampWarn();

}
