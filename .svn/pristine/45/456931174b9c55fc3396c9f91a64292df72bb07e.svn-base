package com.lamp.dao;

import com.lamp.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TPlanManageMapper {

    List<Map<String, Object>> getAreasByOrgCode(@Param("orgCode") String orgCode);

    List<Map<String, Object>> getAllplanGroup(@Param("orgCode") String orgCode);

    List<Map<String, Object>> getplanGroup(@Param("areaId") Integer areaId,@Param("orgCode") String orgCode,@Param("sort") String sort,@Param("orderMsg") String orderMsg);

    int addPlanGroup(@Param("groupName") String groupName,@Param("areaId") Integer areaId,@Param("createby") Integer createby,@Param("createTime") String createTime);

    int updPlanGroup(@Param("groupName") String groupName,@Param("areaId") Integer areaId,@Param("id") Integer id);

    int delPlans(@Param("id") Integer id);

    List<Map<String, Object>>getPlanGroupById(Integer Id);//通过id查询分组管理信息

    List<Map<String, Object>> getPlanRoadByAreaId(@Param("areaId") Integer areaId);

    List<Map<String, Object>> getConCernDataByRoadId(@Param("roadId") Integer roadId,@Param("groupId") Integer groupId);

    int batchAdd(List<Map<String, Object>> list);

    List<Map<String, Object>> getSelGroupData(@Param("roadId") Integer roadId,@Param("groupId") Integer groupId);

    void delselData(@Param("groupId") Integer groupId);

    List<Map<String, Object>> getDetailByPlanId(@Param("planId") Integer planId);

    int deletePlanSenceData(Integer id);

    int getCountPlanstrategyData(HashMap<String,Object> dataMap);

    List<Map<String, Object>> getPlanstrategyData(HashMap<String,Object> dataMap);

    List<Map<String, Object>> getPlanContent();

    int deltePlanStrategyById(Integer id);

    int savePlanStrategyData(TPlanStategy ts);

    List<Map<String, Object>> ajaxSePlanStrategyData(@Param("areaId") Integer areaId,@Param("groupId") Integer groupId,@Param("sort") String sort,@Param("orderBy") String orderBy);

    List<Map<String, Object>> getPlanStragegyById(Integer id);

    int updatePlanStragegyById(TPlanStategy ts);

    List<Map<String, Object>> getConcentratorByStragegyId(@Param("id") Integer id,@Param("sort") String sort,@Param("orderBy") String orderBy);

    List<Map<String, Object>> getRoadByAreaId(Integer id);

    List<Map<String, Object>> getLampByRoadId(Integer id);

    List<Map<String, Object>> getLampStrategyByLampId(@Param("id") Integer id,@Param("orgCode") String orgCode,@Param("orderBy") String orderBy,@Param("sort") String sort);

    List<Map<String, Object>> getLightContent();

    int saveLampStrategyData(TLightStrategyManage tm);

    int delLampStrategyData(Integer id);

    List<Map<String, Object>> getLampStrategyById(Integer id);

    int updateLampStrategy(TLightStrategyManage tm);

    List<Map<String, Object>> getSingleStrategyTask();

    int updateSingleStrategyTaskStatus(Integer id);

    List<Map<String, Object>> getGroupStrategyTask();

    int updateGroupStrategyTaskStatus(@Param("id") String id);

    int updateGroupStrategyTaskStatusById(Integer id);

    List<Map<String, Object>> getSunRiseAndSunSet(@Param("orgCode") String orgCode);

    int saveSceneData(TPlanSence tPlanSence);

    int updateSceneData(TPlanSence tPlanSence);

    List<Map<String, Object>> initSceneSetting(HashMap<String,Object> dataMap);

    int initSceneSettingNum(HashMap<String,Object> dataMap);

    HashMap<String,Object> getSceneDataById(Integer id);
    Integer getSceneDataByName(@Param("cname")String cname);

    int saveSceneDetailData(TPlanSceneDetail tPlanSceneDetail);

    int updateSceneDetailData(TPlanSceneDetail tPlanSceneDetail);

    List<Map<String, Object>> getPlanSceneDetailData(Integer id);

    HashMap<String,Object> getPlanSceneDetailById(Integer id);

    int deleteSceneDetailById(Integer id);

    List<Map<String, Object>> getPlanStrategyPlanData();

    List<Map<String, Object>> getPlanSenceDataForSelect(@Param("orgCode") String orgCode);

    List<Map<String, Object>> getPlansceneDeatilDataBySceneId(Integer id);

    int saveLight_strategy(Light_strategy light_strategy);//添加光照调光策略

    List<Map<String, Object>> selectLight_strategy(HashMap<String, Object> dataMap);//查询所有光照调光策略

    int DeleteLight_strategyByid(Integer id);//删除光照调光策略

    HashMap<String,Object> selectLight_strategyByid(Integer id);//通过id查询光照调光策略

    int getcount(HashMap<String,Object> dataMap);//获取总条数

    int updateLight_strategy(Light_strategy light_strategy);//更新调光策略
}
