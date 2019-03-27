package com.lamp.dao;

import com.lamp.model.Toperlog;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ToperlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Toperlog record);

    int insertOperLog(Toperlog record);

    Toperlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Toperlog record);

    int updateByPrimaryKey(Toperlog record);

    List<Map<String, Object>> getLmCnameForSelect(@Param("orgCode") String orgCode);

    List<Map<String, Object>> getName(Integer id);//二级联动查询模块名称
}