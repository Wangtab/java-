package com.lamp.service;

import com.lamp.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISafeManageService {

    Map<String,Object> getRoleManageData(Integer showNum, Integer curpage, String roleName, String orgCode,String orderBy,String sort);

    Map<String,Object> getRoleDataById(Integer id);

    int saveRoleData(TRoleManage tr);

    int updateRoleData(TRoleManage tr);

    int deleteRoleDataById(Integer id);

    void savePowerList(String powerList,Integer roleId);

    List<Map<String,Object>> getRolManageForSelect(String org_code);

    List<Map<String, Object>> getMenuListByRoleId(Integer roleId);

    List<Map<String,Object>> getOrgManageForSelect(String org_code);

    Map<String,Object> getUserList(Integer showNum, Integer curPage,HashMap<String,Object> sqlMap);

    List<Map<String, Object>> getUserById(Integer id);

    List<Map<String, Object>> getUserByUserName(String username,String orgid);

    int saveUserData(Tsysuser u);

    int updateUserData(Tsysuser u);

    int deleteUserById(Integer id);

    int getUserOldPwdNum(Integer id, String pwd);

    int changeUserPwd(Integer id,String pwd);

    List<Map<String, Object>> getTopOrganizationTree(String orgCode);

    List<Map<String, Object>> getOrganizationTreeByPid(Integer pid,String orgCode,String orderBy,String sort);

    int saveOrgzationData(Torganization t);

    Map<String,Object> getOrganizationById(Integer id);

    int updateOrganizationData(Torganization t);

    HashMap<String,String> delOrganizationById(Integer id);

    int addDataBaseData(sjkhf s);
    int updateDataBaseData(sjkhf s);
    HashMap<String,Object> getDataBaseDataById(Integer id);
    Map<String, Object> getDataBaseData(HashMap<String,Object> dataMap);
    int deleteDataBaseById(Integer id);

    Map<String, Object> getDataBaseStrategy(HashMap<String,Object> dataMap);
    int deleteDataBaseStrategyById(Integer id);
    HashMap<String,Object> getDataBaseStrategyById(Integer id);
    int addDataBaseStrategy(Sjkcl s);
    int updateDataBaseStrategy(Sjkcl s);
}
