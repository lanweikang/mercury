package com.boredou.mercury.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.repository.dao.AmazonCategoryDAO;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.server.service.AmazonCategoryService;

public class AmazonCategoryServiceImpl implements AmazonCategoryService {

	@Autowired
	private AmazonCategoryDAO amazonCategoryDAO;
	@Override
	public void addAmazonCategoryDO(AmazonCategoryDO amazonCategoryDO) {
		// TODO Auto-generated method stub
		amazonCategoryDAO.addAmazonCategoryDO(amazonCategoryDO);
	}

	@Override
	public void delAmazonCategoryDOById(int id) {
		// TODO Auto-generated method stub
		amazonCategoryDAO.delAmazonCategoryDOById(id);
	}

	@Override
	public List<AmazonCategoryDO> getAmazonCategoryList(
			AmazonCategoryDO amazonCategoryDO) {
		// TODO Auto-generated method stub
		return amazonCategoryDAO.getAmazonCategoryList(amazonCategoryDO);
	}

	@Override
	public void updateAmazonCategoryDO(AmazonCategoryDO amazonCategoryDO) {
		// TODO Auto-generated method stub
		amazonCategoryDAO.updateAmazonCategoryDO(amazonCategoryDO);
	}

}
