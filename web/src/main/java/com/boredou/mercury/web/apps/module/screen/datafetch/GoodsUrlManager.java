package com.boredou.mercury.web.apps.module.screen.datafetch;

import static com.alibaba.citrus.util.ObjectUtil.defaultIfNull;
import static org.apache.commons.lang.StringUtils.trimToNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.ItemDO;
import com.boredou.mercury.repository.entity.download.WatchDO;
import com.boredou.mercury.server.service.ItemDownloadService;
import com.boredou.mercury.web.download.test.ExportExcel;

public class GoodsUrlManager {
	private static String ss = "【レビューで送料無料】【靴 シューズ 】【ファッション ブランド】【靴 シューズ サンダル】【革 レザー】<BR>";
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private BufferedRequestContext brc;
	@Autowired
	private ItemDownloadService itemDownloadService;
	private static final String SHEET_ACCOUNT = "amazon_item";
	private static final int DEFAULT_MAX_SIZ = 100;
	public void doPerform(Context context) {
		context.put("cList","cList");
	}

	public void doDownload(@Param("category") String category) 
			throws IOException, InterruptedException{
		System.out.println("lwk..."+new Date());
		response.setCharacterEncoding("utf-8");

		//		System.out.println("out-- "+out);
		System.out.println(category); 
		String filename = defaultIfNull(trimToNull(category), "product") + System.currentTimeMillis() +".csv";
		//		filename = "\"" + escapeURL(filename) + "\"";
		filename = category+"-"+System.currentTimeMillis()+".xls";
		response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes("utf-8"),"iso-8859-1"));
		response.setContentType("application/vnd.ms-excel; charset=utf-8");

		List<ItemDO> list = itemDownloadService.getItemList(category);
		StringBuilder sb = new StringBuilder();
		System.out.println("蓝伟康..."+list.size());

		List<ItemDO> need = list.subList(0, 5);
		List<WatchDO> dataset = new ArrayList<WatchDO>();
		for (ItemDO itemDO : need) {
			JSONObject jo = JSONObject.parseObject(itemDO.getAttrs());
			System.out.println(itemDO.getAttrs());
			System.out.println("------");
			String modelNum = jo.getString("ModelNum");
//			material
			JSONObject material = JSONObject.parseObject(jo.getString("material"));
			String bandColor = material.getString("Band Color");
			String asin = jo.getString("asin");
			
			WatchDO w = new WatchDO(modelNum, asin, bandColor);
			dataset.add(w);
		}
		
		ServletOutputStream out = response.getOutputStream();
		ExportExcel<WatchDO> ex = new ExportExcel<WatchDO>();
		
		ex.exportExcel(null, dataset, out);
		out.flush(); // 立即提示用户下载
		
		
		
		

//		ServletOutputStream out = response.getOutputStream();
//		ExportExcel<Student> ex = new ExportExcel<Student>();
//		String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
////		List<Student> dataset = new ArrayList<Student>();
////		dataset.add(new Student(10000001, "张三", 20, true, new Date()));
////		dataset.add(new Student(20000002, "【レビューで送料無料】", 24, false, new Date()));
////		dataset.add(new Student(30000003, "王五", 22, true, new Date()));
//		ex.exportExcel(headers, dataset, out);
//		out.flush(); // 立即提示用户下载
	}
}



//application/vnd.ms-excel; charset=utf-8
