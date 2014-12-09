package com.boredou.mercury.repository.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsType {
	private static Map<String,String> goodsTypeMap = new HashMap<String, String>();
	public static Map<String,String> getInstanceMap(){
		return goodsTypeMap;
	};
	static{
		goodsTypeMap.put("watch", "watch");
		goodsTypeMap.put("shoes", "shoes");
	}
	
	private static List<String> goodsTypeList = new ArrayList<String>();
	public static List<String> getInstanceList(){
		return goodsTypeList;
	}
	static {
		goodsTypeList.add("watch");
		goodsTypeList.add("shoes");
	}
}
