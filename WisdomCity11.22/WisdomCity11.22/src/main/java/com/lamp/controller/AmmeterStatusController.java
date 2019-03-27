package com.lamp.controller;

import com.lamp.service.TammStatusService;
import com.lamp.utils.ExcelFileGenerator;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AmmeterStatusController extends  BaseController {


    @Autowired
    private TammStatusService tammStatusService;

    @RequestMapping("getammStatusData")
    public void getammStatusData(HttpServletResponse response, HttpServletRequest request,Integer areaId,Integer roadId,Integer showNum, Integer curPage,String orderBy,String sort) throws IOException {
        Map<String,Object> list = null;
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("showNum",showNum);
        sqlMap.put("curPage",curPage);
        sqlMap.put("orderBy",orderBy);
        sqlMap.put("sort",sort);
        sqlMap.put("areaId",areaId);
        sqlMap.put("roadId",roadId);
        sqlMap.put("orgCode",orgCode);
        try {
            list =  tammStatusService.getammStatusData(sqlMap);

        } catch (Exception e){
            e.printStackTrace();
        }
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    @RequestMapping("getPowerPriceData")
    public void getPowerPriceData(HttpServletResponse response, HttpServletRequest request,Integer areaId,Integer roadId,Integer month,Integer showNum, Integer curPage,String orderBy,String sort) throws IOException {
        Map<String,Object> list = null;
        String orgCode = PlatformUtils.getLoginUserCode(request);
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("showNum",showNum);
        sqlMap.put("curPage",curPage);
        sqlMap.put("orderBy",orderBy);
        sqlMap.put("sort",sort);
        sqlMap.put("areaId",areaId);
        sqlMap.put("roadId",roadId);
        sqlMap.put("month",month);
        sqlMap.put("orgCode",orgCode);
        try {
            list =  tammStatusService.getPowerPriceData(sqlMap);

        } catch (Exception e){
            e.printStackTrace();
        }
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    @RequestMapping("ammStateExportExcel")
    public void ammStateExportExcel(HttpServletRequest request,HttpServletResponse response, Integer areaId,Integer roadId) throws Exception{
        try {
        ArrayList<String> fieldName = new ArrayList<String>();
        fieldName.add("序号");
        fieldName.add("区域");
        fieldName.add("道路");
        fieldName.add("配电箱");
        fieldName.add("电表名称");
        fieldName.add("度数");
        fieldName.add("记录时间");
        //*查询导出数据*//*
            String orgCode = PlatformUtils.getLoginUserCode(request);
        ArrayList<ArrayList<String>> fieldData = tammStatusService.ammStateExportExcelList(areaId,roadId,orgCode);
        //使用ExcelFileGenerator完成导出
        ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(fieldName,fieldData);
        OutputStream os = response.getOutputStream();
        //导出excel建议加上重置输出流，可以不加该代码，但是如果不加必须要保证输出流中不应该在存在其他数据，否则导出会有问题
        response.reset();
        //配置：
        //文件名
        String xlsName = "电表状态报表";
        String fileName = xlsName+"（"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"）.xls";
        fileName = new String(fileName.getBytes("gbk"),"iso-8859-1");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        response.setBufferSize(1024);
        //导出excel的操作
        excelFileGenerator.expordExcel(os);
        Integer count=0;
        if(fieldData!=null){
            count=fieldData.size();
        }
        Integer result=0;
        if(count>0){
            result=1;
        }else{
            result=0;
        }
        String msg = result == 1 ? "y":"n";
        response.getWriter().print(msg);
    }catch (Exception e) {
        String msg = "0";
        response.getWriter().print(msg);
        }
    }

}

