package com.lamp.dao;

import com.lamp.model.Trecwarn;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TrecwarnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Trecwarn record);

    int insertSelective(Trecwarn record);

    Trecwarn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Trecwarn record);

    int updateByPrimaryKey(Trecwarn record);
    
}