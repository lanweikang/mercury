package com.boredou.mercury.web.task.util.datafetch;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;

public interface FetchMethod {
	void doFetch(AmazonCategoryDO amazonCategoryDO,String goodsUrl);
}
