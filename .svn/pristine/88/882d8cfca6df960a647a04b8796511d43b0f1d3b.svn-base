package com.lamp.service.impl;
import com.huawei.utils.HuaWeiMethod;
import com.lamp.common.MonitorCommon;
import com.lamp.dao.TMonitorServiceMapper;
import com.lamp.dao.TPlatformSettingMapper;
import com.lamp.model.TDianXiIot;
import com.lamp.model.Tsysuser;
import com.lamp.service.IMonitorService;
import com.lamp.thread.LampSwitchThread;
import com.lamp.utils.DealPage;
import com.lamp.utils.LampSwitchInterface;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class IMonitorServiceImpl implements IMonitorService{

    @Autowired
    private TMonitorServiceMapper monitorServiceMapper;

    @Autowired
    private TPlatformSettingMapper tPlatformSettingMapper;

    @Override
    public String getPowerList() {
        List<Map<String, Object>> list = monitorServiceMapper.getTodayPower();
        if(null == list) return null;
        return JSONArray.fromObject(list).toString();
    }

    @Override
    public String getLampDetail(HttpServletRequest request,Integer id) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String, Object>> list = monitorServiceMapper.getLampDetail(orgCode,id);
        if(null == list) return null;
        return JSONArray.fromObject(list).toString();
    }

    @Override
    public HashMap<String, Object> getDeviceStatusForWin(Integer id) {
        return monitorServiceMapper.getDeviceStatusForWin(id);
    }

    public List<Map<String,Object>> compareTodayPower(HashMap<String,Object> dataMap) {
        return  monitorServiceMapper.compareTodayPower(dataMap);
    }

    /**
     * 单灯开关
     */
    @Override
    @Transactional
    public HashMap<String,Object> singleDeviceController(Integer id, Integer onOff,Integer oper,Integer dimming){
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("id",id);    //控制器id
        onOff = dealOnOffStatusByDimmiing(dimming,oper,sqlMap,onOff);
        //获取灯杆下面所有灯具信息
        List<Map<String, Object>> list = monitorServiceMapper.getNbDeviceByControllerId(sqlMap);
        if(MonitorCommon.LIGHT_SWITCH_OPERATION == oper){
            return commonSwitchLight(list,onOff);
        }else if(MonitorCommon.LIGHT_DIMMING_OPERATION == oper){
            return commonSwitchDimming(list,onOff,dimming);
        }
            return null;
    }

    /**
     * 批量处理
     * @param request
     * @param onOff
     * @return
     */
    public HashMap<String, Object> batchSwitchOperLamp(HttpServletRequest request, String ids, Integer onOff,Integer oper,Integer dimming) {
        JSONArray jsonArray = JSONArray.fromObject(ids);
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jb = (JSONObject) jsonArray.get(i);
            Integer nbId = Integer.parseInt(jb.get("nbId").toString());
            idList.add(nbId);
        }
        HashMap<String,Object> paramMap = new HashMap<>();
        onOff = dealOnOffStatusByDimmiing(dimming,oper,paramMap,onOff);
        paramMap.put("list",idList);

        HashMap<String,Object> resultMap = null;
        List<Map<String, Object>> list = monitorServiceMapper.batchSwitchOperLamp(paramMap);
        if(MonitorCommon.LIGHT_SWITCH_OPERATION == oper){
            resultMap =  commonSwitchLight(list,onOff);
        }else if(MonitorCommon.LIGHT_DIMMING_OPERATION == oper){
            resultMap = commonSwitchDimming(list,onOff,dimming);
        }
        return resultMap;
    }

    /**
     * 根据调光值改变开关状态
     * @param dimming
     * @return
     */
    private Integer dealOnOffStatusByDimmiing(Integer dimming,Integer oper, HashMap<String,Object> sqlMap,Integer onOff){
        if(MonitorCommon.LIGHT_SWITCH_OPERATION == oper){//灯具操作指令
            sqlMap.put("on_off",getOppOnoff(onOff));//相反的设置开关灯指令
        }else if(MonitorCommon.LIGHT_DIMMING_OPERATION == oper){ //灯具调光指令
            if(0 == dimming){//关灯并调光
                onOff = MonitorCommon.LAMP_CLOSE;
            }else{//开灯并调光
                onOff = MonitorCommon.LAMP_OPEN;
            }
            sqlMap.put("on_off",null);//设置开关灯指令
        }
        return onOff;
    }

    /**
     * 路段开关
     */
    @Override
    @Transactional
    public HashMap<String,Object> RoadSwitchController(HttpServletRequest request,Integer roadId, Integer onOff,Integer oper,Integer dimming) {
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("roadId",roadId);
        onOff = dealOnOffStatusByDimmiing(dimming,oper,sqlMap,onOff);
        //获取路段下面所有灯具信息
        List<Map<String, Object>> list = monitorServiceMapper.getNbDeviceByRoadId(sqlMap);
        if(MonitorCommon.LIGHT_SWITCH_OPERATION == oper){
            return commonSwitchLight(list,onOff);
        } else if(MonitorCommon.LIGHT_DIMMING_OPERATION == oper){
            return commonSwitchDimming(list,onOff,dimming);
        }
        return null;
    }

    /**
     * 分组开关
     */
    @Override
    @Transactional
    public HashMap<String,Object> groupSwitchController(Integer groupId, Integer onOff,Integer oper,Integer dimming){
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("groupId",groupId);
        onOff = dealOnOffStatusByDimmiing(dimming,oper,sqlMap,onOff);
        //获取路段下面所有灯具信息
        List<Map<String, Object>> list = monitorServiceMapper.getNbDeviceByGroupId(sqlMap);
        if(MonitorCommon.LIGHT_SWITCH_OPERATION == oper){
            return commonSwitchLight(list,onOff);
        } else if(MonitorCommon.LIGHT_DIMMING_OPERATION == oper){
            return commonSwitchDimming(list,onOff,dimming);
        }
        return null;
    }

    @Override
    public Map<String,Object> getAllLampDetailData(Integer curpage,Integer showNum,String roadId,Integer groupId){
        int count = monitorServiceMapper.getCountAllLampDetailData(roadId,groupId);
        Map<String,Object> dataMap = new HashMap<>();
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curpage = DealPage.dealPage(curpage + "", maxPage);
        Integer num = (curpage -1) * showNum;
        List<Map<String, Object>> list = monitorServiceMapper.getAllLampDetailData(roadId, num, showNum, groupId);
        dataMap.put("count",count);
        dataMap.put("datas",list);
        return dataMap;
    }

    /**
     * 回路开关和集中器开关
     */
    @Override
    @Transactional
    public HashMap<String,Object> loopSwitchController(HttpServletRequest request,Integer concenId, Integer onOff,Integer loop,Integer oper,Integer dimming,String orgCode){
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("concenId",concenId);
        sqlMap.put("loop",loop);
        sqlMap.put("orgCode",orgCode);
        onOff = dealOnOffStatusByDimmiing(dimming,oper,sqlMap,onOff);
        //获取回路开关和集中器开关下面所有灯具信息
        List<Map<String, Object>> list = monitorServiceMapper.getNbDeviceByLoopAndConcenId(sqlMap);
        if(MonitorCommon.LIGHT_SWITCH_OPERATION == oper){
            return commonSwitchLight(list,onOff);
        }else if(MonitorCommon.LIGHT_DIMMING_OPERATION == oper){
            return commonSwitchDimming(list,onOff,dimming);
        }
        return null;
    }

    /**
     *  回路开关控制
     * @param loop 回路编号
     * @param concentratorId 集中器id
     * @param onOff 开关标记
     * @return
     */
    @Override
    @Transactional
    public boolean loopSwitch(Integer loop, Integer concentratorId, Integer onOff){
        Integer opp_on_off = 0;
        if(MonitorCommon.LAMP_OPEN == onOff){//控制开灯
            opp_on_off = 1;//查询关灯条件
        } else if(MonitorCommon.LAMP_CLOSE == onOff){//控制关灯
            opp_on_off = 0;//查询开灯条件
        }
        try {
            monitorServiceMapper.loopSwitchByConcentratorId(onOff,concentratorId,loop,opp_on_off);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通用开关灯处理方法
     */
    private HashMap<String,Object> commonSwitchLight(List<Map<String, Object>> list,Integer on_off){
        HashMap<String,Object> resultMap = new HashMap<>();
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        if(null == list || list.size() == 0){
            resultMap.put("status","empty");
            return resultMap;
        }
        for (Map<String,Object> map : list){
            String nbNum = map.get("nb_device").toString(); //NB编号
            Integer protocol = Integer.parseInt(map.get("protocol").toString());//灯具协议
            Integer dimming = Integer.parseInt(map.get("dimming").toString());//dimming值
            Integer business = Integer.parseInt(map.get("business").toString()); //判断是否是IOT平台还是别的平台
            Integer exeDimming = dimming;
            if(MonitorCommon.LAMP_OPEN == on_off){//开灯命令
                if (0 == dimming){//开灯时 如果调光度为0时 默认给100
                    exeDimming = 100;
                }
                map.put("hope_num",MonitorCommon.HOPE_MORE_THAN);//设置希望值(大于当前数)
            } else if(MonitorCommon.LAMP_CLOSE == on_off){//关灯命令
                exeDimming = 0;
                map.put("hope_num",MonitorCommon.HOPE_LESS_THAN);//设置希望值(小于当前数)
            }
            map.put("set_on_off", on_off);//保存开关命令
            map.put("set_dimming",dimming);//保存调光值
            if(MonitorCommon.NB_BUSINESS_CHANYE_YUAN == business){
                //产业院发送命令
                lampSwitchInterface.sendInfo(protocol,exeDimming,nbNum);
                resultMap.put("status","success");
            }else if(MonitorCommon.NB_BUSINESS_DIAN_XIN == business){
                //查出IOT授权
                Map<String,Object> map1 = tPlatformSettingMapper.getPlatData(Integer.parseInt(map.get("operid").toString()));
                String accessToken = HuaWeiMethod.getAccessToken(map);
                if(accessToken != null){
                    //更新授权密钥
                    TDianXiIot tDianXiIot = new TDianXiIot();
                    tDianXiIot.setId(Integer.parseInt(map.get("id").toString()));
                    tDianXiIot.setAccessToken(accessToken);
                    tDianXiIot.setTokenTime(new Date());
                    tPlatformSettingMapper.updateDianXiIotDataById(tDianXiIot);
                }else {
                    accessToken = map.get("accessToken").toString();
                }
                //IOT平台参数
                Map<String,Object> map2 = new HashMap<String,Object>();
                map2.put("level",exeDimming);
                map2.put("deviceId",nbNum);
                map2.put("appId",map1.get("appId"));
                map2.put("accessToken", accessToken);
                String result = HuaWeiMethod.IOTNBSwitchLightAndDimming(map2);
                resultMap.put("status","success");
            }
        }
        commondealSwitchResult(list);
        return resultMap;
    }

    /**
     * 通用调光处理方法
     * @return
     */
    private HashMap<String,Object> commonSwitchDimming(List<Map<String, Object>> list,Integer onOff,Integer dimming){
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        HashMap<String,Object> resultMap = new HashMap<>();
        if(null == list || list.size() == 0){
            resultMap.put("status","empty");
            return resultMap;
        }
        for (Map<String, Object> map : list){
            String nbNum = map.get("nb_device").toString(); //NB编号
            Integer protocol = Integer.parseInt(map.get("protocol").toString());//灯具协议
            Integer nb_dimming = Integer.parseInt(map.get("dimming").toString());//dimming值
            Integer business = Integer.parseInt(map.get("business").toString()); //判断是否是IOT平台还是别的平台
            /**
             * 如果现有调光值大于需要的调光值 设置希望值小于当前数
             * 如果现有调光值小于需要的调光值 设置希望值大于当前数
             */
            if(nb_dimming > dimming){
                map.put("hope_num",MonitorCommon.HOPE_LESS_THAN);//设置希望值(小于当前数)
            } else {
                map.put("hope_num",MonitorCommon.HOPE_MORE_THAN);//设置希望值(小于当前数)
            }
            map.put("set_on_off", onOff);//保存开关命令
            map.put("set_dimming",dimming);//保存调光值
            if (MonitorCommon.NB_BUSINESS_CHANYE_YUAN == business){
                //发送命令
                lampSwitchInterface.sendInfo(protocol, dimming, nbNum);
                //填写成功状态
                resultMap.put("status", "success");
            }else if (MonitorCommon.NB_BUSINESS_DIAN_XIN == business){
                //查出IOT授权
                Map<String,Object> map1 = tPlatformSettingMapper.getPlatData(Integer.parseInt(map.get("operid").toString()));
                String accessToken = HuaWeiMethod.getAccessToken(map);
                if(accessToken != null){
                    //更新授权密钥
                    TDianXiIot tDianXiIot = new TDianXiIot();
                    tDianXiIot.setId(Integer.parseInt(map.get("id").toString()));
                    tDianXiIot.setAccessToken(accessToken);
                    tDianXiIot.setTokenTime(new Date());
                    tPlatformSettingMapper.updateDianXiIotDataById(tDianXiIot);
                }else {
                    accessToken = map.get("accessToken").toString();
                }
                //IOT平台参数
                Map<String,Object> map2 = new HashMap<String,Object>();
                map2.put("level",0);
                map2.put("deviceId",nbNum);
                map2.put("appId",map1.get("appId"));
                map2.put("accessToken", accessToken);
                String result = HuaWeiMethod.IOTNBSwitchLightAndDimming(map2);
                resultMap.put("status","success");
            }
        }
        commondealSwitchResult(list);
        return resultMap;
    }

    /**
     * 通用处理灯具结果
     */
    public void commondealSwitchResult(List<Map<String, Object>> list){
        LampSwitchThread lt = new LampSwitchThread();
        lt.setMonitorServiceMapper(monitorServiceMapper);
        lt.setList(list);
        lt.run();
    }

    @Override
    public List<Map<String, Object>> getGroupManageByControllerId(Integer id,String orgCode) {
        return monitorServiceMapper.getGroupManageByControllerId(id,orgCode);
    }

    /**
     * 根据开开关状态获取相反数值
     * @return
     */
    private Integer getOppOnoff(Integer onOff){
        Integer oppOnOff = 0;
        if(MonitorCommon.LAMP_OPEN == onOff){//开灯指令 获取关灯状态灯具
            oppOnOff = MonitorCommon.LAMP_CLOSE;
        } else if(MonitorCommon.LAMP_CLOSE == onOff){//关灯指令 获取开灯灯具
            oppOnOff = MonitorCommon.LAMP_OPEN;
        }
        return oppOnOff;
    }

    /**
     * 开关灯 记录结果 保存到数据库中
     * @param state 开关灯的状态
     * @param NbId NB设备表ID
     * @param dimming 调光
     * @return
     */
    @Transactional
    public boolean updateLightOnOffState(Integer state,Integer NbId,Integer dimming){
        try {
            int result =  monitorServiceMapper.updateLightOnOffState(state,NbId,dimming,(state+1));
            if(1 == result){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *  获取单个灯具数据
     */
    @Override
    public HashMap<String,Object> getSingleLampLotData(HttpServletRequest request,Integer id){
        HashMap<String,Object> resultMap = null;
        HashMap<String,Object> dataMap = monitorServiceMapper.getSingleLampLotData(id);
        if(dataMap == null || dataMap.size() == 0){
            return null;
        }
        Integer protocolId = Integer.parseInt(dataMap.get("protocol").toString());
        String nbNum = dataMap.get("nb_device").toString();
        String nbId = dataMap.get("id").toString();
        Integer business = Integer.parseInt(dataMap.get("business").toString());
        if(MonitorCommon.NB_BUSINESS_CHANYE_YUAN == business){
            LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
            HashMap<String,Object> LampdataMap = lampSwitchInterface.getLampDataByNbId(protocolId,nbNum);
            if(null == LampdataMap){
                return null;
            }
            LampdataMap.put("nbid",nbId);
            monitorServiceMapper.updateSingleLampLotData(LampdataMap);
            resultMap = LampdataMap;
        }else if (MonitorCommon.NB_BUSINESS_DIAN_XIN == business){
            //单个灯具IOT平台
            Tsysuser user =  PlatformUtils.getLoginUser(request);
            //查出IOT授权
            Map<String,Object> map = tPlatformSettingMapper.getPlatData(user.getId());
            String accessToken = HuaWeiMethod.getAccessToken(map);
            if(accessToken != null){
                //更新授权密钥
                TDianXiIot tDianXiIot = new TDianXiIot();
                tDianXiIot.setId(Integer.parseInt(map.get("id").toString()));
                tDianXiIot.setAccessToken(accessToken);
                tDianXiIot.setTokenTime(new Date());
                tPlatformSettingMapper.updateDianXiIotDataById(tDianXiIot);
            }else {
                accessToken = map.get("accessToken").toString();
            }
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("appId",map.get("appId"));
            map1.put("accessToken",accessToken);
            map1.put("deviceId", nbNum);
            Map<String,Object> data = HuaWeiMethod.QueryDeviceData(map1);

            HashMap<String,Object> LampdataMap = LampSwitchInterface.getIOTNBByData(data);

            if(null == LampdataMap){
                return null;
            }
            LampdataMap.put("nbid",nbId);
            LampdataMap.put("nb_num",nbNum);
            LampdataMap.put("procotal",protocolId);
            monitorServiceMapper.updateSingleLampLotData(LampdataMap);
            resultMap = LampdataMap;
        }

        return resultMap;
    }

    @Transactional
    public String getManyLampLotData(String roadId){
        List<Map<String, Object>> list = monitorServiceMapper.getManyLampLotData(roadId);
        if(list == null || list.size() == 0){
            return "empty";
        }
        List<Map<String,Object>> newDataList = new ArrayList<>();
        for (Map<String, Object> map : list){
            Integer protocolId = Integer.parseInt(map.get("protocolid").toString());
            String nbNum = map.get("nb_device").toString();
            String nbId = map.get("nbId").toString();
            LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
            HashMap<String,Object> LampdataMap = lampSwitchInterface.getLampDataByNbId(protocolId,nbNum);
            if(null == LampdataMap || LampdataMap.size() < 1){
               continue;
            }
            LampdataMap.put("nbid",nbId);
            newDataList.add(LampdataMap);
        }
        try {
            monitorServiceMapper.batchUpdatelampData(newDataList);
            return "ok";
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public HashMap<String, Object> getCityForMap(String orgCode) {
        return monitorServiceMapper.getCityForMap(orgCode);
    }


    public List<Map<String, Object>> getDistributionListForMap(String orgCode) {
        return monitorServiceMapper.getDistributionListForMap(orgCode);
    }

    public List<Map<String, Object>> getLampDataLocation(String orgCode,Integer aid) {
        return monitorServiceMapper.getLampDataLocation(orgCode,aid);
    }

    public Map<String, Object> getAllLampDetailData2(HttpServletRequest request,Integer curpage, Integer showNum, String roadId, String areaId, String roadxId) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String, Object>> list2 = monitorServiceMapper.getAllLampDetailData2(orgCode,null, null, null, null, null);
        int count=1;
        if(list2!=null){
            count=list2.size();
        }
        Map<String,Object> dataMap = new HashMap<>();
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curpage = DealPage.dealPage(curpage + "",maxPage);
        Integer num = (curpage -1) * showNum;
        List<Map<String, Object>> list = monitorServiceMapper.getAllLampDetailData2(orgCode,roadId, num, showNum, areaId, roadxId);
        dataMap.put("count",count);
        dataMap.put("datas",list);
        return dataMap;
    }

    public Map<String,Object> getAllLampDetailData3(HttpServletRequest request,Integer curpage,Integer showNum,String groupId){
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String, Object>> list2 = monitorServiceMapper.getAllLampDetailData3(orgCode,null,null,null);
        int count=1;
        if(list2!=null){
            count=list2.size();
        }
        Map<String,Object> dataMap = new HashMap<>();
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curpage = DealPage.dealPage(curpage + "",maxPage);
        Integer num = (curpage -1) * showNum;
        List<Map<String, Object>> mapList = monitorServiceMapper.getAllLampDetailData3(orgCode,num, showNum, groupId);
        dataMap.put("count",count);
        dataMap.put("datas",mapList);

        return dataMap;
    }

    public HashMap<String, Object> getLongitudeAndlatitude(HashMap<String,Object> dataMap){
        String dataType = dataMap.get("dataType").toString();
        String dataId = dataMap.get("dataId").toString();
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("areaId",null);
        sqlMap.put("roadId",null);
        sqlMap.put("lineId",null);
        sqlMap.put("orgCode",dataMap.get("orgCode"));
        if("area".equals(dataType)){
            sqlMap.put("areaId",dataId);
        } else if("road".equals(dataType)) {
            sqlMap.put("roadId",dataId);
        } else if("line".equals(dataType)) {
            sqlMap.put("lineId",dataId);
        }

        return monitorServiceMapper.getLongitudeAndlatitude(sqlMap);
    }

}
