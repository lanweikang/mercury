package com.boredou.mercury.web.write.session.util;

import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;



public class SessionTest {
	private static String url = "http://www.amazon.com/dp/B00EPBV39M";
	private static String checkIP = "http://ddns.oray.com/checkip";
	private static String baiduUrl = "http://www.baidu.com";
	public static void main(String[] args){
		HttpClient hc = new HttpClient();
		hc.getParams().setContentCharset("utf-8");
		hc.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		hc.getParams().setParameter("http.protocol.single-cookie-header", true);
//		hc.getState().addCookie(new Cookie("",""));
		
		GetMethod method = new GetMethod(baiduUrl);
//		method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;"); 
//		method.setRequestHeader("Accept-Language", "zh-cn"); 
		method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3");
		method.setRequestHeader("Accept-Charset", "utf8");
//		method.setRequestHeader("Keep-Alive", "3"); 
//		method.setRequestHeader("Connection", "Keep-Alive");
//		method.setRequestHeader("Cache-Control", "no-cache"); 
//		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
		
		
		
		try {
			int code = hc.executeMethod(method);
			System.out.println("code:"+code);
			String res = method.getResponseBodyAsString().trim();
//			System.out.println("res: "+res);
			Cookie[] cookies = hc.getState().getCookies();
			System.out.println("lwk...."+cookies+" ,"+cookies.length);
			for (int i=0;i<cookies.length;i++) {
				System.out.println("name: "+cookies[i].getName());
				System.out.println("value: "+cookies[i].getValue());
				System.out.println("DOmain: "+cookies[i].getDomain());
				System.out.println("Path: "+cookies[i].getPath());
				System.out.println("version: "+cookies[i].getVersion());
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			method.releaseConnection();
		}
	}
}
