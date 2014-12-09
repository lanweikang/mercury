package com.boredou.mercury.server.service.amazon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.server.service.AmazonCategoryService;
import com.boredou.mercury.server.service.AmazonItemService;
import com.boredou.mercury.server.service.AmazonPageService;

public class AmazonUtil {
	
	@Autowired
	AmazonCategoryService amazonCategoryService;
	@Autowired
	AmazonPageService amazonPageService; 
	@Autowired
	AmazonItemService amazonItemService;
	
	
	public List<AmazonCategoryDO> getAmazonCategoryList(){
		AmazonCategoryDO amazonCategoryDO = new AmazonCategoryDO();
		return getAmazonCategoryList(amazonCategoryDO);
	}
	public List<AmazonCategoryDO> getAmazonCategoryList(AmazonCategoryDO amazonCategoryDO){
		return amazonCategoryService.getAmazonCategoryList(amazonCategoryDO);
	}
	
	
	
	
	
	
	public void writeContent(String goodsType,String content){
		
	}
}
