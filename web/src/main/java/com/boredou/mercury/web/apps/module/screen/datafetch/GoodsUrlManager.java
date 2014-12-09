package com.boredou.mercury.web.apps.module.screen.datafetch;

import static com.alibaba.citrus.util.ObjectUtil.defaultIfNull;
import static com.alibaba.citrus.util.StringEscapeUtil.escapeURL;
import static org.apache.commons.lang.StringUtils.trimToNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.boredou.mercury.repository.entity.ItemDO;
import com.boredou.mercury.server.service.ItemDownloadService;

public class GoodsUrlManager {
	@Autowired
    private HttpServletResponse response;
    @Autowired
    private BufferedRequestContext brc;
    @Autowired
	private ItemDownloadService itemDownloadService;
    
	public void doPerform(Context context) {
		   context.put("cList","cList");
	}
	
	public void doDownload(@Param("category") String category) 
			throws IOException, InterruptedException{
		System.out.println("lwk..."+new Date());
		System.out.println(category);
		brc.setBuffering(false);
		String filename = defaultIfNull(trimToNull(category), "product") + System.currentTimeMillis() +".csv";
		filename = "\"" + escapeURL(filename) + "\"";
		response.setHeader("Content-disposition", "attachment; filename=" + filename);
		response.setContentType("csv/plain");

	        PrintWriter out = response.getWriter();
	        List<ItemDO> list = itemDownloadService.getItemList(category);
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < list.size(); i++) {
	            out.flush(); // 立即提示用户下载
	            sb.delete( 0, sb.length() );
	            for (ItemDO itemDO : list) {
	            	sb.append(itemDO.getTitle()+","+itemDO.getCategory()+","+itemDO.getGoodsUrl()+","+itemDO.getAttrs());
				}
	            System.out.println(sb);
	            out.println(sb);

	        }
		
	}
}
