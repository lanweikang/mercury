package com.boredou.mercury.repository.entity;

import lombok.Data;

import com.boredou.mercury.repository.base.BaseDO;
@Data
public class AmazonItemDO extends BaseDO {
	
	private String name;
	
	private String asin;
	
	private String itemUrl;
	
	private int lastHttpCode;
	
	private String wholeContent;
	
	private long belongtoCategoryId;
	
	//另外的字段再加
}
