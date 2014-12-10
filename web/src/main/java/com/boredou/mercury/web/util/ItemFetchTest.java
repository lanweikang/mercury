package com.boredou.mercury.web.util;

import java.util.Date;
import java.util.List;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.web.task.util.FetchItemUtil;
import com.boredou.mercury.web.task.util.FetchPageUtil;
import com.boredou.mercury.web.write.FileUtil;

public class ItemFetchTest {
	private static String goodsUrl = "http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dfashion-mens-watches&field-keywords=MARC+BY+MARC+JACOBS";
	private static String filePath = "C:/Users/Administrator/Desktop/df/test.txt";
	public static void main(String[] args) {
		List<String> pageList = FetchPageUtil.getPageList(goodsUrl);
		FileUtil.writeToFile((new Date()).toString()+"\r\n", filePath ,false);
		for (String string : pageList) {
			FileUtil.writeToFile("page: "+string+"\r\n", filePath ,true);
		}
		FileUtil.writeToFile("---------------------------------------\r\n"+"\r\n", filePath,true);
		
		AmazonCategoryDO amazonCategoryDO = new AmazonCategoryDO();
		amazonCategoryDO.setId(1L);
		amazonCategoryDO.setGoodsType("watch");
		for (String pageUrl : pageList) {
			FileUtil.writeToFile("pageUrl: "+pageUrl+"\r\n", filePath,true);
			List<String> itemList = FetchItemUtil.getItemList(pageUrl);
			for (String item : itemList) {
				FileUtil.writeToFile("item: "+item+"\r\n", filePath,true);
			}
		}
	}

}
