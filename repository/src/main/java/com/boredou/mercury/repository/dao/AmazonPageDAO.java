package com.boredou.mercury.repository.dao;

import java.util.List;

import com.boredou.mercury.repository.base.SqlMapper;
import com.boredou.mercury.repository.entity.AmazonPageDO;

public interface AmazonPageDAO extends SqlMapper {
	
	//新增
	void addAmazonPageDO(AmazonPageDO amazonPageDO);
	
//	//修改
//	void updateAmazonPageDO(AmazonPageDO amazonPageDO);
	
//	//查询
//	List<AmazonPageDO> getAmazonPageListByCategoryId(int categoryId);
}
