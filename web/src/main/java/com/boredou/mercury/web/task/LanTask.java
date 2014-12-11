package com.boredou.mercury.web.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.util.GoodsType;
import com.boredou.mercury.server.service.AmazonCategoryService;
import com.boredou.mercury.server.service.AmazonItemInfoService;
import com.boredou.mercury.server.service.AmazonItemService;
import com.boredou.mercury.server.service.AmazonPageService;
import com.boredou.mercury.web.task.util.FetchItemUtil;
import com.boredou.mercury.web.task.util.FetchPageUtil;

import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.client.SimplePoolHttpClient;
@Data
public class LanTask  {
	@Autowired
	AmazonCategoryService amazonCategoryService;
	@Autowired
	AmazonPageService amazonPageService; 
	@Autowired
	AmazonItemService amazonItemService;
	@Autowired
	AmazonItemInfoService amazonItemInfoService;
	@Setter
	private HttpClient hc;
	private static ExecutorService executor = Executors.newFixedThreadPool(5);
	private final static Logger logger = LoggerFactory.getLogger(FetchPageUtil.class);

	public void start(){
		//		1.获取四个 商品类别url
		//		2.对四个商品类别url 进行循环，得到 页面url
		//		3.对 页面url 进行循环，得到 商品url
		//		4.对 商品url 进行获取内容，存入数据库
		AmazonCategoryDO amazonCategoryDOPara = new AmazonCategoryDO();

		List<AmazonCategoryDO> amazonCategoryList = amazonCategoryService.getAmazonCategoryList(amazonCategoryDOPara);
		List<String> pageList = new ArrayList<String>();
		List<String> perPageItemList ;
		
		for (AmazonCategoryDO amazonCategoryDO : amazonCategoryList) {
			
			if(amazonCategoryDO.getId()>2) continue;
			
//			if(!GoodsType.getInstanceMap().containsKey(amazonCategoryDO.getGoodsType()))
//				logger.warn("goodstype doesn't contains "+amazonCategoryDO.getGoodsType());
			
			if(!GoodsType.getInstanceList().contains(amazonCategoryDO.getGoodsType()))
				logger.warn("goodstype doesn't contains "+amazonCategoryDO.getGoodsType());
			
			
			
			pageList = FetchPageUtil.getPageList(amazonCategoryDO.getSearchUrl());
			
			logger.warn("page total: "+pageList.size());
			for (String string : pageList) {
				System.out.println(string);
			}
			
			for (String pageUrl : pageList) {
				if(i++>0) return ;
				
				perPageItemList = FetchItemUtil.getItemList(pageUrl);
				System.out.println("pageUrl"+pageUrl+" ,itemNum:  "+perPageItemList.size());
				for (String goodsUrl : perPageItemList) {
//					单线程方法
//					FetchItemUtil.getItem(amazonCategoryDO, goodsUrl);
//					线程池方法
					executor.submit(new FetchItem(amazonCategoryDO, goodsUrl));
				}
			}
		}

	}
	static int i=0,j=0,k=0;
	@AllArgsConstructor
	private class FetchItem implements Runnable{
		private AmazonCategoryDO amazonCategoryDO;
		private String goodsUrl;
		@Override
		public void run() {
			System.out.println("新线程执行...");
			FetchItemUtil.getItem(amazonCategoryDO, goodsUrl);
		}
		
	}
	
}
