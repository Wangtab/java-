package com.lamp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GetLocalTimes
{
	public static String getNowTime()
	{
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(now);
	}
	/*获取当前时间*/
	public static String getTime()
	{
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(now);
	}

	/*获取时间戳*/
	public static String timeMillis(){
		long time = System.currentTimeMillis();
		return String.valueOf(time);
	}
}
