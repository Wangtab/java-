package com.lamp.service.impl;

import com.lamp.common.CitySettingCommon;
import com.lamp.common.OrganizationCommon;
import com.lamp.common.SystemOperationCommon;
import com.lamp.dao.*;
import com.lamp.model.*;
import com.lamp.service.ISafeManageService;
import com.lamp.utils.DealPage;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ISafeManageServiceImpl implements ISafeManageService {

    @Autowired
    private TSafeManageMapper tSafeManageMapper;

    @Autowired
    private TMonitorServiceMapper tMonitorServiceMapper;

    @Override
    public Map<String,Object> getRoleManageData(Integer showNum, Integer curPage, String roleName,String orgCode,String orderBy,String sort){
        Map<String,Object> dataMap = new HashMap<>();
        int count = tSafeManageMapper.getCountRoleManageNum(roleName,orgCode);
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer num = (curPage -1) * showNum;
        List<Map<String, Object>> list = tSafeManageMapper.getRoleManageData(roleName,num,showNum,orgCode,orderBy,sort);
        dataMap.put("count",count);
        dataMap.put("datas",list);
        return dataMap;
    }

    @Override
    public Map<String, Object> getRoleDataById(Integer id) {
        return tSafeManageMapper.getRoleDataById(id);
    }

    @Override
    public int saveRoleData(TRoleManage tr) {
        return tSafeManageMapper.saveRoleData(tr);
    }

    @Override
    public int updateRoleData(TRoleManage tr) {
        return tSafeManageMapper.updateRoleData(tr);
    }

    @Override
    public int deleteRoleDataById(Integer id) {
        tSafeManageMapper.deleteRoleListByRoleId(id);
        return tSafeManageMapper.deleteRoleDataById(id);
    }

    public void savePowerList(String powerList,Integer roleId){
        tSafeManageMapper.clearDataByRoId(roleId);
        tSafeManageMapper.clearBtnDataByRoId(roleId);
        List<Map<String,Object>> menuList = new ArrayList<>();
        List<Map<String,Object>> btnList = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(powerList);
        JSONArray menuArray =  (JSONArray)jsonArray.get(0);
        JSONArray btnArray =  (JSONArray)jsonArray.get(1);
        for (int i = 0; i < menuArray.size(); i++) {
            JSONObject jb = (JSONObject) menuArray.get(i);
            String menuId = jb.get("menu_id").toString();
            HashMap<String,Object> tempMap = new HashMap<>();
            tempMap.put("menuId",menuId);
            tempMap.put("roleId",roleId);
            menuList.add(tempMap);
        }

        for (int i = 0; i < btnArray.size(); i++) {
            JSONObject jb = (JSONObject) btnArray.get(i);
            HashMap<String,Object> tempMap = new HashMap<>();
            String menuId = jb.get("menu_id").toString();
            Integer status = Integer.parseInt(jb.get("status").toString());
            String id = jb.get("id").toString();
            if(1 == status){
                tempMap.put("id",id);
                tempMap.put("menuId",menuId);
                tempMap.put("roleId",roleId);
                btnList.add(tempMap);
            }
        }

        if(menuList.size() >  0){
            tSafeManageMapper.batchSaveLampPowerList(menuList);
        }

        if(btnList.size() > 0){
            tSafeManageMapper.batchSaveLampBtnList(btnList);
        }
    }

    @Override
    public List<Map<String, Object>> getRolManageForSelect(String org_code) {
        return tSafeManageMapper.getRolManageForSelect(org_code);
    }

    @Override
    public List<Map<String, Object>> getMenuListByRoleId(Integer roleId){
        return tSafeManageMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public List<Map<String, Object>> getOrgManageForSelect(String org_code){
        return tSafeManageMapper.getOrgManageForSelect(org_code);
    }

    @Override
    public Map<String,Object> getUserList(Integer showNum, Integer curPage,HashMap<String,Object> sqlMap){
        Map<String,Object> dataMap = new HashMap<>();
        int count = tSafeManageMapper.getCountUserNum(sqlMap);
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer num = (curPage -1) * showNum;
        sqlMap.put("num",num);
        sqlMap.put("showNum",showNum);
        List<Map<String, Object>> list = tSafeManageMapper.getUserList(sqlMap);
        dataMap.put("count",count);
        dataMap.put("datas",list);
        return dataMap;
    }

    @Override
    public List<Map<String, Object>> getUserById(Integer id) {
        return tSafeManageMapper.getUserById(id);
    }

    @Override
    public List<Map<String, Object>> getUserByUserName(String username,String orgid) {
        return tSafeManageMapper.getUserByUserName(username,orgid);
    }

    @Override
    public int saveUserData(Tsysuser u) {
        return tSafeManageMapper.saveUserData(u);
    }

    @Override
    public int updateUserData(Tsysuser u) {
        return tSafeManageMapper.updateUserData(u);
    }

    @Override
    public int deleteUserById(Integer id) {
        return tSafeManageMapper.deleteUserById(id);
    }

    @Override
    public int getUserOldPwdNum(Integer id, String pwd) {
        return tSafeManageMapper.getUserOldPwd(id,pwd);
    }

    @Override
    public int changeUserPwd(Integer id, String pwd) {
        return tSafeManageMapper.changeUserPwd(id,pwd);
    }

    @Override
    public List<Map<String, Object>> getTopOrganizationTree(String orgCode){
        return tSafeManageMapper.getTopOrganizationTree(orgCode);
    }

    @Override
    public List<Map<String, Object>> getOrganizationTreeByPid(Integer pid,String orgCode,String orderBy,String sort){
        return tSafeManageMapper.getOrganizationTreeByPid(pid,orgCode,orderBy,sort);
    }

    @Override
    public int saveOrgzationData(Torganization t){
        Integer pid = t.getPid();
        //获取上级节点编码
        HashMap<String,Object> hashMap = tSafeManageMapper.getOrganizationById(pid);
        String  parent_code = hashMap.get("org_code").toString();
        //获取同级节点编码列表
        List<Map<String, Object>> list =  tSafeManageMapper.getOrganizationCodeByPid(pid);
        String numCode = dealOrgCode(list);
        t.setOrgCode(parent_code + numCode);
        tSafeManageMapper.updateOrganizationParentId(t.getPid(),10);
        tSafeManageMapper.saveOrganizationData(t);
        HashMap<String,String > weatherMap = PlatformUtils.dealWeatherTemperatureData(CitySettingCommon.lo + "," + CitySettingCommon.la,"");
        HashMap<String,String> sunTimeMap =  PlatformUtils.getSunRiseAndSet(CitySettingCommon.lo,CitySettingCommon.la);
        //保存位置信息
        TCitySetting tCitySetting = new TCitySetting();
        tCitySetting.setCityName(CitySettingCommon.CITY_NAME);
        tCitySetting.setLo(CitySettingCommon.lo);
        tCitySetting.setLa(CitySettingCommon.la);
        tCitySetting.setOrgId(t.getId());
        tCitySetting.setLowTemp(weatherMap.get(CitySettingCommon.WEATHER_LOW_TEMPERATURE));
        tCitySetting.setHeightTemp(weatherMap.get(CitySettingCommon.WEATHER_HEIGHT_TEMPERATURE));
        tCitySetting.setTempDetail(weatherMap.get(CitySettingCommon.WEATHER_TEMPERATURE_DETAIL));
        tCitySetting.setWeatherPic(weatherMap.get(CitySettingCommon.WEATHER_PIC));
        tCitySetting.setSunRise(sunTimeMap.get("sunrise"));
        tCitySetting.setSunSet(sunTimeMap.get("sunset"));
        //保存Logo信息
        HashMap<String,Object > sqlMap = new HashMap<>();
        sqlMap.put("cname","智慧城市公共设施管理平台");
        sqlMap.put("img","images/logo.png");
        sqlMap.put("org_id",t.getId());
        sqlMap.put("operId",t.getOperid());
        sqlMap.put("times",PlatformUtils.getNowTime());
        tSafeManageMapper.saveLogoData(sqlMap);
        tMonitorServiceMapper.addCityData(tCitySetting);
        TPowerRate powerrate = new TPowerRate();
        powerrate.setOrgId(t.getId());
        powerrate.setPowerRate(BigDecimal.valueOf(1));
        powerrate.setOperId(t.getOperid());
        powerrate.setOperTime(PlatformUtils.getNowTime());
        powerrate.setDelFlag(0);
       // int res = tpowerrateMapper.insertPowerrate(powerrate);//初始化电费信息
        Integer result  = t.getId() == null ? 0 : 1;
        return result;
    }

    @Override
    public Map<String, Object> getOrganizationById(Integer id) {
        return tSafeManageMapper.getOrganizationById(id);
    }

    @Override
    public int updateOrganizationData(Torganization t) {
        return tSafeManageMapper.updateOrganizationData(t);
    }

    @Override
    public HashMap<String,String> delOrganizationById(Integer id) {
        Integer num =  tSafeManageMapper.checkHasChildrenData(id);
        HashMap<String,String> resultMap = new HashMap<>();
        resultMap.put("operation", SystemOperationCommon.OPERATION_SUCCESS);
        if(num == 0){
            int children = tSafeManageMapper.checkParentNodeChildren(id);
            if(1 == children){
                tSafeManageMapper.updateParentNodeChildStatus(id);
            }
            tSafeManageMapper.deleteLogoDataById(id);
            tSafeManageMapper.delOrganizationById(id);
           // tpowerrateMapper.deletePowerByOrgid(id);//删除对应组织电费
        }else{
            resultMap.put("operation", "hasChild");
        }
        return resultMap;
    }

    @Override
    public int addDataBaseData(sjkhf s) {
        return tSafeManageMapper.addDataBaseData(s);
    }

    @Override
    public int updateDataBaseData(sjkhf s) {
        return tSafeManageMapper.updateDataBaseData(s);
    }

    @Override
    public HashMap<String, Object> getDataBaseDataById(Integer id) {
        return tSafeManageMapper.getDataBaseDataById(id);
    }

    @Override
    public Map<String, Object> getDataBaseData(HashMap<String, Object> dataMap) {
        int count = tSafeManageMapper.getCountDataBaseData(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = tSafeManageMapper.getDataBaseData(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public int deleteDataBaseById(Integer id) {
        return tSafeManageMapper.deleteDataBaseById(id);
    }

    @Override
    public Map<String, Object> getDataBaseStrategy(HashMap<String, Object> dataMap) {
        int count = tSafeManageMapper.getCountDataBaseStrategy(dataMap);
        PlatformUtils.dealPageData(dataMap,count);
        List<Map<String, Object>> list = tSafeManageMapper.getDataBaseStrategy(dataMap);
        return PlatformUtils.backMapData(count,list);
    }

    @Override
    public int deleteDataBaseStrategyById(Integer id) {
        return tSafeManageMapper.deleteDataBaseStrategyById(id);
    }

    @Override
    public HashMap<String, Object> getDataBaseStrategyById(Integer id) {
        return tSafeManageMapper.getDataBaseStrategyById(id);
    }

    @Override
    public int addDataBaseStrategy(Sjkcl s) {
        return tSafeManageMapper.addDataBaseStrategy(s);
    }

    @Override
    public int updateDataBaseStrategy(Sjkcl s) {
        return tSafeManageMapper.updateDataBaseStrategy(s);
    }

    private String dealOrgCode(List<Map<String, Object>> list){
        if(null == list || list.size() == 0){
            return "001";
        }
        List<Integer> codeList = new ArrayList<>();
        for (Map<String,Object> map : list){
            String stringCode = map.get("org_code").toString();
            stringCode = stringCode.substring(stringCode.length()- OrganizationCommon.CUT_STRING_LENGTH);
            Integer pcode = Integer.parseInt(stringCode);
            codeList.add(pcode);
        }
        Collections.sort(codeList);
        for (int i = 0; i < codeList.size(); i++) {
            int num = i + 1;
            //存在断续情况
            if(num != codeList.get(i)){
                return String .format("%03d",num);
            }
        }
        //不存在取最后一个编号下一位
        Integer lastCodeNum = codeList.get(codeList.size()-1);
        lastCodeNum ++;
        return String .format("%03d",lastCodeNum);
    }

}
