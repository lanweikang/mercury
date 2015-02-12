package com.boredou.mercury.web.apps.module.screen.download;

import static com.alibaba.citrus.util.ObjectUtil.defaultIfNull;
import static com.alibaba.citrus.util.StringEscapeUtil.escapeURL;
import static org.apache.commons.lang.StringUtils.trimToNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.ItemDO;
import com.boredou.mercury.repository.entity.download.WatchDO;
import com.boredou.mercury.server.service.AmazonCategoryService;
import com.boredou.mercury.server.service.ItemDownloadService;
import com.boredou.mercury.web.download.test.ExportExcel;
public class ItemDownload {
	private static String ss = "【レビューで送料無料】【靴 シューズ 】【ファッション ブランド】【靴 シューズ サンダル】【革 レザー】<BR>";
	@Autowired
    private HttpServletResponse response;
	@Autowired
	private BufferedRequestContext brc;
	@Autowired
	private ItemDownloadService itemDownloadService;
	@Autowired
	AmazonCategoryService amazonCategoryService;
	
	private static final String SHEET_ACCOUNT = "amazon_item";
	private static final int DEFAULT_MAX_SIZ = 100;
	public void doPerform(@Param("filename") String filename) throws Exception {
		System.out.println("cinv...."+new Date());
        // 为了增强用户体验，关闭buffering，让下载立即开始，而不是等待整个文件生成完才通知用户下载。
//        brc.setBuffering(false);

        // 设置headers，下载文件名必须避免非us-ascii字符，因为各浏览器的兼容程度不同。
//        filename = defaultIfNull(trimToNull(filename), "product") + System.currentTimeMillis() +".txt";
        filename = defaultIfNull(trimToNull(filename), "product") + System.currentTimeMillis() + ".csv";
        filename = "\"" + escapeURL(filename) + "\"";

        response.setHeader("Content-disposition", "attachment; filename=" + filename);

        // 只要设置了正确的content type，你就可以让用户下载任何文本或二进制的内容：
        // HTML、JSON、JavaScript、JPG、PDF、EXCEL等。
        response.setContentType("application/csv");

        PrintWriter out = response.getWriter();
//        ServletOutputStream out =  response.getOutputStream();
        
        StringBuilder sb = new StringBuilder();
        sb.append("lan,").append("wei,").append("kang").append("\n");
        out.println();
        sb.append("1,").append("3,").append("2").append("\n");
        sb.append("2,").append("3,").append("2").append("\n");
        
        out.print(sb.toString());
        out.flush();

//        for (int i = 0; i < 100; i++) {
////            out.flush(); // 立即提示用户下载
//
//            for (int j = 0; j < 10; j++) {
//                out.print(i);
//            }
//
//            out.println();
//
////            Thread.sleep(100); // 故意延迟，以减缓下载速度
//        }
	}
	
	public void doDown(){
		System.out.println("doDown...");
	}
	
	public void doDownload(@Param("categoryId") String categoryId,HttpServletResponse response) 
			throws IOException, InterruptedException{
		System.out.println("lwk..."+new Date());
		AmazonCategoryDO cate = amazonCategoryService.loadById(Long.valueOf(categoryId));
		response.setCharacterEncoding("utf-8");

		//		System.out.println("out-- "+out);
		System.out.println(categoryId); 
		String filename = defaultIfNull(trimToNull(categoryId), "product") + System.currentTimeMillis() +".csv";
		//		filename = "\"" + escapeURL(filename) + "\"";
		filename = categoryId+"-"+System.currentTimeMillis()+".xls";
		response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes("utf-8"),"iso-8859-1"));
		response.setContentType("application/vnd.ms-excel; charset=utf-8");

		List<ItemDO> list = itemDownloadService.getItemListById(Integer.valueOf(categoryId));
		StringBuilder sb = new StringBuilder();
		System.out.println("..."+list.size());

		List<ItemDO> need = list.subList(0, 5);
		if(cate.getGoodsType().equals("watch")){
			watchDownload(need, response);
		}else if(cate.getGoodsType().equals("shoes")){
			shoesDownload();
		}
	}
	
	private void watchDownload(List<ItemDO> need,HttpServletResponse response) throws IOException{
		List<Object> dataset = new ArrayList<Object>();
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
		ExportExcel<Object> ex = new ExportExcel<Object>();
		
		ex.exportExcel(null, dataset, out);
		out.flush(); // 立即提示用户下载
	}
	
	private void shoesDownload(){
		
	}
	
}
