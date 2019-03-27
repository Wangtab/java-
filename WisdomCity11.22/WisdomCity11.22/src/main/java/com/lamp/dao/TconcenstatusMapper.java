package com.lamp.dao;

import com.lamp.model.Tconcenstatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TconcenstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tconcenstatus record);

    int insertSelective(Tconcenstatus record);

    Tconcenstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tconcenstatus record);

    int updateByPrimaryKey(Tconcenstatus record);

    int getConcenStatusCount(HashMap<String,Object> map);

    List<Map<String,Object>> getConcenStatusList(HashMap<String,Object> map);

    List<Map<String,Object>> exportConcenStatusData(HashMap<String,Object> map);
}