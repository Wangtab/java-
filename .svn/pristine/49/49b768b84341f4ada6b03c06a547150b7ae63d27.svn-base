package com.lamp.controller;

import com.lamp.common.MenuModelCommon;
import com.lamp.common.SystemOperationCommon;
import com.lamp.model.*;
import com.lamp.service.*;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 计划策略模块
 */
@Controller
public class PlanManageController {

    @Autowired
    private PlanManageService planManageService;

    @Autowired
    private TUserOperationService tUserOperationService;

    @Autowired
    private LampCommonService lampCommonService;

    /** ------------------------------ 分组管理 begin ----------------------------------------*/

    /**
     * 分组管理页面初始化数据
     */
    @RequestMapping("getPlanGroupManage")
    public ModelAndView getPlanGroupManage(Integer areaId,HttpServletRequest request){
        String orgCode = PlatformUtils.getLoginUser(request).getTorganization().getOrgCode();
        List<Map<String, Object>> areaList =  planManageService.getAreasByOrgCode(orgCode);
        List<Map<String, Object>> groupList = planManageService.getplanGroup(areaId,orgCode,null,null);
        ModelAndView mv = new ModelAndView();
        mv.addObject("areaList",areaList);
        mv.addObject("groupList",groupList);
        mv.addObject("selAreaId",areaId);
        mv.setViewName("plan_group_manage");
        return mv;
    }

    /**
     * 获取分组管理页面数据
     * @param areaId
     * @param response
     * @throws IOException
     */
    @RequestMapping("ajaxGetPlanGroupManage")
    public void ajaxGetPlanGroupManage(Integer areaId,String sort,String orderMsg,HttpServletResponse response,HttpServletRequest request) throws IOException{
        String orgCode = PlatformUtils.getLoginUser(request).getTorganization().getOrgCode();
        List<Map<String, Object>> groupList = planManageService.getplanGroup(areaId,orgCode,sort,orderMsg);
        String data = JSONArray.fromObject(groupList).toString();
        response.getWriter().println(data);
    }

    /**
     * 保存分组信息
     */
    @RequestMapping("savePlanGroup")
    public void savePlans(Integer id,Integer areaId,String groupName,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        boolean result = false;
        if(null == id){
             result = planManageService.addPlanGroup(groupName,areaId,tsysuser.getId(),PlatformUtils.getNowTime());
                int i = result==true?1:0;
            tUserOperationService.recordOperationData(groupName,null,tsysuser.getId(), SystemOperationCommon.OPERATION_ADD,i, MenuModelCommon.PLANGROUP_MANAGE);
        }else{
            Object nowobj = planManageService.getPlanGroupById(id).get(0);
            result = planManageService.updPlanGroup(groupName,areaId,id);
            Object oldobj = planManageService.getPlanGroupById(id).get(0);
            int i = result==true?1:0;
            tUserOperationService.recordOperationData(nowobj,oldobj,tsysuser.getId(), SystemOperationCommon.OPERATION_UPDATE,i, MenuModelCommon.PLANGROUP_MANAGE);
        }
        String msg = result == true ? "y" : "n";
        response.getWriter().println(msg);

    }

