package com.boredou.mercury.server.service.amazon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import cn.weili.util.http.client.HttpClient;

import com.boredou.mercury.repository.entity.AmazonItemDO;
import com.boredou.mercury.server.service.AmazonCategoryService;
import com.boredou.mercury.server.service.AmazonItemService;
import com.boredou.mercury.server.service.AmazonPageService;

public class fetchItemExpressUtil {
	@Autowired
	AmazonCategoryService amazonCategoryService;
	@Autowired
	AmazonPageService amazonPageService;
	@Autowired
	AmazonItemService amazonItemService;
	private static String expItemName = "pagnLink.*href=\"(.*)\"";
	private static Pattern pattItemName = Pattern.compile(expItemName);
	
	public void fillItem(String rexp,HttpClient hc,String goodsType){
		
		Matcher mItemLink = pattItemName.matcher(rexp);
		while(mItemLink.find()){
			String itemName = mItemLink.group(1);
			
			
		}
		
	}
}
