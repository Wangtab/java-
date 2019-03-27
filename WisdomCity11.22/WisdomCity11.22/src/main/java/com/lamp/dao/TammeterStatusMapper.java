package com.lamp.dao;

import com.lamp.model.Tammeterstatus;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TammeterStatusMapper {
    List<Map<String, Object>> getammeterStatusList(HashMap<String,Object> sqlMap);

    int getammStatusDataCount(HashMap<String,Object> sqlMap);

    List<Map<String,Object>> getExceportList(HashMap<String,Object> sqlMap);

    List<Map<String, Object>> getPowerPriceData(HashMap<String,Object> sqlMap);


}
