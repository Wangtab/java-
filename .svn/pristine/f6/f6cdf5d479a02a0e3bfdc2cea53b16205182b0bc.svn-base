package com.lamp.service.impl;

import com.lamp.dao.*;
import com.lamp.service.ScreenService;
import com.lamp.utils.PlatformUtils;
import com.lamp.utils.TimeUntils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    private TScreenMapper tScreenMapper;

    /**
     * 获取总能耗
     * @return
     */
    @Override
    public Map<String,Object> getSumPowers(String orgCode) {
        List<Map<String, Object>> fourDayList =  tScreenMapper.getFourDaysSumPowerData(orgCode);
        Double todayPower  = tScreenMapper.getTodayPowerData(orgCode);
        HashMap<String,String> calMap =  calculateNumsAdd(fourDayList, todayPower);
        //保存日期
        List<Object> saveDateList = new ArrayList<>();
        //保存功率
        List<Object> savePowerList = new ArrayList<>();

        for(Map<String, Object> map : fourDayList){
           Object power = String.valueOf(map.get("power"));
           Object date = String.valueOf(map.get("record_time"));
            saveDateList.add(date);
            savePowerList.add(power);
        }

        //将今天的数据保存下来
        savePowerList.add(calMap.get("calPower"));
        saveDateList.add(calMap.get("date"));
        Map<String,Object> result = new HashMap<>();
        result.put("data",savePowerList);
        result.put("date",saveDateList);
        return result;
    }

    /**
     * 区域能耗分析
     * @return
     */
    @Override
    public Map<String,Object> getAreasPowers(String orgCode){
        //获取时间
        TimeUntils tu = new TimeUntils();
        List<String> dateList = tu.getFourDaysBefore();
        List<Map<String, Object>> dataList = new ArrayList<>();
        //获取区域的功率数
        List<Map<String, Object>> list = tScreenMapper.getAreasPower(orgCode);
        if(PlatformUtils.isEmptyList(list)){
            return null;
        }

        //循环遍历区域
        for (Map<String,Object> map : list){
            Map<String,Object> dataMap = new HashMap<>();
            String areaId = map.get("id").toString();
            String areaName = map.get("areaName").toString();
            List<Object> tempList = dealPowerData(dateList,areaId,list);
            dataMap.put("name",areaName);
            dataMap.put("type","line");
            dataMap.put("data",tempList);
            dataList.add(dataMap);
        }

        Map<String,Object> result = new HashMap<>();
        result.put("data",dataList);
        result.put("date",dateList);
        return result;
    }

    //处理区域功率数据
    private List<Object> dealPowerData(List<String> dateList,String areaId,List<Map<String, Object>> powerList){
        List<Object> resultList = new ArrayList<>();
        for (String date : dateList){
            Object power = getPowerByDate(areaId, date, powerList);
            resultList.add(power);
        }
        return resultList;
    }

    //根据单个日期搜索出相应的功率值
    private Object getPowerByDate(String areaId,String date,List<Map<String, Object>> powerList){
        for (Map<String, Object> map:powerList){
            String id = map.get("id").toString();
            String record_time =map.get("record_times").toString();
            if(areaId.equals(id) && date.equals(record_time)){
                return map.get("energy");
            }
        }
        return 0;
    }

    /**
     *  计算两数的和
     *  计算上一天功率和传入功率相加值
     */
    private HashMap<String,String> calculateNumsAdd(List<Map<String, Object>> fourDayList,Double power){
        //计算功率之和
        Map<String,Object> lastDataMap = fourDayList.get(fourDayList.size()-1);
        Double lastPower = Double.parseDouble(lastDataMap.get("power").toString());
        //最后一天功率 + 今天消耗功率
        Double sumPower = lastPower + power;
        String calTodayPower =  String.format("%.3f", sumPower);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sdf.format(new Date());
        HashMap<String,String> dataMap = new HashMap<>();
        dataMap.put("calPower",calTodayPower);
        dataMap.put("date", todayDate);
        return dataMap;
    }

    @Override
    public Map<String, Object> getSumEnergyData(String orgCode) {
        List<Map<String,Object>> list = tScreenMapper.getSumEnergyData(orgCode);
        if(null == list || list.size() == 0){
            return null;
        }

        List<Object> dataList = new ArrayList<>();
        List<Object> dateList = new ArrayList();
        for (Map<String,Object> map : list){
            dataList.add(map.get("energy"));
            dateList.add(map.get("record_time"));
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",dataList);
        resultMap.put("date",dateList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getSingleData(String orgCode) {
        return tScreenMapper.getSingleData(orgCode);
    }
}
