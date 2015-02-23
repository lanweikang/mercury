package com.boredou.mercury.web.task.util.datafetch.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.boredou.mercury.web.write.FileUtil;

import cn.weili.util.StringUtil;

public class ShoesHelp {
	public static String getItemName(String content){
		Matcher mItemName = pattItemName.matcher(content);
		while(mItemName.find()){
			return StringUtil.trimNotWithNull(mItemName.group(1));
		}
		return StringUtils.EMPTY;
	}
	public static List<String> getMaterials(String content){
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
		return materialList;
	}

	public static String getItemJson(String content){
		Matcher mItemJson = pattItemJson.matcher(content);
		while(mItemJson.find()){
			return mItemJson.group(1).trim();
		}
		return StringUtils.EMPTY;
	}

	public static String getItemStock(String ajaxResponse){
		Matcher mItemStock = pattItemStock.matcher(ajaxResponse);
		while(mItemStock.find()){
			return StringUtil.trimNotWithNull(mItemStock.group(1)).replaceAll("\\\\n", "").trim();
		}
		return StringUtils.EMPTY;
	}
	
	public static String getItemPrice(String ajaxResponse){
		Matcher mItemPrice = pattItemPrice.matcher(ajaxResponse);
		while(mItemPrice.find()){
			return StringUtil.trimNotWithNull(mItemPrice.group(1));
		}
		return StringUtils.EMPTY;
	}

	//匹配item的name
	private static String expItemName ="<span id=\"productTitle\" class=\"a-size-large\">(.*?)</span>";
	private static Pattern pattItemName = Pattern.compile(expItemName);

	//展开的Material 
	private static String expItemMaterial ="<div id=\"feature-bullets\" class=\"a-section a-spacing-medium a-spacing-top-small\">[\\s\\S]*?<ul class=\"a-vertical a-spacing-none\">([\\s\\S]*?)</ul>";
	private static Pattern pattItemMaterial = Pattern.compile(expItemMaterial);
	//未展开的Material 
	private static String expItemMaterialEx ="<div aria-live=\"polite\" class=\"a-row a-expander-container a-expander-inline-container\">[\\s\\S]*?<ul class=\"a-vertical a-spacing-none\">([\\s\\S]*?)</ul>";
	private static Pattern pattItemMaterialEx = Pattern.compile(expItemMaterialEx);

	//全部的Material ，单项
	private static String expItemMaterialMain ="<li><span class=\"a-list-item\">([\\s\\S]*?)</span></li>";
	private static Pattern pattItemMaterialMain = Pattern.compile(expItemMaterialMain);

	//匹配脚本中的json字符串
	private static String expItemJson ="twister-js-init-mason-data(?:[\\s\\S]*?)dataToReturn = ([\\s\\S]*?);[\\s]*?return dataToReturn;";
	private static Pattern pattItemJson = Pattern.compile(expItemJson);

	//ajax 请求stack
	//	<span class=\"a-size-medium a-color-success\">\n        \n            Only 5 left in stock.\n            \n        \n        \n<\/span>
	private static String expItemStock ="a-size-medium a-color-success\\\\\">([\\s\\S]*?)<";
	private static Pattern pattItemStock = Pattern.compile(expItemStock);

	//ajax 请求price
	private static String expItemPrice ="priceblock_(?:our|sale)price[\\s\\S]*?>([\\s\\S]*?)<";
	//	<span id=\"priceblock_ourprice\" class=\"a-size-medium a-color-price\">$54.85<\/span>\n
	private static Pattern pattItemPrice = Pattern.compile(expItemPrice);


	public static void main(String[] args){
		String filePath = "C:\\Users\\Administrator\\Desktop\\tools\\B00DUDS0LU.html";
		String  html = FileUtil.readFile(filePath);
		String itemJson ="";
		Matcher mItemJson = pattItemJson.matcher(html);
		while(mItemJson.find()){
			itemJson = mItemJson.group(1);
			System.out.println(itemJson);
			itemJson = StringUtil.trimNotWithNull(itemJson);
			break;
		}

		System.out.println("123456");
	}
}
