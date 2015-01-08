package com.boredou.mercury.web.task.util.datafetch.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import cn.weili.util.StringUtil;

public class WatchHelp {
	
	/**
	 * 
	 * @param content
	 * @return name or empty
	 */
	public static String getItemName(String content){
		Matcher mItemName = pattItemName.matcher(content);
		while(mItemName.find()){
			return StringUtil.trimNotWithNull(mItemName.group(1));
		}
		return StringUtils.EMPTY;
	}
	
	public static String getModelNum(String content){
		Matcher mItemModelNum = pattItemModelNum.matcher(content);
		while(mItemModelNum.find()){
			return StringUtil.trimNotWithNull(mItemModelNum.group(1).trim());
		}
		return StringUtils.EMPTY;
	}
	
	public static String getStock(String content){
		Matcher mItemStock = pattItemStock.matcher(content);
		while(mItemStock.find()){
			return mItemStock.group(1).trim();
		}
		return StringUtils.EMPTY;
	}
	
	public static String getSeller(String content){
		Matcher mItemSeller = pattItemSeller.matcher(content);
		while(mItemSeller.find()){
			return mItemSeller.group(1);
		}
		return StringUtils.EMPTY;
	}
	
	public static String getDialWindowType(String content){
		Matcher mItemDialWindowType = pattItemDialWindowType.matcher(content);
		while(mItemDialWindowType.find()){
			return mItemDialWindowType.group(1).trim();
		}
		return StringUtils.EMPTY;
	}
	
	public static String getCaseMaterial(String content){
		Matcher mItemCaseMaterial = pattItemCaseMaterial.matcher(content);
		while(mItemCaseMaterial.find()){
			return mItemCaseMaterial.group(1).trim();
		}
		return StringUtils.EMPTY;
	}
	
	public static String getBandMaterial(String content){
		Matcher mItemBandMaterial = pattItemBandMaterial.matcher(content);
		while(mItemBandMaterial.find()){
			return mItemBandMaterial.group(1).trim();
		}
		return StringUtils.EMPTY;
	}
	
	public static List<String> getImgUrls(String content){
		List<String> imgUrlList = new ArrayList<String>();
		Matcher mItemImgUrl = pattItemImgUrl.matcher(content);
		while(mItemImgUrl.find()){
			imgUrlList.add(mItemImgUrl.group(1));
		}
		return imgUrlList; 
	}
	
	
	public static String getPrice(String content){
		Matcher mItemPrice = pattItemPrice.matcher(content);
		while(mItemPrice.find()){
			return mItemPrice.group(1);
		}
		return StringUtils.EMPTY;
	}
	
	public static String getBandColor(String content){
		Matcher mItemBandColor = pattItemBandColor.matcher(content);
		while(mItemBandColor.find()){
			return mItemBandColor.group(1).replaceAll("\\\\n", "").trim();
		}
		return StringUtils.EMPTY;
	}
	
	
	
	public static String getBrandSellerorCollectionName(String content){
		Matcher mItemBrandSellerorCollectionName = pattItemBrandSellerorCollectionName.matcher(content);
		while(mItemBrandSellerorCollectionName.find()){
			return mItemBrandSellerorCollectionName.group(1).replaceAll("\\\\n", "").trim();
		}
		return StringUtils.EMPTY;
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

	//		Model number[\s\S]*?</th>[\s\S]*?<td class="a-span7 a-size-base">([\s\S]*?)</td>
	//		Model number
	private static String expItemModelNum ="Model number[\\s\\S]*?</th>[\\s\\S]*?<td class=\"a-span7 a-size-base\">([\\s\\S]*?)</td>";
	private static Pattern pattItemModelNum = Pattern.compile(expItemModelNum);


	private static String expItemBandColor ="<th class=\"a-span5 a-size-base\">[\\s\\S]+?Band Color[\\s\\S]+?</th>[\\s\\S]+?<td class=\"a-span7 a-size-base\">([\\s\\S]*?)</td>";
	private static Pattern pattItemBandColor = Pattern.compile(expItemBandColor);
	
	private static String expItemBrandSellerorCollectionName ="<th class=\"a-span5 a-size-base\">[\\s\\S]+?Brand, Seller, or Collection Name[\\s\\S]+?</th>[\\s\\S]+?<td class=\"a-span7 a-size-base\">([\\s\\S]*?)</td>";
	private static Pattern pattItemBrandSellerorCollectionName = Pattern.compile(expItemBrandSellerorCollectionName);
}
