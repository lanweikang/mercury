package com.boredou.mercury.web.apps.module.screen.datafetch;

import static com.alibaba.citrus.util.ObjectUtil.defaultIfNull;
import static com.alibaba.citrus.util.StringEscapeUtil.escapeURL;
import static org.apache.commons.lang.StringUtils.trimToNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.boredou.mercury.repository.entity.ItemDO;
import com.boredou.mercury.server.service.ItemDownloadService;

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
		filename = "蓝伟康.csv";
		response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes("utf-8"),"iso-8859-1"));
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		
	        List<ItemDO> list = itemDownloadService.getItemList(category);
	        StringBuilder sb = new StringBuilder();
	       System.out.println("蓝伟康...");
	        sb.append("1,").append("2【レビューで送料無料】,").append("9,").append("蓝伟康");
//	        for (int i = 0; i < list.size(); i++) {
//	            sb.delete( 0, sb.length() );
//	            for (ItemDO itemDO : list) {
//	            	sb.append(itemDO.getTitle()+","+itemDO.getCategory()+","+itemDO.getGoodsUrl()+","+itemDO.getAttrs()).append("\n");
//				}
//	            System.out.println(sb);
//	            out.print(sb.toString());
//
//	        }
	        PrintWriter out = response.getWriter();
//	        ServletOutputStream out = response.getOutputStream();
	        out.print(sb.toString());
	        out.flush(); // 立即提示用户下载
		
	}
}



//application/vnd.ms-excel; charset=utf-8
