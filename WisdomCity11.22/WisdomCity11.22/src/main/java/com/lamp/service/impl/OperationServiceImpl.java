package com.lamp.service.impl;


import com.lamp.dao.*;
import com.lamp.model.*;
import com.lamp.service.OperationService;
import com.lamp.utils.DealPage;
import com.lamp.utils.GetLocalTimes;
import com.lamp.utils.PageBean;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017-11-24.
 * 运维管理业务实现层
 * author:LIULIN
 *
 *
 */


@Service
public class OperationServiceImpl  implements OperationService{

    @Autowired
    private TsysuserlogMapper tsysuserlogMapper;

    @Autowired
    private TlamptypeMapper tlamptypeMapper;

    @Autowired
    private TlampMapper tlampMapper;


    @Autowired
    private TLampCommonMapper tLampCommonMapper;



    /**
     * 批量删除灯具类型
     * @param list
     * @return
     */
    public Integer batchDeleteLampType(List<Tlamptype> list) {
        List<Map<String,Object>> mapList = tlamptypeMapper.queryLampRelation(list); //查询是否有关联
        Integer num = mapList.size();
        Integer msg = -1;
        if (num == 0){
            msg = tlamptypeMapper.LampTypeBatchDelete(list);
        }
        return msg;
    }


    /**
     * 根据区域ID查询路段信息
     * @param id
     * @return
     */
    public List<Troadmanage> queryTroadmanageList(HttpServletRequest request,Integer id) {
        return null;
    }


    /**
     * 增加登录信息日志
     * @author LIUlIN
     * @date 2017-11-24
     * @param
     * @return
     */
    public Integer userLogInsertService(){
        Tsysuserlog tsysuserlog = new Tsysuserlog();
        tsysuserlog.setLogDate(GetLocalTimes.getNowTime());
        return  tsysuserlogMapper.userLogInsert(tsysuserlog);
    }


    /**
     * 跳转灯具类型管理页面并分页
     * @param typeName
     * @param page
     * @return
     */
    public List<Map<String, Object>> queryLampTypeManage(HttpServletRequest request,String typeName, String page) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();
        /*查询总数*/
        List<Map<String,Object>> mapList = tlamptypeMapper.queryLampTypeManageList(orgCode,typeName,-1);
        Integer count = mapList.size();
        /*获取分页数据*/
        Integer num = obtainPage(page, count, 10);
        System.out.println("curPage:" + num);
        /*查询灯具类型信息并分页*/
        List<Map<String,Object>> list = tlamptypeMapper.queryLampTypeManageList(orgCode,typeName,num);

