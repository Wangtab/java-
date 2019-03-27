package com.lamp.controller;

import com.lamp.model.TMenu;
import com.lamp.model.Tsysuser;
import com.lamp.service.IMenuService;
import com.lamp.utils.GetLocalTimes;
import com.lamp.utils.PlatformUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 菜单控制器
 */
@Controller
@RequestMapping("menu")
public class MenuController extends  BaseController {

    @Autowired
    private IMenuService iMenuService;

    /**
     * 菜单页面使用
     * @param response
     * @throws IOException
     */
    @RequestMapping("getMenuData")
    public void getMenuData(HttpServletResponse response,HttpServletRequest request) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        Integer roleId = user.getAuthId();
        List<TMenu> list =  iMenuService.getMenuData(roleId);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 获取菜单所对应的按钮信息
     */
    @RequestMapping("getMenuDataByRoleId")
    public void getMenuDataByRoleId(HttpServletResponse response,Integer roleId) throws IOException{
        List<Map<String, Object>> list  =  iMenuService.getMenuDataByRoleId(roleId);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 获取菜单和菜单对应的按钮
     */
    @RequestMapping("getMenuDataAndBtns")
    public void getMenuDataAndBtns(HttpServletResponse response,HttpServletRequest request) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        Integer roleId = user.getAuthId();
        List<TMenu> list =  iMenuService.getMenuDataAndBtns(roleId);
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }



    /**
     * 获取第一层菜单
     */
    @RequestMapping("getFistMenuData")
    public void getFistMenuData(HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = iMenuService.getFistMenuData();
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }

    /**
     * 根据id获取菜单信息
     * @param response
     * @throws IOException
     */
    @RequestMapping("getMenuDataById")
    public void getMenuDataById(Integer id,HttpServletResponse response) throws IOException {
        Map<String, Object> map = iMenuService.getMenuDataById(id);
        response.getWriter().print(JSONArray.fromObject(map).toString());
    }

    /**
     * 保存菜单信息
     */
    @RequestMapping("saveMenuData")
    public void saveMenuData(TMenu menu, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Tsysuser user = PlatformUtils.getLoginUser(request);
        menu.setOperId(user.getId());
        menu.setOperTime(GetLocalTimes.getNowTime());
        Integer status = 0;
        if(menu.getId() == null){
            status = iMenuService.saveMenuData(menu);
        }else{
            status = iMenuService.updateMenuData(menu);
        }
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);
    }

    /**
     * 删除菜单信息
     * @param id
     * @param response
     */
    @RequestMapping("delMenuById")
    public void delMenuById(Integer id,HttpServletResponse response) throws IOException {
        Integer status = iMenuService.delMenuById(id);
        String result = status == 1 ? "y" : "n";
        response.getWriter().print(result);
    }

    /**
     * 分页查询
     */
    @RequestMapping("getMenuDataForPage")
    public void getMenuDataForPage(Integer showNum, Integer curpage,String menuName,
                                   String orderBy,String sort,HttpServletResponse response) throws IOException {
        Map<String,Object> list = null;
        try {
          list =  iMenuService.getMenuDataForPage(showNum,curpage,menuName,orderBy,sort);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(null == list || list.size() == 0){
            response.getWriter().print("n");
        }
        response.getWriter().print(JSONArray.fromObject(list).toString());
    }


}
