package com.lamp.dao;

import com.lamp.model.Tlamp;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TlampMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tlamp record);

    int insertSelectivelamp(Tlamp record);

    Tlamp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tlamp record);

    int updateByPrimaryKey(Tlamp record);

    List<Tlamp> selectAlllamp();

    List<Map<String,Object>> selectlampsite();

    /*查询所有的路灯信息*/
    List<Map<String,Object>> queryLampALL(@Param("org_code") String org_code,@Param("areaId") String areaId,@Param("roadId") String roadId,
                                          @Param("lineId") String lineId,@Param("lampName") String lampName,@Param("page") Integer page);

    /*查询灯具类型*/
    List<Map<String,Object>> queryLampType(@Param("org_code") String org_code,@Param("mid") Integer mid);



    /*增加灯具信息*/
    int insertTlampManage(Tlamp tlamp);

    /*更新灯具信息*/
    int updateTlampMsg(Tlamp tlamp);

    /*查询控制器*/
    List<Map<String,Object>> queryController(@Param("org_code") String org_code,@Param("list") List<Map<String,Object>> list);

    /*查询控制器信息*/
    List<Map<String,Object>> queryControllerOne(@Param("org_code") String org_code,@Param("contId") String contId);

    /*查询所有的运营商信息*/
    List<Map<String,Object>> queryNetworkOperators();

    /*查询所有的协议类型*/
    List<Map<String,Object>> queryProtocolAll();


    /*查询所有的协议类型*/
    List<Map<String,Object>> queryPoleAll(@Param("org_code") String org_code);

    /*查询灯具信息*/
    List<Map<String,Object>> queryLamp(@Param("org_code") String org_code,@Param("id") Integer id);

    /*查询所有的灯具工厂信息*/
    List<Map<String,Object>> querylampFactoryAll(@Param("org_code") String org_code);

    /*查询所有的灯具信息*/
    List<Map<String,Object>> queryLampList(@Param("org_code") String org_code);

    /*根据名称查询区域道路ID*/
    List<Map<String,Object>> queryRegionalList(@Param("org_code") String org_code,@Param("region") String region,@Param("road") String road);

    /*查询配电箱编号*/
    List<Map<String,Object>> queryELeBoxList(@Param("org_code") String org_code,@Param("boxName") String boxName);


    List<Map<String,Object>> queryLampManage(HashMap<String,Object> map);

    int queryLampManageCount(HashMap<String,Object> map);




}