package com.lamp.controller;

import com.lamp.common.CitySettingCommon;
import com.lamp.model.*;
import com.lamp.service.PlatFormSettingService;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("PlatFomSetting")
public class PlatFomSettingController {

    @Autowired
    private PlatFormSettingService platFormSettingService;


    /**
     * 查询所有的组织信息
     * @param request
     * @return
     */
    @RequestMapping(value = "getOrganizationList",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    public @ResponseBody
    String getOrganizationList(HttpServletRequest request){

        String orgCode = PlatformUtils.getLoginUserCode(request);

        List<Map<String, Object>> mapList = platFormSettingService.getOrganizationList(orgCode);

        if(null == mapList || mapList.size() == 0){
            return "n";
        }
        return JSONArray.fromObject(mapList).toString();
    }

    /**
     * 获取电信IOT平台初始化
     * @param response
     */
    @RequestMapping(value = "telecomManageList")
    public void getTelecomManage(HttpServletResponse response)throws Exception{
        HashMap<String,Object> dataMap = platFormSettingService.telecomManageList();
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }
    /**
     * 查询电信IOT平台信息
     * 没有区分电信和产业院的IOT平台
     */
    @RequestMapping("getDianXiIotData")
    public void getDianXiIotData(String orgId,String id,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<String,Object>();
        Integer oId = null;
        if(orgId != null && !orgId.equals("")){
            oId = Integer.parseInt(orgId);
        }
        if(id != null){
            dataMap = platFormSettingService.getDianXiIotData(Integer.parseInt(id),oId);
        }else {
            dataMap = platFormSettingService.getDianXiIotData(null,oId);
        }
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    @RequestMapping("getDianXiIotDataById")
    public void getDianXiIotDataById(Integer id, HttpServletResponse response) throws IOException {
        HashMap<String,Object> dataMap = platFormSettingService.getDianXiIotDataById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 删除平台设置
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "delPlatFomSettingById",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    public @ResponseBody String delPlatFomSettingById(Integer id,HttpServletRequest request){
        int status = platFormSettingService.delPlatFomSettingById(id);
        String msg = status == 1 ? "y" : "n";
        return msg;
    }

    /**
     * 修改电信IOT平台信息
     */
    @RequestMapping("updateDianXiIotDataById")
    public void updateDianXiIotDataById(TDianXiIot tDianXiIot, HttpServletRequest request, HttpServletResponse response) throws IOException{
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        tDianXiIot.setOperId(tsysuser.getId());
        tDianXiIot.setOperTime(PlatformUtils.getNowTime());
        int status = 0;
        if(tDianXiIot.getId() != null){
            //修改
            status = platFormSettingService.updateDianXiIotDataById(tDianXiIot);
        }else {
            //增加
            status = platFormSettingService.saveDianXiIotData(tDianXiIot);
        }

        String msg = status == 1 ? "y" : "n";
        response.getWriter().print(msg);
    }

    /**
     * 获取产业院IOT平台信息
     */
    @RequestMapping("getChanYeYuanIot")
    public void getChanYeYuanIot(HttpServletResponse response,String orderBy,String sort) throws IOException{
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("orderBy",orderBy);
        dataMap.put("sort",sort);
        Map<String, Object> resultMap = platFormSettingService.getChanYeYuanIot(dataMap);
        response.getWriter().print(JSONArray.fromObject(resultMap).toString());
    }

    /**
     *  根据id 获取电信IOT平台信息
     */
    @RequestMapping("getChanYeYuanIotById")
    public void getChanYeYuanIotById(Integer id,HttpServletResponse response) throws  IOException{
        HashMap<String,Object> dataMap = platFormSettingService.getChanYeYuanIotById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 保存产业院信息
     */
    @RequestMapping("saveChanYeYuanIot")
    public void saveChanYeYuanIot(Integer id,String address,HttpServletResponse response,HttpServletRequest request) throws  IOException {
        HashMap<String,Object> paramMap = new HashMap<>();
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        paramMap.put("id",id);
        paramMap.put("address",address);
        paramMap.put("operId",tsysuser.getId());
        paramMap.put("operTime",PlatformUtils.getNowTime());
        int status = platFormSettingService.updateChanYeYuanIot(paramMap);
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 修改电费单价
     */
    @RequestMapping("updatePowerRateData")
    public void updatePowerRateData(TPowerRate TPowerRate,HttpServletRequest request,HttpServletResponse response) throws IOException{
        TPowerRate.setOperId(PlatformUtils.getLoginUser(request).getId());
        TPowerRate.setOperTime(PlatformUtils.getNowTime());
        int status = platFormSettingService.updatePowerRate(TPowerRate);
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 根据ID获取电费ID
     */
    @RequestMapping("getPowerRateById")
    public void getPowerRateById(Integer Id,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> list = platFormSettingService.getPowerRateById(Id);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 查询电费单价
     */
    @RequestMapping("getPowerRateList")
    public void getPowerRateList(Integer showNum, Integer curPage,String sort,String orderBy,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("orgCode",orgCode);
        Map<String,Object> resultMap = platFormSettingService.getPowerRateList(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 查询logo信息
     */
    @RequestMapping("getLogoInfo")
    public void getLogoInfo(HttpServletRequest request,Integer showNum,Integer curPage,String orderBy,String sort,HttpServletResponse response){
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("orgCode",orgCode);
        Map<String,Object> resultMap = platFormSettingService.getLogoInfo(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     *  修改Logo信息
     */
    @RequestMapping("updateLogoInfo")
    public void updateLogoInfo(Tlogo tlogo,@RequestParam("files")  MultipartFile files,HttpServletRequest request, HttpServletResponse response) throws IOException {
        tlogo.setId(PlatformUtils.getLoginUser(request).getOrgId());
        tlogo.setUptime(PlatformUtils.getNowTime());
        String imgPath = PlatformUtils.saveImg("/upload/logo/",request);
        tlogo.setImager(imgPath);
        int status = platFormSettingService.updateLogoInfo(tlogo);
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 根据ID获取logo信息
     */
    @RequestMapping("getLogoInfoById")
    public void getLogoInfoById(Integer id,HttpServletResponse response) throws IOException {
        HashMap<String,Object> dataMap = platFormSettingService.getLogoInfoById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     *  获取城市信息
     */
    @RequestMapping("getCityData")
    public void getCityData(Integer showNum,Integer curPage,String orderBy,String sort,HttpServletResponse response,HttpServletRequest request) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("orgCode",orgCode);
        Map<String,Object> resultMap = platFormSettingService.getCityData(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 根据id获取城市信息
     */
    @RequestMapping("getCityDataById")
    public void getCityDataById(Integer id,HttpServletResponse response) throws IOException {
        HashMap<String,Object>  dataMap = platFormSettingService.getCityDataById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 更新城市信息
     */
    @RequestMapping("updateCityData")
    public void updateCityData(TCitySetting city, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        city.setOperId(user.getId());
        city.setOper_time(PlatformUtils.getNowTime());
        HashMap<String,String > weatherMap = PlatformUtils.dealWeatherTemperatureData(city.getLo() + "," + city.getLa(),"");
        HashMap<String,String> sunTimeMap =  PlatformUtils.getSunRiseAndSet(city.getLo(),city.getLa());
        city.setLowTemp(weatherMap.get(CitySettingCommon.WEATHER_LOW_TEMPERATURE));
        city.setHeightTemp(weatherMap.get(CitySettingCommon.WEATHER_HEIGHT_TEMPERATURE));
        city.setTempDetail(weatherMap.get(CitySettingCommon.WEATHER_TEMPERATURE_DETAIL));
        city.setWeatherPic(weatherMap.get(CitySettingCommon.WEATHER_PIC));
        city.setSunRise(sunTimeMap.get("sunrise"));
        city.setSunSet(sunTimeMap.get("sunset"));
        int status = platFormSettingService.updateCityData(city);
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);
    }

}
