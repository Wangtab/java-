package com.lamp.controller;
import com.lamp.model.Tsysuser;
import com.lamp.service.DeviceService;
import com.lamp.utils.ExcelUtil;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Controller
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("getConcenStatusList")
    public void getConcenStatusList(Integer areaId,Integer roadId,Integer concenSelect,Integer showNum, Integer curPage,String sort,String orderBy,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取登录人信息
        Tsysuser user = PlatformUtils.getLoginUser(request);
        //获取org_code
        String org_code = user.getTorganization().getOrgCode();
        HashMap<String,Object> dataMap = new HashMap<>();
        //将org_code放进map
        dataMap.put("org_code",org_code);
        dataMap.put("sort",sort);
        dataMap.put("orderBy",orderBy);
        dataMap.put("areaId",areaId);
        dataMap.put("roadId",roadId);
        dataMap.put("concenSelect",concenSelect);
        Map<String,Object> list = null;
        try {
            list = deviceService.getConcenStatusList(showNum,curPage,dataMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
            return;
        }
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    @RequestMapping("exportConcenStatusData")
    public void exportConcenStatusData(String sort,String orderBy,Integer areaId,Integer roadId,Integer concenSelect,HttpServletRequest request,HttpServletResponse response) throws IOException{
        String org_code = PlatformUtils.getLoginUser(request).getTorganization().getOrgCode();
        HashMap<String,Object> dataMap = new HashMap<>();
        //将org_code放进map
        dataMap.put("org_code",org_code);
        dataMap.put("sort",sort);
        dataMap.put("orderBy",orderBy);
        dataMap.put("areaId",areaId);
        dataMap.put("roadId",roadId);
        dataMap.put("concenSelect",concenSelect);
        List<Map<String, Object>> list =  deviceService.exportConcenStatusData(dataMap);
        if(null == list || list.size() == 0){
            response.getWriter().print("<h1 style = 'color:#fff'>未找到相关数据</h1>");
            return;
        }

        Map<String,String> titleMap = new LinkedHashMap<>();
        titleMap.put("areaName","区域名称");
        titleMap.put("concentratorname","集中器名称");
        titleMap.put("link_status","联网状态");
        titleMap.put("a_ele","A相电流");
        titleMap.put("a_pov","A相电压");
        titleMap.put("a_power","A相功率");
        titleMap.put("b_ele","B相电流");
        titleMap.put("b_pov","B相电压");
        titleMap.put("b_power","控制器类型");
        titleMap.put("c_ele","C相电流");
        titleMap.put("c_pov","C相电压");
        titleMap.put("c_power","C相功率");
        titleMap.put("temp","温度");
        titleMap.put("record_time","记录时间");

        com.alibaba.fastjson.JSONArray ja =new com.alibaba.fastjson.JSONArray();
        for(int i=0;i<list.size();i++){
            ja.add(list.get(i));
        }
        String title = "控制箱状态报表";
        ExcelUtil.downloadExcelFile(title, titleMap,ja,response);
    }




    @RequestMapping("getmenuname")
    public void getmenuname(Integer id,HttpServletResponse response) throws  IOException{
        List<Map<String, Object>> dataMap =deviceService.getName(id);
        response.getWriter().print(JSONArray.fromObject(dataMap).toString());
    }
}
