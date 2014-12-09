package com.boredou.mercury.web.util;

import java.util.List;

import javax.annotation.Resource;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.client.SimplePoolHttpClient;

public class AmazonSessionUtil {
	@Resource(name="poolHttpClient")
	private static HttpClient hc ;
	
	public static Boolean getSeesion(){
		SimplePoolHttpClient  simpleHttpClient = (SimplePoolHttpClient) hc;
		BasicCookieStore basicCookieStore =   simpleHttpClient.getCookieStore();
//		TODO
		return null;
	}
	
	public static String getSid(HttpClient hc){
		if(hc instanceof SimplePoolHttpClient){
			SimplePoolHttpClient  simpleHttpClient = (SimplePoolHttpClient) hc;
			BasicCookieStore basicCookieStore =   simpleHttpClient.getCookieStore();
			List<Cookie> cookies = basicCookieStore.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("session-id")){
					return cookie.getValue();
				}
			}
		}
		
		return null;
	}
	public static void main(String[] args){
		System.out.println("lwk...");
		StringBuilder sb = new StringBuilder();
		String str = null;
		sb.append("123").append(str).append("999");
		System.out.println(sb.toString());
	}
}