    /**
     * 删除计划分组信息
     */
    @RequestMapping("delPlanGroup")
    public void delPlanById(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException {
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        boolean isOk = planManageService.delPlans(id);
        int i = isOk==true?1:0;
        Object oldobj = planManageService.getPlanGroupById(id).get(0);
        tUserOperationService.recordOperationData(null,oldobj,tsysuser.getId(), SystemOperationCommon.OPERATION_DELETE,i, MenuModelCommon.PLANGROUP_MANAGE);
        String result = "y";
        if(!isOk){
            result = "n";
        }
        response.getWriter().println(result);
    }

    /**
     * 根据区域获取路段
     */
    @RequestMapping("getPlanRoadByAreaId")
    public void getPlanRoadByAreaId(Integer areaId,HttpServletResponse response) throws IOException {
        List<Map<String, Object>> roadList = planManageService.getPlanRoadByAreaId(areaId);
        String data = JSONArray.fromObject(roadList).toString();
        response.getWriter().println(data);
    }

    /**
     * 根据路段ID获取数据
     * @param roadId
     * @param response
     * @throws IOException
     */
    @RequestMapping("getConCernDataByRoadId")
    public void getConCernDataByRoadId(Integer roadId,Integer groupId,HttpServletResponse response) throws IOException{
        List<List<Map<String, Object>>> dataList = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> roadList = planManageService.getConCernDataByRoadId(roadId,groupId);
        List<Map<String, Object>> selGroupList = planManageService.getSelGroupData(roadId,groupId);
        dataList.add(roadList);
        dataList.add(selGroupList);
        String data = JSONArray.fromObject(dataList).toString();
        response.getWriter().println(data);
    }

    /**
     *  根据分组ID查找 相应的分组信息
     */
    @RequestMapping("getSelGroupByAreaId")
    public void getSelGroupByAreaId(Integer groupId,HttpServletResponse response) throws IOException {
        List<Map<String, Object>> selGroupList = planManageService.getSelGroupData(null,groupId);
        String data = JSONArray.fromObject(selGroupList).toString();
        response.getWriter().println(data);
    }

    /**
     * 保存选择的分组信息
     */
    @RequestMapping("savePlanGroupSelGroupData")
    public void saveSelGroupData(String data,HttpServletResponse response) throws IOException{
        JSONArray jsonArray = JSONArray.fromObject(data);
        List<Map<String, Object>> list  = new ArrayList<Map<String, Object>>();
        for (int i = 0;i < jsonArray.size();i++){
            Map<String,Object> dataMap = new HashMap<String,Object>();
            Map map = (Map)jsonArray.get(i);
            dataMap.put("concen_id",map.get("concen_id"));
            dataMap.put("controller_id",map.get("controller_id"));
            dataMap.put("lamp_id",map.get("lamp_id"));
            dataMap.put("is_work",map.get("is_work"));
            dataMap.put("group_id",map.get("group_id"));
            dataMap.put("road_id",map.get("road_id"));
            list.add(dataMap);
        }
        String groupIdObj = list.get(0).get("group_id").toString();
        Integer groupId = Integer.parseInt(groupIdObj);
        planManageService.deleteSelDataByGroupId(groupId);
        boolean isOk = planManageService.batchAdd(list);
        String msg = isOk == true ? "y":"n";
        response.getWriter().println(msg);
    }

    /**
     * 根据分组ID 获取分组相关信息
     * @param planId
     * @param response
     * @throws IOException
     */
    @RequestMapping("getDetailByPlanId")
    public void getDetailByPlanId(Integer planId,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> list =  planManageService.getDetailByPlanId(planId);
        String data = JSONArray.fromObject(list).toString();
        response.getWriter().println(data);
    }

    /** ------------------------------ 分组管理 end ----------------------------------------*/

    /** ------------------------------ 场景管理 begin --------------------------------------*/

    /**
     * 初始化场景数据
     */
    @RequestMapping("initSceneSetting")
    public void initSceneSetting(HttpServletRequest request,HttpServletResponse response,String name,Integer showNum, Integer curPage,String sort,String orderMsg) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",orgCode);
        dataMap.put("name",name);
        dataMap.put("curPage",curPage);
        dataMap.put("showNum",showNum);
        dataMap.put("sort",sort);
        dataMap.put("orderMsg",orderMsg);
        Map<String, Object> resultMap  =  planManageService.initSceneSetting(dataMap);
        Object obj = resultMap.get("data");
        if(null == obj){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(resultMap).toString());
        }

    }

    /**
     * 保存场景数据
     */
    @RequestMapping("saveSceneData")
    public void saveSceneData(TPlanSence tPlanSence,HttpServletResponse response,HttpServletRequest request) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);

        Integer status = 0;
            tPlanSence.setOperId(user.getId());
            tPlanSence.setOperTime(PlatformUtils.getNowTime());
            if(tPlanSence.getId() == null){
                tPlanSence.setCreateBy(user.getId());
                tPlanSence.setCreateTime(PlatformUtils.getNowTime());
                status = planManageService.saveSceneData(tPlanSence);
            }else{
                status = planManageService.updateSceneData(tPlanSence);
            }
        String result = 1 == status ? "y" : "n";
        response.getWriter().print(result);
    }
    @RequestMapping("selectBycname")
    public void seleBycname(String cname,HttpServletResponse response) throws  IOException{
        Integer status = 0;
        Integer psd=planManageService.getSceneDataByName(cname);
        if(psd>0){
            status=1;
        }
       response.getWriter().print(status);
    }

    @RequestMapping("getAllplanGroupData")
    public void getAllplanGroup(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String orgCode = PlatformUtils.getLoginUser(request).getTorganization().getOrgCode();
        List<Map<String, Object>> list = planManageService.getAllplanGroup(orgCode);
        System.out.println(list.get(0).get("group_name"));
        System.out.println(list.size());
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据区域ID获取分组信息
     */
    @RequestMapping("getPlanGroupIdByAreaId")
    public void getPlanGroupIdByAreaId(Integer areaId,String sort,String orderMsg,HttpServletResponse response,HttpServletRequest request) throws IOException{
        String orgCode = PlatformUtils.getLoginUser(request).getTorganization().getOrgCode();
        List<Map<String, Object>> groupList = planManageService.getplanGroup(areaId,orgCode,sort,orderMsg);
        String data = JSONArray.fromObject(groupList).toString();
        response.getWriter().println(data);
    }

    /**
     *  根据ID获取场景信息
     */
    @RequestMapping("getSceneDataById")
    public void getSceneDataById(Integer id,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap = planManageService.getSceneDataById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 删除场景
     */
    @RequestMapping("deletePlanSenceData")
    public void deletePlanSenceData(Integer id,HttpServletResponse response) throws IOException{
       boolean isOk =  planManageService.deletePlanSenceData(id);
       String msg = isOk == true ? "y":"n";
       response.getWriter().println(msg);
    }

    /**
     * 保存场景细节
     */
    @RequestMapping("saveSceneDetailData")
    public void saveSceneDetailData(TPlanSceneDetail tPlanSceneDetail,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tPlanSceneDetail.setOperId(user.getId());
        tPlanSceneDetail.setOperTime(PlatformUtils.getNowTime());
        Integer status = 0;
        if(tPlanSceneDetail.getId() == null){
            tPlanSceneDetail.setCreateBy(user.getId());
            tPlanSceneDetail.setCreateTime(PlatformUtils.getNowTime());
            status = planManageService.saveSceneDetailData(tPlanSceneDetail);
        } else {
            status = planManageService.updateSceneDetailData(tPlanSceneDetail);
        }
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);
    }

    /**
     * 查询所有场景细节
     */
    @RequestMapping("getPlanSceneDetailData")
    public void getPlanSceneDetailData(HttpServletResponse response,Integer id) throws IOException{
        List<Map<String, Object>> list = planManageService.getPlanSceneDetailData(id);
        if(list == null || list.size() == 0){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(list).toString());
        }
    }

    /**
     * 根据ID获取场景细节
     */
    @RequestMapping("getPlanSceneDetailById")
    public void getPlanSceneDetailById(HttpServletResponse response,Integer id) throws IOException{
       HashMap<String,Object> dataMap =  planManageService.getPlanSceneDetailById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 根据ID删除场景信息
     */
    @RequestMapping("deleteSceneDetailById")
    public void deleteSceneDetailById(Integer id,HttpServletResponse response) throws IOException {
        int status = planManageService.deleteSceneDetailById(id);
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);
    }

    /** ------------------------------ 场景管理 end --------------------------------------*/

    /** ------------------------------ 分组策略 start -------------------------------------*/

    @RequestMapping("getPlanStrategyList")
    public void getLampWarnningData(String cName,String sort,String orderBy,Integer curPage,Integer showNum,HttpServletResponse response,HttpServletRequest request) throws IOException {
        String orgCode = PlatformUtils.getLoginUser(request).getTorganization().getOrgCode();
        Map<String,Object> resultMap = null;
         HashMap<String,Object> sqlMap = new HashMap<>();
         sqlMap.put("cName",cName);
         sqlMap.put("sort",sort);
         sqlMap.put("orderBy",orderBy);
         sqlMap.put("showNum",showNum);
         sqlMap.put("curPage",curPage);
         sqlMap.put("orgCode",orgCode);
        try {
            resultMap =  planManageService.getPlanstrategyList(sqlMap);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(PlatformUtils.checkMapDataIsEmpty(resultMap)){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(resultMap).toString());
        }

    }

    /**
     *  获取分组策略计划
     */
    @RequestMapping("getPlanStrategyPlanData")
    public void getPlanStrategyPlanData(HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list =  planManageService.getPlanStrategyPlanData();
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 获取场景信息
     */
    @RequestMapping("getPlanSenceDataForSelect")
    public void getPlanSenceDataForSelect(HttpServletResponse response,HttpServletRequest request) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        List<Map<String, Object>> list =  planManageService.getPlanSenceDataForSelect(orgCode);
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(list).toString());
        }
    }

    /**
     *
     *  初始化 分组策略信息
     */
    @RequestMapping("getPlanStrategyData")
    public ModelAndView getPlanStrategyData(HttpServletRequest request){
        String orgCode = PlatformUtils.getLoginUser(request).getTorganization().getOrgCode();
        List<Map<String, Object>> areaList =  planManageService.getAreasByOrgCode(orgCode);
        List<Map<String, Object>> contentList = planManageService.getPlanContent();
        ModelAndView mv = new ModelAndView();
        mv.addObject("areaList",areaList);
        mv.addObject("contentList",contentList);
        mv.setViewName("PlanStrategyManage");
        return mv;
    }

    /**
     * 删除计划
     * @param id
     */
    @RequestMapping("deltePlanStrategyById")
    public void deltePlanStrategyById(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
        //Tsysuser user = PlatformUtils.getLoginUser(request);
        //Object oldobj = planManageService.getPlanStragegyById(id).get(0);
        boolean result = planManageService.deltePlanStrategyById(id);
        int i = result==true?1:0;
        //tUserOperationService.recordOperationData(null,oldobj,user.getId(), SystemOperationCommon.OPERATION_DELETE,i,MenuModelCommon.GROUPSTRATEMANAGE);
        String msg = result == true ? "y" : "n";
        response.getWriter().println(msg);
    }

    /**
     * 保存分组策略
     */
    @RequestMapping("savePlanStrategyData")
    public void savePlanStrategyData(TPlanStategy ts,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        ts.setOperId(user.getId());
        ts.setOperTime(PlatformUtils.getNowTime());
        boolean status = false;
        if(null == ts.getId()){
            ts.setCreateBy(user.getId());
            ts.setCreateTime(PlatformUtils.getNowTime());
            status = planManageService.savePlanStrategyData(ts);
           // Object nowobj = planManageService.getPlanStragegyById(ts.getId()).get(0);
            //tUserOperationService.recordOperationData(nowobj,null,user.getId(), SystemOperationCommon.OPERATION_ADD,i,MenuModelCommon.GROUPSTRATEMANAGE);
        } else {
            //Object oldobj = planManageService.getPlanStragegyById(ts.getId()).get(0);
            status = planManageService.updatePlanStragegyById(ts);
            //Object nowobj = planManageService.getPlanStragegyById(ts.getId()).get(0);
            //tUserOperationService.recordOperationData(nowobj,oldobj,user.getId(), SystemOperationCommon.OPERATION_UPDATE,i,MenuModelCommon.GROUPSTRATEMANAGE);
        }
        String msg = status == true ? "y" : "n";
        response.getWriter().println(msg);
    }

    /**
     *  查询所有分组计划信息
     */
    @RequestMapping("ajaxSePlanStrategyData")
    public void ajaxSePlanStrategyData(Integer areaId,Integer groupId,String sort,String orderBy,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> areaList = planManageService.ajaxSePlanStrategyData(areaId,groupId,sort,orderBy);
        String data = JSONArray.fromObject(areaList).toString();
        response.getWriter().println(data);
    }

    /**
     * 根据主键获取计划信息
     * @param id 主键
     */
    @RequestMapping("getPlanStragegyById")
    public void getPlanStragegyById(Integer id,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> stragegyList =  planManageService.getPlanStragegyById(id);
        String data = JSONArray.fromObject(stragegyList).toString();
        response.getWriter().println(data);
    }

    /**
     * 根据策略计划主键查找详细信息和集中器名称
     * @param id 主键
     * @param response
     * @throws IOException
     */
    @RequestMapping("getConcentratorByStragegyId")
    public void getConcentratorByStragegyId(Integer id,String sort,String orderBy,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> stragegyList =  planManageService.getConcentratorByStragegyId(id,sort,orderBy);
        String data = JSONArray.fromObject(stragegyList).toString();
        response.getWriter().println(data);
    }

    /** ------------------------------ 分组策略 end -------------------------------------*/

    /** ------------------------------ 灯离线策略 start ----------------------------------*/

    /**
     *
     *  初始化 灯离线策略
     */
    @RequestMapping("getPlanLightStrategyData")
    public ModelAndView getPlanLightStrategyData(HttpServletRequest request){
        String orgCode = PlatformUtils.getLoginUserCode(request);
        List<Map<String, Object>> areaList =  lampCommonService.getAreaNameForSelect(orgCode);
        List<Map<String, Object>> contentList = planManageService.getLightContent();
        ModelAndView mv = new ModelAndView();
        mv.addObject("areaList",areaList);
        mv.addObject("contentList",contentList);
        mv.setViewName("PlanLightLeaveStrategy");
        return mv;
    }

    /**
     *  根据区域ID获取所属路段
     */
    @RequestMapping("getRoadByAreaId")
    public void getRoadByAreaId(Integer id,HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list =  planManageService.getRoadByAreaId(id);
        String data = JSONArray.fromObject(list).toString();
        response.getWriter().println(data);
    }

    /**
     * 根据路段ID获取所属灯具
     */
    @RequestMapping("getLampByRoadId")
    public void getLampByRoadId(Integer id,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> list = planManageService.getLampByRoadId(id);
        String data = JSONArray.fromObject(list).toString();
        response.getWriter().println(data);
    }

    /**
     * 根据灯具ID获取相关离线数据
     * @param lampId
     * @param response
     * @throws IOException
     */
    @RequestMapping("getLampStrategyByLampId")
    public void getLampStrategyByLampId(HttpServletRequest request,Integer lampId,String orderBy,String sort,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> list = planManageService.getLampStrategyByLampId(request,lampId,orderBy,sort);
        String data = JSONArray.fromObject(list).toString();
        response.getWriter().println(data);
    }

    /**
     *  保存策略
     */
    @RequestMapping("saveLampStrategyData")
    public void saveLampStrategyData(HttpServletRequest request,TLightStrategyManage tm,HttpServletResponse response) throws IOException{
        Integer dimming = tm.getDimming();
        if(null == dimming){
            tm.setDimming(0);
        }
        boolean result = false;
        if(null == tm.getId()){
            /*获取用户信息*/
            Tsysuser tsysuser1 = PlatformUtils.getLoginUser(request);
            tm.setCreateby(tsysuser1.getId());
            tm.setCreateTime(PlatformUtils.getNowTime());
           result =  planManageService.saveLampStrategyData(tm);
            int i = result==true?1:0;
            tUserOperationService.recordOperationData(tm,null,tsysuser1.getId(), SystemOperationCommon.OPERATION_ADD,i,MenuModelCommon.LAMPSTRATEGYMANAGE);
        } else {
            result =  planManageService.updateLampStrategy(tm);
        }
        String msg = result == true ? "y" : "n";
        response.getWriter().println(msg);
    }

    /**
     * 删除灯离线计划
     */
    @RequestMapping("delLampStrategyData")
    public void delLampStrategyData(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser tsysuser1 = PlatformUtils.getLoginUser(request);
        Object oldobj =planManageService.getLampStrategyById(id).get(0);
        boolean  result =  planManageService.delLampStrategyData(id);
        String msg = result == true ? "y" : "n";
        int i = result==true?1:0;
        tUserOperationService.recordOperationData(null,oldobj,tsysuser1.getId(), SystemOperationCommon.OPERATION_DELETE,i,MenuModelCommon.LAMPSTRATEGYMANAGE);
        response.getWriter().print(msg);
    }

    /**
     *  根据主键获取灯离线数据
     */
    @RequestMapping("getLampStrategyById")
    public void getLampStrategyById(Integer id,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> list =  planManageService.getLampStrategyById(id);
        String data = JSONArray.fromObject(list).toString();
        response.getWriter().println(data);
    }

    /**
     * 获取日出日落时间
     */
    @RequestMapping("getSunRiseAndSunSet")
    public void getSunRiseAndSunSet(HttpServletResponse response,HttpServletRequest request) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        Map<String,Object> dataMap = planManageService.getSunRiseAndSunSet(orgCode);
        String data = JSONArray.fromObject(dataMap).toString();
        response.getWriter().print(data);
    }
    /** ------------------------------ 光照调光策略 start ----------------------------------*/
    /**
     * 保存光调光策略
     * parameter Light_strategy
     *return int
     */
    @RequestMapping("saveLight_strategy")
    public void saveLight_strategy(HttpServletRequest request,Light_strategy ls,HttpServletResponse response) throws IOException{
        int result = 0;
        String time = PlatformUtils.getNowTime();
        Tsysuser user = PlatformUtils.getLoginUser(request);
        ls.setOper_id(user.getId());
        ls.setOper_time(time);
        if(ls.getId()==null){
            ls.setCreateby(user.getId());
            ls.setCreate_time(time);
            result =planManageService.saveLight_strategy(ls);
            Object nowObj= planManageService.selectLight_strategyByid(ls.getId());
           tUserOperationService.recordOperationData(nowObj,null,user.getId(), SystemOperationCommon.OPERATION_ADD,result,MenuModelCommon.LIGHTMODULATIONMANAGE);
        }else {
            Object oldObj= planManageService.selectLight_strategyByid(ls.getId());
            result =planManageService.updateLight_strategy(ls);
            Object nowObj= planManageService.selectLight_strategyByid(ls.getId());
            tUserOperationService.recordOperationData(nowObj,oldObj,user.getId(), SystemOperationCommon.OPERATION_UPDATE,result,MenuModelCommon.LIGHTMODULATIONMANAGE);

        }

        String msg = result == 1 ? "y":"n";
        response.getWriter().print(msg);
    }
    /**
     * 查询光调光策略
     *
     */
    @RequestMapping("selectLight_strategy")
    public void selectLight_strategy(HttpServletRequest request,HttpServletResponse response,
                                     Integer showNum,String cname,Integer curPage,String orderBy,String sort) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("showNum",showNum);
        dataMap.put("cname",cname);
        dataMap.put("curPage",curPage);
        dataMap.put("orderBy",orderBy);
        dataMap.put("sort",sort);
        Map<String,Object> list = null;
        try {
            list =  planManageService.selectLight_strategy(dataMap);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 通过id查询光调光策略
     * @param id
     * @param response
     */
    @RequestMapping("selectLight_strategyByid")
    public void selectLight_strategyByid(Integer id,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap = planManageService.selectLight_strategyByid(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 删除光调光策略
     *@param id
     *
     */
    @RequestMapping("deleteLight_strategyById")
    public void deleteLight_strategyById(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser tsysuser1 = PlatformUtils.getLoginUser(request);
        Object oldObj= planManageService.selectLight_strategyByid(id);
        int result = planManageService.DeleteLight_strategyByid(id);
        tUserOperationService.recordOperationData(null,oldObj,tsysuser1.getId(), SystemOperationCommon.OPERATION_DELETE,result,MenuModelCommon.LIGHTMODULATIONMANAGE);
        String msg = result == 1 ? "y":"n";
        response.getWriter().print(msg);
    }




    /** ------------------------------ 光照调光策略 end -------------------------------------*/

}
