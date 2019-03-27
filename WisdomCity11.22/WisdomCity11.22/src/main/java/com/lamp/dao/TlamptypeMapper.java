package com.lamp.dao;

import com.lamp.model.Tlamptype;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TlamptypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tlamptype record);

    int insertlamptype(Tlamptype record);

    Tlamptype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tlamptype record);

    int updateByPrimaryKey(Tlamptype record);

    List<Map<String,Object>> selectAlllamptype();

    /*��ת�ƾ����͹���ҳ�沢��ҳ*/
    List<Map<String,Object>> queryLampTypeManageList(@Param("org_code") String org_code,@Param("typeName") String typeName,@Param("page") Integer page);

    /*��ѯ����ֵ��Ϣ*/
    List<Map<String,Object>> querydimmingmodeList();

    /*增加灯具类型信息*/
    Integer addTlamptype(Tlamptype record);

    /*查询灯具信息*/
    List<Map<String,Object>> queryLampType(@Param("org_code") String org_code,@Param("id") Integer id);

    /*查询所有灯具信息*/
    List<Map<String,Object>> queryLampTypeAll();

    /*更新灯具信息*/
    int updateTypeManage(Tlamptype record);

    /*查询是否有灯具类型*/
    int queryLampM(@Param("org_code") String org_code,@Param("mid") String mid,@Param("msg") String msg);

    Integer LampTypeBatchDelete(@Param("list") List<Tlamptype> list); //批量删除

    List<Map<String,Object>> queryLampRelation(@Param("list") List<Tlamptype> list); //批量删除

    List<Map<String,Object>> getLampTypeList(HashMap<String,Object> map);

    int getCountLampTypeList(HashMap<String,Object> map);


























}