package com.boredou.mercury.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;

@Service
public interface AmazonCategoryService {
	//新增
	public void addAmazonCategoryDO(AmazonCategoryDO amazonCategoryDO);
	//逻辑删除
	public void delAmazonCategoryDOById(int id);
	//查询
	public List<AmazonCategoryDO> getAmazonCategoryList(AmazonCategoryDO amazonCategoryDO);
	//更改
	public void updateAmazonCategoryDO(AmazonCategoryDO amazonCategoryDO);
	//取得所有
	public List<AmazonCategoryDO> getAmazonCategoryAll();
}
