package com.lamp.controller;

import com.lamp.common.HuaWeiCommon;
import com.lamp.common.UserCommon;
import com.lamp.model.*;
import com.lamp.service.ISafeManageService;
import com.lamp.utils.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void getRoleManageData(Integer showNum, Integer curPage,String roleName,String orderBy,String sort,HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        Map<String,Object> list = null;
        String orgCode = PlatformUtils.getLoginUserCode(request);
        try {
            list =  iSafeManageService.getRoleManageData(showNum,curPage,roleName,orgCode,orderBy,sort);
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
            //添加createby和createTime字段
            tr.setCreateby(user.getId());
            tr.setCreateTime(PlatformUtils.getNowTime());
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
    public void getRolManageForSelect(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取登录人信息
        Tsysuser user = PlatformUtils.getLoginUser(request);

        String org_code = user.getTorganization().getOrgCode();

        List<Map<String,Object>> list =  iSafeManageService.getRolManageForSelect(org_code);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据角色ID获取相应的
     */
    @RequestMapping("getMenuListByRoleId")
    public void getMenuListByRoleId(Integer id,HttpServletResponse response) throws IOException {
        List<Map<String,Object>> list = iSafeManageService.getMenuListByRoleId(id);
        if(list == null || list.size() == 0){
            response.getWriter().print("n");
        }else{
            response.getWriter().print(JSONArray.fromObject(list).toString());
        }
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
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 获取用户信息
     */
    @RequestMapping("getUserList")
    public void getUserList(Integer showNum, Integer curPage,String userName,String realName,String orgId,String sort,String orderBy,HttpServletRequest request,HttpServletResponse response) throws IOException {
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
        dataMap.put("sort",sort);
        dataMap.put("orderBy",orderBy);
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
     * 根据姓名查询相应信息
     * @param username
     */
    @RequestMapping("getUserByUsername")
    public void getUserByUsername(String username,String orgid,HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = iSafeManageService.getUserByUserName(username,orgid);
        response.getWriter().print(list.size());
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
        String password = MD5.MD5Trans(UserCommon.DEFAULT_PASSWORD);
        u.setPassword(password);
        u.setOperId(user.getId());
        u.setOperTime(GetLocalTimes.getNowTime());
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
        oldPwd = MD5.MD5Trans(oldPwd);
        int num = iSafeManageService.getUserOldPwdNum(userId,oldPwd);
        if(num == 0){
            response.getWriter().print("no_pwd");
            return;
        }
        new_pwd = MD5.MD5Trans(new_pwd);
        int status = iSafeManageService.changeUserPwd(userId,new_pwd);
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);

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
    public void getOrganizationTree(HttpServletResponse response,HttpServletRequest request) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        List<Map<String, Object>> list = iSafeManageService.getTopOrganizationTree(orgCode);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据父节点查找子节点
     */
    @RequestMapping("getOrganizationTreeById")
    public void getOrganizationTreeByPid(Integer pid,HttpServletResponse response,HttpServletRequest request,String orderBy,String sort) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        List<Map<String, Object>> list = iSafeManageService.getOrganizationTreeByPid(pid,orgCode,orderBy,sort);
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
        HashMap<String,String> resultMap = iSafeManageService.delOrganizationById(id);
        response.getWriter().print(JSONArray.fromObject(resultMap).toString());
    }

    /**
     * 保存数据库信息
     */
    @RequestMapping("saveMySqlData")
    public void saveMySqlData(sjkhf t, HttpServletRequest request,HttpServletResponse response){
        int status = 0;
        try {
            if(t.getId() == null){
                String sqlName = new LampSwitchInterface().getUUID();
                sqlName = sqlName + ".sql";
                if(backupDataBase(request,sqlName)){
                    SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMddHHmmss");
                    String date = sdf.format(new Date());
                    t.setSjkname(date);
                    t.setSjkaddress(sqlName);
                    t.setAddtime(PlatformUtils.getNowTime());
                    t.setOperid(PlatformUtils.getLoginUser(request).getId());
                    t.setCztime(PlatformUtils.getNowTime());
                    status = iSafeManageService.addDataBaseData(t);
                }
            }else{
                t.setOperid(PlatformUtils.getLoginUser(request).getId());
                t.setCztime(PlatformUtils.getNowTime());
                status = iSafeManageService.updateDataBaseData(t);
            }
            response.getWriter().print(status == 1 ? "y" : "n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean backupDataBase(HttpServletRequest request,String sqlName){
        try {
            CommonMethod commonMethod = new CommonMethod();
            HashMap<String,String> sqlMap = commonMethod.getMysqlSetting();
            String path = request.getSession().getServletContext().getRealPath("upload/mysql");
            return MySQLDatabaseBackup.exportDatabaseTool("127.0.0.1", sqlMap.get("username"),sqlMap.get("password"),path,sqlName,sqlMap.get("dataBaseName"));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据id获取数据库备份相关信息
     */
    @RequestMapping("getDataBaseDataById")
    public void getDataBaseDataById(Integer id,HttpServletResponse response) throws  IOException {
        HashMap<String,Object> dataMap = iSafeManageService.getDataBaseDataById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 根据id删除数据库备份相关信息
     */
    @RequestMapping("delDataBaseDataById")
    public void delDataBaseDataById(Integer id,HttpServletRequest request,HttpServletResponse response) throws  IOException {
        HashMap<String,Object> dataMap = iSafeManageService.getDataBaseDataById(id);
        String path = request.getSession().getServletContext().getRealPath("upload/mysql") +"/"+ dataMap.get("sjkaddress");
        PlatformUtils.deleteFile(path);
        int status = iSafeManageService.deleteDataBaseById(id);
        response.getWriter().print(status == 1 ? "y" : "n");
    }



    /**
     * 还原数据库
     */
    @RequestMapping("restMySqlData")
    public void restMySqlData(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException {
        int status = 0;
        try {
            HashMap<String,Object> dataMap = iSafeManageService.getDataBaseDataById(id);
            String path = request.getSession().getServletContext().getRealPath("upload/mysql") +"/"+ dataMap.get("sjkaddress");
            CommonMethod commonMethod = new CommonMethod();
            HashMap<String,String> sqlMap = commonMethod.getMysqlSetting();
            MySQLDatabaseBackup.restore("127.0.0.1", sqlMap.get("username"), sqlMap.get("password"), sqlMap.get("dataBaseName"),path);
            status = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 查询数据库信息
     */
    @RequestMapping("getDataBaseData")
    public void getDataBaseData(Integer showNum, Integer curPage, String startDate, String endDate,
                                String sort, String orderBy, HttpServletRequest request, HttpServletResponse response) throws IOException{
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        paramMap.put("orgCode",orgCode);
        Map<String,Object> resultMap =  iSafeManageService.getDataBaseData(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     *  获取数据库策略
     */
    @RequestMapping("getDataBaseStrategy")
    public void getDataBaseStrategy(Integer showNum, Integer curPage,HttpServletRequest request,
                             String startDate,String endDate,String orderBy,String sort,HttpServletResponse response) throws IOException {
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("showNum",showNum);
        paramMap.put("curPage",curPage);
        paramMap.put("orderBy",orderBy);
        paramMap.put("sort",sort);
        paramMap.put("startDate",startDate);
        paramMap.put("endDate",endDate);
        paramMap.put("orgCode",orgCode);
        Map<String,Object> resultMap =  iSafeManageService.getDataBaseStrategy(paramMap);
        PlatformUtils.checkSendDataByResponse(resultMap,response);
    }

    /**
     *  根据ID获取相应的备份策略
     */
    @RequestMapping("getDataBaseStrategyById")
    public void getDataBaseStrategyById(Integer id,HttpServletResponse response) throws IOException {
        HashMap<String,Object> dataMap = iSafeManageService.getDataBaseStrategyById(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }

    /**
     * 删除备份策略
     */
    @RequestMapping("deleteDataBaseStrategyById")
    public void deleteDataBaseStrategyById(Integer id,HttpServletResponse response) throws IOException {
        int status = iSafeManageService.deleteDataBaseStrategyById(id);
        response.getWriter().print(status == 1 ? "y" : "n");
    }

    /**
     * 保存备份策略
     */
    @RequestMapping("saveDataBaseStrategy")
    public void saveDataBaseStrategy(Sjkcl sj, HttpServletResponse response, HttpServletRequest request) throws Exception{
        sj.setOperid(PlatformUtils.getLoginUser(request).getId());
        sj.setUptime(PlatformUtils.getNowTime());
        Integer result=0;
        if(sj.getId()==null){
            sj.setAddtime(PlatformUtils.getNowTime());
            result = iSafeManageService.addDataBaseStrategy(sj);
        }else{
            result = iSafeManageService.updateDataBaseStrategy(sj);
        }
        response.getWriter().print(result == 1 ? "y":"n");
    }

    @RequestMapping("testAddress")
    public void testAddress(){
        //String path = System.getProperty("user.dir");
        String path = HuaWeiCommon.PROJECT_PATH;
        System.out.println("path:"+path);
    }




}
