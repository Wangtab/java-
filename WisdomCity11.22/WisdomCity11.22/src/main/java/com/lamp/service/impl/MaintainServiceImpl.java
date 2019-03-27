package com.lamp.service.impl;

import com.lamp.dao.MaintainMapper;
import com.lamp.model.*;
import com.lamp.service.MaintainService;
import com.lamp.utils.DealPage;
import com.lamp.utils.PlatformUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaintainServiceImpl implements MaintainService {

    @Autowired
    private MaintainMapper maintainMapper;

    @Override
    public Map<String, Object> getBuildingInfoData(HashMap<String, Object> dataMap) {
        int count = maintainMapper.getCountBuildingInfoData(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = maintainMapper.getBuildingInfoData(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public List<Map<String, Object>> exportBuildingInfoData(HashMap<String, Object> dataMap){
        return maintainMapper.exportBuildingInfoData(dataMap);
    }

    @Override
    public List<Map<String, Object>> getRepairPeopleAndNumberData(String orgCode) {
        return maintainMapper.getRepairPeopleAndNumberData(orgCode);
    }

    @Override
    public HashMap<String, Object> getBuildingInfoDataById(Integer id) {
        return maintainMapper.getBuildingInfoDataById(id);
    }

    @Override
    public int delBuildingInfoData(Integer id) {
        return maintainMapper.delBuildingInfoData(id);
    }

    @Override
    public int addBuildingInfoData(Tbuildinfo b) {
        return maintainMapper.addBuildingInfoData(b);
    }

    @Override
    public int updateBuildingInfoData(Tbuildinfo b) {
        int result = maintainMapper.updateBuildingInfoData(b);
        Integer deal = b.getDeal_result();
        if(1 == deal){//完成
            List<Map<String, Object>> list = maintainMapper.getCountRecord_warnByOrderNum(b.getOrdernum());
            if(null != list || list.size() > 0){//判断是否在报警表中存在数据
                Map<String, Object> map = list.get(0);
                String nb_device =  map.get("nb_device").toString();
                String orderNum = map.get("ordernum").toString();
                maintainMapper.updateLampWarnDataByOrderNum(orderNum,nb_device);
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> queryLogUserList(HashMap<String,Object> dataMap) {
        int count = maintainMapper.queryCountLogUserList(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = maintainMapper.queryLogUserList(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public Map<String, Object> getOperationLog(HashMap<String, Object> dataMap) {
        int count = maintainMapper.getCountOperationData(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = maintainMapper.getOperationData(dataMap);
        return PlatformUtils.backMapData(count,list);
    }


    @Override
    public Map<String,Object> getOperationLogData(HashMap<String, Object> dataMap) {
        int count = maintainMapper.countGetOperationLogData(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = maintainMapper.getOperationLogData(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public List<Map<String, Object>> exportOperationLampData(HashMap<String, Object> dataMap) {
        return maintainMapper.exportOperationLampData(dataMap);
    }

    @Override
    public Map<String, Object> getRoutingData(HashMap<String, Object> dataMap) {
        int count = maintainMapper.getCountRoutingData(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = maintainMapper.getRoutingData(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public HashMap<String, Object> getRoutingDataById(Integer id) {
        return maintainMapper.getRoutingDataById(id);
    }

    @Override
    public int delRoutingDataById(Integer id) {
        return maintainMapper.delRoutingDataById(id);
    }

    @Override
    public int addRoutingData(TroutingInspection t) {
        return maintainMapper.addRoutingData(t);
    }

    @Override
    public int updateRoutingData(TroutingInspection t) {
        return maintainMapper.updateRoutingData(t);
    }

    @Override
    public Map<String, Object> getStockData(HashMap<String, Object> dataMap) {
        int count = maintainMapper.getCountStockData(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = maintainMapper.getStockData(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public HashMap<String, Object> getStockDataById(Integer id) {
        return maintainMapper.getStockDataById(id);
    }

    @Override
    public int delStockDataById(Integer id) {
        return maintainMapper.delStockDataById(id);
    }

    @Override
    public int addStockData(Tstock tstock) {
        return maintainMapper.addStockData(tstock);
    }

    @Override
    public int updateStockData(Tstock tstock) {
        return maintainMapper.updateStockData(tstock);
    }

    @Override
    public Map<String, Object> getPlatformDianSumPrice(HashMap<String, Object> dataMap) {
        int count = maintainMapper.countGetPlatformDianSumPrice(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = maintainMapper.getPlatformDianSumPrice(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public Map<String, Object> getRepairData(HashMap<String, Object> dataMap) {
        int count = maintainMapper.getCountRepairData(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = maintainMapper.getRepairData(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public HashMap<String, Object> getRepairDataById(Integer id) {
        return maintainMapper.getRepairDataById(id);
    }

    @Override
    public int deleteRepById(Integer id) {
        return maintainMapper.deleteRepById(id);
    }

    @Override
    public int saveRepData(TrepairPeople t) {
        return maintainMapper.saveRepData(t);
    }

    @Override
    public int updateRepData(TrepairPeople t) {
        return maintainMapper.updateRepData(t);
    }

    @Override
    public int checkRepairNum(String orgCode, String number) {
        return maintainMapper.checkRepairNum(orgCode,number);
    }

    @Override
    public Map<String, Object> getBuildStandard(HashMap<String, Object> dataMap){
        int count = maintainMapper.getCountBuildStandard(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = maintainMapper.getBuildStandard(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public HashMap<String, Object> getBuildStandardById(Integer id) {
        return maintainMapper.getBuildStandardById(id);
    }

    @Override
    public int delBuildStandardById(Integer id) {
        return maintainMapper.delBuildStandardById(id);
    }

    @Override
    public int addBuildStandard(Tbuildstandard t) {
        return maintainMapper.addBuildStandard(t);
    }

    @Override
    public int updateBuildStandard(Tbuildstandard t) {
        return maintainMapper.updateBuildStandard(t);
    }

}
