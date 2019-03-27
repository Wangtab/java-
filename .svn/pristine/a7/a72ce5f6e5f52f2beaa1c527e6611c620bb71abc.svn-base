package com.lamp.utils;

import com.lamp.common.URLCommon;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2018/2/9.
 */
public class sjkUtil {



    public static int upcmdsql() throws Exception{
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        SimpleDateFormat sdf2 =   new SimpleDateFormat( "yyyyMMddHHmmss");
        Runtime rt = Runtime.getRuntime();
        String date = sdf.format(new Date());


//        String durls2=request.getSession().getServletContext().getRealPath("/WEB-INF");

        String durls2=URLCommon.WEBINF_URL;
//        String durls = request.getSession().getServletContext().getRealPath("upload");
        String durls=URLCommon.UPLOAD_URL;

        String name=sdf2.format(new Date());
        Process pss=rt.exec("cmd /c "+durls2+"\\mysqldump -hlocalhost -P3306  -uroot -proot db_wisdomcity>"+durls+"\\"+name+".sql");
        int procount= pss.waitFor();
        return procount;
    }

    public static int upcmdsql(String durls2,String durls) throws Exception{
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        SimpleDateFormat sdf2 =   new SimpleDateFormat( "yyyyMMddHHmmss");
        Runtime rt = Runtime.getRuntime();
        String date = sdf.format(new Date());
        String name=sdf2.format(new Date());
        Process pss=rt.exec("cmd /c "+durls2+"\\mysqldump -hlocalhost -P3306  -uroot -proot db_wisdomcity>"+durls+"\\"+name+".sql");
        int procount= pss.waitFor();
        return procount;
    }

    public static int upcmdsql(String durls2,String durls,Date dates) throws Exception{
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        SimpleDateFormat sdf2 =   new SimpleDateFormat( "yyyyMMddHHmmss");
        Runtime rt = Runtime.getRuntime();
        String date = sdf.format(dates);
        String name=sdf2.format(dates);
        Process pss=rt.exec("cmd /c "+durls2+"\\mysqldump -hlocalhost -P3306  -uroot -proot db_wisdomcity>"+durls+"\\"+name+".sql");
        int procount= pss.waitFor();
        return procount;
    }


}
