package com.boredou.mercury.repository.entity;

import lombok.Data;

import com.boredou.mercury.repository.base.BaseDO;
@Data
public class AmazonPageDO extends BaseDO {
	//
	private String pageUrl;
	
	private long belongtoCategoryId;
}
