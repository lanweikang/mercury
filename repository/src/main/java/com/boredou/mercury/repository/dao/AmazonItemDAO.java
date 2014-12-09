package com.boredou.mercury.repository.dao;

import java.util.List;

import com.boredou.mercury.repository.base.SqlMapper;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemDO;

public interface AmazonItemDAO extends SqlMapper {
	
	//新增
	void addAmazonItemDO(AmazonItemDO amazonItemDO);
	
	//用来判断是新增还是更新
	AmazonItemDO getAmazonItemDOByAsinLimit1(AmazonItemDO amazonItemDO);
	
	//更新,asin已建立索引
	void updateAmazonItemDOByAsin(AmazonItemDO amazonItemDO);
	
	//查询,提供下载的功能
	List<AmazonItemDO> getAmazonItemList(AmazonCategoryDO amazonCategoryDO);
}
