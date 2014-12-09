package com.boredou.mercury.web.task.util.datafetch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemDO;
import com.boredou.mercury.server.service.AmazonItemService;
import com.boredou.mercury.web.util.AmazonSessionUtil;
import com.boredou.mercury.web.util.Consts;

import cn.weili.util.StringUtil;
import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.param.RequestParams;
import cn.weili.util.http.result.ResponseResult;
import lombok.Setter;

public class ShoesFetch implements FetchMethod {
	@Setter
	private HttpClient hc;
	@Autowired
	AmazonItemService amazonItemService;
	
	private static int kkk=0;
	@Override
	public void fetch(AmazonCategoryDO amazonCategoryDO, String goodsUrl) {
		// TODO Auto-generated method stub
		System.out.println("lwk......"+"  ShoesFetch");
		System.out.println(goodsUrl);
		String asin = goodsUrl.substring(goodsUrl.length()-10,goodsUrl.length());
		AmazonItemDO amazonItemDOResult = new AmazonItemDO();
		int code = -1;
		try{
			ResponseResult result = hc.execute(RequestParams.custom().setUrl(goodsUrl).addHeader(Consts.CHEOME_USER_AGENT)
					.setReadTimeout(HttpClient.DEFAULT_READ_TIMEOUT).build());
			code = result.getResultCode();
			String content = StringUtil.trimNotWithNull(result.getValue());
			amazonItemDOResult.setItemUrl(goodsUrl);
			amazonItemDOResult.setLastHttpCode(code);
			amazonItemDOResult.setBelongtoCategoryId(amazonCategoryDO.getId());
			amazonItemDOResult.setAsin(asin);

			StringBuilder WholeContent = new StringBuilder();

			Matcher mItemName = pattItemName.matcher(content);
			while(mItemName.find()){
				String itemName = mItemName.group(1);
				itemName = StringUtil.trimNotWithNull(itemName);
				amazonItemDOResult.setName(itemName);
				System.out.println("itemName:"+itemName);
				break;
			}
			
			StringBuilder sb = new StringBuilder();
			Matcher mItemMaterial = pattItemMaterial.matcher(content);
			while(mItemMaterial.find()){
				String itemMaterial = mItemMaterial.group(1);
				itemMaterial = StringUtil.trimNotWithNull(itemMaterial);
				sb.append(itemMaterial);
				break;
			}
			
			Matcher mItemMaterialEx = pattItemMaterialEx.matcher(content);
			while(mItemMaterialEx.find()){
				String itemMaterialEx = mItemMaterialEx.group(1);
				itemMaterialEx = StringUtil.trimNotWithNull(itemMaterialEx);
				sb.append(itemMaterialEx);
				break;
			}
			
			List<String> materialList = new ArrayList<String>();
			Matcher mItemMaterialMain = pattItemMaterialMain.matcher(sb.toString());
			while(mItemMaterialMain.find()){
				String itemMaterialMain = mItemMaterialMain.group(1);
				itemMaterialMain = StringUtil.trimNotWithNull(itemMaterialMain);
				materialList.add(itemMaterialMain);
			}
			
//			WholeContent.append(materialList);
			
			
			
			
			String itemJson ="";
			Matcher mItemJson = pattItemJson.matcher(content);
			while(mItemJson.find()){
				itemJson = mItemJson.group(1);
				itemJson = StringUtil.trimNotWithNull(itemJson);
				System.out.println("itemJson:"+itemJson);
				break;
			}

//			String sid=AmazonSessionUtil.getSid(hc);

			if(itemJson == null || itemJson == "") return ;
			JSONObject jsonObject0 = JSONObject.parseObject(itemJson);
			JSONObject asinJson = JSONObject.parseObject(itemJson).getJSONObject("data").getJSONObject("stateData").getJSONObject("asin_variation_values");
			Map<String,JSONObject> asinVariationValuesMap = new HashMap<String, JSONObject>();
			List<String> asinList = new ArrayList<String>();
			for(String key:asinJson.keySet()){
				asinVariationValuesMap.put(key, (JSONObject)asinJson.get(key));
				asinList.add(key);
			}
			List<String> colorList = (List<String>) jsonObject0.getJSONObject("data").getJSONObject("stateData").getJSONObject("variation_values").get("color_name");
			List<String> sizeList = (List<String>) jsonObject0.getJSONObject("data").getJSONObject("stateData").getJSONObject("variation_values").get("size_name");
			
			System.out.println("ajax 查询次数:"+asinList.size());
			int i =0;
			StringBuilder ajaxSB = new StringBuilder();
			Map<String,String> asinAndAjaxUrl = getColorAndSizeUrl(jsonObject0,asinVariationValuesMap);
			for (String asinAjax : asinList) {
				ResponseResult ajaxResult = hc.execute(RequestParams.custom().setUrl(asinAndAjaxUrl.get(asinAjax)).addHeader(Consts.CHEOME_USER_AGENT)
						.setReadTimeout(HttpClient.DEFAULT_READ_TIMEOUT).build());
				String ajaxResponse = ajaxResult.getValue();
				Matcher mItemPrice = pattItemPrice.matcher(ajaxResponse);
				Boolean ajaxBoo = Boolean.FALSE;
				int colorNum = asinVariationValuesMap.get(asinAjax).getIntValue("color_name");
				int sizeNum = asinVariationValuesMap.get(asinAjax).getIntValue("size_name");
				ajaxSB.append("[("+colorList.get(colorNum)+","+sizeList.get(sizeNum)+") ");
				while(mItemPrice.find()){
					i++;
					ajaxBoo = Boolean.TRUE;
					String itemPrice = StringUtil.trimNotWithNull(mItemPrice.group(1));
//					priceSB.append("["+"color:"+colorList.get(asinVariationValuesMap.get(asinAjax).getIntValue("color_name"))+itemPrice+"]");
					ajaxSB.append(",price:"+itemPrice);
					break;
				}
				if(ajaxBoo.equals(Boolean.FALSE)){
					System.out.println("goods- "+goodsUrl+" ,"+colorList.get(colorNum)+","+sizeList.get(sizeNum)+" ,执行ajax获取价格失败，没有匹配到价格，ajaxURL："+asinAndAjaxUrl.get(asinAjax));
				}
				
				Matcher mItemStock = pattItemStock.matcher(ajaxResponse);
				String itemStock ="";
				while(mItemStock.find()){
					itemStock = StringUtil.trimNotWithNull(mItemStock.group(1));
					System.out.println(",itemStock:"+itemStock);
					ajaxSB.append("itemStock:"+itemStock.replaceAll("\\\\n", "").trim());
					break;
				}
				ajaxSB.append("]");
				
				
				
			}
			System.out.println("ajax 成功次数:" + i);
			
			if(StringUtils.isNotEmpty(ajaxSB))
				WholeContent.append(ajaxSB);

			if(StringUtils.isNotBlank(WholeContent)) amazonItemDOResult.setWholeContent(WholeContent.toString());

		}catch(Exception e){
			e.getStackTrace();
			amazonItemDOResult.setLastHttpCode(ResponseResult.READ_TIMEOUT);
			amazonItemDOResult.setAsin(asin);
		}finally{
			AmazonItemDO amazonItemDOParam = new AmazonItemDO();
			amazonItemDOParam.setAsin(asin);
			if(amazonItemService.getAmazonItemDOByAsinLimit1(amazonItemDOParam)==null){
				amazonItemService.addAmazonItemDO(amazonItemDOResult);
			}else{
				amazonItemService.updateAmazonItemDOByAsin(amazonItemDOResult);
			}
			System.out.println();
			System.out.println();
		}




	}


