package com.lamp.service.impl;

import com.lamp.dao.*;
import com.lamp.model.Tsysuser;
import com.lamp.service.IStatisticAnalysisService;
import com.lamp.utils.DealPage;
import com.lamp.utils.PageBean;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StatisticAnalysisServiceImpl implements IStatisticAnalysisService{

    @Autowired
    TStatisticAnalysisMapper statisticAnalysisMapper;

    @Override
    public Map<String, Object> dimmingDataAnalysis(HashMap<String, Object> dataMap) {
        String areaId = PlatformUtils.isEmptyBackStringEmpty(dataMap.get("areaId"));
        String roadId = PlatformUtils.isEmptyBackStringEmpty(dataMap.get("roadId"));
        String lineId = PlatformUtils.isEmptyBackStringEmpty(dataMap.get("lineId"));
        Integer curPage = Integer.parseInt(dataMap.get("curPage").toString());
        Integer showNum = Integer.parseInt(dataMap.get("showNum").toString());
        List<Map<String, Object>> list = null;
        String dataType = "area";
        int count = 0;
        //所有
        if(StringUtils.isEmpty(areaId)){
            count = statisticAnalysisMapper.getCountAreaDimmingAnalysisData(dataMap);
            Integer maxPage = (int) (Math.ceil(count / (curPage * 1.0)));
            curPage = DealPage.dealPage(curPage + "",maxPage);
            curPage = (curPage -1) * showNum;
            dataMap.put("showNum",showNum);
            dataMap.put("curPage",curPage);
            list =  statisticAnalysisMapper.getAreaDimmingAnalysisData(dataMap);
        }
        //选择区域
        else if( !StringUtils.isEmpty(areaId) && StringUtils.isEmpty(roadId) ){
            count = statisticAnalysisMapper.countGetAreaDimmingAnalysisDataByAreaId(dataMap);
            curPage = dealCurPage(count,curPage,showNum,dataMap);
            dataMap.put("showNum",showNum);
            dataMap.put("curPage",curPage);
            list =  statisticAnalysisMapper.getAreaDimmingAnalysisDataByAreaId(dataMap);
        }
        //选择道路
        else if(!StringUtils.isEmpty(roadId) && StringUtils.isEmpty(lineId)){
            count = statisticAnalysisMapper.countGetRoadDimmingAnalysisData(dataMap);
            curPage = dealCurPage(count,curPage,showNum,dataMap);
            dataMap.put("showNum",showNum);
            dataMap.put("curPage",curPage);
            list = statisticAnalysisMapper.getRoadDimmingAnalysisData(dataMap);
        }
        //选择线路
        else if(!StringUtils.isEmpty(lineId)){
            count = statisticAnalysisMapper.countGetRoadLineDimmingAnalysisData(dataMap);
            curPage = dealCurPage(count,curPage,showNum,dataMap);
            dataMap.put("showNum",showNum);
            dataMap.put("curPage",curPage);
            list = statisticAnalysisMapper.getRoadLineDimmingAnalysisData(dataMap);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",count);
        resultMap.put("data",list);
        return resultMap;
    }

    @Override
    public Map<String, Object> getDimmingDataCuredChartsById(HashMap<String,Object> paramMap) {
        String dataType = paramMap.get("dataType").toString();
        List<Object> dimmingList = new ArrayList<>();
        List<Object> timeList = new ArrayList<>();
        List<Map<String, Object>> dataList = null;
        HashMap<String,Object> resultMap = new HashMap<>();
        if("area".equals(dataType)){
            dataList =  statisticAnalysisMapper.getAreaDimmingAnalysisDataById(paramMap);
        } else if("road".equals(dataType)){
            dataList = statisticAnalysisMapper.getRoadDimmingAnalysisDataById(paramMap);
        } else if("road_line".equals(dataType)){
            dataList = statisticAnalysisMapper.getRoadLineDimmingAnalysisDataById(paramMap);
        }
        if(null == dataList || dataList.size() == 0){
            resultMap.put("oper","n");
            return resultMap;
        }
        resultMap.put("oper","y");
        for (Map<String, Object> map : dataList){
            dimmingList.add(map.get("dimming"));
            timeList.add(map.get("record_time").toString());
        }
        resultMap.put("dimmingList",dimmingList);
        resultMap.put("timeList",timeList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getSavePowerCharData(HashMap<String, Object> sqlMap) {
        HashMap<String,Object> dataMap = statisticAnalysisMapper.getSavePowerCharData(sqlMap);
        String paramPower = sqlMap.get("power") + "";
        Integer roadId = Integer.parseInt(sqlMap.get("roadId").toString());
        Double setPower = 0.0;
        if(StringUtils.isEmpty(paramPower)){
            setPower = statisticAnalysisMapper.getRoadManagecutonById(roadId);
        }else{
            setPower = Double.parseDouble(paramPower);
        }

        Double energy  = Double.parseDouble(dataMap.get("energy").toString());
        Double avgPower = Double.parseDouble(dataMap.get("num").toString());
        HashMap<String,Object> resultMap = new HashMap<>();

        if(0 == energy || 0 == avgPower || setPower == 0){
            resultMap.put("calPower",0);
            resultMap.put("energy",0);
            return resultMap;
        }

        Double calPower = energy / (avgPower/setPower);
        BigDecimal bg = new BigDecimal(calPower);
        calPower = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();


        resultMap.put("calPower",calPower);
        resultMap.put("energy",energy);
        return resultMap;
    }


      /**
     * 分页数据获取
     */
    public static Integer obtainPage(String page,Integer count,Integer size){
        /*分页*/
        PageBean pageBean = new PageBean();
        pageBean.setCount(count);
        pageBean.setSize(size);
        /*获取总共页数*/
        Integer countPage;
        if(count > size){
            if (count % size == 0){
                countPage = count/size;
            }else{
                countPage = count/size + 1;
            }
        }else {
            countPage = 1;
        }
        /*页码处理*/
        Integer pageindex = DealPage.dealPage(page, pageBean.getTotal());
        /*分页获取数据*/
        Integer num = (pageindex -1)*size;
        return num;
    }

    public Map<String, Object> energyAnalysisDataForList(HashMap<String,Object> paramMap){
        String areaId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("areaId"));
        String roadId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("roadId"));
        String lineId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("lineId"));
        Integer curPage = Integer.parseInt(paramMap.get("curPage").toString());
        Integer showNum = Integer.parseInt(paramMap.get("showNum").toString());
        List<Map<String, Object>> list = null;
        HashMap<String,Object> resultMap = new HashMap<>();
        int count = 0;
        //所有
        if((StringUtils.isEmpty(areaId))){
            count = statisticAnalysisMapper.countPlatformEnergyAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.platformAnalysisDataForList(paramMap);
        }
        //区域
        else if(!StringUtils.isEmpty(areaId) && StringUtils.isEmpty(roadId)){
            count = statisticAnalysisMapper.countAreaAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.areaAnalysisDataForList(paramMap);
        }
        //道路
        else if(!StringUtils.isEmpty(roadId) && StringUtils.isEmpty(lineId)){
            count = statisticAnalysisMapper.countRoadAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.roadAnalysisDataForList(paramMap);
        }
        //线路
        else if(!StringUtils.isEmpty(lineId)){
            count = statisticAnalysisMapper.countRoadLineAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.roadLineAnalysisDataForList(paramMap);
        }
        resultMap.put("count",count);
        resultMap.put("data",list);
        return resultMap;
    }

    private Integer dealCurPage(Integer count,Integer curPage,Integer showNum,HashMap<String,Object> paramMap){
        Integer maxPage = (int) (Math.ceil(count / (curPage * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        curPage = (curPage -1) * showNum;
        paramMap.put("num",curPage);
        paramMap.put("showNum",showNum);
        return curPage;
    }

    public Map<String,Object> energyAnalysisDataForChart(HashMap<String,Object> paramMap){
        String dataType = paramMap.get("dataType").toString();
        List<Map<String, Object>> list = null;
        if("platform".equals(dataType) || "area".equals(dataType)){
            list = statisticAnalysisMapper.platformEnergyAnalysisDataForChart(paramMap);
        } else if("road".equals(dataType)){
            list = statisticAnalysisMapper.roadEnergyAnalysisDataForChart(paramMap);
        } else if("lineId".equals(dataType)){
            list = statisticAnalysisMapper.roadLineEnergyAnalysisDataForChart(paramMap);
        }
        List<Object> dataList = new ArrayList<>();
        List<Object> dateList = new ArrayList<>();
        if(null == list || list.size() == 0){
            return null;
        }

        for (Map<String,Object> map : list){
            dataList.add(map.get("energy"));
            dateList.add(map.get("times"));
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("dataList",dataList);
        resultMap.put("dateList",dateList);
        return resultMap;
    }

    public Map<String,Object> repairAnalysisDataForList(HashMap<String,Object> paramMap){
        String areaId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("areaId"));
        String roadId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("roadId"));
        Integer curPage = Integer.parseInt(paramMap.get("curPage").toString());
        Integer showNum = Integer.parseInt(paramMap.get("showNum").toString());
        List<Map<String, Object>> list = null;
        int count = 0;
        //所有
        if((StringUtils.isEmpty(areaId))){
            count = statisticAnalysisMapper.countPlatformRepairAnalysisDataForList(paramMap);
            curPage = dealCurPage(count,curPage,showNum,paramMap);
            paramMap.put("num",curPage);
            list = statisticAnalysisMapper.platformRepairAnalysisDataForList(paramMap);
        }
        //区域
        else if(!StringUtils.isEmpty(areaId) && StringUtils.isEmpty(roadId)){
            count = statisticAnalysisMapper.countAreaRepairAnalysisDataForList(paramMap);
            curPage = dealCurPage(count,curPage,showNum,paramMap);
            paramMap.put("num",curPage);
            list = statisticAnalysisMapper.areaRepairAnalysisDataForList(paramMap);
        }
        //道路
        else if(!StringUtils.isEmpty(roadId)){
            count = statisticAnalysisMapper.countRoadRepairAnalysisDataForList(paramMap);
            curPage = dealCurPage(count,curPage,showNum,paramMap);
            paramMap.put("num",curPage);
            list = statisticAnalysisMapper.roadRepairAnalysisDataForList(paramMap);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",count);
        resultMap.put("data",list);
        return resultMap;
    }

    public Map<String,Object> repairAnalyAnalysisDataForChart(HashMap<String,Object> paramMap){
        String dataType = paramMap.get("dataType").toString();
        List<Map<String, Object>> list = null;
        if("plfatm".equals(dataType) || "area".equals(dataType)){
            list = statisticAnalysisMapper.platformRepairAnalysisDataForChart(paramMap);
        } else if("road".equals(dataType)){
            list = statisticAnalysisMapper.roadRepairAnalysisDataForChart(paramMap);
        }
        List<Object> dataList = new ArrayList<>();
        List<Object> dateList = new ArrayList<>();
        if(null == list || list.size() == 0){
            return null;
        }

        for (Map<String,Object> map : list){
            dataList.add(map.get("num"));
            dateList.add(map.get("times"));
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("dataList",dataList);
        resultMap.put("dateList",dateList);
        return resultMap;
    }

    public Map<String,Object> routingAnalysisDataForList(HashMap<String,Object> paramMap){
        String areaId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("areaId"));
        String roadId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("roadId"));
        Integer curPage = Integer.parseInt(paramMap.get("curPage").toString());
        Integer showNum = Integer.parseInt(paramMap.get("showNum").toString());
        List<Map<String, Object>> list = null;
        int count = 0;
        //所有
        if((StringUtils.isEmpty(areaId))){
            count = statisticAnalysisMapper.countPlatformRoutingAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.platformRoutingAnalysisDataForList(paramMap);
        }
        //区域
        else if(!StringUtils.isEmpty(areaId) && StringUtils.isEmpty(roadId)){
            count = statisticAnalysisMapper.countAreaRoutingAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.areaRoutingAnalysisDataForList(paramMap);
        }
        //道路
        else if(!StringUtils.isEmpty(roadId)){
            count = statisticAnalysisMapper.countRoadRoutingAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.roadRoutingAnalysisDataForList(paramMap);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("count",count);
        resultMap.put("data",list);
        return resultMap;
    }

    public Map<String,Object> routingAnalysisDataForChart(HashMap<String,Object> paramMap){
        String dataType = paramMap.get("dataType").toString();
        List<Map<String, Object>> list = null;
        if("platform".equals(dataType) || "area".equals(dataType)){
            list = statisticAnalysisMapper.areaRoutingAnalysisDataForChart(paramMap);
        } else if("road".equals(dataType)){
            list = statisticAnalysisMapper.roadRoutingAnalysisDataForChart(paramMap);
        }
        List<Object> dataList = new ArrayList<>();
        List<Object> dateList = new ArrayList<>();
        if(null == list || list.size() == 0){
            return null;
        }

        for (Map<String,Object> map : list){
            dataList.add(map.get("num"));
            dateList.add(map.get("times"));
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("dataList",dataList);
        resultMap.put("dateList",dateList);
        return resultMap;
    }

    public Map<String, Object> rateAnalysisDataForList(HashMap<String,Object> paramMap){
        String areaId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("areaId"));
        String roadId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("roadId"));
        String lineId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("lineId"));
        Integer curPage = Integer.parseInt(paramMap.get("curPage").toString());
        Integer showNum = Integer.parseInt(paramMap.get("showNum").toString());
        List<Map<String, Object>> list = null;
        HashMap<String,Object> resultMap = new HashMap<>();
        int count = 0;
        //所有
        if((StringUtils.isEmpty(areaId))){
            count = statisticAnalysisMapper.countPlatformRateAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.platformRateAnalysisDataForList(paramMap);
        }
        //区域
        else if(!StringUtils.isEmpty(areaId) && StringUtils.isEmpty(roadId)){
            count = statisticAnalysisMapper.countAreaRateAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.areaRateAnalysisDataForList(paramMap);
        }
        //道路
        else if(!StringUtils.isEmpty(roadId) && StringUtils.isEmpty(lineId)){
            count = statisticAnalysisMapper.countRoadRateAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.roadRateAnalysisDataForList(paramMap);
        }
        //线路
        else if(!StringUtils.isEmpty(lineId)){
            count = statisticAnalysisMapper.countRoadLineRateAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.roadLineRateAnalysisDataForList(paramMap);
        }
        resultMap.put("count",count);
        resultMap.put("data",list);
        return resultMap;
    }

    public Map<String,Object> rateAnalysisDataForChart(HashMap<String,Object> paramMap){
        String dataType = paramMap.get("dataType").toString();
        List<Map<String, Object>> list = null;
        if("platform".equals(dataType) || "area".equals(dataType)){
            list = statisticAnalysisMapper.platformRateAnalysisDataForChart(paramMap);
        } else if("road".equals(dataType)){
            list = statisticAnalysisMapper.roadRateAnalysisDataForChart(paramMap);
        } else if("lineId".equals(dataType)){
            list = statisticAnalysisMapper.roadLineRateAnalysisDataForChart(paramMap);
        }
        List<Object> dataList = new ArrayList<>();
        List<Object> dateList = new ArrayList<>();
        if(null == list || list.size() == 0){
            return null;
        }

        for (Map<String,Object> map : list){
            dataList.add(map.get("rate"));
            dateList.add(map.get("times"));
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("dataList",dataList);
        resultMap.put("dateList",dateList);
        return resultMap;
    }

    public Map<String, Object> workHourAnalysisDataForList(HashMap<String,Object> paramMap){
        String areaId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("areaId"));
        String roadId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("roadId"));
        String lineId = PlatformUtils.isEmptyBackStringEmpty(paramMap.get("lineId"));
        Integer curPage = Integer.parseInt(paramMap.get("curPage").toString());
        Integer showNum = Integer.parseInt(paramMap.get("showNum").toString());
        List<Map<String, Object>> list = null;
        HashMap<String,Object> resultMap = new HashMap<>();
        int count = 0;
        //所有
        if((StringUtils.isEmpty(areaId))){
            count = statisticAnalysisMapper.countPlatformWorkHourAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.platformWorkHourAnalysisDataForList(paramMap);
        }
        //区域
        else if(!StringUtils.isEmpty(areaId) && StringUtils.isEmpty(roadId)){
            count = statisticAnalysisMapper.countAreaWorkHourAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.areaWorkHourAnalysisDataForList(paramMap);
        }
        //道路
        else if(!StringUtils.isEmpty(roadId) && StringUtils.isEmpty(lineId)){
            count = statisticAnalysisMapper.countRoadWorkHourAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.roadWorkHourAnalysisDataForList(paramMap);
        }
        //线路
        else if(!StringUtils.isEmpty(lineId)){
            count = statisticAnalysisMapper.countRoadLineWorkHourAnalysisDataForList(paramMap);
            dealCurPage(count,curPage,showNum,paramMap);
            list = statisticAnalysisMapper.roadLineWorkHourAnalysisDataForList(paramMap);
        }
        resultMap.put("count",count);
        resultMap.put("data",list);
        return resultMap;
    }

    public Map<String,Object> workHourAnalysisDataForChart(HashMap<String,Object> paramMap){
        String dataType = paramMap.get("dataType").toString();
        List<Map<String, Object>> list = null;
        if("platform".equals(dataType) || "area".equals(dataType)){
            list = statisticAnalysisMapper.platformWorkHourAnalysisDataForChart(paramMap);
        } else if("road".equals(dataType)){
            list = statisticAnalysisMapper.roadWorkHourAnalysisDataForChart(paramMap);
        } else if("lineId".equals(dataType)){
            list = statisticAnalysisMapper.roadLineWorkHourAnalysisDataForChart(paramMap);
        }
        return commonDealChartData(list);
    }

    @Override
    public Map<String, Object> getLampStatusReport(HashMap<String, Object> dataMap) {
        Map<String,Object> resultMap = new HashMap<>();
        Integer showNum = Integer.parseInt(dataMap.get("showNum").toString());
        Integer curPage = Integer.parseInt(dataMap.get("curPage").toString());
        int count = statisticAnalysisMapper.countGetLampStatusReport(dataMap);
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer nums = (curPage -1) * showNum;
        dataMap.put("num",nums);
        List<Map<String, Object>> list = statisticAnalysisMapper.getLampStatusReport(dataMap);
        resultMap.put("count",count);
        resultMap.put("data",list);
        return resultMap;
    }

    @Override
    public List<Map<String, Object>> exportGetLampStatusReport(HashMap<String,Object> dataMap) {
        return statisticAnalysisMapper.exportGetLampStatusReport(dataMap);
    }

    /**
     * 通用处理分析图形数据信息
     */
    private Map<String,Object> commonDealChartData(List<Map<String, Object>> list){
        if(null == list || list.size() == 0){
            return null;
        }
        List<Object> dataList = new ArrayList<>();
        List<Object> dateList = new ArrayList<>();

        for (Map<String,Object> map : list){
            dataList.add(map.get("data"));
            dateList.add(map.get("times"));
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("dataList",dataList);
        resultMap.put("dateList",dateList);
        return resultMap;
    }
}
