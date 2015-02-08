package com.boredou.mercury.repository.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.boredou.mercury.repository.base.SqlMapper;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;

public interface AmazonCategoryDAO extends SqlMapper{
	
	//新增
	void addAmazonCategoryDO(AmazonCategoryDO amazonCategoryDO);
	//逻辑删除
	void delAmazonCategoryDOById(int id);
	//查询
	List<AmazonCategoryDO> getAmazonCategoryList(AmazonCategoryDO amazonCategoryDO,RowBounds rowBounds);
	int getAmazonCategoryListCount(AmazonCategoryDO amazonCategoryDO);
	//更改
	void updateAmazonCategoryDO(AmazonCategoryDO amazonCategoryDO);
//	取得所有
	public List<AmazonCategoryDO> getAmazonCategoryAll();
	
}
