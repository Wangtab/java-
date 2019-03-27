package com.lamp.dao;

import com.lamp.model.TMenu;
import com.lamp.model.TMenuBtn;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TMenuMapper {
    List<TMenu> getMenuData(Integer id);

    List<Map<String, Object>> getFistMenuData();

    HashMap<String,Object> getMenuDataById(Integer id);

    int updateMenuData(TMenu menu);

    int saveMenuData(TMenu menu);

    int delMenuById(Integer id);

    int getMenuNumByCname(@Param("menuName") String menuName);

    List<Map<String, Object>> getMenuDataForPage(@Param("menuName") String menuName,@Param("num") Integer num,@Param("showNum") Integer showNum,@Param("orderBy") String orderBy,@Param("sort") String sort);

    int getMenuIdByMenuCode(@Param("menuCode") String menuCode);

    List<Map<String, Object>> getMenuDataByRoleId(Integer id);

    List<TMenuBtn> getMenuDataAndBtns(Integer id);


}
