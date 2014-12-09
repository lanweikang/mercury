package task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.weili.util.StringUtil;
import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.param.RequestParams;
import cn.weili.util.http.result.ResponseResult;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemDO;
import com.boredou.mercury.repository.entity.AmazonItemInfoDO;
import com.boredou.mercury.repository.entity.AmazonPageDO;
import com.boredou.mercury.server.service.AmazonCategoryService;
import com.boredou.mercury.server.service.AmazonItemInfoService;
import com.boredou.mercury.server.service.AmazonItemService;
import com.boredou.mercury.server.service.AmazonPageService;
import com.boredou.mercury.server.service.amazon.fileUtil;

public class AmazonDailyTask {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Setter
	private HttpClient hc;
	@Autowired
	AmazonCategoryService amazonCategoryService;
	@Autowired
	AmazonPageService amazonPageService; 
	@Autowired
	AmazonItemService amazonItemService;
	@Autowired
	AmazonItemInfoService amazonItemInfoService;
	
	private static ExecutorService executor = Executors.newFixedThreadPool(5);
	
	//匹配页面链接
	private static String expPageLink = "pagnLink.*href=\"(.*)\"";
	private static Pattern pattPageLink = Pattern.compile(expPageLink);

	//匹配总页面数量 
	/**
	 * >2</a></span>
	 * <span class="pagnRA"
	 */
	private static String expPageTotal = ">([\\d]+)[</a>]*</span>\\s*<span class=\"pagnRA\"";
	private static Pattern pattPageTotal = Pattern.compile(expPageTotal);
	
	//匹配item的url
//	private static String expItemUrl = "result.*prod.*\\s+.*linePlaceholder.*\\s+.*href=\"(.*)\"><div";
	private static String expItemUrl ="result_[0-9]+[\\S\\s]{2}data-asin=\"([a-zA-Z0-9]{10})\"";
	private static Pattern pattItemUrl = Pattern.compile(expItemUrl);
	
	//匹配item的name
	private static String expItemName ="<span id=\"productTitle\" class=\"a-size-large\">(.*?)</span>";
	private static Pattern pattItemName = Pattern.compile(expItemName);
	
	//匹配item的数量
	private static String expItemStock ="<div id=\"availability\" class=\"a-section a-spacing-none\">[\\s\\S]+?<span class=\"a-size-medium a-color-success\">([\\s\\S]*?)</span>";
	private static Pattern pattItemStock = Pattern.compile(expItemStock);
	
	//匹配item的价格
	private static String expItemPrice ="<span id=\"priceblock_ourprice\" class=\"a-size-medium a-color-price\">(\\$.*?)</span>";
	private static Pattern pattItemPrice = Pattern.compile(expItemPrice);
	//匹配item的卖家
	private static String expItemSeller ="Sold by <a href='/gp/help/seller/at-a-glance.html[\\s\\S]+?>([\\s\\S]*?)</a>";
	private static Pattern pattItemSeller = Pattern.compile(expItemSeller);
	
	//材料，material
//	<span style="white-space: normal">Dial window material type</span>
	private static String expItemDialWindowType ="<span style=\"white-space: normal\">Dial window material type</span>[\\s\\S]+?<td class=\"a-span7 a-size-base\">([\\s\\S]+?)</td>";
	private static Pattern pattItemDialWindowType = Pattern.compile(expItemDialWindowType);
	
	private static String expItemCaseMaterial ="<th class=\"a-span5 a-size-base\">[\\s\\S]+?Case material[\\s\\S]+?<td class=\"a-span7 a-size-base\">([\\s\\S]+?)</td>";
	private static Pattern pattItemCaseMaterial = Pattern.compile(expItemCaseMaterial);
	
	private static String expItemBandMaterial ="<th class=\"a-span5 a-size-base\">[\\s\\S]+?Band Material[\\s\\S]+?<td class=\"a-span7 a-size-base\">([\\s\\S]+?)</td>";
	private static Pattern pattItemBandMaterial = Pattern.compile(expItemBandMaterial);
	//large img url 
	private static String expItemImgUrl ="\"large\":\"([\\s\\S]+?)\",\"main\"";
	private static Pattern pattItemImgUrl = Pattern.compile(expItemImgUrl);
	
