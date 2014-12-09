package com.boredou.mercury.server.service;

import org.springframework.stereotype.Service;

import com.boredou.mercury.repository.entity.AmazonPageDO;

@Service
public interface AmazonPageService {
	//新增
	public void addAmazonPageDO(AmazonPageDO amazonPageDO);
}
