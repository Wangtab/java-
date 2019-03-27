package com.lamp.service;

import com.lamp.model.Tstock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017-12-01.
 * 考核管理业务接口
 */
public interface AssessmentService {

    List<String> equipMap(String catName); //二级联动设备信息

    List<Map<String,Object>>getStockubyid(Integer Id);//根据id获取库存信息

    List<Map<String,Object>> selectRoadAll();
}
