package com.lamp.dao;

import com.lamp.model.Tsysuser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TsysuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tsysuser record);

    int updateByPrimaryKey(Tsysuser record);

    String selectAuthByUserid(Integer userid);

    HashMap<String,Object> validedLoginUser(@Param("userName") String userName,@Param("password") String password);


}