package com.boredou.mercury.repository.entity;

import lombok.Data;

@Data
public class ItemDO {
	private int id;
	private String title;
	private String goodsUrl;
	private String attrs;
	private String category;
}
