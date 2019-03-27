package com.lamp.controller;

import com.lamp.model.Tsysuser;
import com.lamp.service.LampCommonService;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 灯具通用类
 */
@Controller
@RequestMapping("lampCommon")
public class LampCommonController {

    @Autowired
    private LampCommonService lampCommonService;

    /**
     * 区域
     */
    @RequestMapping("getAreaNameForSelect")
    public void getAreaNameForSelect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = PlatformUtils.getLoginUserCode(request);
        List<Map<String, Object>>  list = lampCommonService.getAreaNameForSelect(code);
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(list).toString());
        }

    }

    /**
     * 路段
     */
    @RequestMapping("getRoadNameForSelect")
    public void getRoadNameForSelect(HttpServletRequest request, HttpServletResponse response,String areaId) throws IOException{
        String code = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",code);
        dataMap.put("areaId",areaId);
        List<Map<String, Object>>  list = lampCommonService.getRoadNameForSelect(dataMap);
        judgeDataResult(list,response);

    }

    /**
     * 线路
     */
    @RequestMapping("getRoadLineNameForSelect")
    public void getRoadLineNameForSelect(HttpServletRequest request,HttpServletResponse response,String roadId)  throws IOException{
        String code = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",code);
        dataMap.put("roadId",roadId);
        List<Map<String, Object>>  list = lampCommonService.getRoadLineNameForSelect(dataMap);
        judgeDataResult(list,response);
    }

    /**
     * 灯具
     */
    @RequestMapping("getLampNumForSelect")
    public void getLampNumForSelect(HttpServletRequest request,HttpServletResponse response,String lineId)  throws IOException{
        String code = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",code);
        dataMap.put("lineId",lineId);
        List<Map<String, Object>>  list = lampCommonService.getLampNumForSelect(dataMap);
        judgeDataResult(list,response);
    }

    /**
     *  分组
     */
    @RequestMapping("getGroupNameForSelect")
    public void getGroupNameForSelect(Integer areaId,HttpServletRequest request,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap = new HashMap<>();
        String code = PlatformUtils.getLoginUserCode(request);
        dataMap.put("orgCode",code);
        dataMap.put("areaId",areaId);
        List<Map<String, Object>>  list = lampCommonService.getGroupNameForSelect(dataMap);
        judgeDataResult(list,response);
    }

    /**
     * 计划内容
     */
    @RequestMapping("getPlanContentForSelect")
    public void getPlanContentForSelect(HttpServletResponse response) throws IOException{
        List<Map<String, Object>>  list = lampCommonService.getPlanContentForSelect();
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 获取菜单按钮信息
     */
    @RequestMapping("getMenuBtnByUser")
    public void getMenuBtnByUser(Integer menuId,HttpServletRequest request,HttpServletResponse response) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        Integer roleId = user.getAuthId();
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("menuId",menuId);
        dataMap.put("roleId",roleId);
        List<Map<String, Object>> list = lampCommonService.getMenuBtnByUser(dataMap);
        judgeDataResult(list,response);
    }

    /**
     * 配电箱
     */
    @RequestMapping("getDistributionBoxForSelect")
    public void getDistributionBoxForSelect(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        List<Map<String, Object>> list = lampCommonService.getDistributionBoxForSelect(orgCode);
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(list).toString());
        }
    }

    /**
     * 模块
     */
    @RequestMapping("getMenuDataForSelect")
    public void getMenuDataForSelect(HttpServletResponse response) throws IOException {
        List<Map<String, Object>>  list = lampCommonService.getMenuDataForSelect();
        judgeDataResult(list,response);
    }

    /**
     * 调光模式
     */
    @RequestMapping("getDimmingModelForSelect")
    public void getDimmingModelForSelect(HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list =  lampCommonService.getDimmingModelForSelect();
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 控制器类型
     */
    @RequestMapping("getControllerKindForSelect")
    public void getControllerKindForSelect(HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = lampCommonService.getControllerKindForSelect();
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 集中器
     */
    @RequestMapping("getConcentratorForSelect")
    public void getConcentratorForSelect(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String code = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",code);
        List<Map<String, Object>>  list = lampCommonService.getConcentratorForSelect(dataMap);
        judgeDataResult(list,response);
    }

    /**
     * 维修人员
     */
    @RequestMapping("getRepairPeopleForSelect")
    public void getRepairPeopleForSelect(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String code = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",code);
        List<Map<String, Object>>  list = lampCommonService.getRepairPeopleForSelect(dataMap);
        judgeDataResult(list,response);
    }

    /**
     * 施工标准
     */
    @RequestMapping("getBuildStandardForSelect")
    public void getBuildStandardForSelect(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String code = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",code);
        List<Map<String, Object>>  list = lampCommonService.getBuildStandardForSelect(dataMap);
        judgeDataResult(list,response);
    }

    /**
     * 设备类别
     */
    @RequestMapping("getDeviceTypeDataForSelect")
    public void getDeviceTypeDataForSelect(HttpServletResponse response) throws IOException{
        List<Map<String, Object>>  list = lampCommonService.getDeviceTypeDataForSelect();
        judgeDataResult(list,response);
    }

    /**
     * 控制器(设备编号)
     */
    @RequestMapping("getControllerNumDataForSelect")
    public void getControllerNumDataForSelect(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String code = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",code);
        List<Map<String, Object>>  list = lampCommonService.getControllerNumDataForSelect(dataMap);
        judgeDataResult(list,response);
    }

    /**
     * 灯具类型(型号)
     */
    @RequestMapping("getLampTypeDataForSelect")
    public void getLampTypeDataForSelect(Integer typeId,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String code = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",code);
        dataMap.put("typeId",typeId);
        List<Map<String, Object>>  list = lampCommonService.getLampTypeDataForSelect(dataMap);
        judgeDataResult(list,response);
    }

    /**
     * 通用结果判断
     */
    private void judgeDataResult(List<Map<String, Object>> list,HttpServletResponse response) throws IOException {
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(list).toString());
        }
    }
}
