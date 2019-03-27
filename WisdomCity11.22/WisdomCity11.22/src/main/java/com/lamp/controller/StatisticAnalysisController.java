package com.lamp.controller;

import com.lamp.model.areaexcel;
import com.lamp.service.IStatisticAnalysisService;
import com.lamp.utils.ExcelFileGenerator;
import com.lamp.utils.ExcelUtil;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 统计分析模块
 */
@Controller
class StatisticAnalysisController {

    @Autowired
    IStatisticAnalysisService statisticAnalysisService;

    /**
     *  亮灯率分析
     */
    @RequestMapping("dimmingDataAnalysis")
    public void dimmingDataAnalysis(HttpServletRequest request,HttpServletResponse response,
                                    String areaId,String roadId,String lineId,
                                    String startDate,String endDate,String curPage,String showNum) throws IOException{
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,lineId,startDate,endDate,curPage,showNum);
        Map<String,Object> resultMap = statisticAnalysisService.dimmingDataAnalysis(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 亮灯率曲线图
     * @param dataId
     * @param dataType
     */
    @RequestMapping("getDimmingDataCuredChartsById")
    public void getDimmingDataCuredChartsById(String dataId,String dataType,String startDate,String endDate,
                                              HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingChartParam(dataType,dataId,startDate,endDate,request);
        Map<String,Object> dataMap = statisticAnalysisService.getDimmingDataCuredChartsById(paramMap);
        if(null == dataMap || dataMap.size() == 0){
            response.getWriter().print("n");
        } else {
            response.getWriter().print(JSONArray.fromObject(dataMap).toString());
        }
    }

    /**
     * 导出亮灯率
     */
    @RequestMapping(value = "exportDimmingDataExcel")
    public void exportDimmingDataExcel(HttpServletRequest request,HttpServletResponse response,
                                       String areaId,String roadId,String lineId,
                                       String startDate,String endDate) throws IOException,ParseException{
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,lineId,startDate,endDate,"1","9999");
        Map<String,Object> resultMap = statisticAnalysisService.dimmingDataAnalysis(paramMap);
        commonExportAnalysis(resultMap,response,"名称","亮灯率(%)","亮灯率分析表");
    }



    /**
     * 节能率分析统计
     */
    @RequestMapping("getSavePowerCharData")
    public void getSavePowerCharData(Integer roadId,String power,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("orgCode",orgCode);
        sqlMap.put("roadId",roadId);
        sqlMap.put("power",power);
        Map<String,Object> dataMap = statisticAnalysisService.getSavePowerCharData(sqlMap);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     *  能耗分析
     */
    @RequestMapping("energyAnalysisDataForList")
    public void energyAnalysisDataForList(String areaId,String roadId,String lineId,String curPage,String showNum,String startDate,String endDate,
                                          HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,lineId,startDate,endDate,curPage,showNum);
        Map<String,Object> dataMap = statisticAnalysisService.energyAnalysisDataForList(paramMap);
        PlatformUtils.checkSendDataByResponse(dataMap,response);
    }

    /**
     * 能耗分析曲线图
     */
    @RequestMapping("energyAnalysisDataForChart")
    public void energyAnalysisDataForChart(String dataType,String dataId,String startDate,String endDate,HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingChartParam(dataType,dataId,startDate,endDate,request);
        Map<String,Object> dataMap = statisticAnalysisService.energyAnalysisDataForChart(paramMap);
        if(null == dataMap){
            response.getWriter().print("n");
        } else {
            response.getWriter().print(JSONArray.fromObject(dataMap).toString());
        }
    }

