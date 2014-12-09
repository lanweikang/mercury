package com.boredou.mercury.web.apps.module.screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.web.base.AbstractController;
import com.boredou.mercury.web.write.HttpWriter;
import com.boredou.mercury.web.write.JsonHttpWrite;

public class UserSearch extends AbstractController {

	/**
	 * 等同于execute
	 * 
	 * @param context
	 */
	
	public void doPerform(Context context) {
	}

	/**
	 * 搜索执行方法，使用JsonHttpWrite打印对象
	 * 
	 * @param context
	 */
	public void doSearch(Context context) {
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> list = new ArrayList<Object>();
		int i = 0;
		while (i++ < 5)
			list.add(JSONObject
					.parse("{\"id\":\"1217\",\"merchantorderid\":\"张三\",\"systemorderid\":326044800022,\"prepaidnumber\":326044800000,\"orderdate\":\"一年级四班\",\"ordervalue\":32604480070,\"status\":\"0\",\"createdate\":\"一年级四班\",\"userid\":\"一年级四班\"}"));
		map.put("rows", list);
		map.put("results", 5);
		HttpWriter writer = new JsonHttpWrite(response, map);
		System.out.println("doSearch:" + getParametersMap());
		writer.write();
	}
}
