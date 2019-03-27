package com.lamp.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class HuaWeiController {
    /**
     *  添加设备时 反馈信息
     */
    @RequestMapping(value = "/na/iocm/devNotify/v1.1.0/addDevice")
    public ResponseEntity<HttpStatus> recvAddDeviceNotify(Object addDevice_NotifyMessage) throws IOException {
        System.out.println("addDevice_NotifyMessage:"+addDevice_NotifyMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/na/commandRspData")
    public ResponseEntity<HttpStatus> commandRspData(Object commandRspData22) throws IOException {
        System.out.println("commandRspData:"+commandRspData22);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/na/commandRspData11", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> commandRspData11(
            @RequestBody Object addDevice_NotifyMessage) throws IOException {
        System.out.println("接收到新设备信息11："+addDevice_NotifyMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 修改设备时 反馈信息
     */
    @RequestMapping(value = "/na/iocm/devNotify/v1.1.0/updateDeviceInfo")
    public ResponseEntity<HttpStatus> recvModifyDeviceInfoNotify(Object modifyDeviceInfo_NotifyMessage) throws IOException {
        System.out.println("modifyDeviceInfo_NotifyMessage:"+modifyDeviceInfo_NotifyMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 数据发生改变时 反馈信息
     */
    @RequestMapping(value = "/na/iocm/devNotify/v1.1.0/updateDeviceData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> recvDeviceDataChangedNotify1(@RequestBody Object message) throws IOException {
        System.out.println("enter---->deviceDataChanged_NotifyMessage");
        try {
            System.out.println("toString:"+message.toString());
            JSONArray jsonArray = JSONArray.fromObject(message);
            System.out.println("jsonArray:"+jsonArray);
            JSONObject jb = JSONObject.fromObject(message);
            System.out.println("JSONObject:"+jb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
