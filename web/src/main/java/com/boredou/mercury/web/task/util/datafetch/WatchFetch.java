package com.boredou.mercury.web.task.util.datafetch;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.weili.util.StringUtil;
import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.client.SimpleHttpClient;
import cn.weili.util.http.param.RequestParams;
import cn.weili.util.http.result.ResponseResult;

import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemDO;
import com.boredou.mercury.server.service.AmazonItemService;
import com.boredou.mercury.server.service.amazon.fileUtil;
import com.boredou.mercury.web.util.Consts;

public class WatchFetch implements FetchMethod {
	@Resource(name = "poolHttpClient")
	private HttpClient hc;

	@Autowired
	AmazonItemService amazonItemService;

	private static int ijk=0;
	@Override
	public void doFetch(AmazonCategoryDO amazonCategoryDO, String goodsUrl) {
		// TODO Auto-generated method stub
		System.out.println("lwk......"+"  WatchFetch" + "  -----------------  "+goodsUrl);

		String asin = goodsUrl.substring(goodsUrl.length()-10,goodsUrl.length());
		AmazonItemDO amazonItemDOResult = new AmazonItemDO();
		try{
//			System.out.println("SimpleHttpClient before hc:"+hc);
			((SimpleHttpClient )hc).setConnectTimeout(90000);
			((SimpleHttpClient )hc).setReadTimeout(90000);
			((SimpleHttpClient )hc).setSoTimeout(90000);
			System.out.println("Before connectTime:"+((SimpleHttpClient )hc).getConnectTimeout()+",ReadTimeout:"+((SimpleHttpClient )hc).getReadTimeout()+"," +
					"SoTimeout"+((SimpleHttpClient )hc).getSoTimeout());
//			System.out.println("SimpleHttpClient after hc:"+hc);
			ResponseResult result = hc.execute(RequestParams.custom().setUrl(goodsUrl)
					.addHeader(Consts.CHEOME_USER_AGENT)
					.addHeader(Consts.ACCEPT)
					.setReadTimeout( 90000L )
					.build());
			System.out.println("After connectTime:"+((SimpleHttpClient )hc).getConnectTimeout()+",ReadTimeout:"+((SimpleHttpClient )hc).getReadTimeout()+"," +
					"SoTimeout"+((SimpleHttpClient )hc).getSoTimeout());
			int code = result.getResultCode();
			String content = StringUtil.trimNotWithNull(result.getValue());
			amazonItemDOResult.setItemUrl(goodsUrl);
			amazonItemDOResult.setLastHttpCode(code);
			//			amazonItemDOResult.setWholeContent(content);
			amazonItemDOResult.setBelongtoCategoryId(amazonCategoryDO.getId());
			amazonItemDOResult.setAsin(asin);


			if(ijk==0){
				System.out.println("lanweikang");
				fileUtil.writeToFile(content, "C:/Users/Administrator/Desktop/sk/log/log.txt");
			}
			//			pattItemName
			Matcher mItemName = pattItemName.matcher(content);
			while(mItemName.find()){
				String itemName = mItemName.group(1);
				itemName = StringUtil.trimNotWithNull(itemName);
				amazonItemDOResult.setName(itemName);
				System.out.println("itemName:"+itemName);
//				break;
			}

			StringBuilder WholeContent = new StringBuilder();
			JSONObject jsonObject = new JSONObject();

			Matcher mItemStock = pattItemStock.matcher(content);
			while(mItemStock.find()){
				String itemStock = mItemStock.group(1).trim();
//				if(itemStock!=null) WholeContent.append("stock:").append(itemStock);
				jsonObject.put("stock", itemStock);
//				break;
			}

			Matcher mItemPrice = pattItemPrice.matcher(content);
			while(mItemPrice.find()){
				String itemPrice = mItemPrice.group(1);
//				if(itemPrice!=null) WholeContent.append(",").append("price:").append(itemPrice);
				jsonObject.put("price", itemPrice);
//				break;
			}

			Matcher mItemSeller = pattItemSeller.matcher(content);
			while(mItemSeller.find()){
				String itemSeller = mItemSeller.group(1);
//				if(itemSeller!=null) WholeContent.append(",").append("seller:").append(itemSeller);
				jsonObject.put("seller", itemSeller);
//				break;
			}

			//材料
			StringBuilder materialSb = new StringBuilder();//pattItemDialWindowType
			JSONObject materialJsonObject = new JSONObject();
			Matcher mItemDialWindowType = pattItemDialWindowType.matcher(content);
			while(mItemDialWindowType.find()){
				String itemDialWindowType = mItemDialWindowType.group(1).trim();
				if(itemDialWindowType!=null) materialSb.append("Dial window material type:").append(itemDialWindowType);
				materialJsonObject.put("Dial window material type", itemDialWindowType);
//				break;
			}
			//pattItemCaseMaterial
			Matcher mItemCaseMaterial = pattItemCaseMaterial.matcher(content);
			while(mItemCaseMaterial.find()){
				String itemCaseMaterial = mItemCaseMaterial.group(1).trim();
				if(itemCaseMaterial!=null) materialSb.append(",").append("Case Material:").append(itemCaseMaterial);
				materialJsonObject.put("Case Material", itemCaseMaterial);
//				break;
			}
			//pattItemBandMaterial
			Matcher mItemBandMaterial = pattItemBandMaterial.matcher(content);
			while(mItemBandMaterial.find()){
				String itemBandMaterial = mItemBandMaterial.group(1).trim();
				if(itemBandMaterial!=null) materialSb.append(",").append("Band Material:").append(itemBandMaterial);
				materialJsonObject.put("Band Material", itemBandMaterial);
//				break;
			}
			
			if(StringUtils.isNotBlank(materialSb)){
//				WholeContent.append(",").append("material:").append("{").append(materialSb).append("}");
				//				System.out.println("materialSb:"+materialSb);
			}
			
			jsonObject.put("material", materialJsonObject);

			//pattItemImgUrl
			StringBuilder imgUrlsSb = new StringBuilder();
			List<String> imgUrlList = new ArrayList<String>();
			Matcher mItemImgUrl = pattItemImgUrl.matcher(content);
			while(mItemImgUrl.find()){
				String itemImgUrl = mItemImgUrl.group(1);
				if(itemImgUrl!=null) imgUrlsSb.append(",").append("imgUrl:").append(itemImgUrl);
				imgUrlList.add(itemImgUrl);
			}
			jsonObject.put("imgUrl", imgUrlList);
			
			if(StringUtils.isNotBlank(imgUrlsSb)){
//				WholeContent.append(",").append("imgUrls:").append("{").append(imgUrlsSb).append("}");
				System.out.println("imgUrlsSb:"+imgUrlsSb);
			}
			
			WholeContent.append(jsonObject);
			if(ijk==0){
				System.out.println("lanweikangjsononject:"+jsonObject);
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
		ijk++;
	}

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
	//		<span style="white-space: normal">Dial window material type</span>
	private static String expItemDialWindowType ="<span style=\"white-space: normal\">Dial window material type</span>[\\s\\S]+?<td class=\"a-span7 a-size-base\">([\\s\\S]+?)</td>";
	private static Pattern pattItemDialWindowType = Pattern.compile(expItemDialWindowType);

	private static String expItemCaseMaterial ="<th class=\"a-span5 a-size-base\">[\\s\\S]+?Case material[\\s\\S]+?<td class=\"a-span7 a-size-base\">([\\s\\S]+?)</td>";
	private static Pattern pattItemCaseMaterial = Pattern.compile(expItemCaseMaterial);

	private static String expItemBandMaterial ="<th class=\"a-span5 a-size-base\">[\\s\\S]+?Band Material[\\s\\S]+?<td class=\"a-span7 a-size-base\">([\\s\\S]+?)</td>";
	private static Pattern pattItemBandMaterial = Pattern.compile(expItemBandMaterial);
	//large img url 
	private static String expItemImgUrl ="\"large\":\"([\\s\\S]+?)\",\"main\"";
	private static Pattern pattItemImgUrl = Pattern.compile(expItemImgUrl);

}
