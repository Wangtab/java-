package com.lamp.controller;

import com.lamp.common.UserCommon;
import com.lamp.model.TRoleManage;
import com.lamp.model.Torganization;
import com.lamp.model.Tsysuser;
import com.lamp.service.ISafeManageService;
import com.lamp.utils.GetLocalTimes;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("safe")
public class SafeManageController {

    @Autowired
    private ISafeManageService iSafeManageService;

    /**
     * 显示角色管理列表
     */
    @RequestMapping("getRoleManageData")
    public void getRoleManageData(Integer showNum, Integer curPage,String roleName,
                                  HttpServletResponse response) throws IOException {
        Map<String,Object> list = null;
        try {
            list =  iSafeManageService.getRoleManageData(showNum,curPage,roleName);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
            return;
        }
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据ID获取角色信息
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("getRoleDataById")
    public void getRoleDataById(Integer id,HttpServletResponse response) throws IOException {
        Map<String,Object> map = iSafeManageService.getRoleDataById(id);
        response.getWriter().print(JSONArray.fromObject(map).toString());
    }

    /**
     * 保存菜单信息
     */
    @RequestMapping("saveRoleData")
    public void saveRoleData(TRoleManage tr, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        tr.setOperId(user.getId());
        tr.setOperTime(GetLocalTimes.getNowTime());

        Integer status = 0;
        if(tr.getId() == null){
            status = iSafeManageService.saveRoleData(tr);
        }else{
            status = iSafeManageService.updateRoleData(tr);
        }
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);
    }

    /**
     * 删除角色信息ByID
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("deleteRoleDataById")
    public void deleteRoleDataById(Integer id,HttpServletResponse response) throws IOException{
        Integer status = iSafeManageService.deleteRoleDataById(id);
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);
    }

    /**
     * 保存权限
     * @param powerJson
     * @param response
     */
    @RequestMapping("savePowerList")
    public void savePowerList(String powerJson,Integer roleId,HttpServletResponse response) throws IOException {
        try {
            iSafeManageService.savePowerList(powerJson,roleId);
            response.getWriter().print("y");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("n");
        }
    }

    /**
     * 前端页面获取角色名 角色ID
     */
    @RequestMapping("getRolManageForSelect")
    public void getRolManageForSelect(HttpServletResponse response) throws IOException {
        List<Map<String,Object>> list =  iSafeManageService.getRolManageForSelect();
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据角色ID获取相应的
     */
    @RequestMapping("getMenuListByRoleId")
    public void getMenuListByRoleId(Integer id,HttpServletResponse response) throws IOException {
        List<Map<String,Object>> list = iSafeManageService.getMenuListByRoleId(id);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据登录人所属组织查询组织(select)
     */
    @RequestMapping("getOrgManageForSelect")
    public void getOrgManageForSelect(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取登录人信息
        Tsysuser user = PlatformUtils.getLoginUser(request);
        //获取org_code
        String org_code = user.getTorganization().getOrgCode();

        List<Map<String,Object>> list = iSafeManageService.getOrgManageForSelect(org_code);
        System.out.printf("----------------------------------");
        System.out.println(list.size());
        System.out.printf("----------------------------------");
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 获取用户信息
     */
    @RequestMapping("getUserList")
    public void getUserList(Integer showNum, Integer curPage,String userName,String realName,String orgId,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取登录人信息
        Tsysuser user = PlatformUtils.getLoginUser(request);
        //获取org_code
        String org_code = user.getTorganization().getOrgCode();

        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("userName",userName);
        dataMap.put("realName",realName);
        dataMap.put("orgId",orgId);
        //将org_code放进map
        dataMap.put("org_code",org_code);
        Map<String,Object> list = null;
        try {
            list = iSafeManageService.getUserList(showNum,curPage,dataMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
            return;
        }
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据ID查询相应信息
     * @param id
     */
    @RequestMapping("getUserById")
    public void getUserById(Integer id,HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = iSafeManageService.getUserById(id);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 保存用户信息
     * @param u
     * @param response
     * @param request
     */
    @RequestMapping("saveUserData")
    public void saveUserData(Tsysuser u,HttpServletResponse response,HttpServletRequest request) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        u.setPassword(UserCommon.DEFAULT_PASSWORD);
        u.setOperId(user.getId());
        u.setOperTime(GetLocalTimes.getNowTime());
        //保存createby 和 createTime
        u.setCreateBy(user.getId());
        u.setCreateTime(PlatformUtils.getNowTime());
        int status = 0;
        if(u.getId() == null){//保存
            status = iSafeManageService.saveUserData(u);
        }else{
            status = iSafeManageService.updateUserData(u);
        }
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);
    }

    /**
     * 根据ID删除用户
     */
    @RequestMapping("delUserById")
    public void delUserById(Integer id,HttpServletResponse response) throws IOException {
        Integer status = iSafeManageService.deleteUserById(id);
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);
    }

    /**
     * 用户修改密码
     */
    @RequestMapping("changeUserPwd")
    public void changeUserPwd(String oldPwd,String new_pwd,Integer userId,HttpServletResponse response) throws IOException {
        int num = iSafeManageService.getUserOldPwdNum(userId,oldPwd);
        if(num == 0){
            response.getWriter().print("no_pwd");
            return;
        }
        int status = iSafeManageService.changeUserPwd(userId,new_pwd);
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);

    }

    /**
     * 查询组织
     */
    @RequestMapping("selectOrganization")
    public void selectOrganization(Integer showNum, Integer curPage,Integer orgId,HttpServletResponse response){

    }

    /**
     * 保存组织信息
     */
    @RequestMapping("saveOrganization")
    public void saveOrganization(Torganization t,HttpServletResponse response,HttpServletRequest request) throws IOException{
        Tsysuser user = PlatformUtils.getLoginUser(request);
        t.setOperid(user.getId());
        t.setOperTime(PlatformUtils.getNowTime());
        int status = 0;
        if(t.getId() == null){
            status = iSafeManageService.saveOrgzationData(t);
        } else {
            status = iSafeManageService.updateOrganizationData(t);
        }
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);

    }

    /**
     * 获取组织树
     */
    @RequestMapping("getOrganizationTree")
    public void getOrganizationTree(HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = iSafeManageService.getTopOrganizationTree();
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据父节点查找子节点
     */
    @RequestMapping("getOrganizationTreeById")
    public void getOrganizationTreeByPid(Integer pid,HttpServletResponse response) throws IOException{
        List<Map<String, Object>> list = iSafeManageService.getOrganizationTreeByPid(pid);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 获取相应的组织信息
     */
    @RequestMapping("getOrganizationById")
    public void getOrganizationById(Integer id,HttpServletResponse response) throws IOException {
        Map<String,Object> map = iSafeManageService.getOrganizationById(id);
        response.getWriter().print(JSONArray.fromObject(map).toString());
    }

    /**
     * 删除组织
     */
    @RequestMapping("delOrganizationById")
    public void delOrganizationById(Integer id,HttpServletResponse response) throws IOException {
        int status = iSafeManageService.delOrganizationById(id);
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);
    }
}