    /**
     *  能耗分析导出
     */
    @RequestMapping("exportEnergyAnalysisDataForList")
    public void exportEnergyAnalysisDataForList(String areaId,String roadId,String lineId,String startDate,String endDate,
                                                HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,lineId,startDate,endDate,"1","9999");
        Map<String,Object> dataMap = statisticAnalysisService.energyAnalysisDataForList(paramMap);
        commonExportAnalysis(dataMap,response,"名称","能耗(kwh)","能耗分析表");
    }

    /**
     *  维修次数分析
     */
    @RequestMapping("repairAnalysisDataForList")
    public void repairAnalysisDataForList(String areaId,String roadId,String curPage,String showNum,String startDate,String endDate,
                                          HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,null,startDate,endDate,curPage,showNum);
        Map<String,Object> dataMap = statisticAnalysisService.repairAnalysisDataForList(paramMap);
        PlatformUtils.checkSendDataByResponse(dataMap,response);
    }

    /**
     * 维修次数分析曲线图
     */
    @RequestMapping("repairAnalysisDataForChart")
    public void repairAnalysisDataForChart(String dataType,String dataId,String startDate,String endDate,HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingChartParam(dataType,dataId,startDate,endDate,request);
        Map<String,Object> dataMap = statisticAnalysisService.repairAnalyAnalysisDataForChart(paramMap);
        if(null == dataMap || dataMap.size() == 0){
            response.getWriter().print("n");
        } else {
            response.getWriter().print(JSONArray.fromObject(dataMap).toString());
        }
    }

    /**
     *  维修次数分析导出
     */
    @RequestMapping("exportRepairAnalysisDataForList")
    public void exportRepairAnalysisDataForList(String areaId,String roadId,String lineId,String startDate,String endDate,
                                                HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,lineId,startDate,endDate,"1","9999");
        Map<String,Object> dataMap = statisticAnalysisService.repairAnalysisDataForList(paramMap);
        commonExportAnalysis(dataMap,response,"名称","次数","维修列表分析表");
    }

    /**
     *  巡检次数分析
     */
    @RequestMapping("routingAnalysisDataForList")
    public void routingAnalysisDataForList(String areaId,String roadId,String curPage,String showNum,String startDate,String endDate,
                                          HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,null,startDate,endDate,curPage,showNum);
        Map<String,Object> dataMap = statisticAnalysisService.routingAnalysisDataForList(paramMap);
        PlatformUtils.checkSendDataByResponse(dataMap,response);
    }

    /**
     * 巡检次数分析曲线图
     */
    @RequestMapping("routingAnalysisDataForChart")
    public void routingAnalysisDataForChart(String dataType,String dataId,String startDate,String endDate,HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingChartParam(dataType,dataId,startDate,endDate,request);
        Map<String,Object> dataMap = statisticAnalysisService.routingAnalysisDataForChart(paramMap);
        if(null == dataMap || dataMap.size() == 0){
            response.getWriter().print("n");
        } else {
            response.getWriter().print(JSONArray.fromObject(dataMap).toString());
        }
    }

    /**
     *  巡检次数导出
     */
    @RequestMapping("exportRoutingAnalysisDataForList")
    public void exportRoutingAnalysisDataForList(String areaId,String roadId,String lineId,String startDate,String endDate,
                                                HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,lineId,startDate,endDate,"1","9999");
        Map<String,Object> dataMap = statisticAnalysisService.routingAnalysisDataForList(paramMap);
        commonExportAnalysis(dataMap,response,"名称","次数","巡检列表分析表");
    }

    /**
     *  完好率分析
     */
    @RequestMapping("rateAnalysisDataForList")
    public void rateAnalysisDataForList(String areaId,String roadId,String lineId,String curPage,String showNum,String startDate,String endDate,
                                          HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,lineId,startDate,endDate,curPage,showNum);
        Map<String,Object> dataMap = statisticAnalysisService.rateAnalysisDataForList(paramMap);
        PlatformUtils.checkSendDataByResponse(dataMap,response);
    }

    /**
     * 完好率分析曲线图
     */
    @RequestMapping("rateAnalysisDataForChart")
    public void rateAnalysisDataForChart(String dataType,String dataId,String startDate,String endDate,HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingChartParam(dataType,dataId,startDate,endDate,request);
        Map<String,Object> dataMap = statisticAnalysisService.rateAnalysisDataForChart(paramMap);
        if(null == dataMap || dataMap.size() == 0){
            response.getWriter().print("n");
        } else {
            response.getWriter().print(JSONArray.fromObject(dataMap).toString());
        }
    }

    /**
     *  完好率分析导出
     */
    @RequestMapping("exportRateAnalysisDataForList")
    public void exportRateAnalysisDataForList(String areaId,String roadId,String lineId,String startDate,String endDate,
                                                HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,lineId,startDate,endDate,"1","9999");
        Map<String,Object> dataMap = statisticAnalysisService.rateAnalysisDataForList(paramMap);
        commonExportAnalysis(dataMap,response,"名称","数值","完整率列表分析表");
    }

    /**
     *  工作时间分析
     */
    @RequestMapping("workHourAnalysisDataForList")
    public void workHourAnalysisDataForList(String areaId,String roadId,String lineId,String curPage,String showNum,
                                            String startDate,String endDate,HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,lineId,startDate,endDate,curPage,showNum);
        Map<String,Object> dataMap = statisticAnalysisService.workHourAnalysisDataForList(paramMap);
        PlatformUtils.checkSendDataByResponse(dataMap,response);
    }

    /**
     * 工作时长分析曲线图
     */
    @RequestMapping("workAnalysisDataForChart")
    public void workAnalysisDataForChart(String dataType,String dataId,String startDate,String endDate,HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingChartParam(dataType,dataId,startDate,endDate,request);
        Map<String,Object> dataMap = statisticAnalysisService.workHourAnalysisDataForChart(paramMap);
        if(dataMap == null || dataMap.isEmpty()){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(dataMap).toString());
        }
    }

    /**
     *  工作时间导出
     */
    @RequestMapping("exportWorkHourAnalysisDataForList")
    public void exportWorkHourAnalysisDataForList(String areaId,String roadId,String lineId,String startDate,String endDate,
                                              HttpServletRequest request,HttpServletResponse response) throws IOException {
        HashMap<String,Object> paramMap = packingListParam(request,areaId,roadId,lineId,startDate,endDate,"1","9999");
        Map<String,Object> dataMap = statisticAnalysisService.workHourAnalysisDataForList(paramMap);
        commonExportAnalysis(dataMap,response,"名称","时长(H)","时长列表分析表");
    }

    /**
     *  灯具状态报表
     */
    @RequestMapping("getLampStatusReport")
    public void getLampStatusReport(String areaId,String roadId,Integer typeId,Integer curPage,Integer showNum,
                                    String orderBy,String sort,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("areaId",areaId);
        dataMap.put("roadId",roadId);
        dataMap.put("orgCode",orgCode);
        dataMap.put("typeId",typeId);
        dataMap.put("curPage",curPage);
        dataMap.put("showNum",showNum);
        dataMap.put("orderBy",orderBy);
        dataMap.put("sort",sort);
        Map<String,Object> resultMap = statisticAnalysisService.getLampStatusReport(dataMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     * 导出灯具状态报表
     */
    @RequestMapping("exportGetLampStatusReport")
    public void exportGetLampStatusReport(String areaId,String roadId,Integer typeId,
                                          String orderBy,String sort,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("areaId",areaId);
        dataMap.put("roadId",roadId);
        dataMap.put("orgCode",orgCode);
        dataMap.put("typeId",typeId);
        dataMap.put("orderBy",orderBy);
        dataMap.put("sort",sort);
        List<Map<String, Object>> list  = statisticAnalysisService.exportGetLampStatusReport(dataMap);
        com.alibaba.fastjson.JSONArray ja = new com.alibaba.fastjson.JSONArray();
        if(PlatformUtils.isEmptyList(list)){
            Map<String,String> headMap = new LinkedHashMap<>();
            headMap.put("areaName","");
            headMap.put("power","");
            ExcelUtil.downloadExcelFile("数据状态列表", headMap,ja,response);
            return;
        }
        ja.addAll(list);

        Map<String,String> headMap = new LinkedHashMap<>();
        headMap.put("areaName","区域");
        headMap.put("road_name","道路");
        headMap.put("cname","线路");
        headMap.put("lampnum","灯具编号");
        headMap.put("nb_device","设备ID号");
        headMap.put("nb_code","设备号");
        headMap.put("on_off","开关状态");
        headMap.put("conn_state","联网状态");
        if(1 == typeId){
            headMap.put("vol","电压");
            headMap.put("ele","电流");
            headMap.put("power","功率");
            headMap.put("dimming","调光率");
        } else if(2 == typeId){
            headMap.put("vol","LED电压");
            headMap.put("ele","LED电流");
            headMap.put("pvol","光伏电压");
            headMap.put("pele","光伏电流");
            headMap.put("bvol","蓄电池电压");
            headMap.put("sendele","总充电安时");
            headMap.put("enterele","总放电安时");
        }
        headMap.put("record_time","上传时间");
        ExcelUtil.downloadExcelFile("数据日志列表", headMap,ja,response);
    }





    /**
     * 包装分析列表参数
     * @param request
     * @param areaId    区域 Id
     * @param roadId    道路 id
     * @param lineId    线路 id
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param curPage   当前页码
     * @param showNum   每页显示数量
     */
    private HashMap<String,Object> packingListParam(HttpServletRequest request, String areaId, String roadId, String lineId, String startDate, String endDate, String curPage, String showNum){
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("orgCode",orgCode);
        paramMap.put("areaId",areaId);
        paramMap.put("roadId",roadId);
        paramMap.put("lineId",lineId);
        paramMap.put("curPage",curPage);
        paramMap.put("showNum",showNum);
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        return paramMap;
    }

    /**
     * 包装分析查找图表参数
     * @param dataType
     * @param dataId
     * @param startDate
     * @param endDate
     * @param request
     * @return
     */
    private HashMap<String,Object> packingChartParam(String dataType,String dataId,String startDate,String endDate,HttpServletRequest request){
        String orgCode =  PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("orgCode",orgCode);
        paramMap.put("dataType",dataType);
        paramMap.put("dataId",dataId);
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        return paramMap;
    }

    /**
     * 通用分析导出数据
     * @param dataMap
     * @param response
     * @param listTitleName
     * @param listTitleDataName
     * @param title
     * @throws IOException
     */
    private void commonExportAnalysis(Map<String,Object> dataMap,HttpServletResponse response,String listTitleName,String listTitleDataName,String title) throws IOException {
        com.alibaba.fastjson.JSONArray ja = new com.alibaba.fastjson.JSONArray();
        if(!PlatformUtils.checkMapDataIsEmpty(dataMap)){
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get("data");
            for(int i=0;i<dataList.size();i++){
                Map<String,Object> map = dataList.get(i);
                areaexcel areaexcel = new areaexcel();
                areaexcel.setAreaname(map.get("cName").toString());
                areaexcel.setPower(map.get("num").toString());
                ja.add(areaexcel);
            }
        }
        Map<String,String> headMap = new LinkedHashMap<>();
        headMap.put("areaname",listTitleName);
        headMap.put("power",listTitleDataName);
        ExcelUtil.downloadExcelFile(title, headMap,ja,response);
    }
}
