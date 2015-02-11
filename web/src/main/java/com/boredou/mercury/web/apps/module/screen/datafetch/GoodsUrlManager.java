package com.boredou.mercury.web.apps.module.screen.datafetch;

import static com.alibaba.citrus.util.ObjectUtil.defaultIfNull;
import static org.apache.commons.lang.StringUtils.trimToNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.ItemDO;
import com.boredou.mercury.repository.entity.download.WatchDO;
import com.boredou.mercury.server.service.AmazonCategoryService;
import com.boredou.mercury.server.service.ItemDownloadService;
import com.boredou.mercury.web.base.AbstractController;
import com.boredou.mercury.web.download.test.ExportExcel;
import com.boredou.mercury.web.write.JsonHttpWrite;

public class GoodsUrlManager  extends AbstractController  {
	@Autowired
	private AmazonCategoryService amazonCategoryService;
	
	public void doPerform(Context context) {
		context.put("cList","cList");
	}
	
	public void doSearch(Context context,HttpServletResponse response,
			@Param("pageIndex") int pageIndex,@Param("start") int start, @Param("limit") int limit,
			@Param("name") String name
			) {
		System.out.println("lwk..."+getParametersMap());
		AmazonCategoryDO AmazonCategoryParam = new AmazonCategoryDO();
		AmazonCategoryParam.setPP(pageIndex);
		AmazonCategoryParam.setPageSize(limit);
		AmazonCategoryParam.setName(name);
		List<AmazonCategoryDO> resultList = amazonCategoryService.getAmazonCategoryList(AmazonCategoryParam);
			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", resultList);
		map.put("results", AmazonCategoryParam.getTotalCount() );
		JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, map);
		jsonHttpWrite.write();
	}
	
}

