package com.lamp.service;

import com.lamp.model.TRoleManage;
import com.lamp.model.Torganization;
import com.lamp.model.Tsysuser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISafeManageService {

    Map<String,Object> getRoleManageData(Integer showNum, Integer curpage, String roleName);

    Map<String,Object> getRoleDataById(Integer id);

    int saveRoleData(TRoleManage tr);

    int updateRoleData(TRoleManage tr);

    int deleteRoleDataById(Integer id);

    void savePowerList(String powerList,Integer roleId);

    List<Map<String,Object>> getRolManageForSelect();

    List<Map<String, Object>> getMenuListByRoleId(Integer roleId);

    List<Map<String,Object>> getOrgManageForSelect(String org_code);

    Map<String,Object> getUserList(Integer showNum, Integer curPage,HashMap<String,Object> sqlMap);

    List<Map<String, Object>> getUserById(Integer id);

    int saveUserData(Tsysuser u);

    int updateUserData(Tsysuser u);

    int deleteUserById(Integer id);

    int getUserOldPwdNum(Integer id, String pwd);

    int changeUserPwd(Integer id,String pwd);

    List<Map<String, Object>> getTopOrganizationTree();

    List<Map<String, Object>> getOrganizationTreeByPid(Integer pid);

    int saveOrgzationData(Torganization t);

    Map<String,Object> getOrganizationById(Integer id);

    int updateOrganizationData(Torganization t);

    int delOrganizationById(Integer id);

}
