package com.lamp.dao;

import com.lamp.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TSafeManageMapper {
    int getCountRoleManageNum(@Param("roleName") String roleName, @Param("orgCode") String orgCode);

    List<Map<String,Object>> getRoleManageData(@Param("roleName") String roleName,@Param("num") Integer num,@Param("showNum") Integer showNum, @Param("orgCode") String orgCode,@Param("orderBy") String orderBy,@Param("sort") String sort);

    HashMap<String,Object> getRoleDataById(Integer id);

    int saveRoleData(TRoleManage tr);

    int updateRoleData(TRoleManage tr);

    int deleteRoleDataById(Integer id);

    int clearDataByRoId(Integer roleId);

    int clearBtnDataByRoId(Integer roleId);

    int batchSaveLampPowerList(List<Map<String,Object>> list);

    int batchSaveLampBtnList(List<Map<String,Object>> list);

    List<Map<String,Object>> getRolManageForSelect(@Param("org_code") String org_code);

    int deleteRoleListByRoleId(Integer id);

    List<Map<String,Object>> getMenuListByRoleId(Integer id);

    List<Map<String,Object>> getOrgManageForSelect(String org_code);

    List<Map<String,Object>> getUserList(HashMap<String,Object> map);

    int getCountUserNum(HashMap<String,Object> map);

    List<Map<String, Object>> getUserById(Integer id);

    List<Map<String, Object>> getUserByUserName(@Param(("username"))String username,@Param("orgid")String orgid);

    int saveUserData(Tsysuser u);

    int updateUserData(Tsysuser u);

    int deleteUserById(Integer id);

    int getUserOldPwd(@Param("id") Integer id,@Param("pwd") String pwd);

    int changeUserPwd(@Param("id") Integer id,@Param("pwd") String pwd);

    List<Map<String, Object>> getTopOrganizationTree(@Param("orgCode") String orgCode);

    List<Map<String, Object>> getOrganizationTreeByPid(@Param("pid") Integer pid,@Param("orgCode") String orgCode,@Param("orderBy") String orderBy,@Param("sort") String sort);

    HashMap<String,Object> getOrganizationById(Integer id);

    List<Map<String, Object>> getOrganizationCodeByPid(Integer pid);

    int saveOrganizationData(Torganization t);

    int updateOrganizationData(Torganization t);

    int delOrganizationById(Integer id);

    int checkHasChildrenData(Integer id);

    int updateOrganizationParentId(@Param("id") Integer id,@Param("hasChild") Integer hasChild);

    int checkParentNodeChildren(Integer id);

    int updateParentNodeChildStatus(Integer id);

    int saveLogoData(HashMap<String,Object> sqlMap);

    int deleteLogoDataById(Integer id);

    int addDataBaseData(sjkhf s);
    int updateDataBaseData(sjkhf s);
    HashMap<String,Object> getDataBaseDataById(Integer id);
    List<Map<String, Object>> getDataBaseData(HashMap<String,Object> dataMap);
    int getCountDataBaseData(HashMap<String,Object> dataMap);
    int deleteDataBaseById(Integer id);

    int getCountDataBaseStrategy(HashMap<String,Object> dataMap);
    List<Map<String, Object>> getDataBaseStrategy(HashMap<String,Object> dataMap);
    int deleteDataBaseStrategyById(Integer id);
    HashMap<String,Object> getDataBaseStrategyById(Integer id);
    int addDataBaseStrategy(Sjkcl s);
    int updateDataBaseStrategy(Sjkcl s);

    List<Map<String, Object>> getDataBaseStrategyForTask();


}