	private static int i=0,j=0,k=0;
	
	public void start(){
		System.out.println("task..........lanweikang"+new Date());
		
		AmazonCategoryDO amazonCategoryDO = new AmazonCategoryDO();
		
		List<AmazonCategoryDO> amazonCategoryList = amazonCategoryService.getAmazonCategoryList(amazonCategoryDO);
		
		//开始记录下时间
		if(amazonCategoryList == null || amazonCategoryList.size() == 0) return ;
			
		logger.warn("记录下这一次运行的时间"+new Date());
		
		for (AmazonCategoryDO amazonCategoryDO0 : amazonCategoryList) {
			System.out.println("种类：   "+amazonCategoryDO0);
			//根据搜索页面得到pagelist
			//category to pagelist
			i=0;j=0;k=0;
			if( !amazonCategoryDO0.getGoodsType().equals("watch")) continue;
			
			List<AmazonPageDO> amazonPageList = new ArrayList<AmazonPageDO>();
			
			amazonPageList = fetchPageList(amazonCategoryDO0);
			
			//@TEST
			for (AmazonPageDO amazonPageDO : amazonPageList) {
				System.out.println("yemian    "+amazonPageDO);
			}
			
			if(amazonPageList == null || amazonPageList.size() ==0){
				logger.warn("pageList is null");
				continue;
			}
			//把数据库里的pagelist全删除了
			//搞一个批量方法，把pagelist全塞进数据库
			for (AmazonPageDO amazonPageDO : amazonPageList) {
				amazonPageService.addAmazonPageDO(amazonPageDO);
			}
			
			for (AmazonPageDO amazonPageDO : amazonPageList) {
				//获取根据per page 获得 itemList，然后扔到线程池 executor,
				List<AmazonItemDO> amazonItemList = new ArrayList<AmazonItemDO>();
				
				try {
					amazonItemList = fetchItemList(amazonPageDO);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				for (AmazonItemDO amazonItemDO : amazonItemList) {
//					System.out.println("name:  "+amazonItemDO.getName());
//					executor.submit(new FetchItem(amazonItemDO));
//					logger.warn("asin: "+amazonItemDO.getItemUrl());
					fetchItem(amazonItemDO,amazonCategoryDO);
				}
			}
		}
		System.out.println("end...");
	}
	private static int ijk=0;
	//AmazonCategoryDO是为了根据amazonCategoryDO.getGoodsType()来进行不同的ajax请求
	private void fetchItem(AmazonItemDO amazonItemDO,AmazonCategoryDO amazonCategoryDO){
		String asin = amazonItemDO.getItemUrl().substring(amazonItemDO.getItemUrl().length()-10,amazonItemDO.getItemUrl().length());
		AmazonItemDO amazonItemDOResult = new AmazonItemDO();
		try{
			ResponseResult result = hc.execute(RequestParams.custom().setUrl(amazonItemDO.getItemUrl()).build());
			if(result == null) return ;
			int code = result.getResultCode();
			String content = StringUtil.trimNotWithNull(result.getValue());
			amazonItemDOResult.setItemUrl(amazonItemDO.getItemUrl());
			amazonItemDOResult.setLastHttpCode(code);
//			amazonItemDOResult.setWholeContent(content);
			amazonItemDOResult.setBelongtoCategoryId(amazonItemDO.getBelongtoCategoryId());
			amazonItemDOResult.setAsin(asin);
			
			
			if(ijk==0){
				System.out.println("lanweikang");
				fileUtil.writeToFile(content, "C:/Users/Administrator/Desktop/sk/log/log.txt");
				ijk++;
			}
//			pattItemName
			Matcher mItemName = pattItemName.matcher(content);
			while(mItemName.find()){
				String itemName = mItemName.group(1);
				itemName = StringUtil.trimNotWithNull(itemName);
				amazonItemDOResult.setName(itemName);
				System.out.println("itemName:"+itemName);
				break;
			}
			
			StringBuilder WholeContent = new StringBuilder();
			
			Matcher mItemStock = pattItemStock.matcher(content);
			while(mItemStock.find()){
				String itemStock = mItemStock.group(1).trim();
				if(itemStock!=null) WholeContent.append("stock:").append(itemStock);
//				System.out.println("itemStock:"+itemStock);
				break;
			}
			
			Matcher mItemPrice = pattItemPrice.matcher(content);
			while(mItemPrice.find()){
				String itemPrice = mItemPrice.group(1);
				if(itemPrice!=null) WholeContent.append(",").append("price:").append(itemPrice);
//				System.out.println("itemPrice:"+itemPrice);
				break;
			}
			
			Matcher mItemSeller = pattItemSeller.matcher(content);
			while(mItemSeller.find()){
				String itemSeller = mItemSeller.group(1);
				if(itemSeller!=null) WholeContent.append(",").append("seller:").append(itemSeller);
//				System.out.println("itemSeller:"+itemSeller);
				break;
			}
			
			//材料
			StringBuilder materialSb = new StringBuilder();//pattItemDialWindowType
			Matcher mItemDialWindowType = pattItemDialWindowType.matcher(content);
			while(mItemDialWindowType.find()){
				String itemDialWindowType = mItemDialWindowType.group(1).trim();
				if(itemDialWindowType!=null) materialSb.append("Dial window material type:").append(itemDialWindowType);
//				System.out.println("itemDialWindowType:"+itemDialWindowType);
				break;
			}
			//pattItemCaseMaterial
			Matcher mItemCaseMaterial = pattItemCaseMaterial.matcher(content);
			while(mItemCaseMaterial.find()){
				String itemCaseMaterial = mItemCaseMaterial.group(1).trim();
				if(itemCaseMaterial!=null) materialSb.append(",").append("Case Material:").append(itemCaseMaterial);
//				System.out.println("itemCaseMaterial:"+itemCaseMaterial);
				break;
			}
			//pattItemBandMaterial
			Matcher mItemBandMaterial = pattItemBandMaterial.matcher(content);
			while(mItemBandMaterial.find()){
				String itemBandMaterial = mItemBandMaterial.group(1).trim();
				if(itemBandMaterial!=null) materialSb.append(",").append("Band Material:").append(itemBandMaterial);
//				System.out.println("itemBandMaterial:"+itemBandMaterial);
				break;
			}
			
			if(StringUtils.isNotBlank(materialSb)){
				WholeContent.append(",").append("material:").append("{").append(materialSb).append("}");
//				System.out.println("materialSb:"+materialSb);
			}
			
			//pattItemImgUrl
			StringBuilder imgUrlsSb = new StringBuilder();
			Matcher mItemImgUrl = pattItemImgUrl.matcher(content);
			while(mItemImgUrl.find()){
				String itemImgUrl = mItemImgUrl.group(1);
				if(itemImgUrl!=null) imgUrlsSb.append(",").append("imgUrl:").append(itemImgUrl);
				System.out.println("itemImgUrl:"+itemImgUrl);
			}
			
			if(StringUtils.isNotBlank(imgUrlsSb)){
				WholeContent.append(",").append("imgUrls:").append("{").append(imgUrlsSb).append("}");
				System.out.println("imgUrlsSb:"+imgUrlsSb);
			}
			
			if(StringUtils.isNotBlank(WholeContent)) amazonItemDOResult.setWholeContent(WholeContent.toString());
//			amazonItemDOResult.setWholeContent(content);
			
			
////			开始往itemInfo里面塞入
//			AmazonItemInfoDO amazonItemInfoDOParam = new AmazonItemInfoDO();
//			if(amazonCategoryDO.getGoodsType().equals(GoodsType.watch)){
//				amazonItemInfoDOParam.setSelfAsin(asin);
//				AmazonItemInfoDO amazonItemInfoDO = amazonItemInfoService.getAmazonItemInfoDOBySelfAsinLimit1(amazonItemInfoDOParam);
//				
//			}else if(amazonCategoryDO.getGoodsType().equals(GoodsType.shoes)){
//				amazonItemInfoDOParam.setBelongAsin(asin);
//				
//			}
			
			
			
		}catch(Exception e){
			amazonItemDOResult.setLastHttpCode(ResponseResult.READ_TIMEOUT);
			amazonItemDOResult.setAsin(asin);
			e.getStackTrace();
		}finally{
			AmazonItemDO amazonItemDOParam = new AmazonItemDO();
			amazonItemDOParam.setAsin(asin);
			if(amazonItemService.getAmazonItemDOByAsinLimit1(amazonItemDOParam)==null){
				amazonItemService.addAmazonItemDO(amazonItemDOResult);
			}else{
				amazonItemService.updateAmazonItemDOByAsin(amazonItemDOResult);
			}
		}
	}
	
	@AllArgsConstructor
	private class FetchItem implements Runnable{
		
		private final AmazonItemDO amazonItemDO;
		
		private final AmazonCategoryDO amazonCategoryDO;
		
		@Override
		public void run() {
			fetchItem(amazonItemDO,amazonCategoryDO);
		}
		
	}
	
	
	private List<AmazonItemDO> fetchItemList(AmazonPageDO amazonPageDO) {
		
		String pageUrl = amazonPageDO.getPageUrl();
		ResponseResult result = hc.execute(RequestParams.custom().setUrl(pageUrl).build());
		
		String rexp = result.getValue();
		
		List<AmazonItemDO> itemList = new ArrayList<AmazonItemDO>();
		Matcher mItemLink = pattItemUrl.matcher(rexp);
		while(mItemLink.find()){
			String itemLinkAsin = mItemLink.group(1);
			
			AmazonItemDO amazonItemDO = new AmazonItemDO();
			amazonItemDO.setItemUrl("http://www.amazon.com/dp/"+itemLinkAsin);
			amazonItemDO.setBelongtoCategoryId(amazonPageDO.getBelongtoCategoryId());
			
			itemList.add(amazonItemDO);
			
		}
		logger.warn("pageItemSum:"+itemList.size());
		
		return itemList;
	}
	
	private List<AmazonPageDO> fetchPageList(AmazonCategoryDO amazonCategoryDO){
		
		String searchUrl = amazonCategoryDO.getSearchUrl();
		
		ResponseResult result = hc.execute(RequestParams.custom().setUrl(searchUrl).build());
		
		AmazonCategoryDO reamazonCategoryDO = new AmazonCategoryDO();
		reamazonCategoryDO.setId(amazonCategoryDO.getId());
		reamazonCategoryDO.setLastHttpCode(result.getResultCode());
		
		amazonCategoryService.updateAmazonCategoryDO(reamazonCategoryDO);
		
		if(result==null) return null;
		
		String rexp = result.getValue();

		List<AmazonPageDO> pageList = new ArrayList<AmazonPageDO>();
		int pageTotle=0;
		String pageLink="";
		Matcher mPageLink = pattPageLink.matcher(rexp);
		Matcher mPageTotle = pattPageTotal.matcher(rexp);
		//找到总页数
		while(mPageTotle.find()){
			pageTotle = Integer.parseInt(mPageTotle.group(1));
			
			logger.debug("共找到："+pageTotle+"页码");
			break;
		}
		//找到某一个
		while(mPageLink.find()){
			pageLink = mPageLink.group(1);
			logger.debug("pageLink:"+pageLink);
			break;
		}
		if(pageLink=="") return null;
		for(int i=1;i<=pageTotle;i++){
			String s = pageLink.replaceAll("ref=sr_pg_\\d*", "ref=sr_pg_"+i).replaceAll("page=\\d*", "page="+i);
			AmazonPageDO amazonPageDO = new AmazonPageDO();
			amazonPageDO.setPageUrl("http://www.amazon.com"+s);
			amazonPageDO.setBelongtoCategoryId(amazonCategoryDO.getId());
			pageList.add(amazonPageDO);
		}
		return pageList;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("test..........lanweikang"+new Date());
//		HttpClient hc = new HttpClient();
//		System.out.println(hc);
//		String url = "http://www.golandit.com";
//		ResponseResult result = hc.execute(RequestParams.custom().setUrl(url).build());
//		System.out.println(result.getResultCode());
//		System.out.println("end...");
		List<AmazonPageDO> amazonPageList = new ArrayList<AmazonPageDO>();
		
		System.out.println(""+amazonPageList+amazonPageList.size());
		
	}

}
