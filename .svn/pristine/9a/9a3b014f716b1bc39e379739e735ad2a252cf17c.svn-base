package com.lamp.service;

import com.lamp.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Created by MSI on 2017/11/6.
 */
public interface DeviceService {

    List<Map<String,Object>> selectAllckind();

    int DeleteckindById(Integer id);

    List<Map<String,Object>> selectBycname(String kindname);

    int insertSelectivelamp(Tlamp record);

    List<Tlamp> selectAlllamp();
    List<Map<String,Object>> selectlampsite();

    List<Map<String,Object>> selectAllcontrolkind();

    int insertlamptype(Tlamptype record);

    List<Map<String,Object>> selectAlllamptype();

    int insertOperLog(Toperlog record);

    Map<String,Object> getConcenStatusList(Integer showNum, Integer curPage,HashMap<String,Object> sqlMap);

    List<Map<String,Object>> exportConcenStatusData(HashMap<String,Object> map);

    List<Map<String, Object>> getLmCnameForSelect(String orgCode);

    List<Map<String, Object>> getName(Integer id);//二级联动查询模块名称
}
