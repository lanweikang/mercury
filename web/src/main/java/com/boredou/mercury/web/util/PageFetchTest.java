package com.boredou.mercury.web.util;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.web.write.FileUtil;

import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.client.SimpleHttpClient;
import cn.weili.util.http.param.RequestParams;
import cn.weili.util.http.result.ResponseResult;

public class PageFetchTest {

	public static void main(String[] args) {
//		String goodsUrl = "http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dfashion-mens-watches&field-keywords=MARC+BY+MARC+JACOBS";
//		HttpClient hc = SimpleHttpClient.newHttpClient();
//		ResponseResult result = hc.execute(RequestParams.custom().setUrl(goodsUrl)
////				.addHeader(Consts.CHEOME_USER_AGENT)
//				.setReadTimeout(HttpClient.DEFAULT_READ_TIMEOUT)
//				.build());
//		int code = result.getResultCode();
//		System.out.println("start...");
//		System.out.println("code: "+code );
//		String responseBody = result.getValue();
//		FileUtil.writeToFile(responseBody, "C:/Users/Administrator/Desktop/df/goodsResponseBody.txt");
//		
//		System.out.println("end...");
		
//		String jsonString = "{[{a:1,b:2}],[{a:9,b:4}]}";
////		JSONObject jsonObject = JSONObject.parseObject(jsonString);
////		System.out.println("lwk... "+jsonObject.getIntValue("a"));
////		System.out.println("lwk... "+jsonObject.getIntValue("b"));
//		JSONArray jsonArray = JSONArray.parseArray(jsonString);
////		System.out.println(jsonArray.get(0));
//		JSONObject jsonObject = JSONObject.parseObject((String) jsonArray.get(0));
//		System.out.println("lwk..."+jsonObject.getIntValue("a"));
		
		JSONObject jsonObject0 = new JSONObject();
		jsonObject0.put("a", "1");
		jsonObject0.put("b", "2");
		
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("a", null);
		jsonObject1.put("b", "4");
		
		JSONObject jsonObject2 = new JSONObject();
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonObject0);
		System.out.println(jsonArray);
		jsonArray.add(jsonObject1);
		System.out.println(jsonArray);
		jsonArray.add(jsonObject2);
		System.out.println(jsonArray);
		
		
	}

}
