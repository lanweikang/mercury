package com.boredou.mercury.web.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.weili.util.http.client.HttpClient;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.enums.SearchStatus;
import com.boredou.mercury.repository.util.GoodsType;
import com.boredou.mercury.server.service.AmazonCategoryService;
import com.boredou.mercury.server.service.AmazonItemInfoService;
import com.boredou.mercury.server.service.AmazonItemService;
import com.boredou.mercury.server.service.AmazonPageService;
import com.boredou.mercury.web.task.util.FetchItemUtil;
import com.boredou.mercury.web.task.util.FetchPageUtil;
import com.boredou.mercury.web.util.UriUtil;
@Data
public class LanTask  {
	@Autowired
	private AmazonCategoryService amazonCategoryService;
	@Autowired
	private AmazonPageService amazonPageService; 
	@Autowired
	private AmazonItemService amazonItemService;
	@Autowired
	private AmazonItemInfoService amazonItemInfoService;
	@Setter
	private HttpClient hc;
	@Autowired
	private UriUtil uriUtil;
	private static ExecutorService executor = Executors.newFixedThreadPool(5);
	private final static Logger logger = LoggerFactory.getLogger(FetchPageUtil.class);

	public void start(){
		
		System.out.println(uriUtil.getAmazonPicPath());
		//		1.获取四个 商品类别url
		//		2.对四个商品类别url 进行循环，得到 页面url
		//		3.对 页面url 进行循环，得到 商品url
		//		4.对 商品url 进行获取内容，存入数据库
		AmazonCategoryDO amazonCategoryDOPara = new AmazonCategoryDO();

		List<AmazonCategoryDO> amazonCategoryList = amazonCategoryService.getAmazonCategoryList(amazonCategoryDOPara);
		List<String> pageList = new ArrayList<String>();
		List<String> perPageItemList ;
		
		for (AmazonCategoryDO amazonCategoryDO : amazonCategoryList) {
			amazonCategoryDO.setSearchStatus(SearchStatus.searching);
			amazonCategoryService.updateAmazonCategoryDO(amazonCategoryDO);
			
			if(amazonCategoryDO.getId()>2){
				amazonCategoryDO.setSearchStatus(SearchStatus.complete);
				amazonCategoryService.updateAmazonCategoryDO(amazonCategoryDO);
				continue;
			}
			
//			if(!GoodsType.getInstanceMap().containsKey(amazonCategoryDO.getGoodsType()))
//				logger.warn("goodstype doesn't contains "+amazonCategoryDO.getGoodsType());
			
			if(!GoodsType.getInstanceList().contains(amazonCategoryDO.getGoodsType()))
				logger.warn("goodstype doesn't contains "+amazonCategoryDO.getGoodsType());
			
			pageList = FetchPageUtil.getPageList(amazonCategoryDO.getSearchUrl());
			System.out.println("-----"+amazonCategoryDO.getName()+"------------总页数："+pageList.size()+"---------------");
			
			
			
			for (String pageUrl : pageList) {
//				if(i++>0) return ;
				
				System.out.println("pageUrl:********* "+pageUrl);
//				executor.submit(new FetchPage(amazonCategoryDO, pageUrl));
				
				perPageItemList = FetchItemUtil.getItemList(pageUrl);
				System.out.println("itemNum:---  "+perPageItemList.size());
//				System.out.println("pageUrl"+pageUrl+" ,itemNum:  "+perPageItemList.size());
				CountDownLatch perPageLatch = new CountDownLatch(perPageItemList.size());
				for (String goodsUrl : perPageItemList) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					单线程方法
//					FetchItemUtil.getItem(amazonCategoryDO, goodsUrl);
//					线程池方法
					executor.submit(new FetchItem(amazonCategoryDO, goodsUrl,perPageLatch));
					
				}
				try {
					perPageLatch.await(30,TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			amazonCategoryDO.setSearchStatus(SearchStatus.complete);
			amazonCategoryService.updateAmazonCategoryDO(amazonCategoryDO);
		}

	}
	static int i=0,j=0,k=0;
	@AllArgsConstructor
	private class FetchItem implements Runnable{
		private AmazonCategoryDO amazonCategoryDO;
		private String goodsUrl;
		private CountDownLatch latch;
		@Override
		public void run() {
			System.out.println("新线程执行...");
			FetchItemUtil.getItem(amazonCategoryDO, goodsUrl);
			latch.countDown();
		}
	}
	
	@AllArgsConstructor
	private class FetchPage implements Runnable{
		private AmazonCategoryDO amazonCategoryDO;
		private String pageUrl;
		@Override
		public void run() {
			List<String> perPageItemList = FetchItemUtil.getItemList(pageUrl);
			for (String goodsUrl : perPageItemList) {
//				单线程方法
				FetchItemUtil.getItem(amazonCategoryDO, goodsUrl);
//				线程池方法
//				executor.submit(new FetchItem(amazonCategoryDO, goodsUrl));
			}
		}
		
	}
	
}
