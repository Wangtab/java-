package com.lamp.service.impl;

import com.lamp.common.MonitorCommon;
import com.lamp.common.StrategyCommon;
import com.lamp.dao.*;
import com.lamp.model.sjkhf;
import com.lamp.service.IMonitorService;
import com.lamp.service.TimedTaskService;
import com.lamp.utils.CommonMethod;
import com.lamp.utils.LampSwitchInterface;
import com.lamp.utils.PlatformUtils;
import com.lamp.utils.TimeUntils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TimedTaskServiceImpl implements TimedTaskService {

    @Autowired
    private TMonitorServiceMapper tMonitorServiceMapper;

    @Autowired
    private TBaseDeviceMapper tBaseDeviceMapper;

    @Autowired
    private TScreenMapper tScreenMapper;

    @Autowired
    private TSafeManageMapper tSafeManageMapper;

    @Autowired
    private TPlanManageMapper tPlanManageMapper;

    @Autowired
    private IMonitorService monitorService;

    /**
     *  NB设备数据采集
     */
    //@Scheduled(cron = "0 0/20 * * * ?")
    public void getNBlOTDevicedata(){

        List<Map<String, Object>> list =  tMonitorServiceMapper.getNBlOTDevicedata();

        if(PlatformUtils.isEmptyList(list)){
            return;
        }

        List<Map<String,Object>> dataList = new ArrayList<>();
        List<Map<String,Object>> warnList = new ArrayList<>();
        Integer maxNum = tMonitorServiceMapper.getCollectDataMaxNum();
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        for(Map<String,Object> map : list){
            String nbDeviceId = map.get("nb_device").toString();
            Integer controllerKind = Integer.parseInt(map.get("controllerkindid").toString());
            Integer bussiness = Integer.parseInt(map.get("business").toString());
            Integer protocol = Integer.parseInt(map.get("protocol").toString());
            if(MonitorCommon.CONTROLLER_TYPE_NB_LOT == controllerKind){
                if(MonitorCommon.NB_BUSINESS_CHANYE_YUAN == bussiness){
                    HashMap<String,Object> dataMap =  lampSwitchInterface.getLampDataByNbId(protocol,nbDeviceId);
                    dataMap = checkNBdDataIsError(maxNum,dataMap, warnList,map);
                    batchUpdateNBData(maxNum,dataList, dataMap);
                }
            }
        }

        if(dataList.size() > 0){
            tMonitorServiceMapper.batchUpdatelampData(dataList);
            tMonitorServiceMapper.batchAddTodayRecordData(dataList);
        }

        if(warnList.size() > 0){
            tMonitorServiceMapper.batchAddLampWarnData(warnList);
        }

        tMonitorServiceMapper.dealLampWarn();

    }

    /**
     *  每天2点执行大数据处理
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void dealAndCalculateData(){
        try {
            tScreenMapper.dealTodayRecordData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 每天更新天气
     */     
    @Scheduled(cron = "0 0 8 * * ?")
    public void dealWeatherData(){
        List<Map<String, Object>> list = tBaseDeviceMapper.getCitySettingData();
        if(null == list || list.size() == 0){
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map = list.get(i);
            String longitude = PlatformUtils.isEmptyBackStringEmpty(map.get("longitude"));
            String latitude = PlatformUtils.isEmptyBackStringEmpty(map.get("latitude"));
            String location = longitude + "," + latitude + "";
            HashMap dataMap = PlatformUtils.dealWeatherTemperatureData(location,map.get("id").toString());
            HashMap<String,String> sunTimeMap =  PlatformUtils.getSunRiseAndSet(longitude,latitude);
            dataMap.put("sunrise",sunTimeMap.get("sunrise"));
            dataMap.put("sunset",sunTimeMap.get("sunset"));
            if(dataMap != null && dataMap.size() != 0){
                tBaseDeviceMapper.updateCitySettingTempData(dataMap);
            }
        }
    }


    /**
     * 每隔1分钟查询离线任务
     */
    //@Scheduled(cron = "0 0/1 * * * ?")
    public void startLampTaskWork(HttpServletRequest request){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String nowDate = sdf.format(now);//现在的时间
        try {
            SingleLampTaks(nowDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            groupLampTask(now);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //单灯策略任务
    public void SingleLampTaks(String nowDate) throws ParseException {
        List<Map<String, Object>> list  = tPlanManageMapper.getSingleStrategyTask();
        if(null == list || list.size() == 0){
            return;
        }
        for (Map<String, Object> map : list){
            Integer id = Integer.parseInt(map.get("id").toString());
            String exeTime = map.get("exe_time").toString();
            Integer cid = Integer.parseInt(map.get("cid").toString());
            String contentId =map.get("content_id").toString();
            Integer dimming = Integer.parseInt(map.get("dimming").toString());
            if(!PlatformUtils.compareDateIsEqual(nowDate,exeTime,"HH:mm")){
                continue;
            }
            Integer oper = 0;
            Integer onOff = 0;

            if(StrategyCommon.LAMP_CONTENT_OPEN.equals(contentId)){//开灯
                oper = MonitorCommon.LIGHT_SWITCH_OPERATION;
                onOff = MonitorCommon.LAMP_OPEN;
            } else if(StrategyCommon.LAMP_CONTENT_CLOSE.equals(contentId)){//关灯
                oper = MonitorCommon.LIGHT_SWITCH_OPERATION;
                onOff = MonitorCommon.LAMP_CLOSE;
            } else if(StrategyCommon.LMAP_CONTENT_DIMMING.equals(contentId)){//调光
                oper = MonitorCommon.LIGHT_DIMMING_OPERATION;
                onOff = MonitorCommon.LAMP_OPEN;
            }

            String result = commonLampFlow(cid,onOff,oper,dimming);
            if("0".equals(result)){//处理成功后 更新离线状态
                tPlanManageMapper.updateSingleStrategyTaskStatus(id);
            }
        }
    }

    //分组策略任务
    public void groupLampTask(Date date){
        List<Map<String, Object>> list  = tPlanManageMapper.getGroupStrategyTask();
        if(null == list || list.size() == 0){
            return;
        }
        for (Map<String, Object> map : list){
            Integer planId = Integer.parseInt(map.get("plan_id").toString());
            String sceneId = PlatformUtils.isEmptyBackStringEmpty(map.get("sence_id"));
            if(MonitorCommon.PLAN_CONTENT_SCENE == planId && !StringUtils.isEmpty(sceneId)){//场景
                Integer id = Integer.parseInt(sceneId);
                List<Map<String, Object>> sceneList = tPlanManageMapper.getPlansceneDeatilDataBySceneId(id);
                if(null == sceneList || sceneList.size() == 0){
                    continue;
                }
                for (Map<String, Object> sceneMap : sceneList){
                    commonExecuteGroupTask(sceneMap,date);
                }
            }else{
                commonExecuteGroupTask(map, date);
            }

        }
    }

    private void commonExecuteGroupTask(Map<String, Object> map,Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(date);//现在的时间
        sdf = new SimpleDateFormat("HH:mm");
        String nowTime = sdf.format(date);
        Integer planId = Integer.parseInt(map.get("plan_id").toString());
        String startDate = PlatformUtils.isEmptyBackStringEmpty(map.get("start_date"));
        String endDate = PlatformUtils.isEmptyBackStringEmpty(map.get("end_date"));
        String startTime = PlatformUtils.isEmptyBackStringEmpty(map.get("start_time"));
        String endTime = PlatformUtils.isEmptyBackStringEmpty(map.get("end_time"));
        String dimming = PlatformUtils.isEmptyBackStringEmpty(map.get("dimming"));
        String groupId = PlatformUtils.isEmptyBackStringEmpty(map.get("group_id"));
        String weeks =  PlatformUtils.isEmptyBackStringEmpty(map.get("weeks"));
        int setDimming = 0;
        int setGroupId = 0;
        boolean isExecute = false;
        if (!StringUtils.isEmpty(dimming)) {
            setDimming = Integer.parseInt(dimming);
        }
        if (!StringUtils.isEmpty(groupId)) {
            setGroupId = Integer.parseInt(groupId);
        }
        if (MonitorCommon.PLAN_CONTENT_LAMP_CONTROLLER == planId) {//开关灯
            if (PlatformUtils.compareDateIsEqual(startDate, nowDate, "yyyy-MM-dd") && PlatformUtils.compareDateIsEqual(startTime, nowTime, "HH:mm")) {
                isExecute = true;
            } else if (PlatformUtils.compareDateIsEqual(endDate, nowDate, "yyyy-MM-dd") && PlatformUtils.compareDateIsEqual(endTime, nowTime, "HH:mm")) {
                isExecute = true;
            }
        } else if (MonitorCommon.PLAN_CONTENT_LAMP_DIMMING == planId) {//调光
            if (PlatformUtils.compareDateIsEqual(startDate, nowDate, "yyyy-MM-dd") && PlatformUtils.compareDateIsEqual(startTime, nowTime, "HH:mm")) {
                isExecute = true;
            }
        } else if (MonitorCommon.PLAN_CONTENT_DAYS == planId) {//每天
            if (PlatformUtils.compareDateIsEqual(startTime, nowTime, "HH:mm")) {
                isExecute = true;
            } else if (PlatformUtils.compareDateIsEqual(startTime, nowTime, "HH:mm")) {
                isExecute = true;
            }
        } else if (MonitorCommon.PLAN_CONTENT_WEEKS == planId) {//每周
            if (PlatformUtils.compareWeeksIsEqual(weeks, date)){
                if (PlatformUtils.compareDateIsEqual(startTime, nowTime, "HH:mm")) {
                    isExecute = true;
                } else if (PlatformUtils.compareDateIsEqual(endTime, nowTime, "HH:mm")) {
                    isExecute = true;
                }
            }
        }
        if (isExecute) {
            monitorService.groupSwitchController(setGroupId, 0, MonitorCommon.LIGHT_DIMMING_OPERATION, setDimming);
        }
    }


    /**
     * 比较日期是否在此之内
     * @param startDate
     * @param endDate
     * @param nowDate
     * @return
     */
    private boolean compareDate(String startDate,String endDate,String nowDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            Date now = sdf.parse(nowDate);
            return start.getTime() <= now.getTime() && now.getTime() <= end.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *  通用处理灯具流程 111
     * @param poleId
     * @param onOff
     * @param oper
     * @param dimming
     */
    private String commonLampFlow(Integer poleId,Integer onOff,Integer oper,Integer dimming){
        String result = "-1";
        //下发指令
        try {
            HashMap<String,Object> dataMap = monitorService.singleDeviceController(poleId, onOff, oper, dimming);
            result = dataMap.get("status").toString();
            if("success".equals(result)){
                result = "0";
            }
        } catch (Exception e) {
            e.getStackTrace();
            result = "1";
        }
        return result;
    }


    private void batchUpdateNBData(Integer maxNum,List<Map<String,Object>> dataList,HashMap<String,Object> dataMap){
        dataList.add(dataMap);
        if(dataList.size() >= maxNum){
            try {
                tMonitorServiceMapper.batchUpdatelampData(dataList);
                tMonitorServiceMapper.batchAddTodayRecordData(dataList);
                dataList.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private HashMap<String,Object> checkNBdDataIsError(Integer maxNum,HashMap<String,Object> dataMap,List<Map<String,Object>> warnList,Map<String,Object> paramMap){
        if(null == dataMap || dataMap.size() == 0){
            addWarnInfo(maxNum,dataMap,paramMap, warnList,MonitorCommon.WARN_LAMP_LINK_EXCEPTION);
            dataMap = new HashMap<>();
            dataMap.put("record_vol",null);
            dataMap.put("record_ele",null);
            dataMap.put("record_power",null);
            dataMap.put("record_dimming",null);
            dataMap.put("record_pvol",null);
            dataMap.put("record_pele",null);
            dataMap.put("record_bvol",null);
            dataMap.put("record_temp",null);
            dataMap.put("eventTime",PlatformUtils.getNowTime());
            dataMap.put("conSate",MonitorCommon.NB_LAMP_CONNECT_STATE_EXCEPTION);
            dataMap.put("nbid",paramMap.get("id"));
            dataMap.put("id",PlatformUtils.getUUID());
            dataMap.put("on_off",paramMap.get("on_off"));
            dataMap.put("record_dimming",paramMap.get("dimming"));
            dataMap.put("record_time",PlatformUtils.getNowTime());
            return dataMap;
        }
        //整理数据
        dataMap.put("id",PlatformUtils.getUUID());
        dataMap.put("nbid",paramMap.get("id"));
        dataMap.put("on_off",paramMap.get("on_off"));
        dataMap.put("record_time",PlatformUtils.getNowTime());
        String recordDimming = dataMap.get("record_dimming").toString();
        if("-1".equals(recordDimming)){
            dataMap.put("record_dimming",paramMap.get("dimming"));
        }

        Integer lampSwitch = Integer.parseInt(paramMap.get("on_off").toString());
        Integer protocol = Integer.parseInt(paramMap.get("protocol").toString());
        BigDecimal bigDecimal = new BigDecimal(dataMap.get("record_vol").toString());
        Integer vol = bigDecimal.intValue();
        if(MonitorCommon.LAMP_OPEN == lampSwitch){
            //电压
            if(MonitorCommon.NB_COAP == protocol){
                if(vol < MonitorCommon.WARN_LAMP_LOW_VOL){
                    addWarnInfo(maxNum,dataMap,paramMap, warnList,MonitorCommon.WARN_LAMP_LESS_VOL);
                    dataMap.put("conSate",MonitorCommon.NB_LAMP_CONNECT_STATE_EXCEPTION);
                }else if(vol > MonitorCommon.WARN_LAMP_HEIGHT_VOL){
                    addWarnInfo(maxNum,dataMap,paramMap, warnList,MonitorCommon.WARN_LAMP_OVER_VOL);
                    dataMap.put("conSate",MonitorCommon.NB_LAMP_CONNECT_STATE_EXCEPTION);
                }
            }
            //功率
            Double dimming = Integer.parseInt(paramMap.get("dimming").toString()) * 0.01;
            Double power  = Double.parseDouble(dataMap.get("record_power").toString());
            Double standardPower = Double.parseDouble(paramMap.get("power").toString());
            Double factPower = power * dimming;
            Double allowPower =  standardPower * dimming * 0.8;
            if(factPower < allowPower){
                addWarnInfo(maxNum,dataMap,paramMap, warnList,MonitorCommon.WARN_LMAP_DEVICE_BORKEN);
                dataMap.put("conSate",MonitorCommon.NB_LAMP_CONNECT_STATE_EXCEPTION);
            }
        }
        TimeUntils timeUntils = new TimeUntils();
        //离线
        if(!timeUntils.dealDateIsAllot(dataMap.get("eventTime").toString())){
            dataMap.put("conSate",MonitorCommon.NB_LAMP_CONNECT_STATE_OFFlINE);
        }
        return dataMap;
    }

    private void addWarnInfo(Integer maxNum,HashMap<String,Object> dataMap,Map<String,Object> paramMap,List<Map<String,Object>> warnList,Integer warnType){
        if(null == dataMap || dataMap.size() == 0){
            dataMap = new HashMap<>();
            dataMap.put("record_ele","");
            dataMap.put("record_vol","");
            dataMap.put("record_power","");
            dataMap.put("record_dimming","");
            dataMap.put("record_pvol","");
            dataMap.put("record_pele","");
            dataMap.put("record_bvol","");
            dataMap.put("eventTime","");
            dataMap.put("conSate",MonitorCommon.NB_LAMP_CONNECT_STATE_EXCEPTION);
            dataMap.put("nbid",paramMap.get("id"));
        }
        HashMap<String,Object> warnMap = new HashMap<>();
        warnMap.put("nb_device",paramMap.get("id"));
        warnMap.put("content",warnType);
        warnList.add(warnMap);
        if(warnList.size() >= maxNum){
            try {
                tMonitorServiceMapper.batchAddLampWarnData(warnList);
                warnList.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void backupTask(){
        Date nowDate = new Date();
        List<Map<String, Object>> list =  tSafeManageMapper.getDataBaseStrategyForTask();
        if(PlatformUtils.isEmptyList(list)){
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String nowTime = sdf.format(nowDate);
        for (Map<String,Object> map : list) {
            boolean isExecute = false;
            int exeType = Integer.parseInt(map.get("zxname").toString());
            String exeTime = map.get("zxtime").toString();
            Integer userId = Integer.parseInt(map.get("operid").toString());
            if (1 == exeType && compareMonthIsEqual(nowDate) && PlatformUtils.compareDateIsEqual(exeTime, nowTime, "HH:mm")) {//每月1号
                isExecute = true;
            } else if (2 == exeType && PlatformUtils.compareWeeksIsEqual("1", nowDate) && PlatformUtils.compareDateIsEqual(exeTime, nowTime, "HH:mm")) {//每周周一
                isExecute = true;
            } else if (3 == exeType && PlatformUtils.compareDateIsEqual(exeTime, nowTime, "HH:mm")) {//每天
                isExecute = true;
            }

            if (isExecute) {
                String sqlName = new LampSwitchInterface().getUUID();
                sqlName = sqlName + ".sql";
                sjkhf t = new sjkhf();
                if(new CommonMethod().backupDataBase(sqlName)){
                   sdf =  new SimpleDateFormat( "yyyyMMddHHmmss");
                   String date = sdf.format(new Date());
                   t.setSjkname(date);
                   t.setSjkaddress(sqlName);
                   t.setAddtime(PlatformUtils.getNowTime());
                   t.setOperid(userId);
                   t.setCztime(PlatformUtils.getNowTime());
                   t.setInfo("自动备份");
                   tSafeManageMapper.addDataBaseData(t);
                }
            }
        }
    }

    /**
     *  判断今天是否是本月1号
     */
    private boolean compareMonthIsEqual(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        int month =c.get(Calendar.DAY_OF_MONTH);
        if(1 == month){
            return true;
        }
        return false;
    }
}
