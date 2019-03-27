package com.lamp.controller;
import com.lamp.common.MenuModelCommon;
import com.lamp.common.SystemOperationCommon;
import com.lamp.model.*;
import com.lamp.service.MaintainService;
import com.lamp.service.TUserOperationService;
import com.lamp.utils.ExcelUtil;
import com.lamp.utils.GetLocalTimes;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Maintain")
public class MaintainController {

    @Autowired
    private MaintainService maintainService;
    @Autowired
    private TUserOperationService tUserOperationService;

    /**
     * 施工信息查询
     */
    @RequestMapping("getBuildingShowData")
    public void getBuildingShowData(Integer showNum, Integer curPage, String orderNum, String startDate, String endDate,
                                    String sort, String orderBy, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("orderNum",orderNum);
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        paramMap.put("orgCode",orgCode);
        Map<String,Object> resultMap =  maintainService.getBuildingInfoData(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 导出施工信息
     */
    @RequestMapping("exportBuildingData")
    public void exportBuildingData(String orderNum,String startDate,String endDate,HttpServletRequest request,HttpServletResponse response) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("orderNum",orderNum);
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        paramMap.put("orgCode",orgCode);
        List<Map<String,Object>> dataList = maintainService.exportBuildingInfoData(paramMap);
        com.alibaba.fastjson.JSONArray ja =new com.alibaba.fastjson.JSONArray();
        if(PlatformUtils.isEmptyList(dataList)){
            Map<String,String> headMap = new LinkedHashMap<>();
            ExcelUtil.downloadExcelFile("施工信息列表", headMap,ja,response);
            return;
        }
        Map<String,String> titleMap = new LinkedHashMap<>();
        titleMap.put("ordernum","编号");
        titleMap.put("areaName","区域");
        titleMap.put("road_name","道路");
        titleMap.put("modelnum","设备型号");
        titleMap.put("devicenum","设备编号");
        titleMap.put("name","维修人员");
        titleMap.put("number","工号");
        titleMap.put("buildname","设备类型");
        titleMap.put("repairtype","维护类型");
        titleMap.put("deal_result","处理结果");
        titleMap.put("buildtime","施工时间");
        ja.addAll(dataList);
        ExcelUtil.downloadExcelFile("施工信息列表", titleMap,ja,response);
    }

    /**
     * 获取维修人员姓名和工号
     */
    @RequestMapping("getRepairPeopleAndNumberData")
    public void getRepairPeopleAndNumberData(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        List<Map<String,Object>> list = maintainService.getRepairPeopleAndNumberData(orgCode);
        if(PlatformUtils.isEmptyList(list)){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(list).toString());
        }
    }