        request.setAttribute("list",list);
        request.setAttribute("typeName",typeName);
        request.setAttribute("count", count);
        request.setAttribute("curPage", page);
        return list;
    }

    /**
     * 查询所有的工厂信息
     * @return
     */
    public List<Map<String, Object>> queryFactoryList(HttpServletRequest request) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String,Object>> mapList = new ArrayList<>();
        return mapList;
    }

    /**
     * 查询调光值
     * @return
     */
    public List<Map<String, Object>> querydimmingmodeList() {
        List<Map<String,Object>> mapList = tlamptypeMapper.querydimmingmodeList();
        return mapList;
    }

    /**
     * 增加灯具类型信息
     * @param tlamptype
     * @return
     */
    public int addTlamptype(Tlamptype tlamptype) {
        Integer info = tlamptypeMapper.addTlamptype(tlamptype);
        System.out.println("info: " + info);
        return info;
    }

    /**
     * 查询是否有灯具类型
     * @param mid
     * @param msg
     * @return
     */
    public String queryModel(HttpServletRequest request,String mid, String msg) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();
        /*查询是否有灯具型号*/
        Integer count = tlamptypeMapper.queryLampM(orgCode, mid, msg);
        return count.toString();
    }

    /**
     * 更新灯具信息
     * @param tlamptype
     * @return
     */
    public int updateTypeManage(Tlamptype tlamptype) {
        Integer info = tlamptypeMapper.updateTypeManage(tlamptype);
        return info;
    }

    /**
     * 查询灯具信息
     * @param id
     * @return
     */
    public List<Map<String, Object>> queryLampType(HttpServletRequest request,Integer id) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String,Object>> mapList = tlamptypeMapper.queryLampType(orgCode, id);
        return mapList;
    }


    /**
     * 查询所有的路灯信息
     * @param request
     * @param lampName
     * @param page
     * @return
     */
    public List<Map<String, Object>> queryLampManageAll(HttpServletRequest request,String areaId,String roadId,String lineId, String lampName, String page) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        /*查询总数*/
        List<Map<String,Object>> mapList = tlampMapper.queryLampALL(orgCode,areaId,roadId,lineId,lampName, -1);
        Integer count = mapList.size();
        /*获取分页数据*/
        Integer num = obtainPage(page, count, 10);
        System.out.println("curPage:" + num);
        /*查询灯具信息并分页*/
        List<Map<String,Object>> list = tlampMapper.queryLampALL(orgCode,areaId,roadId,lineId,lampName, num);

        request.setAttribute("list", list);
        request.setAttribute("lampName", lampName);
        request.setAttribute("count", count);
        request.setAttribute("curPage", page);
        request.setAttribute("aId",areaId);
        request.setAttribute("rId",roadId);
        request.setAttribute("lId",lineId);
        return list;
    }

    /**
     * 增加灯具信息
     * @param request
     * @param lampnum
     * @param typeId
     * @param pdId
     * @param dbcircuit
     * @param poleCode
     * @param roadlineId
     * @param controller_id
     * @param lo
     * @param la
     * @return
     */
    @Override
    public int insertTlampManage(HttpServletRequest request,String lampnum, String typeId, String pdId, String dbcircuit, String poleCode, String roadlineId, String controller_id, String lo, String la) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);

        Tlamp tlamp = new Tlamp();


        if (!lampnum.isEmpty()){
            tlamp.setLampnum(Integer.parseInt(lampnum));
        }

        if (!typeId.isEmpty()){
            tlamp.setTypeId(Integer.parseInt(typeId));
        }

        if (!pdId.isEmpty()){
            tlamp.setPdId(Integer.parseInt(pdId));
        }

        if (!dbcircuit.isEmpty()){
            tlamp.setDbcircuit(Integer.parseInt(dbcircuit));
        }

        if (!poleCode.isEmpty()){
            tlamp.setPoleCode(poleCode);
        }

        if (!roadlineId.isEmpty()){
            tlamp.setRoadlineId(Integer.parseInt(roadlineId));
        }

        if (!controller_id.isEmpty()){
            tlamp.setControllerId(Integer.parseInt(controller_id));
        }

        if (!lo.isEmpty()){
            tlamp.setLo(new BigDecimal(lo));
        }

        if (!la.isEmpty()){
            tlamp.setLa(new BigDecimal(la));
        }
        tlamp.setOperId(tsysuser.getId());
        tlamp.setOpertime(PlatformUtils.getNowTime());
        tlamp.setCreateby(tsysuser.getId());
        tlamp.setCreateTime(PlatformUtils.getNowTime());

        tlamp.setDelflag(0);


        Integer info = tlampMapper.insertTlampManage(tlamp);

        return info;
    }

    /**
     * 批量增加灯具
     * @param data
     * @return
     */
    public String IntsertLampBatch(HttpServletRequest request,String data) {
        String add = "";
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();
        /*正则表达式*/
        String reg = "^[0-9]*$";
        /*转化数组*/
        JSONArray jsonArray = JSONArray.fromObject(data);

        List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>();
        if (jsonArray.size()  != 0){
            for (int i = 0;i < jsonArray.size();i++){
                Map<String,Object> map = new HashMap<String,Object>();

                Object region = jsonArray.getJSONObject(i).get("所属区域"); //所属区域
                Object road = jsonArray.getJSONObject(i).get("所属道路"); //所属道路
                if (region != null && road != null){
                    List<Map<String,Object>> mapList1 = tlampMapper.queryRegionalList(orgCode, region.toString(), road.toString());
                    if (mapList1.size() != 0){
                        map.put("regionId",mapList1.get(0).get("regionId"));
                        map.put("roadId",mapList1.get(0).get("roadId"));
                        
                    }else {
                        add = "所属区域道路不正确";
                        break;
                    }
                }else{
                    add = "所属区域道路为空";
                    break;
                }

                Object eleBoxName = jsonArray.getJSONObject(i).get("配电箱编号"); //配电箱编号
                if (eleBoxName != null){
                    List<Map<String,Object>> mapList1 = tlampMapper.queryELeBoxList(orgCode,eleBoxName.toString());
                    if (mapList1.size() != 0){
                        map.put("",mapList1.get(0).get("id"));
                    }else {
                        add = "配电箱编号不正确";
                        break;
                    }
                }else {
                    add = "配电箱编号为空";
                    break;
                }

                Object dbcircuit = jsonArray.getJSONObject(i).get("配电箱回路"); //配电箱回路
                if (dbcircuit != null){
                    if (dbcircuit.toString().matches(reg)){
                        if (Integer.parseInt(dbcircuit.toString()) <= 15){
                            map.put("dbcircuit",dbcircuit);
                        }else {
                            add ="配电箱回路大于15";
                            break;
                        }
                    }else {
                        add ="配电箱回路:" +dbcircuit+"不正确";
                        break;
                    }
                }else {
                    add = "配电箱回路为空";
                    break;
                }

                Object mvLamp = jsonArray.getJSONObject(i).get("主副灯"); //主副灯
                if (mvLamp != null){
                    if (mvLamp.toString().equals("主灯") || mvLamp.toString().equals("副灯")){

                    }else {
                        add = "主副灯错误";
                        break;
                    }
                }else {
                    add = "主副灯为空";
                    break;
                }

                Object lampModel = jsonArray.getJSONObject(i).get("灯具型号"); //灯具型号
                Object power = jsonArray.getJSONObject(i).get("额定功率"); //额定功率
                Object lampFactory = jsonArray.getJSONObject(i).get("额定功率"); //灯具工厂

                if (lampModel != null){

                }else {
                    add = "灯具型号为空";
                    break;
                }

                Object lampnum = jsonArray.getJSONObject(i).get("灯具编号"); //灯具编号
                if (lampnum != null){

                }

                Object nbCode = jsonArray.getJSONObject(i).get("控制器编号"); //控制器编号
                if (nbCode != null){

                }else {
                    add = "控制器编号为空";
                    break;
                }
            }
        }else {
            add = "xls文件为空";
        }
        /*增加灯具管理*/



        return add;
    }

    /**
     * 更新灯具信息
     * @param request
     * @param lampnum
     * @param typeId
     * @param pdId
     * @param dbcircuit
     * @param poleCode
     * @param roadlineId
     * @param controller_id
     * @param lo
     * @param la
     * @param id
     * @return
     */
    public int updateTlampMsg(HttpServletRequest request,String lampnum, String typeId, String pdId, String dbcircuit, String poleCode, String roadlineId, String controller_id, String lo, String la, String id) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);

        Tlamp tlamp = new Tlamp();

        if (!lampnum.isEmpty()){
            tlamp.setLampnum(Integer.parseInt(lampnum));
        }

        if (!typeId.isEmpty()){
            tlamp.setTypeId(Integer.parseInt(typeId));
        }

        if (!pdId.isEmpty()){
            tlamp.setPdId(Integer.parseInt(pdId));
        }

        if (!dbcircuit.isEmpty()){
            tlamp.setDbcircuit(Integer.parseInt(dbcircuit));
        }

        if (!poleCode.isEmpty()){
            tlamp.setPoleCode(poleCode);
        }

        if (!roadlineId.isEmpty()){
            tlamp.setRoadlineId(Integer.parseInt(roadlineId));
        }

        if (!controller_id.isEmpty()){
            tlamp.setControllerId(Integer.parseInt(controller_id));
        }

        if (!lo.isEmpty()){
            tlamp.setLo(new BigDecimal(lo));
        }

        if (!la.isEmpty()){
            tlamp.setLa(new BigDecimal(la));
        }

        tlamp.setOperId(tsysuser.getId());

        tlamp.setOpertime(PlatformUtils.getNowTime());

        tlamp.setId(Integer.parseInt(id));

        tlamp.setDelflag(0);

        Integer info = tlampMapper.updateTlampMsg(tlamp);  //更新灯具信息
        return info;
    }

    /**
     * 删除灯具信息
     * @param id
     * @return
     */
    public int deleteLampManage(HttpServletRequest request,String id) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);

        Tlamp tlamp = new Tlamp();

        tlamp.setId(Integer.parseInt(id));
        tlamp.setDelflag(1);
        Integer info = tlampMapper.updateTlampMsg(tlamp);  //删除灯具信息

        return info;
    }

    /**
     * 查询灯具信息
     * @param id
     * @return
     */
    public List<Map<String, Object>> queryLampMeg(HttpServletRequest request,String id) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String,Object>> mapList = tlampMapper.queryLamp(orgCode, Integer.parseInt(id));
        return mapList;
    }

    /**
     * 查询区域
     * @param request
     * @return
     */
    public List<Map<String, Object>> queryRegionList(HttpServletRequest request) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String,Object>> mapList = null;
        return mapList;
    }

    /**
     * 根据区域选择所属道路
     * @param request
     * @return
     */
    public List<Map<String, Object>> queryRegionMap(HttpServletRequest request,Integer id) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String,Object>> mapList = null;
        return mapList;
    }

    /**
     * 查询所属线路
     * @param request
     * @param id
     * @return
     */
    public List<Map<String, Object>> queryRoadlineMap(HttpServletRequest request, Integer id) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        return tLampCommonMapper.queryRoadLineMap(orgCode,id);
    }




    /**
     * 查询所属路段
     * @return
     */
    public List<Tareamanage> selectAreaAll() {
        List<Tareamanage> list = null;
        return list;
    }


    /**
     * 分页数据获取
     */
    public static Integer obtainPage(String page,Integer count,Integer size){
        /*分页*/
        PageBean pageBean = new PageBean();
        pageBean.setCount(count);
        pageBean.setSize(size);
        /*获取总共页数*/
        Integer countPage;
        if(count > size){
            if (count % size == 0){
                countPage = count/size;
            }else{
                countPage = count/size + 1;
            }
        }else {
            countPage = 1;
        }
        /*页码处理*/
        Integer pageindex = DealPage.dealPage(page, pageBean.getTotal());
        /*分页获取数据*/
        Integer num = (pageindex -1)*size;
        return num;
    }


    /**
     * 查询所有的灯具信息
     * @return
     */
    public List<Map<String, Object>> queryLampTypeALL(HttpServletRequest request,Integer mid) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String,Object>> mapList = tlampMapper.queryLampType(orgCode, mid);
        return mapList;
    }

    /**
     * 查询所有的配电箱编号
     * @param request
     * @return
     */
    public List<Map<String, Object>> queryPdboxList(HttpServletRequest request) {
        return null;
    }

    /**
     * 查询所有的控制器
     * @return
     */
    public List<Map<String, Object>> queryControllerALL(HttpServletRequest request) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();
        /*获取已关联的控制器ID*/
        List<Map<String,Object>> list = tlampMapper.queryLampList(orgCode);
        /*查询控制器信息*/
        List<Map<String,Object>> mapList = tlampMapper.queryController(orgCode, list);
        return mapList;
    }

    /**
     * 控制器信息
     * @param request
     * @return
     */
    public List<Map<String, Object>> queryContUnpion(HttpServletRequest request,String contId) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        /*查询控制器信息*/
        List<Map<String,Object>> mapList = tlampMapper.queryControllerOne(orgCode, contId);
        return mapList;
    }

    /**
     * 查询所有的运营商信息
     * @return
     */
    public List<Map<String, Object>> queryNetworkOperatorsALL() {
        List<Map<String,Object>> mapList = tlampMapper.queryNetworkOperators();
        return mapList;
    }


    /**
     * 查询所有的协议类型
     * @return
     */
    public List<Map<String, Object>> queryProtocolAll() {
        List<Map<String,Object>> mapList = tlampMapper.queryProtocolAll();
        return mapList;
    }

    /**
     * 灯杆信息
     * @return
     */
    public List<Map<String, Object>> queryPoleList(HttpServletRequest request) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String,Object>> mapList = tlampMapper.queryPoleAll(orgCode);
        return mapList;
    }

    /**
     * 灯具工厂信息
     * @return
     */
    public List<Map<String, Object>> querylampFactoryAll(HttpServletRequest request) {
        /*获取用户信息*/
        Tsysuser tsysuser = PlatformUtils.getLoginUser(request);
        String orgCode = tsysuser.getTorganization().getOrgCode();

        List<Map<String,Object>> mapList = tlampMapper.querylampFactoryAll(orgCode);
        return mapList;
    }

    public Map<String,Object> getlampTypeManage(Integer showNum, Integer curPage,HashMap<String,Object> sqlMap){
        Map<String,Object> dataMap = new HashMap<>();
        int count = tlamptypeMapper.getCountLampTypeList(sqlMap); /*获取总数量的函数*/
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer num = (curPage -1) * showNum;
        sqlMap.put("num",num);
        sqlMap.put("showNum",showNum);
        List<Map<String, Object>> list = tlamptypeMapper.getLampTypeList(sqlMap);//获取List
        dataMap.put("count",count);
        System.out.println("count："+count);
        System.out.println("datas："+list);
        dataMap.put("datas",list);
        return dataMap;
    }

    public Map<String,Object> getlampManage(Integer showNum, Integer curPage,HashMap<String,Object> sqlMap){
        Map<String,Object> dataMap = new HashMap<>();
        int count = tlampMapper.queryLampManageCount(sqlMap); /*获取总数量的函数*/
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer num = (curPage -1) * showNum;
        sqlMap.put("num",num);
        sqlMap.put("showNum",showNum);
        List<Map<String, Object>> list = tlampMapper.queryLampManage(sqlMap);//获取List
        dataMap.put("count",count);
        dataMap.put("datas",list);
        System.out.println("count:"+sqlMap.get(""));
        return dataMap;
    }


}
