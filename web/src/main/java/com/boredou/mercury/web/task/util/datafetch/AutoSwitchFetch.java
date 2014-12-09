package com.boredou.mercury.web.task.util.datafetch;

import java.util.Map;

import lombok.Setter;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;

public class AutoSwitchFetch implements FetchMethod {
	@Setter
	private static Map<String, FetchMethod> fetchMap;
	
	@Override
	public void fetch(AmazonCategoryDO amazonCategoryDO, String goodsUrl) {
		FetchMethod fetchMethod = fetchMap.get(amazonCategoryDO.getGoodsType());
		fetchMethod.fetch(amazonCategoryDO, goodsUrl);
	}

}
