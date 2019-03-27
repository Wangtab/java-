package com.lamp.controller;

import com.huawei.utils.HuaWeiMethod;
import com.lamp.common.HuaWeiCommon;
import com.lamp.service.IPlatFormTest;
import com.lamp.service.TimedTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 平台测试类
 */
@Controller
@RequestMapping("PlatFomTestController")
public class PlatFomTestController {


    @Autowired
    private IPlatFormTest iPlatFormTest;

    @Autowired
    private TimedTaskService timedTaskService;

    /**
     * 初始化每日功率总和
     */
    @RequestMapping("initSumPower")
    public void initSumPower(HttpServletResponse response) throws IOException {
        String result = iPlatFormTest.initSumPower();
        response.getWriter().print(result);
    }


    /**
     * 测试每日2点线程执行任务
     */
    @RequestMapping("TestTwoClockTask")
    public void TestTwoClockTask(){
        timedTaskService.dealAndCalculateData();
        System.out.println("ok");
    }

    /**
     * 初始化每日单灯功率总和
     * @param response
     * @throws Exception
     */
    @RequestMapping("initLampByDay")
    public void  initLampByDay(HttpServletResponse response) throws Exception{
        String result = iPlatFormTest.initLampByDay();
        response.getWriter().print(result);
    }

    /**
     * 初始化灯具记录表信息
     */
    @RequestMapping("initRecordPowerData")
    public void initRecordPowerData(){
        iPlatFormTest.initRecordPowerData();
    }

    /**
     * 查看 AccessToken数值
     * @param response
     * @throws Exception
     */
    @RequestMapping("lookAccessToken")
    public void lookAccessToken(HttpServletResponse response) throws Exception{
        String str = "DX_ACCESS_TOKEN_VALUE:" + HuaWeiCommon.DX_ACCESS_TOKEN_VALUE +
                "DX_REFRESH_TOKEN_VALUE:" + HuaWeiCommon.DX_REFRESH_TOKEN_VALUE;
        response.getWriter().print(str);
    }

    /**
     * 查看更换数值
     * @param response
     * @throws Exception
     */
    /*@RequestMapping("lookChangeAccessTokenVal")
    public void lookChangeAccessTokenVal(HttpServletResponse response) throws Exception{
        timedTaskService.refreshToken();
        String str = "DX_ACCESS_TOKEN_VALUE:" + HuaWeiCommon.DX_ACCESS_TOKEN_VALUE +
                "DX_REFRESH_TOKEN_VALUE:" + HuaWeiCommon.DX_REFRESH_TOKEN_VALUE;
        response.getWriter().print(str);
    }
    */


    /**
     * 发送命令到电信
     */
    @RequestMapping("SendCommandForDianXi")
    public void SendCommandForDianXi(String deviceId,String dimming,HttpServletResponse response) throws IOException {
        try {
            HuaWeiMethod huaWeiMethod = new HuaWeiMethod();
            String result =  huaWeiMethod.sendCommand(deviceId,dimming);
            response.getWriter().print(result);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("error");
        }
    }




    /**
     * 更新气温数据
     */
    @RequestMapping("updateTemperatureData")
    public void updateTemperatureData(HttpServletResponse response) throws IOException{
        try {
            timedTaskService.dealWeatherData();
            response.getWriter().print("更新完成");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("更新失败");
        }
    }

    /**
     * 查询每日2点记录数据
     * @param response
     * @throws IOException
     */
    @RequestMapping("getDealTodayRecordData")
    public void dealTodayRecordData(HttpServletResponse response) throws IOException{
        timedTaskService.dealAndCalculateData();
        response.getWriter().print("更新完成");
    }

    /**
     *  clear Session User
     */
    @RequestMapping("clearSessionUser")
    public void clearSessionUser(HttpServletRequest request,HttpServletResponse response) throws  IOException{
        request.getSession().setAttribute("loginUser",null);
        response.getWriter().print("deal finish");
    }

    /**
     * 查看数据采集
     */
    @RequestMapping("lookCollectDataMethod")
    public void lookCollectDataMethod(HttpServletResponse response) throws  IOException{
        try {
            timedTaskService.getNBlOTDevicedata();
            response.getWriter().print("deal finish");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print(e.getStackTrace());
        }
    }

    /**
     *  测试备份
     */
    @RequestMapping("testBackupData")
    public void testBackupData(HttpServletResponse response) throws  IOException{
        try {
            timedTaskService.backupTask();
            response.getWriter().print("deal finish");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print(e.getStackTrace());
        }
    }



}
