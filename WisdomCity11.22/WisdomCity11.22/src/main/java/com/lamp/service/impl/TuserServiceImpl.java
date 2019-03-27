package com.lamp.service.impl;

import com.lamp.dao.TsysuserMapper;
import com.lamp.dao.TsysuserlogMapper;
import com.lamp.model.Tsysuser;
import com.lamp.model.Tsysuserlog;
import com.lamp.service.TuserService;
import com.lamp.utils.GetLocalTimes;
import com.lamp.utils.LampSwitchInterface;
import com.lamp.utils.PlatformUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Created by MSI on 2017/11/6.
 */
@Service
public class TuserServiceImpl implements TuserService {

	@Resource
    private TsysuserlogMapper tsysuserlogMapper;
    @Resource
    private TsysuserMapper tsysuserMapper;

    /**
     * ���ӵ�¼��־
     * @param tsysuser
     * @return
     */
    public int addUserLog(Tsysuser tsysuser,String ipAddr) {
        Tsysuserlog log = new Tsysuserlog();
        log.setIp(ipAddr);
        log.setUserId(tsysuser.getId());
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        String uuid = lampSwitchInterface.getUUID();
        log.setUuid(uuid);
        log.setLogDate(PlatformUtils.getNowTime());
        return tsysuserlogMapper.userLogInsert(log);
    }

    @Override
    public HashMap<String, Object> validedLoginUser(String userName, String pwd) {
        return tsysuserMapper.validedLoginUser(userName,pwd);
    }
}








