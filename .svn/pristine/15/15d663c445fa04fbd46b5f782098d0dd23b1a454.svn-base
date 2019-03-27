package com.lamp.service.impl;

import com.lamp.dao.TMenuMapper;
import com.lamp.model.TMenu;
import com.lamp.model.TMenuBtn;
import com.lamp.service.IMenuService;
import com.lamp.utils.DealPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IMenuServiceImpl implements IMenuService {

    @Autowired
    private TMenuMapper tMenuMapper;

    @Override
    public List<TMenu> getMenuData(Integer roleId){
        List<TMenu> list = tMenuMapper.getMenuData(roleId);
        list = dealMenuDataOrder(list);
        return list;
    }

    private List<TMenu> dealMenuDataOrder(List<TMenu> list){
        if(null == list || list.size() == 0){
            return null;
        }

        List<TMenu> topMenuList = new ArrayList();
        for (TMenu  tmenu: list){
            Integer pid =  tmenu.getPid();
            if(0 == pid){
                topMenuList.add(tmenu);
            }
        }
        Collections.sort(topMenuList);
        for (TMenu menu: topMenuList){
            Integer id = menu.getId();
            List<TMenu> secMenuList = new ArrayList<>();
            for (TMenu menus: list){
                TMenu secondMenu = menus;
                if(id == secondMenu.getPid()){
                    secMenuList.add(secondMenu);
                }
            }
            Collections.sort(secMenuList);
            menu.setList(secMenuList);
        }
        return topMenuList;
    }

    public List<TMenu> getMenuDataAndBtns(Integer roleId){
        List<TMenu> list = tMenuMapper.getMenuData(roleId);
        if(list == null || list.size() == 0){
            return null;
        }
        List<TMenuBtn> btnList = tMenuMapper.getMenuDataAndBtns(roleId);
        if(null == btnList || btnList.size() == 0){
            return list;
        }
        for (TMenu menu : list){
            if(0 == menu.getId()){
                continue;
            }
            Integer menuId = menu.getId();
            List<TMenuBtn> tempBtnList = new ArrayList<>();
            for (TMenuBtn tMenuBtn : btnList){
                if(menuId == tMenuBtn.getMenuId()){
                    tempBtnList.add(tMenuBtn);
                }
            }
            menu.setBtnList(tempBtnList);
        }
        list = dealMenuDataOrder(list);
        return list;
    }

    public List<Map<String, Object>> getMenuDataByRoleId(Integer roleId){
        return tMenuMapper.getMenuDataByRoleId(roleId);
    }



    @Override
    public List<Map<String, Object>> getFistMenuData() {
        return tMenuMapper.getFistMenuData();
    }

    @Override
    public HashMap<String, Object> getMenuDataById(Integer id) {
        return tMenuMapper.getMenuDataById(id);
    }

    @Override
    public int updateMenuData(TMenu menu) {
        return tMenuMapper.updateMenuData(menu);
    }

    @Override
    public int saveMenuData(TMenu menu) {
        return tMenuMapper.saveMenuData(menu);
    }

    @Override
    public int delMenuById(Integer id) {
        return tMenuMapper.delMenuById(id);
    }

    @Override
    public  Map<String,Object> getMenuDataForPage(Integer showNum, Integer curpage,String menuName,String orderBy,String sort){
        Map<String,Object> dataMap = new HashMap<>();
        int count = tMenuMapper.getMenuNumByCname(menuName);
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curpage = DealPage.dealPage(curpage + "",maxPage);
        Integer num = (curpage -1) * showNum;
        List<Map<String, Object>> list = tMenuMapper.getMenuDataForPage(menuName,num,showNum,orderBy,sort);
        dataMap.put("count",count);
        dataMap.put("datas",list);
        return dataMap;
    }

}
