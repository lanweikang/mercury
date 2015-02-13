package com.boredou.mercury.web.task.util.datafetch.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import cn.weili.util.StringUtil;

import com.alibaba.fastjson.JSONObject;

public class ShoesHelp {
	
	
	public static String getItemName(String content){
		Matcher mItemName = pattItemName.matcher(content);
		while(mItemName.find()){
			return StringUtil.trimNotWithNull(mItemName.group(1));
		}
		return StringUtils.EMPTY;
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


	private static String expItemPrice ="priceblock_ourprice[\\s\\S]*?>([\\s\\S]*?)<";
	//	<span id=\"priceblock_ourprice\" class=\"a-size-medium a-color-price\">$54.85<\/span>\n
	private static Pattern pattItemPrice = Pattern.compile(expItemPrice);

	//	<span class=\"a-size-medium a-color-success\">\n        \n            Only 5 left in stock.\n            \n        \n        \n<\/span>
	private static String expItemStock ="a-size-medium a-color-success\\\\\">([\\s\\S]*?)<";
	private static Pattern pattItemStock = Pattern.compile(expItemStock);
}
