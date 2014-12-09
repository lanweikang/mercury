package com.boredou.mercury.repository.entity;

import java.util.Date;

import lombok.Data;

import com.boredou.mercury.repository.base.BaseDO;
@Data
public class AmazonUpdateLogDO extends BaseDO {
	//更新的商品种类
	private int updateCategoryId;
	//更新的商品种类的url
	private String updateGoodsUrl;
	//更新时间
	private Date updateTime;
}
