package com.lamp.service;

import com.lamp.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017-11-24.
 * 运维管理业务层接口
 * author:LIULIN
 */
public interface OperationService {
    /*动态增加登录日志业务接口*/
    Integer userLogInsertService();

    List<Troadmanage> queryTroadmanageList(HttpServletRequest request,Integer id);
    /*跳转灯具类型管理页面*/
    List<Map<String,Object>> queryLampTypeManage(HttpServletRequest request,String typeName,String page);

    /*查询所有的工厂信息*/
    List<Map<String,Object>> queryFactoryList(HttpServletRequest request);

    /*查询调光值*/
    List<Map<String,Object>> querydimmingmodeList();

    /*增加灯具类型信息*/
    int addTlamptype(Tlamptype tlamptype);

    /*查询灯具信息*/
    List<Map<String,Object>> queryLampType(HttpServletRequest request,Integer id);

    /*更新灯具信息*/
    int updateTypeManage(Tlamptype tlamptype);

    /*查询所有的灯具信息*/
    List<Map<String,Object>> queryLampManageAll(HttpServletRequest request,String areaId,String roadId,String lineId,String lampName,String page);

    /*增加灯具信息*/
    int insertTlampManage(HttpServletRequest request,String lampnum,String typeId,String pdId,String dbcircuit,String poleCode,String roadlineId,String controller_id,String lo,String la);

    /*更新灯具信息*/
    int updateTlampMsg(HttpServletRequest request,String lampnum,String typeId,String pdId,String dbcircuit,String poleCode,String roadlineId,String controller_id,String lo,String la,String id);

    /*删除灯具信息*/
    int deleteLampManage(HttpServletRequest request,String id);
    /*查询灯具信息*/
    List<Map<String,Object>> queryLampMeg(HttpServletRequest request,String id);

    /*查询灯具类型信息*/
    List<Map<String,Object>> queryLampTypeALL(HttpServletRequest request,Integer mid);

    /*查询所有的灯具工厂*/
    List<Map<String,Object>> querylampFactoryAll(HttpServletRequest request);

    /*查询控制器*/
    List<Map<String,Object>> queryControllerALL(HttpServletRequest request);

    /*控制信息*/
    List<Map<String,Object>> queryContUnpion(HttpServletRequest request,String contId);

    /*查询所有的运营商信息*/
    List<Map<String,Object>> queryNetworkOperatorsALL();

    /*查询所有的协议类型*/
    List<Map<String,Object>> queryProtocolAll();

    /*查询所有的灯杆管理*/
    List<Map<String,Object>> queryPoleList(HttpServletRequest request);

    /*查询所属区域*/
    List<Tareamanage> selectAreaAll();

    /*查询是否有灯具类型*/
    String queryModel(HttpServletRequest request,String mid,String msg);

    /*查询所有区域*/
    List<Map<String,Object>> queryRegionList(HttpServletRequest request);

    /*查询所属道路*/
    List<Map<String,Object>> queryRegionMap(HttpServletRequest request,Integer id);

    /*查询所属线路*/
    List<Map<String,Object>> queryRoadlineMap(HttpServletRequest request,Integer id);

    /*查询配电箱编号*/
    List<Map<String,Object>> queryPdboxList(HttpServletRequest request);

    /*批量增加灯具信息*/
    String IntsertLampBatch(HttpServletRequest request,String data);

    Integer batchDeleteLampType(List<Tlamptype> list); //批量删除灯具类型

    Map<String,Object> getlampTypeManage(Integer showNum, Integer curPage,HashMap<String,Object> sqlMap);

    Map<String,Object> getlampManage(Integer showNum, Integer curPage,HashMap<String,Object> sqlMap);











}
