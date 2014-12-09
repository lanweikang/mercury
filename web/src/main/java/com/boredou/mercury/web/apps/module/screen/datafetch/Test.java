package com.boredou.mercury.web.apps.module.screen.datafetch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.boredou.mercury.repository.entity.ItemDO;
import com.boredou.mercury.server.service.ItemDownloadService;

public class Test {
	@Autowired
	private ItemDownloadService itemDownloadService;
	
	public void doPerform(Context context) {
		System.out.println("start...");
		String categoryname = "男士手表";
		List<ItemDO> list = itemDownloadService.getItemList(categoryname);
		for (ItemDO itemDO : list) {
			System.out.println(itemDO);
		}
		System.out.println("length:    "+list.size());
		context.put("test","this is a test !");
		System.out.println("end...");
	}
}
