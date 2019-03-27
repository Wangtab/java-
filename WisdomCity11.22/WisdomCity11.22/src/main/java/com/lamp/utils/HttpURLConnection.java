package com.lamp.utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.net.URL;


/*
 * http客户端请求
 */
public class HttpURLConnection {
	
	public HttpURLConnection(){
		
	}
	
	/*
	 * 使用get 调用http接口,cat
	 */
	public static String getHttpResponse(String httpUrl){
//		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(httpUrl);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		try {
			httpClient.executeMethod(getMethod);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = getMethod.getResponseBodyAsStream();
			int len = 0;
			byte[] buf = new byte[2048];
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			String responseMsg = out.toString("UTF-8");
			return responseMsg;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return null;
	}
	
	public static String postHttpResponse(String httpUrl,NameValuePair[] param){
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(httpUrl);
		
		postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
//		 NameValuePair[] param = { new NameValuePair("ak","4dSWAXDiFyTFEGWDxMQouV63vtfWY8Dp"),  
//	                new NameValuePair("service_id","115696"),  
//	                new NameValuePair("entity_name","135790246811220")};
		
		 postMethod.setRequestBody(param);
		 
		try {
			DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY); //此处解决Cookie rejected: "$Version="1"; BAIDUID="1E6B32311ACAB688169606EB8862CD69:FG=1"; $Path="/"; $Domain=".baidu.com"". Domain attribute ".baidu.com" violates RFC 2109: host minus domain may not contain any dots
			httpClient.executeMethod(postMethod);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = postMethod.getResponseBodyAsStream();
			int len = 0;
			byte[] buf = new byte[2048];
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			String responseMsg = out.toString("UTF-8");
			return responseMsg;
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		
		return null;
	}


	/**
	 * 根据地址 获取网页中的数据
	 */
	public static String getContentByUrl(String urlPath){
		try {
			URL url = new URL(urlPath);
			java.net.HttpURLConnection con = (java.net.HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			InputStream inStream = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
			String str = "";
			String temp = "";
			while ((temp = br.readLine()) != null) str += temp;
			inStream.close();
			br.close();
			con.disconnect();
			return  str;
		}catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		HttpURLConnection hc = new HttpURLConnection();
		String str = hc.getContentByUrl("http://www.sojson.com/open/api/weather/json.shtml?city=上海");
		System.out.println(str);
	}



}
