package com.boredou.mercury.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boredou.mercury.repository.dao.AmazonItemDAO;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemDO;

@Service
public interface AmazonItemService {
	//新增
	public void addAmazonItemDO(AmazonItemDO amazonItemDO);

	//用来判断是新增还是更新
	AmazonItemDO getAmazonItemDOByAsinLimit1(AmazonItemDO amazonItemDO);

	//更新
	public void updateAmazonItemDOByAsin(AmazonItemDO amazonItemDO);

	//查询,一边提供下载的功能
	public List<AmazonItemDO> getAmazonItemList(AmazonCategoryDO amazonCategoryDO);


}
