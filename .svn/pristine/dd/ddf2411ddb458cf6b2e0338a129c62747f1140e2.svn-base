package com.lamp.service.impl;

import com.huawei.utils.HuaWeiMethod;
import com.lamp.common.*;
import com.lamp.dao.TBaseDeviceMapper;
import com.lamp.dao.TPlatformSettingMapper;
import com.lamp.model.*;
import com.lamp.service.IBaseDeviceService;
import com.lamp.service.LampCommonService;
import com.lamp.service.TUserOperationService;
import com.lamp.utils.DealPage;
import com.lamp.utils.GetLocalTimes;
import com.lamp.utils.LampSwitchInterface;
import com.lamp.utils.PlatformUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class BaseDeviceServiceImpl implements IBaseDeviceService {

    @Autowired
    private TBaseDeviceMapper tBaseDeviceMapper;

    @Autowired
    private TUserOperationService tUserOperationService;

    @Autowired
    private LampCommonService lampCommonService;

    @Autowired
    private TPlatformSettingMapper tPlatformSettingMapper;

    @Override
    public  Map<String,Object> getAreaManageData(HashMap<String,Object> sqlMap){
        int count = tBaseDeviceMapper.getCountAreaManageData(sqlMap);
        PlatformUtils.dealPageData(sqlMap,count);
        List<Map<String, Object>> list = tBaseDeviceMapper.getAreaManageData(sqlMap);
        return PlatformUtils.backMapData(count, list);
    }

    @Override
    public List<Map<String, Object>> getOrganizationList() {
        return tBaseDeviceMapper.getOrganizationList();
    }

    @Override
    public int saveAreaMangeData(Tareamanage ta) {
        return tBaseDeviceMapper.saveAreaMangeData(ta);
    }

    @Override
    public int updateAreaManageData(Tareamanage ta) {
        return tBaseDeviceMapper.updateAreaManageData(ta);
    }

    @Override
    public List<Map<String, Object>> getAreaManageDataById(Integer areaId) {
        return tBaseDeviceMapper.getAreaManageDataById(areaId);
    }

    @Override
    public int delteAreaMangeDataById(Integer areaId) {
        return tBaseDeviceMapper.delteAreaMangeDataById(areaId);
    }

    @Override
    public  Map<String,Object> getRoadManageData(String roadName, Integer showNum, Integer curpage) {
        Map<String,Object> dataMap = new HashMap<>();
        int count = tBaseDeviceMapper.getCountRoadManageData(roadName);
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curpage = DealPage.dealPage(curpage + "",maxPage);
        Integer nums = (curpage -1) * showNum;
        List<Map<String, Object>> list = tBaseDeviceMapper.getRoadManageData(roadName, nums, showNum);
        dataMap.put("count",count);
        dataMap.put("datas", list);
        return dataMap;
    }
    @Override
    public Map<String,Object> getRoadManageDataByOrgCode(HashMap<String,Object> paramMap){
        int count = tBaseDeviceMapper.getCountAreaManageData(paramMap);
        PlatformUtils.dealPageData(paramMap,count);
        List<Map<String, Object>> list = tBaseDeviceMapper.getRoadManageDataByOrgCode(paramMap);
        return PlatformUtils.backMapData(count, list);
    }

    @Override
    public int saveRoadNameData(Troadmanage td) {
        return tBaseDeviceMapper.saveRoadNameData(td);
    }

    @Override
    public int updateRoadNameData(Troadmanage td) {
        return tBaseDeviceMapper.updateRoadNameData(td);
    }

    @Override
    public List<Map<String, Object>> getRoadNameManageById(Integer id) {
        return tBaseDeviceMapper.getRoadNameManageById(id);
    }

    @Override
    public int deleteRoadManageDataById(Integer id) {
        return tBaseDeviceMapper.deleteRoadManageDataById(id);
    }

    @Transactional
    public String ImportControllerData(String data,Integer userId){
        List<String> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);
        int len = jsonArray.length();
        if(len == 1){
            list.add("文件内容为空 无法解析");
            return null;
        }
        List<Map<String, Object>> controllerKindList = null;//获取控制器类型
        List<Map<String, Object>> concentratorList = null;//获取集中器
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0;i < jsonArray.length(); i++){
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            String nbcode = jsonObj.get(ConTrollerCommon.DEVICE_CODE).toString();
            String controllerType = jsonObj.get(ConTrollerCommon.CONTROLLER_TYPE).toString();
            String cNum = jsonObj.get(ConTrollerCommon.CONTROLLER_NUM).toString();
            String factoryName = jsonObj.get(ConTrollerCommon.FACTORY_NAME).toString();
            String concentratorName = jsonObj.get(ConTrollerCommon.CONCENTRATOR_NAME).toString();
            String bussiness = jsonObj.get(ConTrollerCommon.BUSINESS).toString();
            String protocol = jsonObj.get(ConTrollerCommon.PROTOCOL).toString();
            String simCode = jsonObj.get(ConTrollerCommon.SIM_CODE).toString();
            controllerType = PlatformUtils.getListDataByKey(controllerType,"kindname","id",controllerKindList);
            concentratorName = PlatformUtils.getListDataByKey(concentratorName,"concentratorname","id",concentratorList);
            HashMap<String,Object> dataMap = new HashMap<>();
            dataMap.put("nbcode",nbcode);
            dataMap.put("controllerType",controllerType);
            dataMap.put("cNum",cNum);
            dataMap.put("factoryName",factoryName);
            dataMap.put("concentratorName",concentratorName);
            dataMap.put("bussiness",bussiness);
            dataMap.put("protocol",protocol);
            dataMap.put("simCode",simCode);
            dataMap.put("createby",userId);
            dataMap.put("createTime",PlatformUtils.getNowTime());
            dataMap.put("operator",userId);
            dataMap.put("opertime", PlatformUtils.getNowTime());
            dataList.add(dataMap);
        }
        tBaseDeviceMapper.importControlerData(dataList);
        return "ok";
    }

    public String ImportDeviceData(String data,Integer userId){
        List<String> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);
        int len = jsonArray.length();
        if(len == 1){
            list.add("文件内容为空 无法解析");
            return null;
        }
        List<Map<String,Object>> dimmingModelList = lampCommonService.getDimmingModelForSelect();

        List<Map<String,Object>> dataList = new ArrayList<>();
        for (int i = 0;i < jsonArray.length(); i++){
            JSONObject jb = jsonArray.getJSONObject(i);
            String areaName = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.AREA_NAME);
            String areaDesc = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.AREA_DESC);
            String roadName = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.ROAD_NAME);
            String roadDesc = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.ROAD_DESC);
            String roadLineName = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.LINE_NAME);

            String lampType = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.LAMP_TYPE);
            String lampTypeDesc = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.LAMP_TYPE_DESC);
            String lampTypeModel = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.LAMP_TYPE_MODEL);
            String lampTypePower = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.LAMP_TYPE_POWER);
            String lampTypeFactory = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.LAMP_TYPE_FACTORY);
            String lampTypeDimmingModel = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.LAMP_TYPE_DIMMING_MODEL);
            String lampTypeSunPower = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.LAMP_TYPE_SUM_POWER);
            String lampTypeCeilPower = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.LAMP_TYPE_CEIL_POWER);
            Integer lampTypeInteger = 1;
            if(DeviceColumnCommon.SUN_LAMP_TYPE_STRING.equals(lampType)){
                lampTypeInteger = DeviceColumnCommon.SUN_LAMP_TYPE_INTEGER;
            }

            for (Map<String,Object> map : dimmingModelList){
                String model = map.get("cname").toString();
                if(model.equals(lampTypeDimmingModel)){
                    lampTypeDimmingModel = map.get("id").toString();
                    break;
                }
            }

            String eleBoxName =  BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.ELE_BOX_NAME);
            String eleBoxLa =  BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.ELE_BOX_LA);
            String eleBoxLo = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.ELE_BOX_LO);

            String ammeterAddress = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.AMMTER_ADDRESS);
            String ammeterName = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.AMMTER_NAME);
            String ammeterLoop = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.AMMTER_ELE_BOX_LOOP);
            String ammeterCommunication = BackJSONObjectKeyOrBackNull(jb,DeviceColumnCommon.AMMTER_COMMUNICATION);

            HashMap<String,Object> dataMap = new HashMap<>();
            dataMap.put("areaName",areaName);
            dataMap.put("areaDesc",areaDesc);
            dataMap.put("roadName",roadName);
            dataMap.put("roadDesc",roadDesc);
            dataMap.put("roadLineName",roadLineName);

            dataMap.put("lampType",lampTypeInteger);
            dataMap.put("lampTypeDesc",lampTypeDesc);
            dataMap.put("lampTypeModel",lampTypeModel);
            dataMap.put("lampTypePower",lampTypePower);
            dataMap.put("lampTypeFactory",lampTypeFactory);
            dataMap.put("lampTypeDimmingModel",lampTypeDimmingModel);
            dataMap.put("lampTypeSunPower",lampTypeSunPower);
            dataMap.put("lampTypeCeilPower",lampTypeCeilPower);

            dataMap.put("eleBoxName",eleBoxName);
            dataMap.put("eleBoxLa",eleBoxLa);
            dataMap.put("eleBoxLo",eleBoxLo);

            dataMap.put("ammeterAddress",ammeterAddress);
            dataMap.put("ammeterName",ammeterName);
            dataMap.put("ammeterLoop",ammeterLoop);
            dataMap.put("ammeterCommunication",ammeterCommunication);

            dataMap.put("userId",userId);
            dataList.add(dataMap);
        }

        tBaseDeviceMapper.ImportDeviceData(dataList);
        return null;
    }


    @Override
    public Map<String, Object> getAmmeterList(HashMap<String, Object> dataMap){
        int count = tBaseDeviceMapper.getCountAmmeterNum(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = tBaseDeviceMapper.getAmmeterList(dataMap);
        return PlatformUtils.backMapData(count, list);
    }

    @Override
    public int insertAmmeter(Tammeter t) {
        return tBaseDeviceMapper.insertAmmeter(t);
    }

    @Override
    public int updateAmmeterSelective(Tammeter t) {
        return tBaseDeviceMapper.updateAmmeterSelective(t);
    }

    @Override
    public Map<String, Object> getAmmeterDataById(Integer id) {
        return tBaseDeviceMapper.getAmmeterDataById(id);
    }

    @Override
    public int deleteAmmeterDataById(Integer id) {
        return tBaseDeviceMapper.deleteAmmeterDataById(id);
    }

    @Override
    public Map<String, Object> getLampTypeList(HashMap<String, Object> dataMap) {
        int count = tBaseDeviceMapper.getCountLampTypeList(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = tBaseDeviceMapper.getLampTypeList(dataMap);
        return PlatformUtils.backMapData(count, list);
    }

    @Override
    public HashMap<String, Object> getLampTypeDataById(Integer id) {
        return tBaseDeviceMapper.getLampTypeDataById(id);
    }

    @Override
    public int delLampTypeDataById(Integer id) {
        return tBaseDeviceMapper.delLampTypeDataById(id);
    }

    @Override
    public int addLampTypeData(Tlamptype t) {
        return tBaseDeviceMapper.addLampTypeData(t);
    }

    @Override
    public int updateLampTypeData(Tlamptype t) {
        return tBaseDeviceMapper.updateLampTypeData(t);
    }

    @Override
    public int checkLampTypeModel(HashMap<String, Object> dataMap) {
        return tBaseDeviceMapper.checkLampTypeModel(dataMap);
    }

    @Override
    public Map<String, Object> getLampManageData(HashMap<String, Object> dataMap) {
        int count = tBaseDeviceMapper.getCountLampManageData(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = tBaseDeviceMapper.getLampManageData(dataMap);
        return PlatformUtils.backMapData(count, list);
    }

    @Override
    public HashMap<String, Object> getLampManageDataById(Integer id) {
        return tBaseDeviceMapper.getLampManageDataById(id);
    }

    @Override
    public int delLampManageDataById(Integer id) {
        return tBaseDeviceMapper.delLampManageDataById(id);
    }

    @Override
    public int addLampManageData(Tlamp t) {
        return tBaseDeviceMapper.addLampManageData(t);
    }

    @Override
    public int updateLampManageData(Tlamp t) {
        return tBaseDeviceMapper.updateLampManageData(t);
    }

    /**
     *  验证 JSONObject 对象是否有该键值对 没有返回空值
     * @param jb
     * @param key
     */
    private String BackJSONObjectKeyOrBackNull(JSONObject jb, String key){
        if(jb.has(key)){
            return jb.getString(key);
        }
        return null;
    }

    @Transactional
    @Override
    public String ImportConcentratorData(String data,String orgCode){
        List<String> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);
        int len = jsonArray.length();
        if(len == 1){
            list.add("文件内容为空 无法解析");
            return null;
        }
        List<Map<String, Object>> areaList =  lampCommonService.getAreaNameForSelect(orgCode);//区域
        List<Map<String, Object>> concenKindList = tBaseDeviceMapper.getConcentratorKindData();//获取集中器类型
        List<Map<String, Object>> userList = tBaseDeviceMapper.getUserManageData();//用户
        List<Map<String, Object>> eleboxList = lampCommonService.getDistributionBoxForSelect(orgCode);//获取配电箱


        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0;i < jsonArray.length(); i++){
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            String address = jsonObj.get(ConcentratorCommon.ADDRESS).toString();
            String type = jsonObj.get(ConcentratorCommon.TYPE).toString();
            String name = jsonObj.get(ConcentratorCommon.NAME).toString();
            String concentrator = jsonObj.get(ConcentratorCommon.DES).toString();
            String areaName = jsonObj.get(ConcentratorCommon.AREA_NAME).toString();
            String ip = jsonObj.get(ConcentratorCommon.IP).toString();
            String maskCode = jsonObj.get(ConcentratorCommon.MASK_CODE).toString();
            String defaultGateway = jsonObj.get(ConcentratorCommon.DEFAULT_GATEWAY).toString();
            String serviceAddress = jsonObj.get(ConcentratorCommon.SERVICE_ADDRESS).toString();
            String servicePort = jsonObj.get(ConcentratorCommon.SERVICE_PORT).toString();
            String lo = jsonObj.get(ConcentratorCommon.LO).toString();
            String la = jsonObj.get(ConcentratorCommon.LA).toString();
            String user = jsonObj.get(ConcentratorCommon.USER_NAME).toString();
            String eleBox = jsonObj.get(ConcentratorCommon.ELE_BOX).toString();

            user = getUserByNum(user,userList);
            type = getConcentratorKindByNum(type,concenKindList);
            areaName = getAreaByNum(areaName,areaList);
            eleBox = geteleBoxByNum(eleBox,eleboxList);

            HashMap<String,Object> dataMap = new HashMap<>();
            dataMap.put("concentratoraddr",address);
            dataMap.put("concentratorname",name);
            dataMap.put("concentratorkindid",type);
            dataMap.put("concentratordes",concentrator);
            dataMap.put("areaid",areaName);
            dataMap.put("ip",ip);
            dataMap.put("subnetmask",maskCode);
            dataMap.put("defaultgateway",defaultGateway);
            dataMap.put("serverip",serviceAddress);
            dataMap.put("serverport",servicePort);
            dataMap.put("la",la);
            dataMap.put("lo",lo);
            dataMap.put("operator", user);
            dataMap.put("opertime",GetLocalTimes.getNowTime());
            dataMap.put("ibox",eleBox);
            dataList.add(dataMap);
        }
        tBaseDeviceMapper.importConcentratorData(dataList);
        return "ok";
    }

    @Override
    public Map<String,Object> getEleBoxData(HashMap<String,Object> dataMap) {
        int count = tBaseDeviceMapper.getCountEleBoxData(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = tBaseDeviceMapper.geteleBoxData(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public int getCounteleBoxData(String cname) {
        return 0;
    }

    @Override
    public int addeleBoxData(Telecboxmanage tb) {
        return tBaseDeviceMapper.addeleBoxData(tb);
    }

    @Override
    public int updateeleBoxData(Telecboxmanage tb) {
        return tBaseDeviceMapper.updateeleBoxData(tb);
    }

    @Override
    public List<Map<String, Object>> geteleBoxDataById(Integer id) {
        return tBaseDeviceMapper.geteleBoxDataById(id);
    }

    @Override
    public int delEleBoxDataById(Integer id) {
        return tBaseDeviceMapper.delEleBoxDataById(id);
    }

    @Override
    public int checkDelAreaById(Integer id) {
        return tBaseDeviceMapper.checkDelAreaById(id);
    }

    @Override
    public int checkDelRoadById(Integer id) {
        return tBaseDeviceMapper.checkDelRoadById(id);
    }

    private String getAreaByNum(String num,List<Map<String, Object>> areaList){
        for (Map<String, Object> map : areaList) {
            String poleNum = map.get("areaName").toString();
            if(num.equals(poleNum)){
                return map.get("id").toString();
            }
        }
        return null;
    }

    private String geteleBoxByNum(String num,List<Map<String, Object>> list){
        for (Map<String, Object> map : list) {
            String poleNum = map.get("name").toString();
            if(num.equals(poleNum)){
                return map.get("id").toString();
            }
        }
        return null;
    }

    private String getConcentratorKindByNum(String num,List<Map<String, Object>> concenKindList){
        for (Map<String, Object> map : concenKindList) {
            String poleNum = map.get("kindname").toString();
            if(num.equals(poleNum)){
                return map.get("id").toString();
            }
        }
        return null;
    }

    private String getUserByNum(String num,List<Map<String, Object>> userList){
        for (Map<String, Object> map : userList) {
            String poleNum = map.get("user_name").toString();
            if(num.equals(poleNum)){
                return map.get("id").toString();
            }
        }
        return null;
    }

    @Override
    public HashMap<String,String> saveData(Tcontroller tt, HttpServletRequest request){
        Tsysuser user =  PlatformUtils.getLoginUser(request);
        HashMap<String,String> resultMap = null;
        int result = 0;
        if(tt.getId() == null){
            resultMap = dealIOTData(request,tt);
            if(!SystemOperationCommon.OPERATION_SUCCESS.equals(resultMap.get("operation"))){
                return resultMap;
            }
            //1.添加 nb_manage 信息
            Tnbmanage tnbmanage = new Tnbmanage();
            tnbmanage.setNbDevice(resultMap.get("nbDeviceId"));
            tnbmanage.setNbCode(tt.getNbCode());
            tnbmanage.setOperId(tt.getOperator());
            tnbmanage.setOperTime(PlatformUtils.getNowTime());
            tBaseDeviceMapper.addNbLotManageData(tnbmanage);
            //2. 添加默认实时状态
            addCommonInfo(tt.getNbDeviceId(),tt.getProtocol(),tnbmanage.getId());
            tt.setNbDeviceId(resultMap.get("nbDeviceId"));
            tt.setCreateby(user.getId());
            tt.setCreateTime(PlatformUtils.getNowTime());
            //3.添加控制器信息
            tt.setNBManageId(tnbmanage.getId());
            result =  tBaseDeviceMapper.addControllerData(tt);
            if(result>0){
                //tUserOperationService.recordOperationData(tt,null,user.getId(), SystemOperationCommon.OPERATION_ADD,result, MenuModelCommon.CONTROLLER_MANAGE);
            }
        }else{
            Object tt2 = tBaseDeviceMapper.getCOntrollerDataById(tt.getId()).get(0);
            result = tBaseDeviceMapper.updateControllerData(tt);
            if(result>0) {
                tUserOperationService.recordOperationData(tt, tt2, user.getId(), SystemOperationCommon.OPERATION_UPDATE, result, MenuModelCommon.CONTROLLER_MANAGE);
            }
        }
        return resultMap;
    }

    public HashMap<String,String> dealIOTData(HttpServletRequest request,Tcontroller tc){
        HashMap<String,String> dataMap = null;
        //设备类型为NB设备
        if(MonitorCommon.CONTROLLER_TYPE_NB_LOT == tc.getControllerkindid()){
            if(MonitorCommon.NB_BUSINESS_CHANYE_YUAN == tc.getBusiness()){//产业院
                dataMap = dealNBDevice(tc);
            }else if(MonitorCommon.NB_BUSINESS_DIAN_XIN == tc.getBusiness()){ //电信
                dataMap = dealDianXiNBDevice(request,tc);
            }
        }
        return dataMap;
    }

    private HashMap<String,String> dealDianXiNBDevice(HttpServletRequest request,Tcontroller tc){
        HashMap<String,String> resultMap = new HashMap<>();
        resultMap.put("operation",SystemOperationCommon.OPERATION_FAIL);
        resultMap.put("error","该设备已注册,请勿反复注册");
        //1.查看数据库中是否有该条数据
        int num =  checkHashNBCode(tc.getNbCode());
        if(num > 1){
            return resultMap;
        }
        //单个灯具IOT平台
        Tsysuser user =  PlatformUtils.getLoginUser(request);
        //查出IOT授权
        Map<String,Object> map = tPlatformSettingMapper.getPlatData(user.getId());
        //获取token
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
        //注册设备参数
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("verifyCode",tc.getSimCode());
        map1.put("nodeId",tc.getSimCode());
        map1.put("timeout", new Date().getTime());
        map1.put("name",tc.getcNum());
        map1.put("manufacturerId","ChanYanYuan");
        map1.put("manufacturerName", "ChanYanYuan");
        map1.put("deviceType", "SolarLight");
        map1.put("model", "NBIoTDevice");
        map1.put("protocolType", "CoAP");
        map1.put("appId", map.get("appId").toString());
        map1.put("accessToken", accessToken);

        Map<String,String> stringMap = HuaWeiMethod.RegisterDirectlyConnectedDevice(map1);

        if(stringMap.get("deviceId") != null){
            resultMap.put("operation",SystemOperationCommon.OPERATION_FAIL);
            resultMap.put("error","注册失败，请稍后尝试");
        }else{
            resultMap.put("operation",SystemOperationCommon.OPERATION_SUCCESS);
            resultMap.put("nbDeviceId",stringMap.get("deviceId").toString());
        }
        return resultMap;
    }

    /**
     * 验证NB设备 如果成功返回 nb_deviceId
     */
    private HashMap<String,String> dealNBDevice(Tcontroller tc){
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        HashMap<String,String> resultMap = new HashMap<>();
        resultMap.put("operation",SystemOperationCommon.OPERATION_FAIL);
        resultMap.put("error","该设备已注册,请勿反复注册");
        //1.查看数据库中是否有该条数据
        int num =  checkHashNBCode(tc.getNbCode());
        if(num > 1){
            return resultMap;
        }
        //2.UDP协议
        if(MonitorCommon.NB_UDP == tc.getProtocol()){
            //2.1 获取UDP协议数据
            HashMap<String,Object> dataMap = lampSwitchInterface.getLampDataByNbId(MonitorCommon.NB_UDP,tc.getNbCode());
            // 2.2 可以正常获取该数值
            if(dataMap.size() > 0){
                resultMap.put("operation",SystemOperationCommon.OPERATION_SUCCESS);
                resultMap.put("nbDeviceId",tc.getNbCode());
                return resultMap;
            }
            // 注册失败 不能获取数据
            else {
                resultMap.put("operation",SystemOperationCommon.OPERATION_FAIL);
                resultMap.put("error","不能获取数据");
                return resultMap;
            }
        }
        //3.COAP协议
        else if(MonitorCommon.NB_COAP == tc.getProtocol()){
            //3.1 注册NB设备
            String data = lampSwitchInterface.registerCoapForIot(tc.getNbCode());
            // 注册失败 (IOT平台连接不到相应的数据)
            if(null == data){
                resultMap.put("operation",SystemOperationCommon.OPERATION_FAIL);
                resultMap.put("error","产业院IOT平台设备注册失败,请稍后尝试");
                return resultMap;
            }
            //注册失败 (输入的设备编号不合法)
            else if(MonitorCommon.CHAN_YE_YUAN_IOT_ERROR_NBCODE_ERROR.equals(data)){
                resultMap.put("operation",SystemOperationCommon.OPERATION_FAIL);
                resultMap.put("error","您输入的设备号不合法");
                return resultMap;
            }
            //注册成功
            resultMap.put("operation",SystemOperationCommon.OPERATION_SUCCESS);
            resultMap.put("nbDeviceId",data);
            return resultMap;
        }
        return resultMap;
    }

    /**
     * 验证数据库中是否已经存在该设备编号
     * @param nbCode
     * @return
     */
    private int checkHashNBCode(String nbCode){
        return tBaseDeviceMapper.hasNbCode(nbCode);
    }

    private HashMap<String,String> dealDianXiDevice(HttpServletRequest request,String nbCode){
        HashMap<String,String> resultMap = new HashMap<>();
        resultMap.put("operation","fail");
        resultMap.put("error","hasNbCode");
        int num =  checkHashNBCode(nbCode);
        if(num > 1){
            return resultMap;
        }
        //获取控制器电信sim卡号
        Tcontroller tc = tPlatformSettingMapper.getTcontroller(nbCode);
        try {
            //单个灯具IOT平台
            Tsysuser user =  PlatformUtils.getLoginUser(request);
            //查出IOT授权
            Map<String,Object> map = tPlatformSettingMapper.getPlatData(user.getId());
            //获取token
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
            //注册设备参数
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("verifyCode",tc.getSimCode());
            map1.put("nodeId",tc.getSimCode());
            map1.put("timeout", new Date().getTime());
            map1.put("name",tc.getcNum());
            map1.put("manufacturerId","ChanYanYuan");
            map1.put("manufacturerName", "ChanYanYuan");
            map1.put("deviceType", "SolarLight");
            map1.put("model", "NBIoTDevice");
            map1.put("protocolType", "CoAP");
            map1.put("appId", map.get("appId").toString());
            map1.put("accessToken", accessToken);

            Map<String,String> stringMap = HuaWeiMethod.RegisterDirectlyConnectedDevice(map1);
            resultMap.put("nbDeviceId",stringMap.get("deviceId").toString());
            resultMap.put("operation",SystemOperationCommon.OPERATION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    private void addCommonInfo(String deviceId,Integer protocol,Integer nbManageId){
        HashMap<String,Object> sqlMap = new HashMap<>();
        //通用信息
        sqlMap.put("nb_device",nbManageId);
        sqlMap.put("networking_state","已联网");
        sqlMap.put("vol",0);
        sqlMap.put("ele",0);
        sqlMap.put("power",0);
        sqlMap.put("dimming",0);//调光值为零
        sqlMap.put("onOff",MonitorCommon.LAMP_CLOSE);//关灯
        sqlMap.put("conn_state",MonitorCommon.NB_LAMP_CONNECT_STATE_ONlINE);//在线
        sqlMap.put("hour",0);
        sqlMap.put("signal",0);
        sqlMap.put("nbId",nbManageId);
        sqlMap.put("record_time",PlatformUtils.getNowTime());
        sqlMap.put("pVol",0);
        sqlMap.put("pele",0);
        sqlMap.put("bVol",0);
        sqlMap.put("temp",0);
        tBaseDeviceMapper.addRealNBData(sqlMap);
    }

    @Override
    public int saveConcentratorData(Tconcentrator tt, HttpServletRequest request){
        int result = 0;
       // Object tt2= tBaseDeviceMapper.getConcentratorDataById(tt.getId()).get(0);
        Tsysuser user = (Tsysuser)request.getSession().getAttribute("loginUser");
        if(tt.getId() == null){
            tt.setCreateby(user.getId());
            tt.setCreateTime(tt.getOpertime());
            result = tBaseDeviceMapper.addConcentratorData(tt);
          //  tUserOperationService.recordOperationData(tt,null,user.getId(), SystemOperationCommon.OPERATION_ADD,result, MenuModelCommon.CENTRALIER_MANAGE);
        }else {
            result = tBaseDeviceMapper.updateConcentratorData(tt);
           // tUserOperationService.recordOperationData(tt,tt2,user.getId(), SystemOperationCommon.OPERATION_UPDATE,result, MenuModelCommon.CENTRALIER_MANAGE);
        }
        return result;
    }





    @Override
    public List<Map<String, Object>> getCOntrollerDataById(Integer id) {
        return tBaseDeviceMapper.getCOntrollerDataById(id);
    }

    public List<Map<String, Object>> getConcentratorDataById(Integer id){
        return tBaseDeviceMapper.getConcentratorDataById(id);
    }

    @Override
    public int deleteControllerById(HttpServletRequest request,Integer id) {
        Integer status = 0;
        HashMap<String,Object> dataMap = tBaseDeviceMapper.getNbInfoByControllerId(id);
        Integer kind = Integer.parseInt(dataMap.get("controllerkindid").toString());
        Integer business = Integer.parseInt(dataMap.get("business").toString());
        Integer nbId = Integer.parseInt(dataMap.get("id").toString());
        Integer protocol = Integer.parseInt(dataMap.get("protocol").toString());
        String nbDeviceId = dataMap.get("nb_device").toString();
        boolean isOk = false;
        //设备类型为NB设备
        if(MonitorCommon.CONTROLLER_TYPE_NB_LOT == kind){
            if(MonitorCommon.NB_BUSINESS_CHANYE_YUAN == business){//产业院
                if(MonitorCommon.NB_UDP == protocol){
                    isOk = true;
                }else if(MonitorCommon.NB_COAP == protocol){
                    isOk = new LampSwitchInterface().deleteCoapForIot(nbDeviceId);
                }
            } else if(MonitorCommon.NB_BUSINESS_DIAN_XIN == business){ //电信
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
                //删除平台设备
                Map<String,Object> map1 = new HashMap<String,Object>();
                map1.put("appId",map.get("appid").toString());
                map1.put("accessToken",accessToken);
                map1.put("deviceId",map.get("deviceId").toString());
                String data = HuaWeiMethod.DeleteDirectlyConnectedDevice(map1);
                if(data != null){
                    isOk = true;
                }
            }
        }
        if(isOk){
            tBaseDeviceMapper.deleteNbManageById(nbId);
            tBaseDeviceMapper.deleteRealNbDataById(nbId);
            status =  tBaseDeviceMapper.deleteControllerById(id);
        }
        return status;
    }

    public int deleteConcentratorData(Integer id) {
        return tBaseDeviceMapper.deleteConcentratorData(id);
    }

    @Override
    public Map<String,Object> getControllerData(HashMap<String,Object> paramMap){
        int count = tBaseDeviceMapper.getCountControllerData(paramMap);
        PlatformUtils.dealPageData(paramMap,count);
        List<Map<String, Object>> list = tBaseDeviceMapper.getControllerData(paramMap);
        return PlatformUtils.backMapData(count,list);
    }

    public Map<String,Object> getConcentratorListData(HashMap<String,Object> sqlMap){
        int count = tBaseDeviceMapper.getCountConcentratorNum(sqlMap);
        PlatformUtils.dealPageData(sqlMap,count);
        List<Map<String, Object>> list = tBaseDeviceMapper.getConcentratorListData(sqlMap);
        return PlatformUtils.backMapData(count,list);
    }

    public Map<String,Object> getLampWarnningData(Integer showNum, Integer curpage,Integer areaId,Integer roadId,Integer lineId,Integer lampId,
                                                  String startDate,String endDate,String orgCode,String orderBy,String sort){
        Map<String,Object> dataMap = new HashMap<>();

        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("areaId",areaId);
        sqlMap.put("roadId",roadId);
        sqlMap.put("lampId",lampId);
        sqlMap.put("lineId",lineId);
        sqlMap.put("startDate",startDate);
        sqlMap.put("endDate",endDate);
        sqlMap.put("orgCode",orgCode);
        sqlMap.put("orderBy",orderBy);
        sqlMap.put("sort",sort);
        int count = tBaseDeviceMapper.getCountLampWarnningData(sqlMap);
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curpage = DealPage.dealPage(curpage + "",maxPage);
        Integer nums = (curpage -1) * showNum;
        sqlMap.put("num",nums);
        sqlMap.put("showNum",showNum);
        List<Map<String, Object>> list = tBaseDeviceMapper.getLampWarnningData(sqlMap);
        List<Map<String, Object>> orderList = tBaseDeviceMapper.getdealingBuillingInfo();
        dataMap.put("count",count);
        dataMap.put("datas",list);
        dataMap.put("orderList",orderList);
        return dataMap;
    }

    public List<Map<String, Object>> getExportWarnData(Map<String,Object> dataMap){
        return tBaseDeviceMapper.getExportWarnData(dataMap);
    }

    @Override
    public List<Map<String, Object>> getSendBuildingDataByLampId(Integer id) {
        return tBaseDeviceMapper.getSendBuildingDataByLampId(id);
    }

    @Override
    public int updateWarnLampDataByOrderNum(String ordernum, String nb_device) {
        return tBaseDeviceMapper.updateWarnLampDataByOrderNum(ordernum,nb_device);
    }

    @Override
    public int saveRoadLineManageData(TRoadLineManage tRoadLineManage) {
        return tBaseDeviceMapper.saveRoadLineManageData(tRoadLineManage);
    }

    @Override
    public int updateRoadLineManageData(TRoadLineManage tRoadLineManage) {
        return tBaseDeviceMapper.updateRoadLineManageData(tRoadLineManage);
    }

    @Override
    public Map<String,Object> queryRoadLineManage(HashMap<String, Object> dataMap) {
        int count = tBaseDeviceMapper.queryCountRoadLineManage(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = tBaseDeviceMapper.queryRoadLineManage(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public HashMap<String, Object> queryRoadLineManageById(Integer id) {
        return tBaseDeviceMapper.queryRoadLineManageById(id);
    }

    @Override
    public int deleteRoadLineManageById(Integer id) {
        return tBaseDeviceMapper.deleteRoadLineManageById(id);
    }

    @Override
    public HashMap<String, Object> getPlatformTemperatureInfo(String orgCode) {
        return tBaseDeviceMapper.getPlatformTemperatureInfo(orgCode);
    }

}
