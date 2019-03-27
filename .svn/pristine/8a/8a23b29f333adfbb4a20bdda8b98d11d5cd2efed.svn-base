package com.lamp.service.impl;

import com.lamp.dao.TammeterStatusMapper;
import com.lamp.service.TammStatusService;
import com.lamp.utils.DealPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TammStatusImpl implements TammStatusService {

    @Autowired
    private TammeterStatusMapper statusMapper;

    @Override
    public Map<String, Object> getammStatusData(HashMap<String,Object> sqlMap) {
        Integer showNum = Integer.parseInt(sqlMap.get("showNum").toString());
        Integer curPage = Integer.parseInt(sqlMap.get("curPage").toString());
        Map<String,Object> dataMap = new HashMap<>();
        int count = statusMapper.getammStatusDataCount(sqlMap);
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer num = (curPage -1) * showNum;
        sqlMap.put("num",num);
        List<Map<String, Object>> list = statusMapper.getammeterStatusList(sqlMap);
        dataMap.put("datas", list);
        dataMap.put("count",count);
        return dataMap;
    }

    @Override
    public ArrayList<ArrayList<String>> ammStateExportExcelList(Integer areaId,Integer roadId,String orgCode) {
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("areaId",areaId);
        sqlMap.put("roadId",roadId);
        sqlMap.put("orgCode",orgCode);
        List<Map<String,Object>> mapList=statusMapper.getExceportList(sqlMap);
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < mapList.size(); i++) {
            ArrayList<String> list = new ArrayList<String>();
            Integer id = i + 1;
            list.add(id.toString());
            if (mapList.get(i).get("areaName") != null) {//区域
                list.add(mapList.get(i).get("areaName").toString());
            } else {
                list.add("");
            }
            if (mapList.get(i).get("road_name") != null) {//道路
                list.add(mapList.get(i).get("road_name").toString());
            } else {
                list.add("");
            }
            if (mapList.get(i).get("name") != null) {//配电箱
                list.add(mapList.get(i).get("name").toString());
            } else {
                list.add("");
            }
            if (mapList.get(i).get("c_name") != null){//电表名称
                list.add(mapList.get(i).get("c_name").toString());
            }else {
                list.add("");
            }
            if (mapList.get(i).get("num") != null){//度数
                list.add(mapList.get(i).get("num").toString());
            }else {
                list.add("");
            }
            if (mapList.get(i).get("record_time") != null){//记录时间
                list.add(mapList.get(i).get("record_time").toString());
            }else {
                list.add("");
            }

            arrayLists.add(list);
        }

        return arrayLists;
    }

    @Override
    public  Map<String, Object> getPowerPriceData(HashMap<String,Object> sqlMap){
        Integer showNum = Integer.parseInt(sqlMap.get("showNum").toString());
        Integer curPage = Integer.parseInt(sqlMap.get("curPage").toString());
        Map<String,Object> dataMap = new HashMap<>();
        int count = 1;
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer num = (curPage -1) * showNum;
        sqlMap.put("num",num);
        List<Map<String, Object>> list = statusMapper.getPowerPriceData(sqlMap);
        dataMap.put("datas", list);
        dataMap.put("count",count);
        return dataMap;
    }

}

