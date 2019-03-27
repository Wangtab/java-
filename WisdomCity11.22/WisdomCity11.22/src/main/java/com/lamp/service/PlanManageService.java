package com.lamp.service;

import com.lamp.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PlanManageService {
    List<Map<String, Object>> getAreasByOrgCode(String orgCode);

    List<Map<String, Object>> getplanGroup(Integer areaId,String orgCode,String sort,String orderMsg);

    List<Map<String, Object>>getPlanGroupById(Integer Id);//通过id查询分组管理信息

    boolean addPlanGroup(String groupName,Integer areaId,Integer createby,String createTime);

    boolean updPlanGroup(String groupName,Integer areaId,Integer id);

    boolean delPlans(Integer id);

    Map<String, Object> getPlanstrategyList(HashMap<String,Object> sqlMap);

    List<Map<String, Object>> getPlanRoadByAreaId(Integer areaId);

    List<Map<String, Object>> getConCernDataByRoadId(Integer roadId,Integer groupId);

    boolean batchAdd(List<Map<String, Object>> list);

    List<Map<String, Object>> getSelGroupData(Integer roadId,Integer groupId);

    void deleteSelDataByGroupId(Integer groupId);

    List<Map<String, Object>> getDetailByPlanId(Integer planId);

    boolean deletePlanSenceData(Integer id);

/*    List<Map<String, Object>> getPlanstrategyData(String orgCode);*/

    List<Map<String, Object>> getPlanContent();

    boolean deltePlanStrategyById(Integer id);

    boolean savePlanStrategyData(TPlanStategy ts);

    List<Map<String, Object>> ajaxSePlanStrategyData(Integer areaId,Integer groupId,String sort,String orderBy);

    List<Map<String, Object>> getPlanStragegyById(Integer id);

    boolean updatePlanStragegyById(TPlanStategy ts);

    List<Map<String, Object>> getConcentratorByStragegyId(Integer id,String sort,String orderBy);

    List<Map<String, Object>> getRoadByAreaId(Integer id);

    List<Map<String, Object>> getLampByRoadId(Integer id);

    List<Map<String, Object>> getLampStrategyByLampId(HttpServletRequest request,Integer id,String orderBy,String sort);

    List<Map<String, Object>> getLightContent();

    boolean saveLampStrategyData(TLightStrategyManage tm);

    boolean delLampStrategyData(Integer id);

    List<Map<String, Object>> getLampStrategyById(Integer id);

    boolean updateLampStrategy(TLightStrategyManage tm);

    Map<String,Object> getSunRiseAndSunSet(String orgCode);

    List<Map<String, Object>> getAllplanGroup(String orgCode);

    Integer getSceneDataByName(String cname);

    Integer saveSceneData(TPlanSence tPlanSence);

    Integer updateSceneData(TPlanSence tPlanSence);

    Map<String, Object> initSceneSetting(HashMap<String,Object> dataMap);

    HashMap<String,Object> getSceneDataById(Integer id);

    int saveSceneDetailData(TPlanSceneDetail tPlanSceneDetail);
    int updateSceneDetailData(TPlanSceneDetail tPlanSceneDetail);
    List<Map<String, Object>> getPlanSceneDetailData(Integer id);
    HashMap<String,Object> getPlanSceneDetailById(Integer id);
    int deleteSceneDetailById(Integer id);

    List<Map<String, Object>> getPlanStrategyPlanData();

    List<Map<String, Object>> getPlanSenceDataForSelect(String orgCode);

    int saveLight_strategy(Light_strategy light_strategy);//保存光照调度策略

    Map <String,Object> selectLight_strategy(HashMap<String, Object> dataMap);//查询所有光照调光策略

    int DeleteLight_strategyByid(Integer id);//删除光照调光策略

    HashMap<String,Object> selectLight_strategyByid(Integer id);//通过id查询光照调光策略

    int updateLight_strategy(Light_strategy light_strategy);//更新调光策略
}
