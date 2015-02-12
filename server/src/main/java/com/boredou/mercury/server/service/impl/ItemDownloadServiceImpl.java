package com.boredou.mercury.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.repository.dao.ItemDAO;
import com.boredou.mercury.repository.entity.ItemDO;
import com.boredou.mercury.server.service.ItemDownloadService;

public class ItemDownloadServiceImpl implements ItemDownloadService {
	@Autowired
	private ItemDAO itemDAO;

	@Override
	public List<ItemDO> getItemListById(long categoryId) {
		return itemDAO.getItemListById(categoryId);
	}
}
