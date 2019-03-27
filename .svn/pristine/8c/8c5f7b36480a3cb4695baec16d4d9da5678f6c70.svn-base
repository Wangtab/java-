package com.lamp.utils;
import com.lamp.common.HuaWeiCommon;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.io.Resources;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class CommonMethod {

    public HashMap<String,String> getMysqlSetting(){
        try {
            HashMap<String,String> resultMap = new HashMap<>();
            Properties props = Resources.getResourceAsProperties("jdbc.properties");
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            url = StringUtils.substringBetween(url,"/","?");
            url = StringUtils.substringAfter(url,"/");
            String dataBaseName = StringUtils.substringAfter(url,"/");
            resultMap.put("username",username);
            resultMap.put("password",password);
            resultMap.put("dataBaseName",dataBaseName);
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean backupDataBase(String sqlName){
        try {
            CommonMethod commonMethod = new CommonMethod();
            HashMap<String,String> sqlMap = commonMethod.getMysqlSetting();
            String path = HuaWeiCommon.PROJECT_PATH + "upload/mysql";
            return MySQLDatabaseBackup.exportDatabaseTool("127.0.0.1", sqlMap.get("username"),sqlMap.get("password"),path,sqlName,sqlMap.get("dataBaseName"));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
