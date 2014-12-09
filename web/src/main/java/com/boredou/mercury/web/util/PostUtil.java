package com.boredou.mercury.web.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.weili.util.StringUtil;
import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.client.SimpleHttpClient;
import cn.weili.util.http.client.SimplePoolHttpClient;
import cn.weili.util.http.param.RequestParams;
import cn.weili.util.http.result.ResponseResult;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicHeader;

public class PostUtil {
	
	//匹配item的name
	private static String expItemName ="<span id=\"productTitle\" class=\"a-size-large\">(.*?)</span>";
	private static Pattern pattItemName = Pattern.compile(expItemName);
	//匹配脚本中的json字符串
	private static String expItemJson ="<script type=\"a-state\" data-a-state=\"\\{&quot;key&quot;:&quot;twisterData&quot;\\}\">(.*?)</script>";
	private static Pattern pattItemJson = Pattern.compile(expItemJson);
	
	//sid
//	ue_sid
	private static String expItemSid ="ue_sid='(.*?)'";
	private static Pattern pattItemSid = Pattern.compile(expItemSid);
	
	private final static Logger logger = LoggerFactory.getLogger(PostUtil.class);
	static HttpClient hc = SimplePoolHttpClient.newHttpClient();
	public static void main(String[] args){
		String url ="http://www.amazon.com/dp/B00EPBV39M";

		ResponseResult result = hc.execute(RequestParams.custom().setUrl(url).addHeader(Consts.CHEOME_USER_AGENT).build());
//		System.out.println(result.getValue());
		String content = result.getValue();
		
		Matcher mItemName = pattItemName.matcher(content);
		while(mItemName.find()){
			String itemName = mItemName.group(1);
			itemName = StringUtil.trimNotWithNull(itemName);
			System.out.println("itemName:"+itemName);
			break;
		}
		
		
		String itemJson ="";
		Matcher mItemJson = pattItemJson.matcher(content);
		while(mItemJson.find()){
			itemJson = mItemJson.group(1);
			itemJson = StringUtil.trimNotWithNull(itemJson);
			System.out.println("itemJson:"+itemJson);
			break;
		}
		
		Matcher mItemSid = pattItemSid.matcher(content);
		while(mItemSid.find()){
			String itemSid = mItemSid.group(1);
			itemSid = StringUtil.trimNotWithNull(itemSid);
			System.out.println("itemSid:"+itemSid);
			break;
		}
		System.out.println("----------------");
		Header[] headerr = result.getRespHeaders();
		
//		String str = result.setRespHeaders("Set-cookie");
		for (Header header : headerr) {
			System.out.println(header.getName());
			if(header.getName().equals("Set-cookie")){
				System.out.println(header.getValue());
			}
		}
		System.out.println(headerr.length);
		
		if(hc instanceof SimplePoolHttpClient){
			SimplePoolHttpClient  simpleHttpClient = (SimplePoolHttpClient) hc;
			BasicCookieStore basicCookieStore =   simpleHttpClient.getCookieStore();
			
			System.out.println("cookies: "+simpleHttpClient.getCookieStore());
			System.out.println("basicCookieStore: "+basicCookieStore);
			
			List<Cookie> cookies = basicCookieStore.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("session-id")){
					System.out.println(cookie.getName()+cookie.getValue());
				}
			}
			
		}
		
		
//		if(itemJson == null || itemJson == "") return ;
//		JSONObject jsonObject0 = JSONObject.parseObject(itemJson);
//		
//		JSONObject asinJson = jsonObject0.getJSONObject("data").getJSONObject("stateData").getJSONObject("asin_variation_values");
//		
//		for(String key:asinJson.keySet()){
////			System.out.println("key: "+key+" ,value:"+asinJson.get(key));
//		}
//		System.out.println("-------------------------------------");
//		JSONArray colorJSONArray = (JSONArray) jsonObject0.getJSONObject("data").getJSONObject("stateData").getJSONObject("variation_values").get("color_name");
//		
//		JSONArray sizeJSONArray = (JSONArray) jsonObject0.getJSONObject("data").getJSONObject("stateData").getJSONObject("variation_values").get("size_name");
//		
//		Object ajaxUrl = jsonObject0.getJSONObject("data").getJSONObject("contextMetaData").getJSONObject("full").get("AJAXUrl");
//		
//		System.out.println(ajaxUrl);
		
		System.out.println("end...");
	}
	
}
