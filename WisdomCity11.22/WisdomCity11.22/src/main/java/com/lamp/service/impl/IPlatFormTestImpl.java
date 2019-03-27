package com.lamp.service.impl;

import com.lamp.dao.TPlatFormTestMapper;
import com.lamp.service.IPlatFormTest;
import com.lamp.utils.LampSwitchInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class IPlatFormTestImpl implements IPlatFormTest {

    @Autowired
    private TPlatFormTestMapper tPlatFormTestMapper;

    @Override
    public String initSumPower(){
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        List<Map<String, Object>> dataList = new ArrayList<>();
        tPlatFormTestMapper.delRecordSumPower();
        List<Map<String, Object>> list = tPlatFormTestMapper.getFourDaysAgoSumPower();
        //如果没有数据的时候,则添加0作为补充
        if(list == null || list.size() == 0){
            return "no_data_deal";
        }

        //将4天前总功率提取出来
        for(Map<String,Object> map : list){
            HashMap<String,Object> dataMap = new HashMap<>();
            String recordDate =  map.get("record_date").toString();
            Double sumPower = tPlatFormTestMapper.getSumPowerByDate(recordDate);
            dataMap.put("uuid",lampSwitchInterface.getUUID());
            dataMap.put("sumPower",sumPower);
            dataMap.put("record_date",recordDate);
            dataList.add(dataMap);
        }
        //保存到数据库
        tPlatFormTestMapper.batchSaveSumpPowerData(dataList);
        return "deal_finish";
    }

    /**
     * 初始化灯具每天的功率总和
     */
    public String initLampByDay() throws ParseException {
        //删除所有信息
        tPlatFormTestMapper.delRecordLampPower();
        List<Map<String, Object>> dateList = tPlatFormTestMapper.getAllRecordData();
        if(dateList == null || dateList.size() == 0){
            return "no_date_list";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        //循环日期列表
        for(Map<String, Object> map : dateList){
            Object obj = map.get("times");
            String date =  sdf.format(obj);
            List<Map<String, Object>> powerList = tPlatFormTestMapper.getPowerDateByDate(date);
            if(powerList == null || powerList.size() == 0){
                continue;
            }
            List<Map<String, Object>> dataList = new ArrayList<>();
            //循环功率列表
            for (Map<String, Object> powerMap : powerList){
                HashMap<String,Object> dataMap = new HashMap<>();
                dataMap.put("id",lampSwitchInterface.getUUID());
                dataMap.put("nb_device",powerMap.get("nb_device"));
                dataMap.put("power",powerMap.get("power"));
                Date recordDate  = sdf.parse(date);
                dataMap.put("record_time",recordDate);
                dataList.add(dataMap);
            }
            //批量保存今日功率列表信息
            tPlatFormTestMapper.batchSaveLampPower(dataList);
        }
        return "deal_finish";
    }

    /**
     * 初始化道路每天功率总和
     */
    private void initRoadByDay() throws ParseException{
        tPlatFormTestMapper.clearRecordRoadPower();
        List<Map<String, Object>> dateList = tPlatFormTestMapper.getRecordLampPowerDate();
        if(dateList == null || dateList.size() == 0){
            System.out.println("no_lamp_date_list");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        //循环日期列表
        for(Map<String, Object> map : dateList){
            Object obj = map.get("record_time");
            String date =  sdf.format(obj);
            List<Map<String, Object>> powerList = tPlatFormTestMapper.dealRoadRecordPowerByDate(date);
            if(powerList == null || powerList.size() == 0){
                continue;
            }
            List<Map<String, Object>> dataList = new ArrayList<>();
            //循环功率列表
            for (Map<String, Object> powerMap : powerList){
                HashMap<String,Object> dataMap = new HashMap<>();
                dataMap.put("id",lampSwitchInterface.getUUID());
                dataMap.put("road_id",powerMap.get("id"));
                dataMap.put("power",lampSwitchInterface.dealPowerToKW(powerMap.get("power").toString()));
                Date recordDate  = sdf.parse(date);
                dataMap.put("record_time",recordDate);
                dataList.add(dataMap);
            }
            //批量保存今日功率列表信息
            tPlatFormTestMapper.batchSaveRoadPower(dataList);
        }
        System.out.println("deal_finish");
    }

    /**
     * 初始化区域每天功率总和
     */
    private void initAreaByDay() throws ParseException{
        tPlatFormTestMapper.clearRecordAreaPower();
        List<Map<String, Object>> dateList = tPlatFormTestMapper.getRecordRoadPowerDate();
        if(dateList == null || dateList.size() == 0){
            System.out.println("no_lamp_date_list");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();

        //循环日期列表
        for(Map<String, Object> map : dateList){
            Object obj = map.get("record_time");
            String date =  sdf.format(obj);
            List<Map<String, Object>> powerList = tPlatFormTestMapper.dealAreaRecordPowerByDate(date);
            if(powerList == null || powerList.size() == 0){
                continue;
            }
            List<Map<String, Object>> dataList = new ArrayList<>();
            //循环功率列表
            for (Map<String, Object> powerMap : powerList){
                HashMap<String,Object> dataMap = new HashMap<>();
                dataMap.put("id",lampSwitchInterface.getUUID());
                dataMap.put("area_id",powerMap.get("id"));
                dataMap.put("power",powerMap.get("power").toString());
                Date recordDate  = sdf.parse(date);
                dataMap.put("record_time",recordDate);
                dataList.add(dataMap);
            }
            //批量保存今日功率列表信息
            tPlatFormTestMapper.batchSaveAreaPower(dataList);
        }
        System.out.println("deal_finish");
    }



    public void initRecordPowerData(){
        try {
            initLampByDay();
            initRoadByDay();
            initAreaByDay();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
