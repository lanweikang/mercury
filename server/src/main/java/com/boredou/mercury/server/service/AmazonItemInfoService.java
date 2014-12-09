package com.boredou.mercury.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemInfoDO;

@Service
public interface AmazonItemInfoService {
	//新增
		void addAmazonItemInfoDO(AmazonItemInfoDO amazonItemInfoDO);
		
		//根据item的asin，颜色品种的asin，尺寸的asin来确定，还是说能用最后一个asin来唯一确定？
		void updateAmazonItemInfoByMultiAsin(AmazonItemInfoDO amazonItemInfoDO);
		
		void updateAmazonItemInfoBySelfAsin(AmazonItemInfoDO amazonItemInfoDO);
		//
		AmazonItemInfoDO getAmazonItemInfoDOByMultiAsinLimit1(AmazonItemInfoDO amazonItemInfoDO);
		
		AmazonItemInfoDO getAmazonItemInfoDOBySelfAsinLimit1(AmazonItemInfoDO amazonItemInfoDO);
		
		//TODO 这里不急写
		List<AmazonItemInfoDO> getAmazonItemInfoList(AmazonCategoryDO amazonCategoryDO);
}