	//匹配item的name
	private static String expItemName ="<span id=\"productTitle\" class=\"a-size-large\">(.*?)</span>";
	private static Pattern pattItemName = Pattern.compile(expItemName);

	//匹配脚本中的json字符串
	private static String expItemJson ="<script type=\"a-state\" data-a-state=\"\\{&quot;key&quot;:&quot;twisterData&quot;\\}\">(.*?)</script>";
	private static Pattern pattItemJson = Pattern.compile(expItemJson);

	//sid
	private static String expItemSid ="ue_sid='(.*?)'";
	private static Pattern pattItemSid = Pattern.compile(expItemSid); 
	
	//展开的Material 
	private static String expItemMaterial ="<div id=\"feature-bullets\" class=\"a-section a-spacing-medium a-spacing-top-small\">[\\s\\S]*?<ul class=\"a-vertical a-spacing-none\">([\\s\\S]*?)</ul>";
	private static Pattern pattItemMaterial = Pattern.compile(expItemMaterial);
	//未展开的Material 
	private static String expItemMaterialEx ="<div aria-live=\"polite\" class=\"a-row a-expander-container a-expander-inline-container\">[\\s\\S]*?<ul class=\"a-vertical a-spacing-none\">([\\s\\S]*?)</ul>";
	private static Pattern pattItemMaterialEx = Pattern.compile(expItemMaterialEx);
	//全部的Material ，单项
	private static String expItemMaterialMain ="<li><span class=\"a-list-item\">([\\s\\S]*?)</span></li>";
	private static Pattern pattItemMaterialMain = Pattern.compile(expItemMaterialMain);

	private String getCompleteAjaxUrl(JSONObject jsonObject , String asin){
		return "http://www.amazon.com"+jsonObject.getJSONObject("data").getJSONObject("contextMetaData").getJSONObject("full").get("AJAXUrl") +
				asin +"&isFlushing=2&id="+asin+"&prefetchParam=0&mType=full&dpEnvironment=softlines"
				;
	}
	
	
	private static String expItemPrice ="priceblock_ourprice[\\s\\S]*?>([\\s\\S]*?)<\\\\/span>\\\\n";
//	<span id=\"priceblock_ourprice\" class=\"a-size-medium a-color-price\">$54.85<\/span>\n
	private static Pattern pattItemPrice = Pattern.compile(expItemPrice);
	
//	<span class=\"a-size-medium a-color-success\">\n        \n            Only 5 left in stock.\n            \n        \n        \n<\/span>
private static String expItemStock ="a-size-medium a-color-success\\\\\">([\\s\\S]*?)<";
private static Pattern pattItemStock = Pattern.compile(expItemStock);
	
	
	

	private Map<String,String> getColorAndSizeUrl( JSONObject jsonObject,Map<String,JSONObject> asinVariationValuesMap){
		Map<String,String> asinAndUrl = new HashMap<String, String>();
		for(String key:asinVariationValuesMap.keySet()){
			asinAndUrl.put(key, getCompleteAjaxUrl(jsonObject , key) );
		}
		return asinAndUrl;
	} 

}
