package com.lamp.controller;

import com.lamp.common.MenuModelCommon;
import com.lamp.common.SystemOperationCommon;
import com.lamp.model.*;
import com.lamp.service.IBaseDeviceService;
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
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BaseDeviceController{

    @Autowired
    private IBaseDeviceService iBaseDeviceService;

    @Autowired
    private TUserOperationService tUserOperationService;

    /**--------------------------------------区域 begin-----------------------------------------*/

    /**
     *  获取组织信息
     */
    @RequestMapping("/getOrganizationDataInfo")
    public void getOrganizationDataInfo(HttpServletResponse response) throws IOException{
        List<Map<String, Object>> orgList = iBaseDeviceService.getOrganizationList();
        response.getWriter().print(JSONArray.fromObject(orgList).toString());
    }

    /**
     * 根据名称来查询区域
     */
    @RequestMapping("getAreaManageByareaName")
    public void getAreaManageByName(String areaName,Integer showNum,Integer curPage,String orderBy,String sort,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        String orgCode = user.getTorganization().getOrgCode();
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("areaName",areaName);
        sqlMap.put("showNum",showNum);
        sqlMap.put("curPage",curPage);
        sqlMap.put("orderBy",orderBy);
        sqlMap.put("sort",sort);
        sqlMap.put("orgCode",orgCode);
        Map<String,Object> resultMap = iBaseDeviceService.getAreaManageData(sqlMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 保存区域信息
     */
    @RequestMapping("saveAreaMangeData")
    public void saveAreaMangeData(Tareamanage ta, HttpServletResponse response, HttpServletRequest request) throws IOException{
            int result = 0;
            Tsysuser user = PlatformUtils.getLoginUser(request);
            ta.setOperid(user.getId());
            String time = PlatformUtils.getNowTime();
            ta.setOpertime(time);
            if(ta.getId() == null){
                ta.setCreateby(user.getId());
                ta.setCreateTime(time);
                result = iBaseDeviceService.saveAreaMangeData(ta);
                tUserOperationService.recordOperationData(ta,null,user.getId(), SystemOperationCommon.OPERATION_ADD,result,MenuModelCommon.AREA_MANAGE);
            }else{
                Object oldObj = iBaseDeviceService.getAreaManageDataById(ta.getId()).get(0);
                result = iBaseDeviceService.updateAreaManageData(ta);
                tUserOperationService.recordOperationData(ta,oldObj,user.getId(), SystemOperationCommon.OPERATION_UPDATE,result,MenuModelCommon.AREA_MANAGE);
            }
            response.getWriter().print(result == 1 ? "y":"n");
    }


    /**
     * 根据区域ID获取相关信息
     * @param areaId
     * @param response
     * @throws IOException
     */
    @RequestMapping("getAreaManageDataById")
    public void getAreaManageDataById(Integer areaId,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> list = iBaseDeviceService.getAreaManageDataById(areaId);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据区域ID删除该条信息
     * @param areaId
     */
    @RequestMapping("delteAreaMangeDataById")
    public void delteAreaMangeDataById(Integer areaId,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        int check = iBaseDeviceService.checkDelAreaById(areaId);
        if(check == 0){
            check =  iBaseDeviceService.delteAreaMangeDataById(areaId);
            String msg = check == 1 ? "y" : "n";
            Object nowObj = iBaseDeviceService.getAreaManageDataById(areaId).get(0);
            tUserOperationService.recordOperationData(nowObj,null,user.getId(), SystemOperationCommon.OPERATION_DELETE,check,MenuModelCommon.AREA_MANAGE);
            response.getWriter().print(msg);
        }else{
            response.getWriter().print("has_data");
        }
    }

    /**--------------------------------------区域 end-----------------------------------------*/

    /**--------------------------------------道路 start-----------------------------------------*/

    /**
     * 查询道路
     */
    @RequestMapping("getRoadManageData")
    public void getRoadManageData(String cname,Integer showNum,Integer curPage,String orderBy,String sort,
                                  HttpServletResponse response,Integer areaId,HttpServletRequest request) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("roadName",cname);
        paramMap.put("areaId",areaId);
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("orgCode",orgCode);
        Map<String,Object> resultMap = iBaseDeviceService.getRoadManageDataByOrgCode(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 保存道路信息
     */
    @RequestMapping("saveRoadNameData")
    public void saveRoadNameData(Troadmanage ta, HttpServletResponse response, HttpServletRequest request) throws Exception{
        int result = 0;
        Tsysuser user = PlatformUtils.getLoginUser(request);
        ta.setOperId(user.getId());
        String time = PlatformUtils.getNowTime();
        ta.setOperTime(time);
        if(ta.getId() == null){
            ta.setCreateby(user.getId());
            ta.setCreateTime(time);
            System.out.println(ta);
            result = iBaseDeviceService.saveRoadNameData(ta);
            tUserOperationService.recordOperationData(ta,null,user.getId(), SystemOperationCommon.OPERATION_ADD,result,MenuModelCommon.ROAD_MANAGE);
        }else{
            Object oldObj = iBaseDeviceService.getRoadNameManageById(ta.getId()).get(0);
            result = iBaseDeviceService.updateRoadNameData(ta);
            tUserOperationService.recordOperationData(ta,oldObj,user.getId(), SystemOperationCommon.OPERATION_UPDATE,result,MenuModelCommon.ROAD_MANAGE);
        }
        response.getWriter().print(result == 1 ? "y":"n");
    }

    /**
     * 根据道路ID获取相关信息
     */
    @RequestMapping("getRoadNameManageById")
    public void getRoadNameManageById(Integer roadId,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> list = iBaseDeviceService.getRoadNameManageById(roadId);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据道路ID删除该条信息
     */
    @RequestMapping("deleteRoadManageDataById")
    public void deleteRoadManageDataById(Integer roadId,HttpServletResponse response,HttpServletRequest request ) throws IOException{
        int check = iBaseDeviceService.checkDelRoadById(roadId);
        Tsysuser user = PlatformUtils.getLoginUser(request);
        if(check == 0){
            int i =  iBaseDeviceService.deleteRoadManageDataById(roadId);
            Object nowObj = iBaseDeviceService.getRoadNameManageById(roadId).get(0);
            tUserOperationService.recordOperationData(nowObj,null,user.getId(), SystemOperationCommon.OPERATION_DELETE,i,MenuModelCommon.ROAD_MANAGE);
            String msg = i == 1 ? "y" : "n";
            response.getWriter().print(msg);
        }else{
            response.getWriter().print("has_data");
        }

    }

    /**--------------------------------------道路 end---------------------------------------------*/

    /**--------------------------------------控制器 start-----------------------------------------*/

    /**
     * 保存控制器相关数据
     */
    @RequestMapping("saveControllerData")
    public void saveControllerData(Tcontroller tt,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tt.setOperator(user.getId());
        tt.setOpertime(PlatformUtils.getNowTime());
        HashMap<String,String> dataMap =  iBaseDeviceService.saveData(tt,request);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 获取控制器相关信息
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("getCOntrollerDataById")
    public void getCOntrollerDataById(Integer id,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> data = iBaseDeviceService.getCOntrollerDataById(id);
        response.getWriter().print(JSONArray.fromObject(data).toString());
    }

    /**
     * 删除控制器
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("deleteControllerById")
    public void deleteControllerById(Integer id,HttpServletResponse response,HttpServletRequest request ) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        Object nowObj = iBaseDeviceService.getCOntrollerDataById(id);
        int data = iBaseDeviceService.deleteControllerById(request,id);
        String msg = "";
        if(1 == data){
           // tUserOperationService.recordOperationData(nowObj,null,user.getId(), SystemOperationCommon.OPERATION_DELETE,data,MenuModelCommon.CONTROLLER_MANAGE);           msg = "y";
        }else{
            msg = "n";
        }
        msg = data == 1 ? "y" : "n";
        response.getWriter().print(msg);
    }

    /**
     * 获取控制信息并分页
     */
    @RequestMapping("getControllerDatas")
    public void getControllerDatas(String cname,Integer showNum,HttpServletRequest request,Integer curPage,String orderBy,String sort,HttpServletResponse response) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("cname",cname);
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("orgCode",orgCode);
        Map<String,Object> resultMap =  iBaseDeviceService.getControllerData(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 导入控制器
     * @param data
     * @param response
     * @throws IOException
     */
    @RequestMapping("ImportControllerData")
    public void ImportControllerData(String data,HttpServletResponse response,HttpServletRequest request) throws IOException{
       Tsysuser tsysuser =  PlatformUtils.getLoginUser(request);
        try {
            String msg = iBaseDeviceService.ImportControllerData(data,tsysuser.getId());
            if(null == msg){
                response.getWriter().print("no");
            }else{
                response.getWriter().print("yes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("no");
        }
    }

    /**--------------------------------------控制器 end-----------------------------------------*/

    /**--------------------------------------集中器 start---------------------------------------*/

    /**
     * 导入集中器
     * @param data
     * @param response
     * @throws IOException
     */
    @RequestMapping("ImportConcentratorData")
    public void ImportConcentratorData(String data,HttpServletResponse response,HttpServletRequest request) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,String> dataMap = new HashMap<>();
        try {
            String msg = iBaseDeviceService.ImportConcentratorData(data,orgCode);
            if(null == msg){
                dataMap.put("info","no");
            }else{
                dataMap.put("info","yes");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            response.getWriter().print("no");
            dataMap.put("info","no");
        }
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 保存集中器相关数据
     */
    @RequestMapping("saveConcentratorData")
    public void saveConcentratorData(Tconcentrator tt, HttpServletResponse response, HttpServletRequest request) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tt.setOperator(user.getId());
        tt.setOpertime(PlatformUtils.getNowTime());
        int data = iBaseDeviceService.saveConcentratorData(tt,request);
        String msg = "";
        if(1 == data){

            msg = "y";
        }else{
            msg = "n";
        }
        response.getWriter().print(msg);
    }

    /**
     * 获取集中器相关信息
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("getConcentratorDataById")
    public void getConcentratorDataById(Integer id,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> data = iBaseDeviceService.getConcentratorDataById(id);
        response.getWriter().print(JSONArray.fromObject(data).toString());
    }

    /**
     * 删除集中器
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("deleteConcentratorById")
    public void deleteConcentratorById(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
        int data = iBaseDeviceService.deleteConcentratorData(id);
        Tsysuser user = PlatformUtils.getLoginUser(request);
        Object Concentrator = iBaseDeviceService.getConcentratorDataById(id).get(0);
        String msg = "";
        if(1 == data){
            tUserOperationService.recordOperationData(Concentrator,null,user.getId(), SystemOperationCommon.OPERATION_DELETE,1, MenuModelCommon.CENTRALIER_MANAGE);
            msg = "y";
        }else{
            msg = "n";
        }
        response.getWriter().print(msg);
    }

    /**
     * 获取集中器信息并分页
     */
    @RequestMapping("getConcentratorListData")
    public void getConcentratorListData(String cname,Integer showNum,Integer curPage,String orderBy,String sort,
                                        HttpServletResponse response,HttpServletRequest request) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("cname",cname);
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("orgCode",orgCode);
        Map<String,Object> resultMap = iBaseDeviceService.getConcentratorListData(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**--------------------------------------集中器 end---------------------------------------*/

    /**--------------------------------------配电箱 start---------------------------------------*/

    /**
     * 保存配电箱相关数据
     */
    @RequestMapping("saveEleBoxData")
    public void saveEleBoxData(Telecboxmanage tt,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tt.setOperId(user.getId());
        tt.setOperTime(GetLocalTimes.getNowTime());
        int data = 0;
        if(tt.getId() == null){
            tt.setCreateby(user.getId());
            tt.setCreateTime(GetLocalTimes.getNowTime());
            data = iBaseDeviceService.addeleBoxData(tt);
            if(data == 1){
                tUserOperationService.recordOperationData(tt,null,user.getId(), SystemOperationCommon.OPERATION_ADD,data,MenuModelCommon.DISTRIBUTIONBOX);
            }
        }else{
            Object tt2 = iBaseDeviceService.geteleBoxDataById(tt.getId()).get(0);
            data = iBaseDeviceService.updateeleBoxData(tt);
            if(data == 1){
                tUserOperationService.recordOperationData(tt,tt2,user.getId(), SystemOperationCommon.OPERATION_UPDATE,data,MenuModelCommon.DISTRIBUTIONBOX);
                          }
        }
        String msg = null;
        if(1 == data){
            msg = "y";
        }else{
            msg = "n";
        }
        response.getWriter().print(msg);
    }

    /**
     * 获取配电箱相关信息
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("getEleBoxDataById")
    public void getEleBoxDataById(Integer id,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> data = iBaseDeviceService.geteleBoxDataById(id);
        response.getWriter().print(JSONArray.fromObject(data).toString());
    }
    /**
     * 删除配电箱
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("deleteEleBoxDataById")
    public void deleteEleBoxDataById(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Object tt = iBaseDeviceService.geteleBoxDataById(id).get(0);
        int data = iBaseDeviceService.delEleBoxDataById(id);
        Tsysuser user = PlatformUtils.getLoginUser(request);
        String msg = "";
        if(1 == data){
            msg = "y";
            tUserOperationService.recordOperationData(tt,null,user.getId(), SystemOperationCommon.OPERATION_DELETE,data,MenuModelCommon.DISTRIBUTIONBOX);
        }else{
            msg = "n";
        }
        response.getWriter().print(msg);
    }

    /**
     * 获取配电箱信息并分页
     */
    @RequestMapping("getEleBoxData")
    public void getEleBoxData(String eleNum,Integer showNum,Integer curPage,String orderBy,String sort,
                              HttpServletResponse response,HttpServletRequest request) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("orgCode",orgCode);
        paramMap.put("eleNum",eleNum);
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        Map<String,Object> resultMap =  iBaseDeviceService.getEleBoxData(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**--------------------------------------配电箱 end---------------------------------------*/

    /**--------------------------------------报警报表 start---------------------------------------*/

    /**
     *  灯具报警报表
     */
    @RequestMapping("getLampWarnningData")
    public void getLampWarnningData(Integer showNum, Integer curpage,Integer areaId,Integer roadId,Integer lampId,Integer lineId,String orderBy,String sort
                                    ,String startDate,String endDate,HttpServletResponse response,HttpServletRequest request) throws IOException {
        Map<String,Object> list = null;
        try {
            String orgCode = PlatformUtils.getLoginUser(request).getTorganization().getOrgCode();
            list =  iBaseDeviceService.getLampWarnningData(showNum,curpage,areaId,roadId,lineId,lampId,startDate,endDate,orgCode,orderBy,sort);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 导出灯具报警信息
     * @param areaId
     * @param roadId
     * @param lampId
     * @param startDate
     * @param endDate
     * @param response
     * @throws IOException
     */
    @RequestMapping("exportLampWarnData")
    public void exportLampWarnData(Integer areaId,Integer roadId,Integer lampId,HttpServletRequest request,
                                   String startDate,String endDate,HttpServletResponse response) throws IOException{
        HashMap<String,Object> sqlMap = new HashMap<>();
        String orgCode = PlatformUtils.getLoginUser(request).getTorganization().getOrgCode();
        sqlMap.put("areaId",areaId);
        sqlMap.put("roadId",roadId);
        sqlMap.put("lampId",lampId);
        sqlMap.put("startDate",startDate);
        sqlMap.put("endDate",endDate);
        sqlMap.put("orgCode",orgCode);
        List<Map<String, Object>> list =  iBaseDeviceService.getExportWarnData(sqlMap);
        if(null == list || list.size() == 0){
            response.getWriter().print("<h1 style = 'color:#fff'>未找到相关数据</h1>");
            return;
        }

        Map<String,String> titleMap = new LinkedHashMap<>();
        titleMap.put("areaName","区域");
        titleMap.put("road_name","道路");
        titleMap.put("linename","线路");
        titleMap.put("factoryname","灯具厂家");
        titleMap.put("lamptypename","灯具类型");
        titleMap.put("kindname","控制器类型");
        titleMap.put("levelname","报警级别");
        titleMap.put("warn_name","报警内容");
        titleMap.put("warn_time","报警时间");
        //titleMap.put("","处理情况");

        com.alibaba.fastjson.JSONArray ja =new com.alibaba.fastjson.JSONArray();
        for(int i=0;i<list.size();i++){
            ja.add(list.get(i));
        }

        String title = "灯具报警记录数据表";
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com = fsv.getHomeDirectory();
        ExcelUtil.downloadExcelFile(title, titleMap,ja,response);
    }

    /**
     * 保存施工管理信息
     */
    @RequestMapping("LampWarnsaveBuildingData")
    public void LampWarnsaveBuildingData(Tbuildinfo tt,String nb_device,HttpServletResponse response,HttpServletRequest request) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tt.setOperid(user.getId());
        tt.setOpertime(PlatformUtils.getNowTime());
        int data = 0;
        //data = iBaseDeviceService.addBuildingInfoData(tt);
        iBaseDeviceService.updateWarnLampDataByOrderNum(tt.getOrdernum(),nb_device);
        String msg = null;
        if(1 == data){
            msg = "y";
        }else{
            msg = "n";
        }
        response.getWriter().print(msg);
    }

    /**
     * 获取派工所填信息
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("getSendBuildingDataByLampId")
    public void getSendBuildingDataByLampId(Integer id,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> data = iBaseDeviceService.getSendBuildingDataByLampId(id);
        response.getWriter().print(JSONArray.fromObject(data).toString());
    }

    /**--------------------------------------报警报表 end---------------------------------------*/

    /**--------------------------------------线路管理 start-------------------------------------*/

    /**
     * 保存线路管理信息
     */
    @RequestMapping("saveRoadLineData")
    public void saveRoadLineData(HttpServletRequest request,TRoadLineManage tRoadLineManage,HttpServletResponse response) throws IOException{
        int result = 0;
        String time = PlatformUtils.getNowTime();
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tRoadLineManage.setOperId(user.getId());
        tRoadLineManage.setOperTime(time);
        if(tRoadLineManage.getId() == null){
            tRoadLineManage.setCreateBy(user.getId());
            tRoadLineManage.setCreateTime(time);
            result = iBaseDeviceService.saveRoadLineManageData(tRoadLineManage);
            tUserOperationService.recordOperationData(tRoadLineManage,null,user.getId(), SystemOperationCommon.OPERATION_ADD,result,MenuModelCommon.ROADLINE_MANAGE);
        }else{
            Object oldObj = iBaseDeviceService.queryRoadLineManageById(tRoadLineManage.getId());
            result = iBaseDeviceService.updateRoadLineManageData(tRoadLineManage);
            tUserOperationService.recordOperationData(tRoadLineManage,oldObj,user.getId(), SystemOperationCommon.OPERATION_UPDATE,result,MenuModelCommon.ROADLINE_MANAGE);
        }

        String msg = result == 1 ? "y":"n";
        response.getWriter().print(msg);
    }

    /**
     * 查询线路管理信息
     */
    @RequestMapping("queryRoadLineManage")
    public void queryRoadLineManage(Integer areaId,Integer roadId,Integer showNum,String cName,Integer curPage,String orderBy,String sort
                                    ,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("areaId",areaId);
        dataMap.put("roadId",roadId);
        dataMap.put("showNum",showNum);
        dataMap.put("cName",cName);
        dataMap.put("curPage",curPage);
        dataMap.put("orgCode",orgCode);
        dataMap.put("orderBy",orderBy);
        dataMap.put("sort",sort);
        Map<String,Object> resultMap = iBaseDeviceService.queryRoadLineManage(dataMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 查询 线路管理信息
     * @param id
     * @param response
     */
    @RequestMapping("queryRoadLineManageById")
    public void queryRoadLineManageById(Integer id,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap =  iBaseDeviceService.queryRoadLineManageById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 删除 线路管理信息
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("deleteRoadLineManageById")
    public void deleteRoadLineManageById(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        Object nowObj = iBaseDeviceService.queryRoadLineManageById(id);
        int result = iBaseDeviceService.deleteRoadLineManageById(id);
        tUserOperationService.recordOperationData(nowObj,null,user.getId(), SystemOperationCommon.OPERATION_DELETE,result,MenuModelCommon.ROADLINE_MANAGE);
        String msg = result == 1 ? "y":"n";
        response.getWriter().print(msg);
    }

    /**
     *  导入设备
     */
    @RequestMapping("ImportDeviceData")
    public void ImportDeviceData(HttpServletRequest request,String data,HttpServletResponse response) throws IOException {
        Tsysuser tsysuser =  PlatformUtils.getLoginUser(request);
        try {
            String msg = iBaseDeviceService.ImportDeviceData(data,tsysuser.getId());
            if(null == msg){
                response.getWriter().print("no");
            }else{
                response.getWriter().print("yes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("no");
        }
    }

    /**--------------------------------------  电表 start  -------------------------------------*/

    /**
     * 电表查询
     */
    @RequestMapping("getAmmeterList")
    public void getAmmeterList(Integer showNum,Integer curPage,String cName,String sort,String orderBy,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("cName",cName);
        dataMap.put("orgCode",orgCode);
        dataMap.put("sort",sort);
        dataMap.put("orderBy",orderBy);
        dataMap.put("curPage",curPage);
        dataMap.put("showNum",showNum);
        Map<String,Object> resultMap = null;
        try {
            resultMap = iBaseDeviceService.getAmmeterList(dataMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(PlatformUtils.checkMapDataIsEmpty(resultMap)){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(resultMap).toString());
        }
    }

    /**
     * 保存电表信息
     * @param tammeter
     * @param response
     * @param request
     */

    @RequestMapping("saveDianbiaoData")
    public void saveDianbiaoData(Tammeter tammeter, HttpServletResponse response,HttpServletRequest request) throws IOException {
        int status =0;
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tammeter.setOperId(user.getId());
        tammeter.setOperTime(PlatformUtils.getNowTime());
        tammeter.setDelFlag(0);
        if(tammeter.getId()==null){
            tammeter.setCreateby(user.getId());
            tammeter.setCreateTime(PlatformUtils.getNowTime());
            status = iBaseDeviceService.insertAmmeter(tammeter);
        }
        else {
            status = iBaseDeviceService.updateAmmeterSelective(tammeter);
        }
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 根据ID获取电表信息
     */
    @RequestMapping("getAmmeterDataById")
    public void getAmmeterDataById(Integer Id,HttpServletResponse response) throws IOException{
        Map<String, Object> dataMap = iBaseDeviceService.getAmmeterDataById(Id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     *  删除电表信息
     */
    @RequestMapping("deleteAmmeterDataById")
    public void deleteAmmeterDataById(Integer id,HttpServletResponse response) throws IOException{
        int check =  iBaseDeviceService.deleteAmmeterDataById(id);
        String msg = check == 1 ? "y" : "n";
        response.getWriter().print(msg);
    }

    /**
     * 获取灯具类型
     */
    @RequestMapping("getLampTypeList")
    public void getLampTypeList(Integer showNum, Integer curPage,String sort,String orderBy,String typeName,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("typeName",typeName);
        dataMap.put("orgCode",orgCode);
        dataMap.put("sort",sort);
        dataMap.put("orderBy",orderBy);
        dataMap.put("curPage",curPage);
        dataMap.put("showNum",showNum);
        Map<String,Object> resultMap = iBaseDeviceService.getLampTypeList(dataMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     *  根据ID获取相应的灯具类型
     */
    @RequestMapping("getLampTypeDataById")
    public void getLampTypeDataById(Integer id,HttpServletResponse response) throws IOException{
        HashMap<String,Object> dataMap = iBaseDeviceService.getLampTypeDataById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 根据ID删除灯具类型
     */
    @RequestMapping("delLampTypeDataById")
    public void delLampTypeDataById(Integer id,HttpServletResponse response) throws IOException {
        int status = iBaseDeviceService.delLampTypeDataById(id);
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     *  保存灯具类型
     */
    @RequestMapping("saveLampTypeData")
    public void saveLampTypeData(Tlamptype t,HttpServletRequest request,HttpServletResponse response) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        t.setOperator(user.getId().toString());
        t.setOpertime(PlatformUtils.getNowTime());
        int status = 0;
        if(t.getId() == null){
            t.setCreateby(user.getId());
            t.setCreateTime(PlatformUtils.getNowTime());
            status = iBaseDeviceService.addLampTypeData(t);
        }else{
            status = iBaseDeviceService.updateLampTypeData(t);
        }
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 检验灯具型号是否重复
     */
    @RequestMapping("checkLampTypeModel")
    public void checkLampTypeModel(HttpServletRequest request,String typeId,String model,String id,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orgCode",orgCode);
        dataMap.put("typeId",typeId);
        dataMap.put("model",model);
        dataMap.put("id",id);
        int count = iBaseDeviceService.checkLampTypeModel(dataMap);
        response.getWriter().print(count);
    }

    /**
     * 获取灯具信息
     */
    @RequestMapping("getLampManageData")
    public void getLampManageData(Integer showNum, Integer curPage,String sort,String orderBy,String poleCode,
                                  Integer areaId,Integer roadId,Integer lineId,
                                  HttpServletRequest request,HttpServletResponse response) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("poleCode",poleCode);
        dataMap.put("orgCode",orgCode);
        dataMap.put("sort",sort);
        dataMap.put("orderBy",orderBy);
        dataMap.put("curPage",curPage);
        dataMap.put("showNum",showNum);
        dataMap.put("areaId",areaId);
        dataMap.put("roadId",roadId);
        dataMap.put("lineId",lineId);
        Map<String,Object> resultMap = iBaseDeviceService.getLampManageData(dataMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 查询灯具信息
     */
    @RequestMapping("getLampManageDataById")
    public void getLampManageDataById(Integer id,HttpServletResponse response) throws IOException {
       HashMap<String,Object> dataMap =  iBaseDeviceService.getLampManageDataById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 根据ID删除灯具
     */
    @RequestMapping("delLampManageDataById")
    public void delLampManageDataById(Integer id,HttpServletResponse response) throws IOException{
        int status = iBaseDeviceService.delLampManageDataById(id);
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 保存灯具信息
     */
    @RequestMapping("saveLampManageData")
    public void saveLampManageData(Tlamp tlamp,HttpServletRequest request,HttpServletResponse response) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tlamp.setOperId(user.getId());
        tlamp.setOpertime(PlatformUtils.getNowTime());
        int status = 0;
        if(tlamp.getId() == null){
            tlamp.setCreateby(user.getId());
            tlamp.setCreateTime(PlatformUtils.getNowTime());
            status = iBaseDeviceService.addLampManageData(tlamp);
            //tUserOperationService.recordOperationData(tlamp,null,user.getId(), SystemOperationCommon.OPERATION_ADD,info,MenuModelCommon.LAMP_MANAGE);
        }else{
            status = iBaseDeviceService.updateLampManageData(tlamp);
            //tUserOperationService.recordOperationData(tt,tt2,user.getId(), SystemOperationCommon.OPERATION_UPDATE,info,MenuModelCommon.LAMP_MANAGE);
        }
        response.getWriter().print(status == 1 ? "y" : "n");
    }
}
