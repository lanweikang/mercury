package com.boredou.mercury.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.repository.dao.AmazonPageDAO;
import com.boredou.mercury.repository.entity.AmazonPageDO;
import com.boredou.mercury.server.service.AmazonPageService;

public class AmazonPageServiceImpl implements AmazonPageService {
	
	@Autowired
	AmazonPageDAO amazonPageDAO;
	@Override
	public void addAmazonPageDO(AmazonPageDO amazonPageDO) {
		// TODO Auto-generated method stub
		amazonPageDAO.addAmazonPageDO(amazonPageDO);
	}

}
