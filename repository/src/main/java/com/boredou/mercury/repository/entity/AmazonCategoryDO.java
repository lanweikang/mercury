package com.boredou.mercury.repository.entity;

import lombok.Data;

import com.boredou.mercury.repository.base.BaseDO;
import com.boredou.mercury.repository.enums.Display;
import com.boredou.mercury.repository.enums.SearchStatus;
import com.boredou.mercury.repository.enums.Sex;
@Data
public class AmazonCategoryDO extends BaseDO {
	//名称
	private String name;
	
	//页面搜索的url
	private String searchUrl;
	
	//商品种类
	private String goodsType;
	
	private Sex sex;
	//是否爬
	private Display display=Display.yes;
	
	//TODO
	private SearchStatus searchStatus; 
	
	private int lastHttpCode;
	
}
