package com.boredou.mercury.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.repository.dao.AmazonItemInfoDAO;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemInfoDO;
import com.boredou.mercury.server.service.AmazonItemInfoService;

public class AmazonItemInfoServiceImpl implements AmazonItemInfoService {
	
	@Autowired
	private AmazonItemInfoDAO amazonItemInfoDAO;

	@Override
	public void addAmazonItemInfoDO(AmazonItemInfoDO amazonItemInfoDO) {
		amazonItemInfoDAO.addAmazonItemInfoDO(amazonItemInfoDO);
	}

	@Override
	public void updateAmazonItemInfoByMultiAsin(
			AmazonItemInfoDO amazonItemInfoDO) {
		amazonItemInfoDAO.updateAmazonItemInfoByMultiAsin(amazonItemInfoDO);
	}

	@Override
	public void updateAmazonItemInfoBySelfAsin(AmazonItemInfoDO amazonItemInfoDO) {
		amazonItemInfoDAO.updateAmazonItemInfoBySelfAsin(amazonItemInfoDO);
	}

	@Override
	public AmazonItemInfoDO getAmazonItemInfoDOByMultiAsinLimit1(
			AmazonItemInfoDO amazonItemInfoDO) {
		return amazonItemInfoDAO.getAmazonItemInfoDOByMultiAsinLimit1(amazonItemInfoDO);
	}

	@Override
	public AmazonItemInfoDO getAmazonItemInfoDOBySelfAsinLimit1(
			AmazonItemInfoDO amazonItemInfoDO) {
		return amazonItemInfoDAO.getAmazonItemInfoDOBySelfAsinLimit1(amazonItemInfoDO);
	}

	@Override
	public List<AmazonItemInfoDO> getAmazonItemInfoList(
			AmazonCategoryDO amazonCategoryDO) {
		// TODO Auto-generated method stub
		return null;
	}

}
