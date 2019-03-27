package com.lamp.service.impl;

import com.lamp.common.MenuModelCommon;
import com.lamp.common.SystemOperationCommon;
import com.lamp.dao.TMenuMapper;
import com.lamp.dao.TSystemOperationMapper;
import com.lamp.model.*;
import com.lamp.service.TUserOperationService;
import com.lamp.utils.LampSwitchInterface;
import com.lamp.utils.PlatformUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户操作类
 */
@Service
public class TUserOperationServiceImpl implements TUserOperationService {

    @Autowired
    private TSystemOperationMapper tSystemOperationMapper;

    @Autowired
    private TMenuMapper tMenuMapper;

    /**
     * 记录模块操作方法
     * @param nowObj 现在的对象
     * @param oldObj 之前的对象(要修改的对象)
     * @param userId 登录人ID
     * @param operationKind 操作类型 添加 删除 修改
     * @param result 执行数据后的结果 大于0执行成功 等于 0 执行失败
     * @param  modelName 模块名称
     */
    @Override
    public void recordOperationData(Object nowObj,Object oldObj,Integer userId,String operationKind,Integer result,String modelName){
        HashMap<String,Object> dataMap = new HashMap<>();
        //操作失败
        if(result == 0 || result == null){
            return;
        }
        dataMap.put("userId",userId);
        dataMap.put("kind_id",operationKind);
        dataMap.put("modelName",modelName);
        dataMap.put("nowObj",nowObj);
        dataMap.put("oldObj",oldObj);
        dealUserOperation(dataMap);
    }


    public void dealUserOperation(HashMap<String,Object> dataMap){
        String modelName = dataMap.get("modelName").toString();
        Integer menuId = tMenuMapper.getMenuIdByMenuCode(modelName);
        dataMap.put("menu_id",menuId);
        //区域管理
        if(MenuModelCommon.AREA_MANAGE.equals(modelName)){
            dealAreaManage(dataMap);
        }else if(MenuModelCommon.ROAD_MANAGE.equals(modelName)){
            dealRoadManage(dataMap);
        }else if(MenuModelCommon.ROADLINE_MANAGE.equals(modelName)){
            dealRoadLineManage(dataMap);
        }else if(MenuModelCommon.CENTRALIER_MANAGE.equals(modelName)){
            dealCentralierManage(dataMap);
        }else if(MenuModelCommon.CONTROLLER_MANAGE.equals(modelName)){
            dealControllerManage(dataMap);
        } else if(MenuModelCommon.ELEBOX_MANAGE.equals(modelName)){
            dealeleBoxmanage(dataMap);
        } else if(MenuModelCommon.DISTRIBUTIONBOX.equals(modelName)){
            dealdistributionbox(dataMap);
        }else if(MenuModelCommon.LAMPKIND_MANAGE.equals(modelName)){
            deallampkind(dataMap);
        }else if(MenuModelCommon.LAMP_MANAGE.equals(modelName)){
            deallampmng(dataMap);
        }else if(MenuModelCommon.INFORMATION_MANGAGE.equals(modelName)){
            dealINFORMATIONManage(dataMap);
        }else if(MenuModelCommon.STOCK.equals(modelName)){
            dealStockManage(dataMap);
        }else if(MenuModelCommon.INSPECTION_MANAGE.equals(modelName)){
            dealinspectionManage(dataMap);
        }else if(MenuModelCommon.STANDARD_MANAGE_.equals(modelName)) {
            dealstandardManage(dataMap);
        }else if(MenuModelCommon.POSITIONMANAGE.equals(modelName)) {
            dealpositionManage(dataMap);
        }else if(MenuModelCommon.REPAIRPERSOONEL_MANAGE.equals(modelName)) {
            dealRepairpersonnelManage(dataMap);
        }else if(MenuModelCommon.PLANGROUP_MANAGE.equals(modelName)) {
            dealPlanGroupManage(dataMap);
        }else if(MenuModelCommon.GROUPSTRATEMANAGE.equals(modelName)) {
            dealGroupstrateManageManage(dataMap);
        }else if(MenuModelCommon.LAMPSTRATEGYMANAGE.equals(modelName)) {
            dealLampstrategyManage(dataMap);
        }else if(MenuModelCommon.LIGHTMODULATIONMANAGE.equals(modelName)) {
            dealLightmodulationManage(dataMap);
        }
    }
    //灯具管理
    private void deallampmng(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if(SystemOperationCommon.OPERATION_ADD.equals(operation)){ //添加
            Map nowObj = (Map)dataMap.get("nowObj");
            detailInfo = "灯具编号：" + nowObj.get("lampnum");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        }else if(SystemOperationCommon.OPERATION_UPDATE.equals(operation)){ //修改
            Map tt = (Map)dataMap.get("oldObj");
            Map tt2 = (Map)dataMap.get("nowObj");

            StringBuffer operdes1 = new StringBuffer();
            operdes1.append("编号【" + tt.get("lampnum") + "】灯具:");
            if (tt.get("lampnum") != null && tt2.get("lampnum") != null){
                if (!tt.get("lampnum").toString().equals(tt2.get("lampnum").toString())) {
                    operdes1.append("修改设备编号：【" + tt2.get("lampnum") + "】为【" +  tt.get("lampnum") + "】；");
                }
            }if (tt.get("roadlineId") != null && tt2.get("roadlineId") != null){
                if (!tt.get("roadlineId").toString().equals(tt2.get("roadlineId").toString())) {
                    operdes1.append("修改所属线路；" );
                }
            }if (tt.get("regionId") != null && tt2.get("regionId") != null){
                if (!tt.get("regionId").toString().equals(tt2.get("regionId").toString())) {
                    operdes1.append("修改所属区域；" );
                }
            }if (tt.get("roadId") != null && tt2.get("roadId") != null){
                if (!tt.get("roadId").toString().equals(tt2.get("roadId").toString())) {
                    operdes1.append("修改所属道路；" );
                }
            }if (tt.get("conId") != null && tt2.get("conId") != null){
                if (!tt.get("conId").toString().equals(tt2.get("conId").toString())) {
                    operdes1.append("修改所属控制器；" );
                }
            }
            if (tt.get("lamptypename") != null && tt2.get("lamptypename") != null){
                if (!tt.get("lamptypename").toString().equals(tt2.get("lamptypename").toString())) {
                    operdes1.append("修改灯具类型名"+ tt.get("lamptypename").toString()+"为"+tt2.get("lamptypename").toString());
                }
            }if (tt.get("dbcircuit") != null && tt2.get("dbcircuit") != null){
                if (!tt.get("dbcircuit").toString().equals(tt2.get("dbcircuit").toString())) {
                    operdes1.append("修改配电箱回路"+ tt.get("dbcircuit").toString()+"为"+tt2.get("dbcircuit").toString());
                }
            }
            if (tt.get("lo") != null &&tt.get("la") != null && tt2.get("lo") != null && tt2.get("la") != null) {
                if ((Double.parseDouble(tt.get("la").toString())!=Double.parseDouble(tt2.get("la").toString()))||((Double.parseDouble(tt.get("lo").toString())!=Double.parseDouble(tt2.get("lo").toString())))){
                    operdes1.append("调整了经纬度：【"+tt2.get("la")+","+tt2.get("lo")+"】为【"+tt.get("la")+","+tt.get("lo")+"】;");
                }}

            detailInfo = operdes1.toString();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        }else{ //删除
            Map nowObj = (Map)dataMap.get("nowObj");
            detailInfo = "灯具编号：" + nowObj.get("lampnum");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        commonCheckDeatilInfo(dataMap,detailInfo);
    }
    //区域管理
    private void dealAreaManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if(SystemOperationCommon.OPERATION_ADD.equals(operation)){ //添加
           Tareamanage tareamanage = (Tareamanage)dataMap.get("nowObj");
           detailInfo = "区域名称：" + tareamanage.getAreaname() + "区域描述：" + tareamanage.getAreades();
           dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        }else if(SystemOperationCommon.OPERATION_UPDATE.equals(operation)){ //修改
            Map oldObj = (Map)dataMap.get("oldObj");
            Tareamanage newObj = (Tareamanage)dataMap.get("nowObj");
            String areaName = oldObj.get("areaName").toString();
            if(!newObj.getAreaname().equals(areaName)){
                detailInfo = "区域名称：由 " + areaName + " 修改为：" + newObj.getAreaname() + ";";
            }
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        }else{ //删除
            Map map = (Map)dataMap.get("nowObj");
            detailInfo = "区域名称：" + map.get("areaName");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        commonCheckDeatilInfo(dataMap,detailInfo);
    }

    //道路管理
    private void dealRoadManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Troadmanage  troadmanage = (Troadmanage)dataMap.get("nowObj");
            detailInfo = "道路名称：" + troadmanage.getRoadName() + "道路描述：" + troadmanage.getRoadDes();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map oldObj = (Map)dataMap.get("oldObj");
            Troadmanage  troadmanage = (Troadmanage)dataMap.get("nowObj");
            String roadName = oldObj.get("road_name").toString();
            if(!troadmanage.getRoadName().equals(roadName)){
                detailInfo = "道路名称：由 " + roadName + " 修改为：" + troadmanage.getRoadName() + ";";
            }
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            Map map = (Map)dataMap.get("nowObj");
            detailInfo = "道路名称：" + map.get("road_name");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }

