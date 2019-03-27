package com.lamp.service.impl;

import com.lamp.dao.TLampCommonMapper;
import com.lamp.service.LampCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LampCommonServiceImpl implements LampCommonService {

    @Autowired
    private TLampCommonMapper tLampCommonMapper;

    @Override
    public List<Map<String, Object>> getAreaNameForSelect(String orgCode) {
        return tLampCommonMapper.getAreaNameForSelect(orgCode);
    }

    @Override
    public List<Map<String, Object>> getRoadNameForSelect(HashMap<String, Object> dataMap) {
        return tLampCommonMapper.getRoadNameForSelect(dataMap);
    }

    @Override
    public List<Map<String, Object>> getRoadLineNameForSelect(HashMap<String, Object> dataMap) {
        return tLampCommonMapper.getRoadLineNameForSelect(dataMap);
    }

    @Override
    public List<Map<String, Object>> getDimmingModelForSelect() {
        return tLampCommonMapper.getDimmingModelForSelect();
    }

    @Override
    public List<Map<String, Object>> getControllerKindForSelect() {
        return tLampCommonMapper.getControllerKindForSelect();
    }

    @Override
    public List<Map<String, Object>> getConcentratorForSelect(HashMap<String, Object> dataMap) {
        return tLampCommonMapper.getConcentratorForSelect(dataMap);
    }

    @Override
    public List<Map<String, Object>> getMenuDataForSelect() {
        return tLampCommonMapper.getMenuDataForSelect();
    }

    @Override
    public List<Map<String, Object>> getRepairPeopleForSelect(HashMap<String, Object> dataMap) {
        return tLampCommonMapper.getRepairPeopleForSelect(dataMap);
    }

    @Override
    public List<Map<String, Object>> getBuildStandardForSelect(HashMap<String, Object> dataMap) {
        return tLampCommonMapper.getBuildStandardForSelect(dataMap);
    }

    @Override
    public List<Map<String, Object>> getDeviceTypeDataForSelect() {
        return tLampCommonMapper.getDeviceTypeDataForSelect();
    }

    @Override
    public List<Map<String, Object>> getControllerNumDataForSelect(HashMap<String, Object> dataMap) {
        return tLampCommonMapper.getControllerNumDataForSelect(dataMap);
    }

    @Override
    public List<Map<String, Object>> getLampTypeDataForSelect(HashMap<String,Object> dataMap) {
        return tLampCommonMapper.getLampTypeDataForSelect(dataMap);
    }

    @Override
    public List<Map<String, Object>> getLampNumForSelect(HashMap<String, Object> dataMap) {
        return tLampCommonMapper.getLampNumForSelect(dataMap);
    }

    @Override
    public List<Map<String, Object>> getGroupNameForSelect(HashMap<String, Object> dataMap) {
        return tLampCommonMapper.getGroupNameForSelect(dataMap);
    }

    @Override
    public List<Map<String, Object>> getPlanContentForSelect() {
        return tLampCommonMapper.getPlanContentForSelect();
    }

    @Override
    public List<Map<String, Object>> getMenuBtnByUser(HashMap<String, Object> dataMap) {
        return tLampCommonMapper.getMenuBtnByUser(dataMap);
    }

    @Override
    public List<Map<String, Object>> getDistributionBoxForSelect(String orgCode) {
        return tLampCommonMapper.getDistributionBoxForSelect(orgCode);
    }


}
