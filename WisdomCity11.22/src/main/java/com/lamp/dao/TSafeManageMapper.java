package com.lamp.dao;

import com.lamp.model.TRoleManage;
import com.lamp.model.Torganization;
import com.lamp.model.Tsysuser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TSafeManageMapper {
    int getCountRoleManageNum(@Param("roleName") String roleName);

    List<Map<String,Object>> getRoleManageData(@Param("roleName") String roleName,@Param("num") Integer num,@Param("showNum") Integer showNum);

    HashMap<String,Object> getRoleDataById(Integer id);

    int saveRoleData(TRoleManage tr);

    int updateRoleData(TRoleManage tr);

    int deleteRoleDataById(Integer id);

    int clearDataByRoId(Integer roleId);

    int batchSaveLampPowerList(List<Map<String,Object>> list);

    List<Map<String,Object>> getRolManageForSelect();

    int deleteRoleListByRoleId(Integer id);

    List<Map<String,Object>> getMenuListByRoleId(Integer id);

    List<Map<String,Object>> getOrgManageForSelect(String org_code);

    List<Map<String,Object>> getUserList(HashMap<String,Object> map);

    int getCountUserNum(HashMap<String,Object> map);

    List<Map<String, Object>> getUserById(Integer id);

    int saveUserData(Tsysuser u);

    int updateUserData(Tsysuser u);

    int deleteUserById(Integer id);

    int getUserOldPwd(@Param("id") Integer id,@Param("pwd") String pwd);

    int changeUserPwd(@Param("id") Integer id,@Param("pwd") String pwd);

    List<Map<String, Object>> getTopOrganizationTree();

    List<Map<String, Object>> getOrganizationTreeByPid(Integer pid);

    HashMap<String,Object> getOrganizationById(Integer id);

    List<Map<String, Object>> getOrganizationCodeByPid(Integer pid);

    int saveOrganizationData(Torganization t);

    int updateOrganizationData(Torganization t);

    int delOrganizationById(Integer id);
}