        commonCheckDeatilInfo(dataMap,detailInfo);
    }

    //线路配置管理
    private void dealRoadLineManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            TRoadLineManage tRoadLineManage = (TRoadLineManage)dataMap.get("nowObj");
            detailInfo = "线路名称：" + tRoadLineManage.getcName();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map oldObj = (Map)dataMap.get("oldObj");
            TRoadLineManage  tRoadLineManage = (TRoadLineManage)dataMap.get("nowObj");
            String cname = oldObj.get("cname").toString();
            if(!tRoadLineManage.getcName().equals(cname)){
                detailInfo = "线路名称：由 " + cname + " 修改为：" + tRoadLineManage.getcName() + ";";
            }
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            detailInfo = "线路名称：" + dataMap.get("cname");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }

        commonCheckDeatilInfo(dataMap,detailInfo);
    }

    //控制器管理
    private void dealCentralierManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Tcontroller tt = (Tcontroller) dataMap.get("nowObj");
            detailInfo = "控制器设备编号：" + tt.getNbCode();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map tt2 = (Map)dataMap.get("oldObj");
            Tcontroller tt = (Tcontroller) dataMap.get("nowObj");

            StringBuffer operdes1 = new StringBuffer();
            operdes1.append("【" + tt.getControllername() + "】集中器:");
            if (tt.getNbCode() != null && tt2.get("nb_code") != null){
                if (!tt.getNbCode().equals(tt2.get("nb_code").toString())) {
                    operdes1.append("修改设备编号：【" + tt2.get("nb_code") + "】为【" + tt.getNbCode() + "】；");
                }
            }
            if (tt.getControllerkindid() != null && tt2.get("controllerkindid") != null) {
                if (tt.getControllerkindid()!=Integer.parseInt(tt2.get("controllerkindid").toString())){
                    operdes1.append("修改集中器类型；");
                }}
            if (tt.getcNum() != null && tt2.get("c_num") != null) {
                if (!tt.getcNum().equals(tt2.get("c_num").toString())){
                    operdes1.append("修改型号：【"+tt2.get("c_num")+"】为【"+tt.getcNum()+"】；");
                }}
            if (tt.getFactoryName() != null && tt2.get("factory_name") != null) {
                if (!tt.getFactoryName().equals(tt2.get("factory_name").toString())){
                    operdes1.append("修改厂家：【"+tt2.get("factory_name")+"】为【"+tt.getFactoryName()+"】；");
                }}
            if (tt.getConcentratorId() != null && tt2.get("concentrator_id") != null) {
                if (tt.getConcentratorId()!=Integer.parseInt(tt2.get("concentrator_id").toString())){
                    operdes1.append("修改所属集中器；");
                }}
            if (tt.getBusiness() != null && tt2.get("business") != null) {
                if (tt.getBusiness()!=Integer.parseInt(tt2.get("business").toString())){
                    operdes1.append("修改所属运营商；");
                }}
            if (tt.getProtocol() != null && tt2.get("protocol") != null) {
                if (tt.getProtocol()!=Integer.parseInt(tt2.get("protocol").toString())){
                    operdes1.append("修改协议类型；");
                }}
            if (tt.getSimCode() != null && tt2.get("sim_code") != null) {
                if (!tt.getSimCode().equals(tt2.get("sim_code").toString())){
                    operdes1.append("修改Sim卡号：【"+tt2.get("sim_code")+"】为【"+tt.getSimCode()+"】；");
                }}

             detailInfo = operdes1.toString();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            Map map = (Map)dataMap.get("nowObj");
            detailInfo = "控制器设备编号：" + map.get("nb_code");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }

        commonCheckDeatilInfo(dataMap,detailInfo);
    }

    //集中器管理
    private void dealControllerManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Tconcentrator tt = (Tconcentrator)dataMap.get("nowObj");
            detailInfo = "集中器名称：" + tt.getConcentratorname();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map tt2 = (Map)dataMap.get("oldObj");
            Tconcentrator  tt = (Tconcentrator)dataMap.get("nowObj");

            StringBuffer operdes1 = new StringBuffer();
            operdes1.append("【" + tt.getConcentratorname() + "】集中器:");

            if (tt.getConcentratorname() != null && tt2.get("concentratorname") != null){
                if (!tt.getConcentratorname().equals(tt2.get("concentratorname").toString())) {
                    operdes1.append("修改集中器名称：【" + tt2.get("concentratorname") + "】为【" + tt.getConcentratorname() + "】;");
                }
            }
            if (tt.getConcentratoraddr() != null && tt2.get("concentratoraddr") != null) {
                if (!tt.getConcentratoraddr().equals(tt2.get("concentratoraddr").toString())) {
                    operdes1.append("修改集中器地址：【" + tt2.get("concentratoraddr") + "】为【" + tt.getConcentratoraddr() + "】;");
                }
            }
            if (tt.getConcentratorkindid() != null && tt2.get("concentratorkindid") != null) {
                if (tt.getConcentratorkindid()!=Integer.parseInt(tt2.get("concentratorkindid").toString())){
                    operdes1.append("修改集中器类型;");
                }}

            if (tt.getConcentratordes() != null && tt2.get("concentratordes") != null) {
                if (!tt.getConcentratordes().equals(tt2.get("concentratordes").toString())){
                    operdes1.append("修改集中器描述：【"+tt2.get("concentratordes")+"】为【"+tt.getConcentratordes()+"】;");
                }}
            if (tt.getAreaid() != null && tt2.get("areaid") != null) {
                if (tt.getAreaid()!=Integer.parseInt(tt2.get("areaid").toString())){
                    operdes1.append("变更所属区域;");
                }}
            if (tt.getIbox() != null && tt2.get("ibox") != null) {
                if (tt.getIbox()!=Integer.parseInt(tt2.get("ibox").toString())){
                    operdes1.append("变更所属配电箱;");
                }}
            if (tt.getLa() != null &&tt.getLo() != null && tt2.get("lo") != null && tt2.get("la") != null) {
                if ((tt.getLa()!=Double.parseDouble(tt2.get("la").toString()))||((tt.getLo()!=Double.parseDouble(tt2.get("lo").toString())))){
                    operdes1.append("调整了经纬度：【"+tt2.get("la")+","+tt2.get("lo")+"】为【"+tt.getLa()+","+tt.getLo()+"】;");
                }}

            detailInfo = operdes1.toString();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            Map map = (Map)dataMap.get("nowObj");
            detailInfo = "集中器名称：" + map.get("concentratorname");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }

        commonCheckDeatilInfo(dataMap,detailInfo);
    }

    private void dealdistributionbox(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Telecboxmanage tt = (Telecboxmanage)dataMap.get("nowObj");
            detailInfo = "配电箱号：" + tt.getName();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map tt2 = (Map)dataMap.get("oldObj");
            Telecboxmanage  tt = (Telecboxmanage)dataMap.get("nowObj");
            StringBuffer operdes1 = new StringBuffer();
            operdes1.append("【" + tt.getName() + "】配电箱:");

            if (tt.getName() != null && tt2.get("name") != null){
                if (!tt.getName().equals(tt2.get("name").toString())) {
                    operdes1.append("修改配电箱号：【" + tt2.get("name") + "】为【" + tt.getName() + "】");
                }
            }

            if (tt.getRoadId() != null && tt2.get("road_id") != null) {
                if (tt.getRoadId()!=Integer.parseInt(tt2.get("road_id").toString())){
                    operdes1.append("修改所属道路：【" + tt2.get("road_id") + "】为【" + tt.getRoadId() + "】");
                }}
            if (tt.getLongitude() != null &&tt.getLatitude() != null && tt2.get("longitude") != null && tt2.get("latitude") != null) {
                if ((tt.getLatitude()!=Double.parseDouble(tt2.get("latitude").toString()))||((tt.getLongitude()!=Double.parseDouble(tt2.get("longitude").toString())))){
                    operdes1.append("调整了经纬度：【"+tt2.get("latitude")+","+tt2.get("longitude")+"】为【"+tt.getLatitude()+","+tt.getLongitude()+"】");
                }}

            detailInfo = operdes1.toString();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            Map map = (Map)dataMap.get("nowObj");
            detailInfo = "配电箱号：" + map.get("name");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }

        commonCheckDeatilInfo(dataMap,detailInfo);
    }

    private void deallampkind(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Tlamptype tt = (Tlamptype)dataMap.get("nowObj");
            detailInfo = "灯具类型：" + tt.getLamptypename();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map tt = (Map)dataMap.get("oldObj");
            Tlamptype  tlamptype = (Tlamptype)dataMap.get("nowObj");
             StringBuffer operdes1 = new StringBuffer();
            operdes1.append("灯具类型【"+tt.get("lamptypename")+"】:");
            if(tlamptype.getLamptypename()!=null&&tt.get("lamptypename")!=null){
            if (!tlamptype.getLamptypename().equals(tt.get("lamptypename").toString())){
                operdes1.append("修改灯具类型名称：【"+tt.get("lamptypename")+"】为【"+tlamptype.getLamptypename()+"】；");
            }}

            if(tlamptype.getLamptypedes()!=null&&tt.get("lamptypedes")!=null){
            if (!tlamptype.getLamptypedes().equals(tt.get("lamptypedes").toString())){
                operdes1.append("修改类型描述：【"+tt.get("lamptypedes")+"】为【"+tlamptype.getLamptypedes()+"】；");
            }}

            if(tlamptype.getPower()!=null&&tt.get("power")!=null){
            if (tlamptype.getPower()!=Integer.parseInt(tt.get("power").toString())){
                operdes1.append("修改蓄电池功率：【"+tt.get("power")+"】为【"+tlamptype.getPower()+"】；");
            }}

            if(tlamptype.getDimmingmode()!=null&&tt.get("dimmingmode")!=null){
            if (!tlamptype.getDimmingmode().equals(tt.get("dimmingmode").toString())){
                operdes1.append("修改调光模式：【"+tt.get("dimmingmode")+"】为【"+tlamptype.getDimmingmode()+"】；");
            } }

            if(tlamptype.getFactoryid()!=null&&tt.get("factoryid")!=null){
            if (tlamptype.getFactoryid()!=Integer.parseInt(tt.get("factoryid").toString())){
                operdes1.append("修改所属厂家；");
            }}

            detailInfo = operdes1.toString();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            Map map = (Map)dataMap.get("nowObj");
            detailInfo = "灯具类型：" + map.get("lamptypename");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        commonCheckDeatilInfo(dataMap,detailInfo);
    }

    private void dealeleBoxmanage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Twatchmanage tt = (Twatchmanage)dataMap.get("nowObj");
            detailInfo = "电表名称：" + tt.getElecboxname();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){

            detailInfo = "update_operation";
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            /*Map map = (Map)dataMap.get("nowObj");*/
            detailInfo = "delete_operation";
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }

        commonCheckDeatilInfo(dataMap,detailInfo);
    }


    //施工标准管理
    private void dealstandardManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Tbuildstandard tbuildstandard = (Tbuildstandard)dataMap.get("nowObj");
            detailInfo = "添加了施工标准类型为：" + tbuildstandard.getBuildname()+"施工标准类型描述"+tbuildstandard.getBuilddescribe();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            HashMap oldObj = (HashMap)dataMap.get("oldObj");
            HashMap nowObj = (HashMap)dataMap.get("nowObj");
            String oldbuildname = oldObj.get("buildname").toString();
            String oldbuilddescribe=oldObj.get("builddescribe").toString();
            String nowbuildname = nowObj.get("buildname").toString();
            String nowbuilddescribe=nowObj.get("builddescribe").toString();
            StringBuffer operdes1 = new StringBuffer();
            operdes1.append("将"+"【" + oldbuildname + "】:");
            if(nowbuildname!=null&& oldbuildname!= null){
                if(!nowbuildname.equals(oldbuildname)){
                    operdes1.append( "施工标准类型：由 " + oldbuildname + " 修改为：" + nowbuildname +";");
                } }
            if(nowbuilddescribe!=null&& oldbuilddescribe!= null){
                if(!nowbuilddescribe.equals(oldbuilddescribe)){
                    operdes1.append("施工类型描述由:"+oldbuilddescribe+"修改为："+nowbuilddescribe+";");
                }}
            detailInfo=operdes1.toString();
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            HashMap nowObj = (HashMap)dataMap.get("nowObj");
            detailInfo = "删除了施工标准类型为：" + nowObj.get("buildname")+"施工标准类型描述"+nowObj.get("builddescribe");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }

        commonCheckDeatilInfo(dataMap,detailInfo);

    }

    //库存管理
    private void dealStockManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        StringBuffer operdes1 = new StringBuffer();
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Tstock tstock = (Tstock) dataMap.get("nowObj");
            operdes1.append("添加设备类别:" +tstock.getCatId()+"设备类型为"+tstock.getEquipName()+",入库时间:"+tstock.getTotime()+",改变数量"+tstock.getChangenum()+",备注:"+tstock.getNode());
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map oldObj = (Map)dataMap.get("oldObj");
            Map nowObj = (Map) dataMap.get("nowObj");
            String oldcatname = oldObj.get("catName").toString();
            String nowcatname = nowObj.get("catName").toString();
            if(nowcatname!=null&& oldcatname!= null) {
                if (!nowcatname.equals(oldcatname)) {
                    operdes1.append("类别：由 " + oldcatname + " 修改为：" + nowcatname + ";");
                } }
            String oldequipName = oldObj.get("equipName").toString();
            String nowequipName = nowObj.get("equipName").toString();
            if(nowequipName!=null&& oldequipName!= null) {
                if (!nowequipName.equals(oldequipName)) {
                    operdes1.append("类型：由 " + oldequipName + " 修改为：" + nowequipName + ";");
                } }
            String oldtotime = oldObj.get("totime").toString();
            String nowtotime = nowObj.get("totime").toString();
            if(nowtotime!=null&& oldtotime!= null) {
                if (!nowtotime.equals(oldtotime)) {
                    operdes1.append("入库时间：由 " + oldtotime + " 修改为：" + nowtotime + ";");
                } }
            String oldstocknum = oldObj.get("stocknum").toString();
             String nowstocknum= nowObj.get("stocknum").toString();
            if(nowstocknum!=null&& oldstocknum!= null) {
                if (!nowstocknum.equals(oldstocknum)) {
                    operdes1.append("库存数量：由 " + oldstocknum + " 修改为：" +nowstocknum + ";");
                } }
            String oldnode = oldObj.get("node").toString();
            String nownode = nowObj.get("node").toString();
            if(nownode!=null&& oldnode!= null) {
                if (!nownode.equals(oldnode)) {
                    operdes1.append("描述：由 " + oldnode + " 修改为：" + nownode + ";");
                } }
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {//删除
            Map oldobj = (Map)dataMap.get("oldObj");
            operdes1.append("删除的类别：" +oldobj.get("catName") +"设备类型为"+oldobj.get("equipName")+",入库时间:"+oldobj.get("totime")+",改变数量"+oldobj.get("changenum")+",备注:"+oldobj.get("node"));
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        detailInfo=operdes1.toString();
        commonCheckDeatilInfo(dataMap,detailInfo);

    }

    //巡检管理
    private void dealinspectionManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        StringBuffer operdes1 = new StringBuffer();
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Map nowObj=(Map)dataMap.get("nowObj");
            operdes1.append("添加巡检区域为：" +nowObj.get("areaName")+"道路为:"+nowObj.get("road_name")+"巡检人员为"+nowObj.get("name")+
                    "开始时间"+nowObj.get("startime")+"结束时间"+nowObj.get("endtime")+"描述"+nowObj.get("checkdescribe"));
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map oldObj = (Map)dataMap.get("oldObj");
            Map nowObj = (Map)dataMap.get("nowObj");
            String oldareaName = oldObj.get("areaName").toString();
            String nowareaName = nowObj.get("areaName").toString();
            if (!StringUtils.isEmpty(oldareaName)&&!StringUtils.isEmpty(nowareaName))
            if(!oldareaName.equals(nowareaName)){
                operdes1.append( "区域名称：由 " + oldareaName + " 修改为：" + nowareaName + ";");
            }
            String oldroad_name = oldObj.get("road_name").toString();
            String nowroad_name = nowObj.get("road_name").toString();
            if ((!StringUtils.isEmpty(oldroad_name) && !StringUtils.isEmpty(nowroad_name))){
                if(!nowroad_name.equals(oldroad_name)){
                    operdes1.append("道路：由 " + oldroad_name + " 修改为：" + nowroad_name + ";");
                }}
            String oldname = oldObj.get("name").toString();
            String nowname = nowObj.get("name").toString();
            if ((!StringUtils.isEmpty(oldname) && !StringUtils.isEmpty(nowname))){
                if(!nowname.equals(oldname)){
                    operdes1.append("维修人员：由 " + oldname + " 修改为：" + nowname + ";");
                }}
            String oldstartime = oldObj.get("startime").toString();
            String nowstartime = nowObj.get("startime").toString();
            if ((!StringUtils.isEmpty(nowstartime) && !StringUtils.isEmpty(oldstartime))){
                if(!nowstartime.equals(oldstartime)){
                    operdes1.append("开始时间：由 " + oldstartime + " 修改为：" + nowstartime + ";");
                }}
            String oldendtime = oldObj.get("endtime").toString();
            String nowendtime = nowObj.get("endtime").toString();
            if ((!StringUtils.isEmpty(oldendtime) && !StringUtils.isEmpty(nowendtime))){
                if(!nowendtime.equals(oldendtime)){
                    operdes1.append("结束时间：由 " + oldendtime + " 修改为：" + nowendtime + ";");
                }}
            String oldcheckdescribe = oldObj.get("checkdescribe").toString();
            String nowcheckdescribe = nowObj.get("checkdescribe").toString();
            if ((!StringUtils.isEmpty(oldcheckdescribe) && !StringUtils.isEmpty(nowcheckdescribe))){
                if(!nowcheckdescribe.equals(oldcheckdescribe)){
                    operdes1.append("生效日期：由 " + oldcheckdescribe + " 修改为：" + nowcheckdescribe + ";");
                }}
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            Map oldobj = (Map)dataMap.get("oldObj");
            operdes1.append("删除巡检为：" +oldobj.get("areaName")+"道路为:"+oldobj.get("road_name")+"巡检人员为"+oldobj.get("name")+
                    "开始时间"+oldobj.get("startime")+"结束时间"+oldobj.get("endtime")+"描述"+oldobj.get("checkdescribe"));
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        detailInfo=operdes1.toString();
        commonCheckDeatilInfo(dataMap,detailInfo);

    }

    //施工信息管理
    private void dealINFORMATIONManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        StringBuffer operdes1 = new StringBuffer();
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
             Map nowObj= (Map) dataMap.get("nowObj");
            String nowrepairtypeid =nowObj.get("repairtype").toString();
            String nowrepairtype=Integer.parseInt(nowrepairtypeid)==1?"维修":"更换";
            String nowdeal_resultid = nowObj.get("deal_result").toString();
            String nowdeal_result=Integer.parseInt(nowdeal_resultid)==1?"完成":"在维";
            operdes1.append("添加：编号为："+nowObj.get("ordernum")+","+
                    "区域名称为:"+nowObj.get("areaName")+","+"道路名称为:"+ nowObj.get("road_name")+","+"设备型号为:"+
                    nowObj.get("modelnum")+","+"设备编号:"+nowObj.get("devicenum")+","+"维修人员:"+nowObj.get("name")+
                    ","+"设备类型:"+nowObj.get("buildname")+","+"维护类型:"+nowrepairtype+","+"处理结果:"+nowdeal_result+
                    ","+"施工时间:"+nowObj.get("buildtime"));

            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map oldObj = (Map)dataMap.get("oldObj");
            Map nowObj= (Map) dataMap.get("nowObj");
            operdes1.append("【" + oldObj.get("ordernum") + "的施工信息】:");
            String oldareaName =oldObj.get("areaName").toString();
            String  nowareaName=nowObj.get("areaName").toString();
            if(nowareaName!= null && oldareaName!= null){
                if(!nowareaName.equals(oldareaName)){
                    operdes1.append("所属区域：由 " +oldareaName+ " 修改为：" +nowareaName+ ";");
                } }
            String oldroad_name =oldObj.get("road_name").toString();
            String nowroad_name=nowObj.get("road_name").toString();
            if(nowroad_name!= null && oldroad_name!= null){
                if(!nowroad_name.equals(oldroad_name)){
                    operdes1.append("所属路段：由 " +oldroad_name+ " 修改为：" +nowroad_name+ ";");
                } }
            String oldmodelnum =oldObj.get("modelnum").toString();
            String nowmodelnum =nowObj.get("modelnum").toString();
            if(nowmodelnum!= null && oldmodelnum!= null){
                if(!nowmodelnum.equals(oldmodelnum)){
                    operdes1.append("设备型号：由 " +oldmodelnum+ " 修改为：" +nowmodelnum+ ";");
                } }
            String olddevicenum =oldObj.get("devicenum").toString();
            String nowdevicenum=nowObj.get("devicenum").toString();
            if(nowdevicenum!= null && olddevicenum!= null){
                if(!nowdevicenum.equals(olddevicenum)){
                    operdes1.append("设备编号：由 " +nowdevicenum+ " 修改为：" +olddevicenum+ ";");
                } }

            String oldrepairmaname =oldObj.get("name").toString();
            String nowrepairmaname =nowObj.get("name").toString();
            if(nowrepairmaname!= null && oldrepairmaname!= null){
                if(!nowrepairmaname.equals(oldrepairmaname)){
                    operdes1.append("维修人员：由 " +oldrepairmaname+ " 换成：" +nowrepairmaname+ ";");
                } }

            String oldbuildtype =oldObj.get("buildname").toString();
            String nowbuildtype =nowObj.get("buildname").toString();
            if(nowbuildtype!= null && oldbuildtype!= null){
                if(!nowbuildtype.equals(oldbuildtype)){
                    operdes1.append("设备类型：由 " +oldbuildtype+ " 换成：" +nowbuildtype+ ";");
                } }
            String oldrepairtypeid =oldObj.get("repairtype").toString();
            String nowrepairtypeid =nowObj.get("repairtype").toString();
            if(nowrepairtypeid!= null && oldrepairtypeid!= null){
                if(!nowrepairtypeid.equals(oldrepairtypeid)){
                    String oldrepairtype=Integer.parseInt(oldrepairtypeid)==1?"维修":"更换";
                    String nowrepairtype=Integer.parseInt(nowrepairtypeid)==1?"维修":"更换";
                    operdes1.append("维护类型：由 " +nowrepairtype+ " 换成：" + oldrepairtype+ ";");
                } }
            String olddeal_resultid = oldObj.get("deal_result").toString();
            String nowdeal_resultid = nowObj.get("deal_result").toString();
            if(nowdeal_resultid!= null && olddeal_resultid!= null){
                if(!nowdeal_resultid.equals(olddeal_resultid)){
                    String olddeal_result=Integer.parseInt(olddeal_resultid)==1?"完成":"在维";
                    String nowdeal_result=Integer.parseInt(nowdeal_resultid)==1?"完成":"在维";
                    operdes1.append("处理结果：由 " +olddeal_result+ " 换成：" +nowdeal_result+ ";");
                } }
            String oldbuildtime =oldObj.get("buildtime").toString();
            String nowbuildtime =nowObj.get("buildtime").toString();
            if(nowbuildtime!= null && oldbuildtime!= null){
                if(!nowbuildtime.equals(oldbuildtime)){
                    operdes1.append("施工时间：由 " +oldbuildtime+ " 换成：" +nowbuildtime+ ";");
                } }

            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            Map oldObj= (Map) dataMap.get("nowObj");;
            String oldrepairtypeid =oldObj.get("repairtype").toString();
            String oldrepairtype=Integer.parseInt(oldrepairtypeid)==1?"维修":"更换";
            String olddeal_resultid = oldObj.get("deal_result").toString();
            String olddeal_result=Integer.parseInt(olddeal_resultid)==1?"完成":"在维";
            operdes1.append("删除施工信息管理：编号为："+oldObj.get("ordernum")+","+
                    "区域名称为:"+oldObj.get("areaName")+","+"道路名称为:"+ oldObj.get("road_name")+","+"设备型号为:"+
                    oldObj.get("modelnum")+","+"设备编号:"+oldObj.get("devicenum")+","+"维修人员:"+oldObj.get("name")+","+
                    "设备类型:"+oldObj.get("buildname")+","+"维护类型:"+oldrepairtype+","+"处理结果:"+olddeal_result+","+
                    "施工时间:"+oldObj.get("buildtime"));
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        detailInfo=operdes1.toString();
        commonCheckDeatilInfo(dataMap,detailInfo);

    }

    //位置管理
    private void dealpositionManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map oldObj = (Map)dataMap.get("oldObj");
            TCitySetting city= (TCitySetting)dataMap.get("nowObj");
            String cityName = oldObj.get("city_name").toString();
            if(!city.getCityName().equals(cityName)){
                detailInfo = "城市名称：由 " + cityName + " 修改为：" + city.getCityName() + ";";
            }
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        }
        commonCheckDeatilInfo(dataMap,detailInfo);

    }

    //维修人员管理
    private void dealRepairpersonnelManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        StringBuffer operdes1 = new StringBuffer();
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            TrepairPeople trepairPeople= (TrepairPeople)dataMap.get("nowObj");
            String sex =Integer.parseInt(trepairPeople.getSex().toString())==1?"女":"男";
            operdes1.append("添加了名为：" +trepairPeople.getName()+",工号"+trepairPeople.getNumber()+",工种"+
                    trepairPeople.getNumjob()+",性别"+sex+",手机:"+trepairPeople.getTel()+
                    ",地址"+trepairPeople.getAddress()+",的维修人员");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map oldObj = (Map)dataMap.get("oldObj");
            TrepairPeople trepairPeople= (TrepairPeople)dataMap.get("nowObj");
            operdes1.append("【" + oldObj.get("name") + "的信息】:");
            if(!StringUtils.isEmpty(trepairPeople.getName()) && !StringUtils.isEmpty(oldObj.get("name").toString())){
                if(!(trepairPeople.getName()).equals(oldObj.get("name").toString())){
                    operdes1.append("姓名：由 " +oldObj.get("name").toString()+ " 修改为：" +trepairPeople.getName()+ ";");
                } }

            if(!StringUtils.isEmpty(trepairPeople.getNumber()) && !StringUtils.isEmpty(oldObj.get("number").toString())){
                if(!(trepairPeople.getNumber()).equals(oldObj.get("number").toString())){
                    operdes1.append("工号：由 " +oldObj.get("number").toString()+ " 修改为：" +trepairPeople.getNumber()+ ";");
                } }
            if(!StringUtils.isEmpty(trepairPeople.getNumjob()) && !StringUtils.isEmpty(oldObj.get("numjob").toString())){
                if(!(trepairPeople.getNumjob()).equals(oldObj.get("numjob").toString())){
                    operdes1.append("工种：由 " +oldObj.get("numjob").toString()+ " 修改为：" +trepairPeople.getNumjob()+ ";");
                } }
            String oldsex =Integer.parseInt(oldObj.get("sex").toString())==1?"女":"男";
            String nowsex =Integer.parseInt(trepairPeople.getSex().toString())==1?"女":"男";
            if(!StringUtils.isEmpty(oldsex) && !StringUtils.isEmpty(nowsex)){
                if(!(nowsex).equals(oldsex)){
                    operdes1.append("性别：由 " +oldsex+ " 修改为：" +nowsex+ ";");
                } }
            if(!StringUtils.isEmpty(trepairPeople.getTel()) && !StringUtils.isEmpty(oldObj.get("tel").toString())){
                if(!(trepairPeople.getTel()).equals(oldObj.get("tel").toString())){
                    operdes1.append("手机：由 " +oldObj.get("tel").toString()+ " 修改为：" +trepairPeople.getTel()+ ";");
                } }
            if(!StringUtils.isEmpty(trepairPeople.getAddress()) && !StringUtils.isEmpty(oldObj.get("address").toString())){
                if(!(trepairPeople.getAddress()).equals(oldObj.get("address").toString())){
                    operdes1.append("地址：由 " +oldObj.get("address").toString()+ " 修改为：" +trepairPeople.getAddress()+ ";");
                } }
        } else {
            Map oldObj= (Map)dataMap.get("oldObj");
            String sex =Integer.parseInt(oldObj.get("sex").toString())==1?"女":"男";
            operdes1.append( "删除了名为：" +oldObj.get("name")+",工号"+oldObj.get("number")+",工种"+
                    oldObj.get("numjob")+",性别"+sex+",手机:"+oldObj.get("tel")+
                    ",地址"+oldObj.get("address")+",的维修人员");
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        detailInfo=operdes1.toString();
        commonCheckDeatilInfo(dataMap,detailInfo);

    }

    //分组管理
    private void dealPlanGroupManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        StringBuffer operdes1 = new StringBuffer();
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            String Name=(String)dataMap.get("nowObj");
            operdes1.append("添加分组管理名为：" +Name);
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map oldObj = (Map)dataMap.get("oldObj");
            Map nowObj = (Map)dataMap.get("nowObj");
            String  oldareaName= oldObj.get("areaName").toString();
            String  nowareaName= nowObj.get("areaName").toString();
            if (nowareaName != null && oldareaName!=null){
            if(!nowareaName.equals(oldareaName)){
                operdes1.append("区域名称：由 " + oldareaName + " 修改为：" + nowareaName + ";");
            }}
            String  oldgroupname= oldObj.get("group_name").toString();
            String  nowgroupname= nowObj.get("group_name").toString();
            if (nowareaName != null && oldareaName!=null){
                if(!nowareaName.equals(oldareaName)){
                    operdes1.append("分组名称：由 " + oldgroupname + " 修改为：" + nowgroupname + ";");
                }}
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            Map oldobj = (Map)dataMap.get("oldObj");
            operdes1.append( "删除分组的详细信息：区域为:" + oldobj.get("areaName")+"分组名称:"+oldobj.get("group_name"));
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        detailInfo=operdes1.toString();
        commonCheckDeatilInfo(dataMap,detailInfo);

    }

    //分组策略
    private void dealGroupstrateManageManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        StringBuffer operdes1 = new StringBuffer();
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Map nowObj=(Map)dataMap.get("nowObj");
            String content = Integer.parseInt(nowObj.get("content_id").toString())==1?"开关灯":"调光";
            operdes1.append("添加分组策略:计划名称为：" +nowObj.get("cname")+",区域为:"+nowObj.get("areaName")+
                    "<br>"+",分组:"+nowObj.get("group_name")+",计划内容:"+content+",生效日期:"+nowObj.get("start_time")
                    +",失效日期:"+nowObj.get("end_time")+"<br>"+",开灯时间:"+nowObj.get("open_time")+",调光值:"+nowObj.get("dimming"));
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map oldObj = (Map)dataMap.get("oldObj");
            Map nowObj = (Map)dataMap.get("nowObj");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>oldobj"+oldObj);
            String  oldcname= oldObj.get("cname").toString();
            String  nowcname= nowObj.get("cname").toString();
            if (!StringUtils.isEmpty(nowcname)  && !StringUtils.isEmpty(oldcname)){
                if(!nowcname.equals(oldcname)){
                    operdes1.append("计划名称：由 " + oldcname + " 修改为：" + nowcname + ";");
                }}
            String  oldareaName= oldObj.get("areaName").toString();
            String  nowareaName= nowObj.get("areaName").toString();
            if ((!StringUtils.isEmpty(oldareaName) && !StringUtils.isEmpty(nowareaName))){
                if(!nowareaName.equals(oldareaName)){
                    operdes1.append("区域名称：由 " + oldareaName + " 修改为：" + nowareaName + ";");
                }}
            String  oldgroup_name= oldObj.get("group_name").toString();
            String  nowgroup_name= nowObj.get("group_name").toString();
            if ((!StringUtils.isEmpty(oldgroup_name) && !StringUtils.isEmpty(nowgroup_name))){
                if(!nowgroup_name.equals(oldgroup_name)){
                    operdes1.append("分组名称：由 " + oldgroup_name + " 修改为：" + nowgroup_name + ";");
                }}
            String nowcontent = Integer.parseInt(nowObj.get("content_id").toString())==1?"开关灯":"调光";
            String oldcontent = Integer.parseInt(oldObj.get("content_id").toString())==1?"开关灯":"调光";
            if ((!StringUtils.isEmpty(oldcontent) && !StringUtils.isEmpty(nowcontent))){
                if(!nowcontent.equals(oldcontent)){
                    operdes1.append("计划内容：由 " + oldcontent + " 修改为：" + nowcontent + ";");
                }}
            String  oldstart_time= oldObj.get("start_time").toString();
            String  nowstart_time= nowObj.get("start_time").toString();
            if ((!StringUtils.isEmpty(oldstart_time) && !StringUtils.isEmpty(nowstart_time))){
                if(!nowstart_time.equals(oldstart_time)){
                    operdes1.append("生效日期：由 " + oldstart_time + " 修改为：" + nowstart_time + ";");
                }}
            String  oldend_time= oldObj.get("end_time").toString();
            String  nowend_time= nowObj.get("end_time").toString();
            if ((!StringUtils.isEmpty(oldend_time) && !StringUtils.isEmpty(nowend_time))){
                if(!nowend_time.equals(oldend_time)){
                    operdes1.append("失效日期：由 " + oldend_time + " 修改为：" + nowend_time + ";");
                }}
            String  oldopen_time= oldObj.get("open_time").toString();
            String  nowopen_time= nowObj.get("open_time").toString();
            if ((!StringUtils.isEmpty(oldopen_time) && !StringUtils.isEmpty(nowopen_time))){
                if(!nowopen_time.equals(oldopen_time)){
                    operdes1.append("开灯时间：由 " + oldopen_time + " 修改为：" + nowopen_time + ";");
                }}
            String  oldclose_time= oldObj.get("close_time").toString();
            String  nowclose_time= nowObj.get("close_time").toString();
            if ((!StringUtils.isEmpty(oldclose_time) && !StringUtils.isEmpty(nowclose_time))){
                if(!nowclose_time.equals(oldclose_time)){
                    operdes1.append("关灯时间：由 " + oldclose_time + " 修改为：" + nowclose_time + ";");
                }}
            String  olddimming= oldObj.get("dimming").toString();
            String  nowdimming= nowObj.get("dimming").toString();
            if ((!StringUtils.isEmpty(olddimming) && !StringUtils.isEmpty(nowdimming))){
                if(!nowdimming.equals(olddimming)){
                    operdes1.append("关灯时间：由 " + olddimming + " 修改为：" + nowdimming + ";");
                }}
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            Map oldobj = (Map)dataMap.get("oldObj");
            String content = Integer.parseInt(oldobj.get("content_id").toString())==1?"开关灯":"调光";
            operdes1.append("删除:计划名称为：" +oldobj.get("cname")+",区域为:"+oldobj.get("areaName")+
                    "<br>"+",分组:"+oldobj.get("group_name")+",计划内容:"+content+",生效日期:"+oldobj.get("start_time")
                    +",失效日期:"+oldobj.get("end_time")+"<br>"+",开灯时间:"+oldobj.get("open_time")+",调光值:"+oldobj.get("dimming"));
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        detailInfo=operdes1.toString();
        commonCheckDeatilInfo(dataMap,detailInfo);

    }

    //灯离线策略管理
    private void dealLampstrategyManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo;
        StringBuffer operdes1 = new StringBuffer();
        String content;
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            TLightStrategyManage nowObj= (TLightStrategyManage)dataMap.get("nowObj");

            if (nowObj.getContentId().toString().equals("2")){
                 content="关灯";
            }else if (nowObj.getContentId().toString().equals("1")){
                 content="开灯";
            }else {
                content="调光";
            }
            operdes1.append("添加了计划名称为：" +nowObj.getCname()+",计划内容为:"+content
            +",生效时间"+nowObj.getExe_time());
            System.out.println(">>>>>>>>>"+(nowObj.getDimming()!=0));
            if (nowObj.getDimming()!=0){
                operdes1.append(",控制值"+nowObj.getDimming());
            }
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            String operdes= (String)dataMap.get("nowObj");
            detailInfo = operdes;
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            TLightStrategyManage oldObj= (TLightStrategyManage)dataMap.get("oldObj");
            if (oldObj.getContentId().toString().equals("2")){
                content="关灯";
            }else if (oldObj.getContentId().toString().equals("1")){
                content="开灯";
            }else {
                content="调光";
            }
            operdes1.append("删除了计划名称为：" +oldObj.getCname()+",计划内容为:"+content
                    +",生效时间"+oldObj.getExe_time());
            System.out.println(">>>>>>>>>"+(oldObj.getDimming()!=0));
            if (oldObj.getDimming()!=0){
                operdes1.append(",控制值"+oldObj.getDimming());
            }
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        detailInfo=operdes1.toString();
        commonCheckDeatilInfo(dataMap,detailInfo);

    }

   // 光照调光策略管理
    private void dealLightmodulationManage(HashMap<String,Object> dataMap){
        String operation = dataMap.get("kind_id").toString();
        String detailInfo = "";
        StringBuffer operdes1 = new StringBuffer();
        if (SystemOperationCommon.OPERATION_ADD.equals(operation)){
            Map nowObj=(Map)dataMap.get("nowObj");

            operdes1.append("添加光照策略名称为：" +nowObj.get("cname")+",区域为:"+nowObj.get("areaName")+
                    "<br>"+",道路:"+nowObj.get("road_name")+",道路光照值:"+nowObj.get("light_num")+",设置调光值:"+nowObj.get("diming")+
                    "执行状态"+nowObj.get("openName"));
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_ADD);
        } else if (SystemOperationCommon.OPERATION_UPDATE.equals(operation)){
            Map oldObj = (Map)dataMap.get("oldObj");
            Map nowObj = (Map)dataMap.get("nowObj");
            String  oldcname= oldObj.get("cname").toString();
            String  nowcname= nowObj.get("cname").toString();
            if (!StringUtils.isEmpty(nowcname)  && !StringUtils.isEmpty(oldcname)){
                if(!nowcname.equals(oldcname)){
                    operdes1.append("策略名称：由 " + oldcname + " 修改为：" + nowcname + ";");
                }}
            String  oldareaName= oldObj.get("areaName").toString();
            String  nowareaName= nowObj.get("areaName").toString();
            if ((!StringUtils.isEmpty(oldareaName) && !StringUtils.isEmpty(nowareaName))){
                if(!nowareaName.equals(oldareaName)){
                    operdes1.append("区域名称：由 " + oldareaName + " 修改为：" + nowareaName + ";");
                }}
            String  oldroad_name= oldObj.get("road_name").toString();
            String  nowroad_name= nowObj.get("road_name").toString();
            if ((!StringUtils.isEmpty(oldroad_name) && !StringUtils.isEmpty(nowroad_name))){
                if(!nowroad_name.equals(oldroad_name)){
                    operdes1.append("道路名称：由 " + oldroad_name + " 修改为：" + nowroad_name + ";");
                }}
            String  oldlight_num= oldObj.get("light_num").toString();
            String  nowlight_num= nowObj.get("light_num").toString();
            if ((!StringUtils.isEmpty(oldlight_num) && !StringUtils.isEmpty(nowlight_num))){
                if(!nowlight_num.equals(oldlight_num)){
                    operdes1.append("道路光照值：由 " + oldlight_num + " 修改为：" + nowlight_num + ";");
                }}
            String  olddiming= oldObj.get("diming").toString();
            String  nowdiming= nowObj.get("diming").toString();
            if ((!StringUtils.isEmpty(olddiming) && !StringUtils.isEmpty(nowdiming))){
                if(!nowdiming.equals(olddiming)){
                    operdes1.append("调光设置值：由 " + olddiming + " 修改为：" + nowdiming + ";");
                }}
            String  oldopenName= oldObj.get("openName").toString();
            String  nowopenName= nowObj.get("openName").toString();
            if ((!StringUtils.isEmpty(oldopenName) && !StringUtils.isEmpty(nowopenName))){
                if(!nowopenName.equals(oldopenName)){
                    operdes1.append("开灯时间：由 " + oldopenName + " 修改为：" + nowopenName + ";");
                }}
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_UPDATE);
        } else {
            Map oldobj = (Map)dataMap.get("oldObj");
            operdes1.append("删除光照策略名称为：" +oldobj.get("cname")+",区域为:"+oldobj.get("areaName")+
                    "<br>"+",道路:"+oldobj.get("road_name")+",道路光照值:"+oldobj.get("light_num")+",设置调光值:"+oldobj.get("diming")+
                    "执行状态"+oldobj.get("openName"));
            dataMap.put("kind_id",SystemOperationCommon.OPERATION_DELETE);
        }
        detailInfo=operdes1.toString();
        commonCheckDeatilInfo(dataMap,detailInfo);

    }





    /**
     *  通用判断方法
     */
    public void commonCheckDeatilInfo(HashMap<String,Object> dataMap,String detailInfo){
        if(!StringUtils.isEmpty(detailInfo)){
            dataMap.put("operDes",detailInfo);
            commonRecordSaveData(dataMap);
        }
    }

    /**
     * 通用处理方法
     */
    private void commonRecordSaveData(HashMap<String,Object> dataMap){
        HashMap<String,Object> sqlMap = new HashMap<>();
        sqlMap.put("id",new LampSwitchInterface().getUUID());
        sqlMap.put("userId",dataMap.get("userId"));
        sqlMap.put("menu_id",dataMap.get("menu_id"));
        sqlMap.put("kind_id",dataMap.get("kind_id"));
        sqlMap.put("operDes",dataMap.get("operDes"));
        sqlMap.put("operTime", PlatformUtils.getNowTime());
        tSystemOperationMapper.saveLogData(sqlMap);
    }


}
