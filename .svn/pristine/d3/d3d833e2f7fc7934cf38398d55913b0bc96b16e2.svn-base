package com.lamp.service;

import com.lamp.model.TMenu;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMenuService {
    List<TMenu> getMenuData(Integer roleId);

    List<Map<String, Object>> getFistMenuData();

    HashMap<String,Object> getMenuDataById(Integer id);

    int updateMenuData(TMenu menu);

    int saveMenuData(TMenu menu);

    int delMenuById(Integer id);

    Map<String,Object> getMenuDataForPage(Integer showNum, Integer curpage,String menuName,String orderBy,String sort);

    List<Map<String, Object>> getMenuDataByRoleId(Integer roleId);

    List<TMenu> getMenuDataAndBtns(Integer roleId);

}
