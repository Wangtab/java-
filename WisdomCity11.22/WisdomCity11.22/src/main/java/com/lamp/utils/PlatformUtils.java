package com.lamp.utils;

import com.lamp.common.CitySettingCommon;
import com.lamp.model.Tsysuser;
import net.sf.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 通用类
 */
public class PlatformUtils {

    /**
     * 获取登录人信息
     * @return 登录人对象
     */
    public static Tsysuser getLoginUser(HttpServletRequest request){
        return (Tsysuser)request.getSession().getAttribute("loginUser");
    }

    /**
     * 获取登录人所属组织编码
     * @return 登录人所属组织编码
     */
    public static String getLoginUserCode(HttpServletRequest request){
        Tsysuser user = getLoginUser(request);
        return user.getTorganization().getOrgCode();
    }

    /**
     *  获取当前时间
     * @return
     */
    public static String getNowTime(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(now);
    }

    /**
     *  保存文件
     * @param request
     * @param file_path "/upload/" 文件夹路径
     * @return         文件保存路径
     */
    public static String saveImg(String file_path, HttpServletRequest request) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath(file_path);
        String originalFilename = null;
        LampSwitchInterface lampSwitchInterface = new LampSwitchInterface();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFiles = multipartRequest.getFiles("files");
        for(MultipartFile file : multipartFiles){
            if(file.isEmpty()){
                return null;
            }else{
                originalFilename = file.getOriginalFilename();
                String imgLastName = originalFilename.substring(originalFilename.lastIndexOf("."));
                originalFilename = lampSwitchInterface.getUUID() + imgLastName;
                try {
                    FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, originalFilename));
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return file_path + originalFilename;
    }


    /**
     * 根据提供的数值 从List列表中搜索出对应的数据
     * @param value 要匹配的数值
     * @param queryKey 查询中根据key取出对应的 value
     * @param resultKey 查找到该数值后 返回的 结果
     * @param list 要查询的列表
     * @return 若有数值则返回该数值 没有返回 Null
     */
    public static String getListDataByKey(String value,String queryKey,String resultKey,List<Map<String, Object>> list){
        String result = null;
        for (Map<String, Object> map : list) {
            String data = map.get(queryKey).toString();
            if(value.equals(data)){
                return map.get(resultKey).toString();
            }
        }
        return result;
    }

