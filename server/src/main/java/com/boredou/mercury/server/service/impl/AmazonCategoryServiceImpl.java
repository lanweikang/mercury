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
		amazonCategoryDAO.addAmazonCategoryDO(amazonCategoryDO);
	}

	@Override
	public void delAmazonCategoryDOById(int id) {
		amazonCategoryDAO.delAmazonCategoryDOById(id);
	}

	@Override
	public List<AmazonCategoryDO> getAmazonCategoryList(
			AmazonCategoryDO amazonCategoryDO) {
		int totalCount = amazonCategoryDAO.getAmazonCategoryListCount(amazonCategoryDO);
		amazonCategoryDO.setTotalCount(totalCount);
		return amazonCategoryDAO.getAmazonCategoryList(amazonCategoryDO,amazonCategoryDO.getRowBounds());
	}

	@Override
	public void updateAmazonCategoryDO(AmazonCategoryDO amazonCategoryDO) {
		amazonCategoryDAO.updateAmazonCategoryDO(amazonCategoryDO);
	}

	@Override
	public List<AmazonCategoryDO> getAmazonCategoryAll() {
		return amazonCategoryDAO.getAmazonCategoryAll();
	}

	@Override
	public AmazonCategoryDO loadById(long categoryId) {
		return amazonCategoryDAO.loadById(categoryId);
	}
	
}
