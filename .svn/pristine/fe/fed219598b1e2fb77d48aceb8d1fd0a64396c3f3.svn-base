package com.lamp.service.impl;

import com.lamp.dao.TPlanManageMapper;
import com.lamp.model.*;
import com.lamp.service.PlanManageService;
import com.lamp.utils.DealPage;
import com.lamp.utils.PlatformUtils;
import com.lamp.utils.TimeUntils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlanManageServiceImpl implements PlanManageService {


    @Autowired
    private TPlanManageMapper tPlanManageMapper;

    @Override
    public List<Map<String, Object>> getAreasByOrgCode(String orgCode) {
        return tPlanManageMapper.getAreasByOrgCode(orgCode);
    }

    @Override
    public List<Map<String, Object>> getplanGroup(Integer areaId,String orgCode,String sort,String orderMsg) {
        return tPlanManageMapper.getplanGroup(areaId,orgCode,sort,orderMsg);
    }

    @Override
    @Transactional
    public boolean addPlanGroup(String groupName, Integer areaId,Integer createby,String createTime) {
        try {
            int i = tPlanManageMapper.addPlanGroup(groupName, areaId,createby,createTime);
            if(1 == i){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updPlanGroup(String groupName, Integer areaId, Integer id) {
        try {
            int i = tPlanManageMapper.updPlanGroup(groupName, areaId, id);
            if(1 == i){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delPlans(Integer id) {
        try {
            int i = tPlanManageMapper.delPlans(id);
            if(1 == i){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getPlanRoadByAreaId(Integer areaId) {
        return tPlanManageMapper.getPlanRoadByAreaId(areaId);
    }

    @Override
    public List<Map<String, Object>> getConCernDataByRoadId(Integer roadId,Integer groupId) {
        return tPlanManageMapper.getConCernDataByRoadId(roadId, groupId);
    }

    @Override
    @Transactional
    public boolean batchAdd(List<Map<String, Object>> list) {
        try {
            tPlanManageMapper.batchAdd(list);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getSelGroupData(Integer roadId, Integer groupId) {
        return tPlanManageMapper.getSelGroupData(roadId,groupId);
    }

    @Override
    public void deleteSelDataByGroupId(Integer groupId) {
        tPlanManageMapper.delselData(groupId);
    }

    @Override
    public List<Map<String, Object>> getDetailByPlanId(Integer planId){
        return tPlanManageMapper.getDetailByPlanId(planId);
    }

    public List<Map<String, Object>>getPlanGroupById(Integer Id){
        List<Map<String, Object>> plan_group= tPlanManageMapper.getPlanGroupById(Id);
        return plan_group;
    }


    @Override
    public boolean deletePlanSenceData(Integer id) {
        try {
            int i = tPlanManageMapper.deletePlanSenceData(id);
            if(1 == i){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Map<String, Object> getPlanstrategyList(HashMap<String,Object> sqlMap) {
        Map<String,Object> dataMap = new HashMap<>();
        int count =  tPlanManageMapper.getCountPlanstrategyData(sqlMap);
        Integer showNum = Integer.parseInt(sqlMap.get("showNum").toString());
        Integer curPage = Integer.parseInt(sqlMap.get("curPage").toString());
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer num = (curPage -1) * showNum;
        sqlMap.put("num",num);
        List<Map<String, Object>> list = tPlanManageMapper.getPlanstrategyData(sqlMap);
        dataMap.put("count",count);
        dataMap.put("data",list);
        return dataMap;
    }


    @Override
    public List<Map<String, Object>> getPlanContent() {
        return tPlanManageMapper.getPlanContent();
    }

    @Override
    public boolean deltePlanStrategyById(Integer id) {
        try {
            int i = tPlanManageMapper.deltePlanStrategyById(id);
            if(1 == i){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean savePlanStrategyData(TPlanStategy ts) {
        try {
            int i = tPlanManageMapper.savePlanStrategyData(ts);
            if(1 == i){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> ajaxSePlanStrategyData(Integer areaId, Integer groupId,String sort,String orderBy) {
        return tPlanManageMapper.ajaxSePlanStrategyData(areaId, groupId,sort,orderBy);
    }

    @Override
    public List<Map<String, Object>> getPlanStragegyById(Integer id) {
        return tPlanManageMapper.getPlanStragegyById(id);
    }

    @Override
    public boolean updatePlanStragegyById(TPlanStategy ts) {
        try {
            int i = tPlanManageMapper.updatePlanStragegyById(ts);
            if(1 == i){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getConcentratorByStragegyId(Integer id,String sort,String orderBy) {
        return tPlanManageMapper.getConcentratorByStragegyId(id,sort,orderBy);
    }

    @Override
    public List<Map<String, Object>> getRoadByAreaId(Integer id) {
        return tPlanManageMapper.getRoadByAreaId(id);
    }

    @Override
    public List<Map<String, Object>> getLampByRoadId(Integer id) {
        return tPlanManageMapper.getLampByRoadId(id);
    }

    @Override
    public List<Map<String, Object>> getLampStrategyByLampId(HttpServletRequest request,Integer id,String orderBy,String sort) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        return tPlanManageMapper.getLampStrategyByLampId(id,orgCode,orderBy,sort);
    }

    @Override
    public List<Map<String, Object>> getLightContent() {
        return tPlanManageMapper.getLightContent();
    }

    @Override
    public boolean saveLampStrategyData(TLightStrategyManage tm) {
        try {
            int i = tPlanManageMapper.saveLampStrategyData(tm);
            if(1 == i){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delLampStrategyData(Integer id) {
        try {
            int i = tPlanManageMapper.delLampStrategyData(id);
            if(1 == i){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getLampStrategyById(Integer id) {
        return tPlanManageMapper.getLampStrategyById(id);
    }

    @Override
    public boolean updateLampStrategy(TLightStrategyManage tm) {
        try {
            int i = tPlanManageMapper.updateLampStrategy(tm);
            if(1 == i){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }







    /**
     *  获取日出日落時間
     */
    public Map<String,Object> getSunRiseAndSunSet(String orgCode){
        List<Map<String, Object>> list = tPlanManageMapper.getSunRiseAndSunSet(orgCode);
        Map<String, Object> map = list.get(0);
        HashMap<String,String> dataMap = new HashMap<>();
        dataMap.put("sunrise",map.get("sunrise").toString());
        dataMap.put("sunset",map.get("sunset").toString());
        Map<String,Object> calMap = new TimeUntils().dealSunRiseAndSunSet(dataMap);
        if(null == calMap){
            return map;
        }
        return calMap;
    }

    public List<Map<String, Object>> getAllplanGroup(String orgCode){
        return tPlanManageMapper.getAllplanGroup(orgCode);
    }

    @Override
    public Integer saveSceneData(TPlanSence tPlanSence) {
        return tPlanManageMapper.saveSceneData(tPlanSence);
    }

    @Override
    public Integer updateSceneData(TPlanSence tPlanSence) {
        return tPlanManageMapper.updateSceneData(tPlanSence);
    }

    @Override
    public Map<String, Object> initSceneSetting(HashMap<String,Object> dataMap) {
        Integer showNum = Integer.parseInt(dataMap.get("showNum").toString());
        Integer curPage = Integer.parseInt(dataMap.get("curPage").toString());
        int count = tPlanManageMapper.initSceneSettingNum(dataMap);
         Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer nums = (curPage -1) * showNum;
        dataMap.put("num",nums);
        List<Map<String, Object>> list = tPlanManageMapper.initSceneSetting(dataMap);
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",count);
        resultMap.put("data",list);
        return resultMap;
    }

    @Override
    public HashMap<String, Object> getSceneDataById(Integer id) {
        return tPlanManageMapper.getSceneDataById(id);
    }

    @Override
    public int saveSceneDetailData(TPlanSceneDetail tPlanSceneDetail) {
        return tPlanManageMapper.saveSceneDetailData(tPlanSceneDetail);
    }

    @Override
    public Integer getSceneDataByName(String cname) {
        return tPlanManageMapper.getSceneDataByName(cname);
    }

    @Override
    public int updateSceneDetailData(TPlanSceneDetail tPlanSceneDetail) {
        return tPlanManageMapper.updateSceneDetailData(tPlanSceneDetail);
    }

    @Override
    public List<Map<String, Object>> getPlanSceneDetailData(Integer id) {
        return tPlanManageMapper.getPlanSceneDetailData(id);
    }

    @Override
    public HashMap<String, Object> getPlanSceneDetailById(Integer id) {
        return tPlanManageMapper.getPlanSceneDetailById(id);
    }

    @Override
    public int deleteSceneDetailById(Integer id){
        return tPlanManageMapper.deleteSceneDetailById(id);
    }

    @Override
    public List<Map<String, Object>> getPlanStrategyPlanData() {
        return tPlanManageMapper.getPlanStrategyPlanData();
    }

    @Override
    public List<Map<String, Object>> getPlanSenceDataForSelect(String orgCode) {
        return tPlanManageMapper.getPlanSenceDataForSelect(orgCode);
    }

    //添加光照调光策略
    @Override
    public int saveLight_strategy(Light_strategy light_strategy) {
        return tPlanManageMapper.saveLight_strategy(light_strategy);
    }

    //查询光照调光策略
    @Override
    public Map<String, Object> selectLight_strategy(HashMap<String, Object> dataMap) {
        Map<String,Object> resultMap = new HashMap<>();
        Integer showNum = Integer.parseInt(dataMap.get("showNum").toString());
        Integer curPage = Integer.parseInt(dataMap.get("curPage").toString());
        int count = tPlanManageMapper.getcount(dataMap);
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer nums = (curPage -1) * showNum;
        dataMap.put("num",nums);
        List<Map<String, Object>> list= tPlanManageMapper.selectLight_strategy(dataMap);
        resultMap.put("data",list);
        resultMap.put("count",count);
        return resultMap;
    }

    //删除光照调光策略
    @Override
    public int DeleteLight_strategyByid(Integer id) {
        return tPlanManageMapper.DeleteLight_strategyByid(id);
    }

    //通过id查询光照调光策略
    @Override
    public HashMap<String, Object> selectLight_strategyByid(Integer id) {
        HashMap<String, Object> ls = tPlanManageMapper.selectLight_strategyByid(id);
        return ls;
    }

    @Override
    public int updateLight_strategy(Light_strategy light_strategy) {
        return tPlanManageMapper.updateLight_strategy(light_strategy);
    }
}
