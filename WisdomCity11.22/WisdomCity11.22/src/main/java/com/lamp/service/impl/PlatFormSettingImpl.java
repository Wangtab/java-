package com.lamp.service.impl;

import com.lamp.dao.TPlatformSettingMapper;
import com.lamp.model.TCitySetting;
import com.lamp.model.TDianXiIot;
import com.lamp.model.TPowerRate;
import com.lamp.model.Tlogo;
import com.lamp.service.PlatFormSettingService;
import com.lamp.utils.PlatformUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlatFormSettingImpl implements PlatFormSettingService {

    @Autowired
    private TPlatformSettingMapper tPlatformSettingMapper;


    @Override
    public List<Map<String, Object>> getOrganizationList(String orgCode) {
        return tPlatformSettingMapper.getOrganizationList(orgCode);
    }

    @Override
    public int saveDianXiIotData(TDianXiIot tDianXiIot) {
        return tPlatformSettingMapper.saveDianXiIotData(tDianXiIot);
    }

    @Override
    public int delPlatFomSettingById(Integer id) {
        return tPlatformSettingMapper.delPlatFomSettingById(id);
    }

    @Override
    public HashMap<String,Object> telecomManageList() {
        List<Map<String,Object>> mapList = tPlatformSettingMapper.telecomManageList();
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",mapList);
        resultMap.put("count",1);
        return resultMap;
    }

    @Override
    public HashMap<String, Object> getDianXiIotData(Integer id,Integer orgId) {
        List<Map<String, Object>> list = tPlatformSettingMapper.getDianXiIotData(id,orgId);
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",list);
        resultMap.put("count",1);
        return resultMap;
    }

    @Override
    public  HashMap<String,Object> getDianXiIotDataById(Integer id){
        List<Map<String, Object>> list = tPlatformSettingMapper.getDianXiIotDataById(id);
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",list);
        resultMap.put("count",1);
        return resultMap;
    }

    @Override
    public int updateDianXiIotDataById(TDianXiIot tDianXiIot) {
        return tPlatformSettingMapper.updateDianXiIotDataById(tDianXiIot);
    }

    @Override
    public Map<String, Object> getChanYeYuanIot(HashMap<String, Object> dataMap) {
        List<Map<String, Object>> list = tPlatformSettingMapper.getChanYeYuanIot(dataMap);
        HashMap<String,Object> resultMap = new HashMap();
        resultMap.put("count",2);
        resultMap.put("data",list);
        return resultMap;
    }

    @Override
    public HashMap<String, Object> getChanYeYuanIotById(Integer id) {
        return tPlatformSettingMapper.getChanYeYuanIotById(id);
    }

    @Override
    public int updateChanYeYuanIot(HashMap<String, Object> dataMap) {
        return tPlatformSettingMapper.updateChanYeYuanIot(dataMap);
    }

    public int updatePowerRate(TPowerRate TPowerRate){
        return tPlatformSettingMapper.updatePowerRate(TPowerRate);
    }

    public List<Map<String,Object>> getPowerRateById(Integer id){
        return tPlatformSettingMapper.getPowerRateById(id);
    }

    public  Map<String,Object> getPowerRateList(HashMap<String,Object> sqlMap){
        int count = tPlatformSettingMapper.getPowerRateCount(sqlMap);
        PlatformUtils.dealPageData(sqlMap,count);
        List<Map<String, Object>> list = tPlatformSettingMapper.getPowerRateList(sqlMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public Map<String, Object> getLogoInfo(HashMap<String, Object> dataMap) {
        int count = tPlatformSettingMapper.countGetLogoInfo(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = tPlatformSettingMapper.getLogoInfo(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public int updateLogoInfo(Tlogo l) {
        return tPlatformSettingMapper.updateLogoInfo(l);
    }

    @Override
    public HashMap<String, Object> getLogoInfoById(Integer id) {
        return tPlatformSettingMapper.getLogoInfoById(id);
    }

    @Override
    public Map<String, Object> getCityData(HashMap<String, Object> dataMap) {
        int count = tPlatformSettingMapper.getCityDataNum(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = tPlatformSettingMapper.getCityData(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public HashMap<String, Object> getCityDataById(Integer id) {
        return tPlatformSettingMapper.getCityDataById(id);
    }

    @Override
    public int updateCityData(TCitySetting t) {
        return tPlatformSettingMapper.updateCityData(t);
    }
}