    /**
     * 根据id获取施工信息
     */
    @RequestMapping("getBuildingInfoDataById")
    public void getBuildingInfoDataById(Integer id,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap = maintainService.getBuildingInfoDataById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 删除施工信息
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("deleteBuildingShowData")
    public void deleteBuildingShowData(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
        //Tsysuser user = PlatformUtils.getLoginUser(request);
       // Object nowObj = iBaseDeviceService.getBuildingInfoDataById(id).get(0);
        int status = maintainService.delBuildingInfoData(id);
       // tUserOperationService.recordOperationData(nowObj,null,user.getId(), SystemOperationCommon.OPERATION_DELETE,data, MenuModelCommon.INFORMATION_MANGAGE);
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 保存施工管理信息
     */
    @RequestMapping("saveBuildingData")
    public void saveBuildingData(Tbuildinfo tt, HttpServletResponse response, HttpServletRequest request) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tt.setOperid(user.getId());
        tt.setOpertime(GetLocalTimes.getNowTime());
        int status = 0;
        if(tt.getId() == null){
            tt.setCreateTime(PlatformUtils.getNowTime());
            tt.setCreateby(user.getId());
            status = maintainService.addBuildingInfoData(tt);
           // Object nowObj = iBaseDeviceService.getBuildingInfoDataById(tt.getId()).get(0);
            //tUserOperationService.recordOperationData(nowObj,null,user.getId(), SystemOperationCommon.OPERATION_ADD,data, MenuModelCommon.INFORMATION_MANGAGE);
        }else{
            //Object oldObj  = iBaseDeviceService.getBuildingInfoDataById(tt.getId()).get(0);
            status = maintainService.updateBuildingInfoData(tt);
            //Object nowObj=iBaseDeviceService.getBuildingInfoDataById(tt.getId()).get(0);
            //tUserOperationService.recordOperationData(nowObj,oldObj,user.getId(), SystemOperationCommon.OPERATION_UPDATE,data, MenuModelCommon.INFORMATION_MANGAGE);
/*            Integer deal = tt.getDeal_result();
            if(1 == deal){//完成
                List<Map<String, Object>> list = iBaseDeviceService.getCountRecord_warnByOrdernum(tt.getOrdernum());
                if(null != list || list.size() > 0){//判断是否在报警表中存在数据
                    Map<String, Object> map = list.get(0);
                    String nb_device =  map.get("nb_device").toString();
                    String orderNum = map.get("ordernum").toString();
                    iBaseDeviceService.updateLampWarnDataByOrderNum(orderNum,nb_device);
                }
            }*/
        }
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 登录日志
     */
    @RequestMapping("queryLogUserList")
    public void queryLogUserList(Integer showNum, Integer curPage, String startDate, String endDate,
                                 String sort, String orderBy, HttpServletRequest request, HttpServletResponse response) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        paramMap.put("orgCode",orgCode);
        Map<String,Object> resultMap =  maintainService.queryLogUserList(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 操作日志
     */
    @RequestMapping("getOperationLog")
    public void getOperationLog(Integer showNum, Integer curPage, String startDate, String endDate,Integer kindId,
                                 String sort, String orderBy, HttpServletRequest request,Integer menuId, HttpServletResponse response) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        paramMap.put("orgCode",orgCode);
        paramMap.put("kindId",kindId);
        paramMap.put("menuId",menuId);
        Map<String,Object> resultMap =  maintainService.getOperationLog(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 查看普通操作日志
     */
    @RequestMapping("getOperationLogData")
    public void getOperationLogData(HttpServletRequest request,Integer curPage,Integer showNum,Integer areaId,Integer roadId,
                                    String orderBy,String sort,Integer typeId,
                                    Integer lineId,Integer lampId,String startDate,String endDate,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",orgCode);
        dataMap.put("curPage",curPage);
        dataMap.put("showNum",showNum);
        dataMap.put("areaId",areaId);
        dataMap.put("roadId",roadId);
        dataMap.put("lineId",lineId);
        dataMap.put("lampId",lampId);
        dataMap.put("startDate",startDate);
        dataMap.put("endDate",endDate);
        dataMap.put("orderBy",orderBy);
        dataMap.put("sort",sort);
        dataMap.put("typeId",typeId);
        Map<String, Object> resultMap = maintainService.getOperationLogData(dataMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     *  导出数据日志操作
     */
    @RequestMapping("exportOperationLampData")
    public void exportOperationLampData(HttpServletRequest request,Integer curPage,Integer showNum,Integer areaId,Integer roadId,
                                        String orderBy,String sort,Integer typeId,
                                        Integer lineId,Integer lampId,String startDate,String endDate,HttpServletResponse response){
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",orgCode);
        dataMap.put("curPage",curPage);
        dataMap.put("showNum",showNum);
        dataMap.put("areaId",areaId);
        dataMap.put("roadId",roadId);
        dataMap.put("lineId",lineId);
        dataMap.put("lampId",lampId);
        dataMap.put("startDate",startDate);
        dataMap.put("endDate",endDate);
        dataMap.put("orderBy",orderBy);
        dataMap.put("sort",sort);
        dataMap.put("typeId",typeId);
        List<Map<String, Object>> list = maintainService.exportOperationLampData(dataMap);
        com.alibaba.fastjson.JSONArray ja = new com.alibaba.fastjson.JSONArray();
        if(PlatformUtils.isEmptyList(list)){
            Map<String,String> headMap = new LinkedHashMap<>();
            headMap.put("areaname","");
            headMap.put("power","");
            ExcelUtil.downloadExcelFile("数据日志列表", headMap,ja,response);
            return;
        }
        ja.addAll(list);

        Map<String,String> headMap = new LinkedHashMap<>();
        headMap.put("areaName","区域");
        headMap.put("road_name","道路");
        headMap.put("cname","线路");
        headMap.put("poleCode","灯杆编号");
        headMap.put("lampModel","灯具型号");
        headMap.put("lampnum","灯具编号");
        headMap.put("power","额定功率");
        headMap.put("lampFactory","灯具厂家");
        headMap.put("lamptypename","灯具类型");
        headMap.put("nb_device","设备ID号");
        headMap.put("nb_code","设备号");
        headMap.put("kindname","控制器类型");
        headMap.put("c_num","控制器型号");
        headMap.put("factory_name","控制器厂家");
        headMap.put("business","运营商");
        headMap.put("protocol","协议类型");
        headMap.put("sim_code","SIM卡号");
        headMap.put("eleName","配电箱编号");
        headMap.put("on_off","开关状态");
        headMap.put("conn_state","连接状态");
        if(1 == typeId){
            headMap.put("vol","电压");
            headMap.put("ele","电流");
            headMap.put("power","功率");
            headMap.put("dimming","调光率");
        } else if(2 == typeId){
            headMap.put("vol","LED电压");
            headMap.put("ele","LED电流");
            headMap.put("pvol","光伏电压");
            headMap.put("temp","温度");
            headMap.put("pele","光伏电流");
            headMap.put("bvol","蓄电池电压");
        }

        headMap.put("record_time","上传时间");
        ExcelUtil.downloadExcelFile("数据日志列表", headMap,ja,response);
    }

    /**
     * 查询巡检管理
     */
    @RequestMapping(value = "/getRoutingData")
    public void getRoutingData(HttpServletRequest request,Integer showNum,Integer curPage,String sort , String orderBy,
                                        String cname, HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("showNum",showNum);
        dataMap.put("curPage",curPage);
        dataMap.put("orgCode",orgCode);
        dataMap.put("cname",cname);
        dataMap.put("sort",sort);
        dataMap.put("orderBy",orderBy);
        Map<String,Object> resultMap = maintainService.getRoutingData(dataMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 根据ID查询巡检管理
     */
    @RequestMapping("getRoutingDataById")
    public void getRoutingDataById(Integer id,HttpServletResponse response) throws IOException{
        HashMap<String,Object> mapList = maintainService.getRoutingDataById(id);
        response.getWriter().print(JSONArray.fromObject(mapList).toString());
    }

    /**
     * 删除巡检管理
     */
    @RequestMapping("delRoutingDataById")
    public void delRoutingDataById(Integer id,HttpServletResponse response) throws IOException {
        int status = maintainService.delRoutingDataById(id);
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 保存巡检管理
     */
    @RequestMapping("saveRoutingData")
    public void saveRoutingData(HttpServletRequest request, TroutingInspection  troutingInspection, HttpServletResponse response) throws IOException{
        int status = 0;
        Tsysuser user = PlatformUtils.getLoginUser(request);
        troutingInspection.setOperid(user.getId());
        troutingInspection.setOpertime(PlatformUtils.getNowTime());
        if (troutingInspection.getId()==null){
            troutingInspection.setCreateby(user.getId());
            troutingInspection.setCreateTime(PlatformUtils.getNowTime());
            status=maintainService.addRoutingData(troutingInspection);
            //Object nowobj= maintainService.getRoutingDataById(troutingInspection.getId());
            //tUserOperationService.recordOperationData(nowobj,null,user.getId(), SystemOperationCommon.OPERATION_ADD,result, MenuModelCommon.INSPECTION_MANAGE);
        }else {
           // Object oldobj = maintainService.getRoutingDataById(troutingInspection.getId());
              status = maintainService.updateRoutingData(troutingInspection);
           // tUserOperationService.recordOperationData(troutingInspection,oldobj,user.getId(), SystemOperationCommon.OPERATION_UPDATE,result, MenuModelCommon.INSPECTION_MANAGE);
        }
        response.getWriter().print(status == 1 ? "y":"n");
    }

    /**
     * 查询库存信息
     */
    @RequestMapping("/getStockData")
    public void getStockData(HttpServletRequest request,HttpServletResponse response,Integer showNum,
                             Integer curPage,Integer cname,String equipName,String sort,String orderBy) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("showNum",showNum);
        dataMap.put("cname",cname);
        dataMap.put("curPage",curPage);
        dataMap.put("equipName",equipName);
        dataMap.put("orgCode",orgCode);
        dataMap.put("sort",sort);
        dataMap.put("orderBy",orderBy);
        Map<String,Object> resultMap = maintainService.getStockData(dataMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 根据ID查询库存管理
     */
    @RequestMapping("getStockDataById")
    public void getStockDataById(Integer id,HttpServletResponse response) throws IOException{
        HashMap<String,Object> mapList = maintainService.getStockDataById(id);
        response.getWriter().print(JSONArray.fromObject(mapList).toString());
    }

    /**
     * 根据ID删除库存管理
     */
    @RequestMapping("delStockDataById")
    public void delStockDataById(Integer id,HttpServletResponse response) throws IOException{
        int status = maintainService.delStockDataById(id);
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 增加和修改库存管理信息
     * @param tstock
     */
    @RequestMapping("saveStockData")
    public void addAssessmentStock(HttpServletRequest request, Tstock tstock, HttpServletResponse response) throws IOException {
        int result = 0;
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tstock.setOpertime(PlatformUtils.getNowTime());
        tstock.setEndopertime(PlatformUtils.getNowTime());
        tstock.setOperid(user.getId());
        if (tstock.getId()==null){
            tstock.setCreateby(user.getId());
            tstock.setCreateTime(PlatformUtils.getNowTime());
            result = maintainService.addStockData(tstock);
            tUserOperationService.recordOperationData(tstock,null,user.getId(), SystemOperationCommon.OPERATION_ADD,result, MenuModelCommon.STOCK);
        }else {
            Map oldobj = maintainService.getStockDataById(tstock.getId());
            int oldstocknum =Integer.parseInt(oldobj.get("stocknum").toString()) ;
            if (oldstocknum != tstock.getStocknum()) {
                int nowstocknum =tstock.getStocknum() - oldstocknum;
                tstock.setChangenum(nowstocknum);
            }
            result = maintainService.updateStockData(tstock);
            Object nowobj = maintainService.getStockDataById(tstock.getId());
           // tUserOperationService.recordOperationData(nowobj,oldobj,user.getId(), SystemOperationCommon.OPERATION_UPDATE,result, MenuModelCommon.STOCK);
        }
        response.getWriter().print(result == 1 ? "y":"n");
    }

    /**
     *  统计电费
     */
    @RequestMapping("getPlatformDianSumPrice")
    public void getPlatformDianSumPrice(HttpServletRequest request,Integer curPage,Integer showNum,
                                        HttpServletResponse response,String orderBy,String sort) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",orgCode);
        dataMap.put("curPage",curPage);
        dataMap.put("showNum",showNum);
        dataMap.put("orderBy",orderBy);
        dataMap.put("sort",sort);
        Map<String, Object> resultMap = maintainService.getPlatformDianSumPrice(dataMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 获取维修人员
     */
    @RequestMapping("getRepairData")
    public void getRepairData(HttpServletRequest request,HttpServletResponse response,Integer showNum,
                              Integer curPage,String name,String orderBy,String sort) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",orgCode);
        dataMap.put("curPage",curPage);
        dataMap.put("showNum",showNum);
        dataMap.put("orderBy",orderBy);
        dataMap.put("sort",sort);
        dataMap.put("name",name);
        Map<String, Object> resultMap = maintainService.getRepairData(dataMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 根据ID获取相应的维修人员信息
     */
    @RequestMapping("getRepairDataById")
    public void getRepairDataById(Integer id,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap  = maintainService.getRepairDataById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 删除维修人员
     */
    @RequestMapping("deleteRepById")
    public void deleteRepById(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser tsysuser1 = PlatformUtils.getLoginUser(request);
        Object nowObj = maintainService.getRepairDataById(id);
        int status  = maintainService.deleteRepById(id);
         tUserOperationService.recordOperationData(null,nowObj,tsysuser1.getId(), SystemOperationCommon.OPERATION_DELETE,status,MenuModelCommon.REPAIRPERSOONEL_MANAGE);
        String msg = status == 1 ? "y":"n";
        response.getWriter().print(msg);
    }

    /**
     * 保存维修人员
     */
    @RequestMapping("saveRepData")
    public void saveRepData(TrepairPeople ta, HttpServletResponse response, HttpServletRequest request) throws IOException{
        int result = 0;
        Tsysuser user = PlatformUtils.getLoginUser(request);
        ta.setOrgid(user.getOrgId());
        ta.setOperid(user.getId());
        ta.setOperTime(PlatformUtils.getNowTime());
        if(ta.getId() == null){
            ta.setCreateby(user.getId());
            ta.setCreateTime(PlatformUtils.getNowTime());
            result = maintainService.saveRepData(ta);
             tUserOperationService.recordOperationData(ta,null,user.getId(), SystemOperationCommon.OPERATION_ADD,result,MenuModelCommon.REPAIRPERSOONEL_MANAGE);
        }else{
            ta.setCreateby(user.getId());
            ta.setCreateTime(PlatformUtils.getNowTime());
            Object oldObj = maintainService.getRepairDataById(ta.getId());
            result = maintainService.updateRepData(ta);
            tUserOperationService.recordOperationData(ta,oldObj,user.getId(), SystemOperationCommon.OPERATION_UPDATE,result,MenuModelCommon.REPAIRPERSOONEL_MANAGE);
        }
        response.getWriter().print(result == 1 ? "y":"n");
    }

    /**
     * 验证维修人员工号是否重复
     */
    @RequestMapping("checkRepairNum")
    public void checkRepairNum(String number,HttpServletRequest request,HttpServletResponse response) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        int num = maintainService.checkRepairNum(orgCode,number);
        response.getWriter().print(num);
    }

    /**
     * 获取登录人所属组织名称
     */
    @RequestMapping("getLoginUserBelongOrgName")
    public void getLoginUserBelongOrgName(HttpServletRequest request,HttpServletResponse response) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        String orgName = user.getTorganization().getOrgName();
        response.getWriter().print(orgName);
    }

    /**
     * 查询施工标准信息
     */
    @RequestMapping("getBuildStandard")
    public void getBuildStandard(String cname,HttpServletRequest request, Integer showNum,HttpServletResponse response, Integer curPage,
                                String sort,String orderBy) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("showNum", showNum);
        dataMap.put("curPage", curPage);
        dataMap.put("orgCode", orgCode);
        dataMap.put("cname", cname);
        dataMap.put("sort",sort);
        dataMap.put("orderBy",orderBy);
        Map<String, Object> resultMap = maintainService.getBuildStandard(dataMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 删除施工标准
     */
    @RequestMapping(value="delBuildStandardById")
    public void delBuildStandardById(Integer id,HttpServletResponse response) throws IOException {
       // Object nowobj = bsservice.selectByPrimaryKey(id);
        int result = maintainService.delBuildStandardById(id);
        //tUserOperationService.recordOperationData(nowobj,null,user.getId(), SystemOperationCommon.OPERATION_DELETE,result,MenuModelCommon.STANDARD_MANAGE_);
        response.getWriter().print(result == 1 ? "y":"n");
    }

    /**
     * 根据ID获取施工标准
     */
    @RequestMapping("getBuildStandardById")
    public void getBuildStandardById(Integer id,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap =  maintainService.getBuildStandardById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }



    /**
     * 增加和修改施工标准信息
     */
    @RequestMapping("saveBuildStandard")
    public void saveBuildStandard(HttpServletRequest request,Tbuildstandard tbuildstandard,HttpServletResponse response) throws IOException {
        int result = 0;
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tbuildstandard.setOperid(user.getId());
        tbuildstandard.setOpertime(PlatformUtils.getNowTime());
        if (tbuildstandard.getId()==null){
            tbuildstandard.setCreateby(user.getId());
            tbuildstandard.setCreateTime(PlatformUtils.getNowTime());
            result=maintainService.addBuildStandard(tbuildstandard);
            //tUserOperationService.recordOperationData(tbuildstandard,null,user.getId(), SystemOperationCommon.OPERATION_ADD,result,MenuModelCommon.STANDARD_MANAGE_);
        }else {
            //Object oldobj=bsservice.selectByPrimaryKey(tbuildstandard.getId());
            result = maintainService.updateBuildStandard(tbuildstandard);
            //Object nowobj=bsservice.selectByPrimaryKey(tbuildstandard.getId());
            //tUserOperationService.recordOperationData(nowobj,oldobj,user.getId(), SystemOperationCommon.OPERATION_UPDATE,result,MenuModelCommon.STANDARD_MANAGE_);
        }
        response.getWriter().print(result == 1 ? "y":"n");
    }


}
