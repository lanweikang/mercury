package com.boredou.mercury.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.repository.dao.AmazonItemDAO;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemDO;
import com.boredou.mercury.server.service.AmazonItemService;

public class AmazonItemServiceImpl implements AmazonItemService {

	@Autowired
	private AmazonItemDAO amazonItemDAO;
	@Override
	public void addAmazonItemDO(AmazonItemDO amazonItemDO) {
		// TODO Auto-generated method stub
		amazonItemDAO.addAmazonItemDO(amazonItemDO);
	}

	@Override
	public void updateAmazonItemDOByAsin(AmazonItemDO amazonItemDO) {
		// TODO Auto-generated method stub
		amazonItemDAO.updateAmazonItemDOByAsin(amazonItemDO);
	}

	@Override
	public List<AmazonItemDO> getAmazonItemList(
			AmazonCategoryDO amazonCategoryDO) {
		// TODO Auto-generated method stub
		return amazonItemDAO.getAmazonItemList(amazonCategoryDO);
	}

	@Override
	public AmazonItemDO getAmazonItemDOByAsinLimit1(
			AmazonItemDO amazonItemDO) {
		// TODO Auto-generated method stub
		return amazonItemDAO.getAmazonItemDOByAsinLimit1(amazonItemDO);
	}

}
