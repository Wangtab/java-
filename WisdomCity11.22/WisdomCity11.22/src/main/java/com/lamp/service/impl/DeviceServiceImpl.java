package com.lamp.service.impl;

import com.lamp.dao.*;
import com.lamp.model.*;
import com.lamp.service.DeviceService;
import com.lamp.utils.DealPage;
import com.lamp.utils.PlatformUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lamp.service.impl.OperationServiceImpl.obtainPage;

/**
 * @Description: Created by MSI on 2017/11/6.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private ToperlogMapper toperlogMapper;
    @Autowired
    private TlampMapper tlampMapper;

    @Autowired
    private TlamptypeMapper tlamptypeMapper;

    @Autowired
    private TconcenstatusMapper tconcenstatusMapper;
    public  List<Map<String,Object>> selectAllckind(){
        return null;
    }

    public int DeleteckindById(Integer id){
        return 0;
    }

    public  List<Map<String,Object>> selectBycname(String kindname){
        return null;
    }

    public int insertSelectivelamp(Tlamp record){
        return tlampMapper.insertSelectivelamp(record);
    }


    public  List<Tlamp> selectAlllamp(){
        return tlampMapper.selectAlllamp();
    }

    public List<Map<String,Object>> selectlampsite(){
        return tlampMapper.selectlampsite();
    }

    @Override
    public List<Map<String, Object>> selectAllcontrolkind() {
        return null;
    }

    public int insertlamptype(Tlamptype record){
        return tlamptypeMapper.insertlamptype(record);
    }

    public  List<Map<String,Object>> selectAlllamptype(){
        return tlamptypeMapper.selectAlllamptype();
    }

    public int insertOperLog(Toperlog record){
        return toperlogMapper.insertOperLog(record);
    }

    public Map<String,Object> getConcenStatusList(Integer showNum, Integer curPage,HashMap<String,Object> sqlMap){
        Map<String,Object> dataMap = new HashMap<>();
        int count = tconcenstatusMapper.getConcenStatusCount(sqlMap);
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer num = (curPage -1) * showNum;
        sqlMap.put("num",num);
        sqlMap.put("showNum",showNum);
        List<Map<String, Object>> list = tconcenstatusMapper.getConcenStatusList(sqlMap);
        dataMap.put("count",count);
        dataMap.put("datas",list);
        return dataMap;
    }

    public List<Map<String,Object>> exportConcenStatusData(HashMap<String,Object> map){
        List<Map<String,Object>>  mapList =  tconcenstatusMapper.exportConcenStatusData(map);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> getLmCnameForSelect(String orgCode) {
        return toperlogMapper.getLmCnameForSelect(orgCode);
    }

    @Override
    public  List<Map<String, Object>> getName(Integer id) {
        return toperlogMapper.getName(id);
    }
}