    /**
     * 获取今天天气信息
     */
    public static HashMap<String,String> getTodayWeather(String location){
        HashMap<String,String> dataMap = new HashMap<>();
        try {
            String url = "http://api.map.baidu.com/telematics/v3/weather?location="+location+"output=JSON&ak=TctTVkBukEdVc80cEc6RaoX2Sj8sR7zm";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(url);

            NodeList tempList = document.getElementsByTagName("temperature");
            Node tempNode = tempList.item(0).getFirstChild();
            String tempValue = tempNode.getNodeValue();
            String[] tempArr = tempValue.split("~");
            String heightTemp = tempArr[0];
            String lowTemp = tempArr[1];
            lowTemp = lowTemp.replace("℃","");

            NodeList weatherList = document.getElementsByTagName("weather");
            Node weatherNode = weatherList.item(0).getFirstChild();
            String weatherValue = weatherNode.getNodeValue();
            String weatherPic = getWeatherPicByName(weatherValue);

            dataMap.put(CitySettingCommon.WEATHER_PIC,weatherPic);
            dataMap.put(CitySettingCommon.WEATHER_HEIGHT_TEMPERATURE,heightTemp);
            dataMap.put(CitySettingCommon.WEATHER_LOW_TEMPERATURE,lowTemp);
            dataMap.put(CitySettingCommon.WEATHER_TEMPERATURE_DETAIL,weatherValue);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DOMException e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    /**
     * 处理天气信息
     */
    public static HashMap<String,String> dealWeatherTemperatureData(String location,String id){
        int flag = 0;
        HashMap<String,String> dataMap = new HashMap<>();
        while(dataMap.size() == 0 && flag < 3){
            dataMap  = PlatformUtils.getTodayWeather(location);
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            flag ++;
        }
        dataMap.put("id",id);
        dataMap.put("record_time",PlatformUtils.getNowTime());
        return dataMap;
    }

    /**
     * 根据天气名称来获取相应的图片
     */
    private static String getWeatherPicByName(String weatherName){
        String picAddress = CitySettingCommon.WEATHER_DEFAULT_PIC;
        if(StringUtils.isEmpty(weatherName)){
            return picAddress;
        } else if(weatherName.contains(CitySettingCommon.WEATHER_SNOW_NAME)){
            picAddress = CitySettingCommon.WEATHER_SNOW_PIC;
        } else if(weatherName.contains(CitySettingCommon.WEATHER_RAIN_NAME)){
            picAddress = CitySettingCommon.WEATHER_RAIN_PIC;
        } else if(weatherName.contains(CitySettingCommon.WEATHER_CLOUDY_NAME) || weatherName.contains(CitySettingCommon.WEATHER_CLOUDY2_NAME)){
            picAddress = CitySettingCommon.WEATHER_CLOUDY_PIC;
        } else if(weatherName.contains(CitySettingCommon.WEATHER_FINE_NAME)){
            picAddress = CitySettingCommon.WEATHER_FINE_PIC;
        }
        return picAddress;
    }

    /**
     * 获取日落日出时间
     */
    public static HashMap<String,String> getSunRiseAndSet(String lo,String la){
        HashMap<String,String> dataMap = new HashMap<>();
        try {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH) + 1;
            int day = now.get(Calendar.DAY_OF_MONTH);
            Double longitude = Double.parseDouble(lo);
            Double latitude = Double.parseDouble(la);
            String sunrise = SunRiseSet.getSunrise(year,month,day,longitude,latitude);
            String sunset = SunRiseSet.getSunset(year,month,day,longitude,latitude);
            dataMap.put("sunrise",sunrise);
            dataMap.put("sunset",sunset);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            dataMap.put("sunrise","06:05");
            dataMap.put("sunset","18:05");
        }
        return dataMap;
    }

    /**
     * 获取IP地址
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String client_ip = request.getHeader("x-forwarded-for");
        if(client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
            client_ip = request.getHeader("Proxy-Client-IP");
        }
        if(client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
            client_ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(client_ip == null || client_ip.length() == 0 || "unknown".equalsIgnoreCase(client_ip)) {
            client_ip = request.getRemoteAddr();
            if(client_ip.equals("127.0.0.1") || client_ip.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                client_ip = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(client_ip != null && client_ip.length() > 15){ //"***.***.***.***".length() = 15
            if(client_ip.indexOf(",") > 0){
                client_ip = client_ip.substring(0,client_ip.indexOf(","));
            }
        }
        return client_ip;
    }


    /**
     * 计算小时
     */
    public static Double getDatePoor(Date nowDate,Date endDate) {
        double nh = 1000 * 60 * 60 * 1.0;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少小时
        Double hour = diff / nh ;
        hour = Double.parseDouble(String.format("%.2f",hour));
        return hour;
    }

    /**
     * 验证 Map集合中的数据是否为空
     * @param dataMap 要验证的Map集合
     * @return 空 true  非空 false
     */
    public static boolean checkMapDataIsEmpty(Map<String,Object> dataMap){
        boolean isEmpty = false;
        try {
            Object obj = dataMap.get("data");
            if(null == obj){
                isEmpty = true;
            }

            List<Map<String, Object>> list = (List<Map<String, Object>>)obj;
            if(list.size() == 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return isEmpty;
    }

    /**
     *
     * 比较两个时间是否相等
     */
    public static boolean compareDateIsEqual(String nowDate,String paramDate,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date d1 = sdf.parse(nowDate);
            Date d2 = sdf.parse(paramDate);
            return d1.getTime() == d2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean compareWeeksIsEqual(String weeks,Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        String[] array = weeks.split(",");
        for (String str : array){
            Integer num = Integer.parseInt(str);
            if((num + 1) == (weekday)){
                return true;
            } else if(num == 7 && weekday == 1){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符是否为空
     */
    public static String isEmptyBackStringEmpty(Object str){
        if(str == null){
            return "";
        }
        String temp = str.toString();
        if(StringUtils.isEmpty(temp)){
            return "";
        }
        return temp;
    }

    /**
     * 判断集合是否为空
     */
    public static boolean isEmptyList(List list){
        if(null == list || list.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * 判断是否是数字
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 如果不是数字 返回 0
     * @param str
     * @return
     */
    public static String noIntegerBackZero(String str){
        if(isInteger(str)){
            return str;
        }
        return "0";
    }

    /**
     * 获取uuid编码
     * @return
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString();
        uid = uid.replace("-","");
        return uid.toString();
    }

    /**
     * 验证信息是否为空 并且发送数据
     * @param dataMap 需要验证的信息
     */
    public static void checkSendDataByResponse(Map<String,Object> dataMap,HttpServletResponse response){
        try {
            if(PlatformUtils.checkMapDataIsEmpty(dataMap)){
                response.getWriter().print("n");
            }else{
                response.getWriter().print(JSONArray.fromObject(dataMap).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理分页
     * @param dataMap 分页信息
     * @param count 总个数
     */
    public static synchronized  void dealPageData(HashMap<String,Object> dataMap,Integer count){
        Integer showNum = Integer.parseInt(dataMap.get("showNum").toString());
        Integer curPage = Integer.parseInt(dataMap.get("curPage").toString());
        Integer maxPage = (int) (Math.ceil(count / (showNum * 1.0)));
        curPage = DealPage.dealPage(curPage + "",maxPage);
        Integer num = (curPage -1) * showNum;
        dataMap.put("num",num);
    }

    /**
     * 返回处理过的Map集合
     * @param count
     * @param list
     * @return
     */
    public static  synchronized  Map<String,Object> backMapData(Integer count,List<Map<String, Object>> list){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("count",count);
        dataMap.put("data",list);
        return dataMap;
    }

    /**
     * 删除图片
     */

    public static void deleteFile(String sPath) {
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    public static void main(String[] args) throws ParseException {

    }

}
