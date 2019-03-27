package com.lamp.controller;
import com.lamp.service.IMonitorService;
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
 * 监控中心模块代码
 */
@Controller
public class MonitorController {

    @Autowired
    private IMonitorService monitorService;

    /**
     * 获取今日功率曲线图数据
     */
    @RequestMapping("today_power")
    public void today_power(HttpServletResponse response) throws IOException{
        String data = monitorService.getPowerList();
        response.getWriter().println(data);
    }

    /**
     *  显示菜单也
     * @param request
     * @return
     */
    @RequestMapping("getMenuJsp")
    public String getMenuJsp(HttpServletRequest request,String urlTo){
        request.setAttribute("urlTo", urlTo);
        return "menu";
    }

    /**---------------------------------------监控中心 start-----------------------------------------------*/

    /**
     * 获取经纬度
     */
    @RequestMapping("getLongitudeAndlatitude")
    public void getLongitudeAndlatitude(HttpServletRequest request,HttpServletResponse response,String dataType,String dataId) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",orgCode);
        dataMap.put("dataType",dataType);
        dataMap.put("dataId",dataId);
        HashMap<String,Object> resultMap = monitorService.getLongitudeAndlatitude(dataMap);
        if(resultMap == null || resultMap.size() == 0){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(resultMap).toString());
        }
    }

    /**
     * 功率分析
     * @param request
     * @param roadId
     * @return
     */
    @RequestMapping(value = "queryRoadPower")
    public void queryRoadPower(HttpServletRequest request,String roadId,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",orgCode);
        dataMap.put("roadId",roadId);
        List<Map<String,Object>> list = monitorService.compareTodayPower(dataMap);
        if(PlatformUtils.isEmptyList(list)){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(list).toString());
        }
    }

    /**
     * 根据控制器ID查询所属分组
     */
    @RequestMapping("getGroupManageByControllerId")
    public void getGroupManageByControllerId(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        List<Map<String, Object>> list =  monitorService.getGroupManageByControllerId(id,orgCode);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     *  页面初始化获取灯具位置信息
     */
    @RequestMapping("getLampDataLocation")
    public void getLampDataLocation(HttpServletResponse response,HttpServletRequest request) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        List<Map<String, Object>> list =  monitorService.getLampDataLocation(orgCode,null);
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(list).toString());
        }
    }

    /**
     * 获取灯具数据信息(监控中心) 初始化页面
     */
    @RequestMapping("getLampDetailList")
    public void getLampDetailList(HttpServletRequest request,HttpServletResponse response,Integer id) throws IOException{
        String data = monitorService.getLampDetail(request,id);
        response.getWriter().println(data);
    }

    /**
     * 获取灯控窗口 灯具状态
     * @param id 控制器ID
     */
    @RequestMapping("getDeviceStatusForWin")
    public void getDeviceStatusForWin(Integer id,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap =  monitorService.getDeviceStatusForWin(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     *  获取配电箱数据信息
     */
    @RequestMapping("getDistributionListForMap")
    public void getDistributionListForMap(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        List<Map<String, Object>> list =  monitorService.getDistributionListForMap(orgCode);
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(list).toString());
        }
    }

    /**
     * 单灯控制
     */
    @RequestMapping("singleDeviceController")
    public void singleDeviceController(HttpServletResponse response,Integer id,Integer onOff,Integer oper,Integer dimming) throws IOException {
        HashMap<String,Object> dataMap = monitorService.singleDeviceController(id, onOff, oper, dimming);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 批量处理灯控
     * @param request
     * @param ids
     * @param onOff
     * @return
     */
    @RequestMapping("batchSwitchOperLamp")
    public void batchSwitchOperLamp(HttpServletRequest request,String ids,Integer onOff,Integer oper,Integer dimming,HttpServletResponse response) throws IOException {
        HashMap<String,Object> dataMap = monitorService.batchSwitchOperLamp(request,ids,onOff,oper,dimming);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 道路控制
     */
    @RequestMapping("RoadSwitchController")
    public void RoadSwitchController(Integer roadId,Integer on_off,Integer oper,Integer dimming,HttpServletRequest request,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap =  monitorService.RoadSwitchController(request,roadId,on_off,oper,dimming);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 分组控制
     */
    @RequestMapping("groupSwitchController")
    public void groupSwitchController(Integer groupId,Integer on_off,Integer oper,Integer dimming,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap =  monitorService.groupSwitchController(groupId,on_off,oper,dimming);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 回路控制
     */
    @RequestMapping("loopSwitchController")
    public void loopSwitchController(Integer concenId, Integer onOff,Integer loop,Integer oper,Integer dimming,
                                     HttpServletResponse response,HttpServletRequest  request) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap =  monitorService.loopSwitchController(request,concenId,onOff,loop,oper,dimming,orgCode);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 回路开关控制
     * @param loop
     * @param concentratorId
     * @param onOff
     * @param response
     * @throws IOException
     */
    @RequestMapping("loopSwitch")
    public void loopSwitch(Integer loop,Integer concentratorId,Integer onOff,HttpServletResponse response) throws IOException{
        boolean flag = monitorService.loopSwitch(loop,concentratorId,onOff);
        String data = flag == true ? "y":"n";
        response.getWriter().println(data);
    }

    /**
     * 获取所有灯具细节
     * @throws IOException
     */
    @RequestMapping("getAllLampDetailData")
    public void getAllLampDetailData(Integer curpage,Integer showNum,String roadId,Integer groupId,HttpServletResponse response) throws IOException{
        Map<String,Object> dataMap =  monitorService.getAllLampDetailData(curpage,showNum,roadId,groupId);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 获取单个灯具状态
     */
    @RequestMapping("getSingleLampLotData")
    public void getSingleLampLotData(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap = monitorService.getSingleLampLotData(request,id);
        if(null == dataMap || dataMap.size() == 0){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(dataMap).toString());
        }
    }

    /**
     *  获取多个灯具状态数据
     */
    @RequestMapping("getManyLampLotData")
    public void getManyLampLotData(String roadId,HttpServletResponse response) throws IOException{
        String msg = monitorService.getManyLampLotData(roadId);
        response.getWriter().print(msg);
    }

    /**---------------------------------------监控中心 end-----------------------------------------------*/

    /**
     *  获取地图数据显示登录人所在城市信息
     */
    @RequestMapping("getCityForMap")
    public void getCityForMap(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String code = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = monitorService.getCityForMap(code);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**-------------------------------新的*/
    /**
     * 获取所有灯具细节
     * @throws IOException
     */
    @RequestMapping("getAllLampDetailData2")
    public void getAllLampDetailData2(HttpServletRequest request,Integer curpage,Integer showNum,String roadId,String areaId,String roadxId,HttpServletResponse response) throws IOException{
        Map<String,Object> dataMap =  monitorService.getAllLampDetailData2(request , curpage, showNum, roadId, areaId, roadxId);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    @RequestMapping("getAllLampDetailData3")
    public void getAllLampDetailData3(Integer curpage,Integer showNum,String groupId,HttpServletRequest request,HttpServletResponse response) throws IOException{
        Map<String,Object> dataMap =  monitorService.getAllLampDetailData3(request ,curpage, showNum, groupId);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }
}
