package com.boredou.mercury.web.task.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.param.RequestParams;
import cn.weili.util.http.result.ResponseResult;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.web.task.util.datafetch.FetchMethod;

public class FetchItemUtil {
	@Setter
	private static HttpClient hc;
	private final static Logger logger = LoggerFactory.getLogger(FetchItemUtil.class);
//	@Setter
//	private static Map<String,FetchMethod> goodsTypeMap;
	@Setter
	private static FetchMethod fetchMethod;

	//匹配item的url
	//	private static String expItemUrl = "result.*prod.*\\s+.*linePlaceholder.*\\s+.*href=\"(.*)\"><div";
	private static String expItemUrl ="result_[0-9]+[\\S\\s]{2}data-asin=\"([a-zA-Z0-9]{10})\"";
	private static Pattern pattItemUrl = Pattern.compile(expItemUrl);

	public static List<String> getItemList(String pageUrl){
		List<String> myItemList = new ArrayList<String>();
		ResponseResult result = hc.execute(RequestParams.custom().setUrl(pageUrl).build());

		String rexp = result.getValue();

		Matcher mItemLink = pattItemUrl.matcher(rexp);
		while(mItemLink.find()){
			String itemLinkAsin = mItemLink.group(1);
			String itemUrl = "http://www.amazon.com/dp/"+itemLinkAsin;

			myItemList.add(itemUrl);

		}
		logger.warn("pageItemSum:"+myItemList.size());

		return myItemList;
		
	}
	public static void getItem(AmazonCategoryDO amazonCategoryDO,String goodsUrl){
		
		fetchMethod.fetch(amazonCategoryDO, goodsUrl);
		
		
	}
}
