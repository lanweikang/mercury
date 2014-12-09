package com.boredou.mercury.repository.dao;

import com.boredou.mercury.repository.base.SqlMapper;
import com.boredou.mercury.repository.entity.UserDO;

public interface UserDOMapper extends SqlMapper {
	int deleteByPrimaryKey(long id);

	int insert(UserDO record);

	int insertSelective(UserDO record);

	UserDO selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserDO record);

	int updateByPrimaryKey(UserDO record);
	
}