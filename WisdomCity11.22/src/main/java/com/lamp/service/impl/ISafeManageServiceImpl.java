package com.lamp.service.impl;

import com.lamp.common.OrganizationCommon;
import com.lamp.dao.TMenuMapper;
import com.lamp.dao.TSafeManageMapper;
import com.lamp.model.TMenu;
import com.lamp.model.TRoleManage;
import com.lamp.model.Torganization;
import com.lamp.model.Tsysuser;
import com.lamp.service.ISafeManageService;
import com.lamp.utils.DealPage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ISafeManageServiceImpl implements ISafeManageService {

    @Autowired
    private TSafeManageMapper tSafeManageMapper;

    @Override
    public Map<String,Object> getRoleManageData(Integer showNum, Integer curPage, String roleName){
        Map<String,Object> dataMap = new HashMap<>();
        int count = tSafeManageMapper.getCountRoleManageNum(roleName);
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer num = (curPage -1) * showNum;
        List<Map<String, Object>> list = tSafeManageMapper.getRoleManageData(roleName,num,showNum);
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
        List<Map<String,Object>> datList = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(powerList);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jb = (JSONObject) jsonArray.get(i);
            String menuId = jb.get("menu_id").toString();
            HashMap<String,Object> tempMap = new HashMap<>();
            tempMap.put("menuId",menuId);
            tempMap.put("roleId",roleId);
            datList.add(tempMap);
        }
        tSafeManageMapper.batchSaveLampPowerList(datList);
    }

    @Override
    public List<Map<String, Object>> getRolManageForSelect() {
        return tSafeManageMapper.getRolManageForSelect();
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
    public List<Map<String, Object>> getTopOrganizationTree(){
        return tSafeManageMapper.getTopOrganizationTree();
    }

    @Override
    public List<Map<String, Object>> getOrganizationTreeByPid(Integer pid){
        return tSafeManageMapper.getOrganizationTreeByPid(pid);
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
        return tSafeManageMapper.saveOrganizationData(t);
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
    public int delOrganizationById(Integer id) {
        return tSafeManageMapper.delOrganizationById(id);
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
