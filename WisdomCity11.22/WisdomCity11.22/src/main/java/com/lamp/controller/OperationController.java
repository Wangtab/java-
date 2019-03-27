package com.lamp.controller;

import com.alibaba.fastjson.JSON;
import com.lamp.common.MenuModelCommon;
import com.lamp.common.SystemOperationCommon;
import com.lamp.model.*;
import com.lamp.service.OperationService;
import com.lamp.service.TUserOperationService;
import com.lamp.utils.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lenovo on 2017-11-24.
 * 运维管理视图层action
 * author LIULIN
 */
@Controller
public class OperationController {
    @Autowired
    private OperationService operationService;

    @Autowired
    private TUserOperationService tUserOperationService;

    /**
     * 增加登录日志
     * @aurhor LIULIN
     * @return
     */
    @RequestMapping(value = "/OperationUserLog.do")
    public void OperationUserLog()throws  Exception{
        operationService.userLogInsertService();
    }

    /**
     * 区域和路段二级联动
     * @param aid
     * @return
     */
    @RequestMapping(value = "/queryRoadList.do", produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryRoadList(HttpServletRequest request,Integer aid){

        return JSON.toJSONString(operationService.queryTroadmanageList(request,aid));
    }

    /**
     * 跳转灯具类型管理页面
     * @param request
     * @return
     */
    @RequestMapping(value =  "/lampTypeManage.do")
    public String lampTypeManage(HttpServletRequest request,String typeName,String page){
        operationService.queryLampTypeManage(request, typeName, page);
        return "device_lamp_type";
    }

    @RequestMapping(value = "getlampTypeManage")
    public void getlampTypeManage(Integer showNum, Integer curPage,String typeName,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取登录人信息
        Tsysuser user = PlatformUtils.getLoginUser(request);
        //获取org_code
        String org_code = user.getTorganization().getOrgCode();
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("typeName",typeName);
        //将org_code放进map
        dataMap.put("org_code",org_code);
        Map<String,Object> list = null;
        try {
            list = operationService.getlampTypeManage(showNum,curPage,dataMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
            return;
        }
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 增加灯具类型信息
     * @param request
     * @param lamptypename
     * @param lamptypedes
     * @param power
     * @param lampFactory
     * @param dimmingmode
     * @param imgurl
     * @return
     */
    @RequestMapping(value = "/addTypeManage.do", produces = "text/html;charset=UTF-8")
    public @ResponseBody String addTypeManage(HttpServletRequest request,String lamptypename,String lamptypedes,String power,
                 String lampFactory,String dimmingmode,String spower,String bpower,String lampModel,@RequestParam MultipartFile imgurl)throws  Exception{
        System.out.println("imgurl: "+imgurl.getOriginalFilename());
        /*获取用户信息*/
        Tsysuser tsysuser = (Tsysuser)request.getSession().getAttribute("loginUser");
        /*上传图片*/
        String path = "lampTypeImg";
        String filePath = UploadFileUtils.uploadFiles(request, imgurl, path);
        /*增加灯具类型*/
        Tlamptype tlamptype = new Tlamptype();
        tlamptype.setLamptypename(lamptypename);
        tlamptype.setLamptypedes(lamptypedes);
        if (!power.isEmpty()){
            tlamptype.setPower(Integer.parseInt(power));
        }
        if (!lampFactory.isEmpty()){
            tlamptype.setLampFactory(lampFactory);
        }
        tlamptype.setDimmingmode(dimmingmode);
        tlamptype.setImgurl(filePath);
        tlamptype.setOperator(tsysuser.getUserName());
        tlamptype.setOpertime(GetLocalTimes.getNowTime());
        tlamptype.setDelflag(0);

        tlamptype.setCreateby(tsysuser.getId());
        tlamptype.setCreateTime(PlatformUtils.getNowTime());
        tlamptype.setLampModel(lampModel);

        if (!spower.isEmpty()){
            tlamptype.setSpower(Integer.parseInt(spower));
        }
        if (!bpower.isEmpty()){
            tlamptype.setBpower(Integer.parseInt(bpower));
        }

        /*增加灯具类型信息*/
        Integer data = operationService.addTlamptype(tlamptype);
        String msg = "";
        if (data == 1){
            Tsysuser user = PlatformUtils.getLoginUser(request);
          /*  String operdes = "创建一个新灯具类型，类型名称【"+tlamptype.getLamptypename()+"】";
            deviceController.insertOperLog(user.getUserName(),0,modulename0,operdes);*/
            tUserOperationService.recordOperationData(tlamptype,null,user.getId(), SystemOperationCommon.OPERATION_ADD,data, MenuModelCommon.LAMPKIND_MANAGE);

            msg ="添加成功";
        }
        return msg;
    }

    /**
     * 查询是否有灯具类型
     * @param mid
     * @return
     */
    @RequestMapping(value = "/queryLampModel.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryLampModel(HttpServletRequest request,String mid,String msg){
        String info = operationService.queryModel(request, mid, msg);
        return info;
    }
    /**
     * 查询灯具信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryLampType.do", produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryLampType(HttpServletRequest request,String id){
        List<Map<String,Object>> mapList = operationService.queryLampType(request, Integer.parseInt(id));
        return JSON.toJSONString(mapList);
    }

    /**
     * 更新灯具类型信息
     * @param request
     * @param lamptypename
     * @param lamptypedes
     * @param power
     * @param id
     * @param lampFactory
     * @param dimmingmode
     * @param imgurl
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateTypeManage.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String updateTypeManage(HttpServletRequest request,String lamptypename,String lamptypedes,String power,
             String id,String path,String lampFactory,String dimmingmode,String lampModel,String spower,String bpower,@RequestParam MultipartFile imgurl)throws Exception{

        /*删除原有图片*/
        if (!imgurl.getOriginalFilename().equals("")){
            /*路径*/
            String pic_path = request.getSession().getServletContext().getRealPath("/");
            File targetFile = new File(pic_path,path);
            /*删除原图片*/
            UploadFileUtils.deleteFile(targetFile.toString());
        }
        Object tt = operationService.queryLampType(request,Integer.parseInt(id)).get(0);
        /*获取用户信息*/
        Tsysuser tsysuser = (Tsysuser)request.getSession().getAttribute("loginUser");
        /*上传图片*/
        String pathPic = "lampTypeImg";
        String filePath = UploadFileUtils.uploadFiles(request, imgurl, pathPic);
        System.out.println("filePath: "+filePath);
        /*增加灯具类型*/
        Tlamptype tlamptype = new Tlamptype();
        tlamptype.setId(Integer.parseInt(id));
        tlamptype.setLamptypename(lamptypename);
        tlamptype.setLamptypedes(lamptypedes);
        if (!power.isEmpty()){
            tlamptype.setPower(Integer.parseInt(power));
        }
        if (!lampFactory.isEmpty()){
            tlamptype.setLampFactory(lampFactory);
        }
        tlamptype.setDimmingmode(dimmingmode);
        if (!spower.isEmpty()){
            tlamptype.setSpower(Integer.parseInt(spower));
        }
        if (!bpower.isEmpty()){
            tlamptype.setBpower(Integer.parseInt(bpower));
        }
        tlamptype.setImgurl(filePath);
        tlamptype.setOperator(tsysuser.getUserName());
        tlamptype.setOpertime(GetLocalTimes.getNowTime());
        tlamptype.setDelflag(0);

        tlamptype.setLampModel(lampModel);
        /*更新灯具类型信息*/
        Integer data = operationService.updateTypeManage(tlamptype);
        String msg = "";
        if (data == 1){
            msg ="更新成功";
            tUserOperationService.recordOperationData(tlamptype,tt,tsysuser.getId(), SystemOperationCommon.OPERATION_UPDATE,data,MenuModelCommon.LAMPKIND_MANAGE);
        }
        return msg;
    }

    /**
     * 删除灯具类型信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteTypeManage.do" ,produces = "text/html;charset=UTF-8" )
    public @ResponseBody void deleteTypeManage(String id,HttpServletRequest request,HttpServletResponse response) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        Tlamptype tlamptype = new Tlamptype();
        tlamptype.setId(Integer.parseInt(id));
        tlamptype.setDelflag(1);
        /*删除灯具信息*/
        Integer data = operationService.updateTypeManage(tlamptype);
        String msg1 = data == 1 ? "y" : "n";
        response.getWriter().print(msg1);
    }

    /**
     * 查询所有所属路段
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryRegionList.do",produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryRegionList(HttpServletRequest request){
        List<Map<String,Object>> mapList = operationService.queryRegionList(request);
        return JSON.toJSONString(mapList);
    }

    /**
     * 根据ID查询路段
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryRegionArr.do",produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryRegionArr(HttpServletRequest request,String id){
        List<Map<String,Object>> mapList = operationService.queryRegionMap(request, Integer.parseInt(id));
        return JSON.toJSONString(mapList);
    }

    /**
     * 根据ID查询线路
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryRoadlineArr.do",produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryRoadlineArr(HttpServletRequest request,String id){
        List<Map<String,Object>> mapList = operationService.queryRoadlineMap(request, Integer.parseInt(id));
        return JSON.toJSONString(mapList);
    }

    /**
     * 查询所有的工厂信息
     * @return
     */
    @RequestMapping(value = "/queryFactoryList.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryFactoryList(HttpServletRequest request){
        List<Map<String,Object>> mapList = operationService.queryFactoryList(request);
        return JSON.toJSONString(mapList);
    }

    /**
     * 查询调光值
     * @return
     */
    @RequestMapping(value = "/querydimmingmodeList.do",produces = "text/html;charset=UTF-8")
    public @ResponseBody String querydimmingmodeList(){
        List<Map<String,Object>> mapList = operationService.querydimmingmodeList();
        return JSON.toJSONString(mapList);
    }

    /**
     * 查询所有的灯具信息
     * @param request
     * @param region_id
     * @param road_id
     * @param line_id
     * @param lampName
     * @param page
     * @return
     */
    @RequestMapping(value = "/lampManage.do")
    public String queryLampManage(HttpServletRequest request,String region_id,String road_id,String line_id,String lampName,String page){
        operationService.queryLampManageAll(request,region_id,road_id,line_id,lampName,page);
        return "device_lamp";
    }

    @RequestMapping(value = "getlampManage")
    public void getlampManage(Integer showNum, Integer curPage,Integer region_id,Integer road_id,Integer line_id,String lampName,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取登录人信息
        Tsysuser user = PlatformUtils.getLoginUser(request);
        //获取org_code
        String org_code = user.getTorganization().getOrgCode();
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("region_id",region_id);
        dataMap.put("road_id",road_id);
        dataMap.put("line_id",line_id);
        dataMap.put("lampName",lampName);
        //将org_code放进map
        dataMap.put("org_code",org_code);
        Map<String,Object> list = null;
        try {
            list = operationService.getlampManage(showNum,curPage,dataMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
            return;
        }
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }




    /**
     * 批量导入灯具
     * @param request
     * @param response
     * @param data
     * @return
     */
    @RequestMapping(value = "LampAddBatch.do",produces = "text/html;charset=UTF-8")
    public @ResponseBody String LampAddBatch(HttpServletRequest request,HttpServletResponse response,String data){
        String msg = operationService.IntsertLampBatch(request, data);
        return JSON.toJSONString(msg);
    }

    /**
     * 增加灯具信息
     * @param request
     * @param lampnum
     * @param typeId
     * @param pdId
     * @param dbcircuit
     * @param poleCode
     * @param roadlineId
     * @param controller_id
     * @param lo
     * @param la
     * @return
     */
    @RequestMapping( value =  "/addLampManage.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String addLampManage(HttpServletRequest request,String lampnum,String typeId,String pdId,String dbcircuit,String poleCode,String roadlineId,String controller_id,String lo,String la){
        Tsysuser user = PlatformUtils.getLoginUser(request);
        Integer info =  operationService.insertTlampManage(request,lampnum,typeId,pdId,dbcircuit,poleCode,roadlineId,controller_id, lo, la);
        String str = "";
        if (info == 1){
            str = "添加成功";
           // Tlamp tlamp = new Tlamp();
            //tlamp.setLampnum(Integer.parseInt(lampnum));
            //tUserOperationService.recordOperationData(tlamp,null,user.getId(), SystemOperationCommon.OPERATION_ADD,info,MenuModelCommon.LAMP_MANAGE);
        }
        return str;
    }

    /**
     * 更新灯具信息
     * @param request
     * @param lampnum
     * @param typeId
     * @param pdId
     * @param dbcircuit
     * @param poleCode
     * @param roadlineId
     * @param controller_id
     * @param lo
     * @param la
     * @return
     */
    @RequestMapping(value = "/updateLampManage.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String updateLampManage(HttpServletRequest request,String lampnum,String typeId,String pdId,String dbcircuit,String poleCode,String roadlineId,String controller_id,String lo,String la,String id){
        Tsysuser user = PlatformUtils.getLoginUser(request);
        Object tt2 = operationService.queryLampMeg(request,id).get(0);
        Integer info = operationService.updateTlampMsg(request, lampnum, typeId, pdId, dbcircuit, poleCode, roadlineId, controller_id, lo, la, id);
        Object tt = operationService.queryLampMeg(request,id).get(0);
        String str = "";
        if (info == 1){
            str = "更新成功";
            tUserOperationService.recordOperationData(tt,tt2,user.getId(), SystemOperationCommon.OPERATION_UPDATE,info,MenuModelCommon.LAMP_MANAGE);
        }
        return str;
    }

    /**
     * 删除灯具信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteLampManage.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody void deleteLampManage(HttpServletRequest request,HttpServletResponse response,String id) throws IOException {
        Integer info = operationService.deleteLampManage(request, id);
        String msg = info == 1 ? "y" : "n";
        response.getWriter().print(msg);
    }

    /**
     * 查询灯具信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryLampMeg.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryLampMeg(HttpServletRequest request,String id){
        List<Map<String,Object>> mapList = operationService.queryLampMeg(request,id);
        return JSON.toJSONString(mapList);
    }

    /**
     * 查询灯具类型
     * @return
     */
    @RequestMapping(value = "/queryLampTypeList.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryLampTypeList(HttpServletRequest request,String id){
        List<Map<String,Object>> mapList = operationService.queryLampTypeALL(request, Integer.parseInt(id));
        return JSON.toJSONString(mapList);
    }

    /**
     * 查询配电箱编号
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryPdboxAll.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryPdboxAll(HttpServletRequest request){
        List<Map<String,Object>> mapList = operationService.queryPdboxList(request);
        return JSON.toJSONString(mapList);
    }

    /**
     * 查询灯具工厂
     * @return
     */
    @RequestMapping(value = "/querylampFactoryList.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String querylampFactoryList(HttpServletRequest request){
        List<Map<String,Object>> mapList = operationService.querylampFactoryAll(request);
        return JSON.toJSONString(mapList);
    }

    /**
     * 查询控制器
     * @return
     */
    @RequestMapping(value = "/queryControllerList.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryControllerList(HttpServletRequest request){
        List<Map<String,Object>> mapList = operationService.queryControllerALL(request);
        return JSON.toJSONString(mapList);
    }

    /**
     * 控制器信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryContSelected.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryContSelected(HttpServletRequest request,String contId){
        List<Map<String,Object>> mapList = operationService.queryContUnpion(request, contId);
        return JSON.toJSONString(mapList);
    }

    /**
     * 查询所有的运营商
     * @return
     */
    @RequestMapping(value = "/queryNetworkOperators.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryNetworkOperators(){
        List<Map<String,Object>> mapList = operationService.queryNetworkOperatorsALL();
        return JSON.toJSONString(mapList);
    }

    /**
     * 查询协议类型
     * @return
     */
    @RequestMapping(value = "/queryProtocol.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryProtocolList(){
        List<Map<String,Object>> mapList = operationService.queryProtocolAll();
        return JSON.toJSONString(mapList);
    }
    /**
     * 查询协议类型
     * @return
     */
    @RequestMapping(value = "/queryPoleALL.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String queryPoleList(HttpServletRequest request){
        List<Map<String,Object>> mapList = operationService.queryPoleList(request);
        return JSON.toJSONString(mapList);
    }


    /**
     * 查询所属区域
     * @return
     */
    @RequestMapping(value = "/selectAreaAll.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String selectAreaAll(){
        List<Tareamanage> list = operationService.selectAreaAll();
      return JSON.toJSONString(list);
    }

    /**
     * 批量删除灯具类型
     * @param idArr
     * @return
     */
    @RequestMapping(value = "/batchDeletingLampType.do" , produces = "text/html;charset=UTF-8")
    public @ResponseBody String batchDeletingLampType(String idArr){
        String[] arr = idArr.split(",");
        List<Tlamptype> list = new ArrayList<Tlamptype>();
        for (int i = 0;i < arr.length;i++){
            Tlamptype tlamptype = new Tlamptype();
            tlamptype.setDelflag(1);
            tlamptype.setId(Integer.parseInt(arr[i]));
            list.add(tlamptype);
        }
        Integer msg = operationService.batchDeleteLampType(list);
        return JSON.toJSONString(msg);
    }
}
