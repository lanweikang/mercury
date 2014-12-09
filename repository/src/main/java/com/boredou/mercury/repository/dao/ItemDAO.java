package com.boredou.mercury.repository.dao;

import java.util.List;

import com.boredou.mercury.repository.base.SqlMapper;
import com.boredou.mercury.repository.entity.ItemDO;

public interface ItemDAO extends SqlMapper {
	List<ItemDO> getItemList(String category);
}
