package com.lamp.utils;

import com.lamp.common.MonitorCommon;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.apache.commons.lang.StringUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUntils {
    public static int copys = 12;//份数

    public static void main(String[] args) throws ParseException {
        String str = "jdbc:mysql://localhost:3306/db_wisdomcity?allowMultiQueries=true&amp;useUnicode=true&amp;characterEncoding=utf8";
        str = StringUtils.substringBetween(str,"/","?");
        str = StringUtils.substringAfter(str,"/");
        str = StringUtils.substringAfter(str,"/");
        System.out.println(str);
    }


    /**
     * 处理日出日落时间
     * 在日出后10分关灯
     * 日落前10分开灯
     * @param dateMap
     */
    public HashMap<String,Object> dealSunRiseAndSunSet(HashMap<String,String> dateMap){
        HashMap<String,Object> dataMap = new HashMap<>();
        String sunrise = dateMap.get("sunrise");
        String sunset = dateMap.get("sunset");
        int minute = 10 * 60 * 1000;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date riseDate = format.parse(sunrise);//日出
            Date setDate = format.parse(sunset);//日落
            riseDate = new Date(riseDate.getTime() + minute);//日出后10分
            setDate = new Date(setDate.getTime() - minute); //日落前10分
            sunrise =  format.format(riseDate);
            sunset = format.format(setDate);
            dataMap.put("sunrise",sunrise);
            dataMap.put("sunset",sunset);
            return dataMap;
        } catch (ParseException e){
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 获取当前日期 和 前4天日期
     * @return
     */
    public List<String> getFourDaysBefore(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        Date date=new Date();
        for (int i = 4; i>=0;i--){
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, -i);
            String dayBefore = sdf.format(c.getTime());
            list.add(dayBefore);
        }
        return list;
    }

    /**
     * 处理日期是否允许通过(小于5天) 获取灯具数据时检查
     */
    public boolean dealDateIsAllot(String str){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, MonitorCommon.CHECK_IS_LAMP_LEAVING_DATA);
            Date minDate = calendar.getTime();
            Long minNum = minDate.getTime();
            Long checkDate = date.getTime();
            if(minNum <= checkDate){
                return true;
            } else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 处理日期时间
     * @param strDate
     * @return
     */
    public String dealIOTTime(String strDate){
        try {
            if(StringUtils.isEmpty(strDate)){
                return null;
            }else if(strDate.indexOf("T") < 0){
                return strDate;
            }
            String data = StringUtils.substringBetween(strDate,"T","Z");
            String time = StringUtils.substring(data,0,2);
            String date = StringUtils.substringBefore(strDate,"T");//日期
            String minAndSecond = StringUtils.substring(data,2,6); //分钟
            Integer hour = Integer.parseInt(time) + MonitorCommon.TIME_ZONE;//小时
            String str_hour = String .format("%02d",hour);
            String calculateTime = date + str_hour + minAndSecond;
            Date d1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(calculateTime);
            String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d1);
            return  result;
        } catch (Exception e) {
            return null;
        }

    }

}
