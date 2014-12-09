package com.boredou.mercury.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boredou.mercury.repository.entity.ItemDO;

@Service
public interface ItemDownloadService {
	List<ItemDO> getItemList(String category);
}
