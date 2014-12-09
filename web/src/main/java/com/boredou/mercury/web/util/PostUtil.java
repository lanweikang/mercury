package com.boredou.mercury.web.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import com.boredou.mercury.web.write.FileUtil;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicHeader;

public class PostUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(PostUtil.class);
	private static HttpClient hc = SimplePoolHttpClient.newHttpClient();
	public static void main(String[] args){
		
//		//无伸展
//		String url ="http://www.amazon.com/dp/B00EPBV39M";
//		//有伸展 http://www.amazon.com/dp/B00JRVDCZI
////		String url = "http://www.amazon.com/dp/B00JRVDCZI";
//		ResponseResult result = hc.execute(RequestParams.custom().setUrl(url).addHeader(Consts.CHEOME_USER_AGENT)
//				.setReadTimeout(HttpClient.DEFAULT_READ_TIMEOUT).build());
////		System.out.println(result.getValue());
//		String content = result.getValue();
//		
//		Matcher mItemName = pattItemName.matcher(content);
//		while(mItemName.find()){
//			String itemName = mItemName.group(1);
//			itemName = StringUtil.trimNotWithNull(itemName);
//			System.out.println("itemName:"+itemName);
//			break;
//		}
//		
//		
//		String itemJson ="";
//		Matcher mItemJson = pattItemJson.matcher(content);
//		while(mItemJson.find()){
//			itemJson = mItemJson.group(1);
//			itemJson = StringUtil.trimNotWithNull(itemJson);
//			System.out.println("itemJson:"+itemJson);
//			break;
//		}
//		
//		System.out.println("----------------");
//		
//		
//		if(itemJson == null || itemJson == "") return ;
//		JSONObject jsonObject0 = JSONObject.parseObject(itemJson);
//		
//		JSONObject asinJson = jsonObject0.getJSONObject("data").getJSONObject("stateData").getJSONObject("asin_variation_values");
//		
//		Map<String,JSONObject> asinVariationValuesMap = new HashMap<String, JSONObject>();
//		
//		for(String key:asinJson.keySet()){
////			System.out.println("key: "+key+" ,value:"+asinJson.get(key));
//			asinVariationValuesMap.put(key, (JSONObject)asinJson.get(key));
//		}
//		for(String key:asinVariationValuesMap.keySet()){
//			System.out.println("key: "+key+" ,ASIN: "+(asinVariationValuesMap.get(key)).getString("ASIN")+" ,color_name: "+(asinVariationValuesMap.get(key)).getInteger("color_name")+" ,size_name: "+(asinVariationValuesMap.get(key)).getInteger("size_name"));
//		}
//		System.out.println("-------------------------------------");
//		
//		Object ajaxUrl = jsonObject0.getJSONObject("data").getJSONObject("contextMetaData").getJSONObject("full").get("AJAXUrl");
//		
//		System.out.println(ajaxUrl);
//		
//		Map<String,String> map = getColorAndSizeUrl(jsonObject0, asinVariationValuesMap);
//		for(String key:map.keySet()){
//			System.out.println("key: "+key+" ,ajaxUrl: "+map.get(key));
//		}
		System.out.println("------------         fengexian         ---------");
		
		
		String ajax = "http://www.amazon.com/gp/twister/ajaxv2?sid=179-0957629-2823009&ptd=SHOES&json=1&dpxAjaxFlag=1&sCac=1&isUDPFlag=1&twisterView=glance&ee=2&pgid=shoes_display_on_website&nodeID=672123011&rid=1Q1N970T4HWGDEAA8ESE&parentAsin=B00G42NQKE&enPre=1&dStr=size_name%2Ccolor_name&auiAjax=1&storeID=shoes&psc=1&asinList=B00EK9RA46&isFlushing=2&id=B00EK9RA46&prefetchParam=0&mType=full&dpEnvironment=softlines";
		
		ResponseResult result1 = hc.execute(RequestParams.custom().setUrl(ajax).addHeader(Consts.CHEOME_USER_AGENT)
				.setReadTimeout(HttpClient.DEFAULT_READ_TIMEOUT).build());
		String ajaxString = result1.getValue();
		System.out.println("size: "+ajaxString.length());
		Matcher mItemPrice = pattItemPrice.matcher(ajaxString);
		while(mItemPrice.find()){
			System.out.println("zhaodaole...");
			String itemPrice = mItemPrice.group(1).trim();
			itemPrice = StringUtil.trimNotWithNull(itemPrice);
			System.out.println("itemPrice:"+itemPrice);
			break;
		}
		FileUtil.writeToFile(ajaxString, "C:/Users/Administrator/Desktop/df/log.txt");
		
		System.out.println("end...");
	}
	
	
	//匹配item的name
		private static String expItemName ="<span id=\"productTitle\" class=\"a-size-large\">(.*?)</span>";
		private static Pattern pattItemName = Pattern.compile(expItemName);
		//匹配脚本中的json字符串
		private static String expItemJson ="<script type=\"a-state\" data-a-state=\"\\{&quot;key&quot;:&quot;twisterData&quot;\\}\">(.*?)</script>";
		private static Pattern pattItemJson = Pattern.compile(expItemJson);
		
		private static String expItemPrice ="priceblock_ourprice[\\s\\S]*?>([\\s\\S]*?)<";
//		<span id=\"priceblock_ourprice\" class=\"a-size-medium a-color-price\">$54.85<\/span>\n
		private static Pattern pattItemPrice = Pattern.compile(expItemPrice);
		
		
		
		
		
		private static String getCompleteAjaxUrl(JSONObject jsonObject , String asin){
			return "http://www.amazon.com"+jsonObject.getJSONObject("data").getJSONObject("contextMetaData").getJSONObject("full").get("AJAXUrl") +
					asin +"&isFlushing=2&id="+asin+"&prefetchParam=0&mType=full&dpEnvironment=softlines"
					;
		}
		private static Map<String,String> getColorAndSizeUrl( JSONObject jsonObject,Map<String,JSONObject> asinVariationValuesMap){
			Map<String,String> asinAndUrl = new HashMap<String, String>();
			for(String key:asinVariationValuesMap.keySet()){
				asinAndUrl.put(key, getCompleteAjaxUrl(jsonObject , key) );
			}
			return asinAndUrl;
		} 
}
