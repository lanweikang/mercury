package com.boredou.mercury.web.apps.module.screen.ajax;

import static com.alibaba.citrus.util.ObjectUtil.defaultIfNull;
import static com.alibaba.citrus.util.StringEscapeUtil.escapeURL;
import static org.apache.commons.lang.StringUtils.trimToNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.boredou.mercury.web.download.AmazonDownload;

public class Download {
	@Autowired
    private HttpServletResponse response;
	@Resource(name = "AutoSwitchDownload")
	AmazonDownload amazonDownload;
    @Autowired
    private BufferedRequestContext brc;
	public void doDownload(@Param("filename") String filename,@Param("goodsType") String goodsType,@Param("sex") String sex) 
			throws IOException, InterruptedException{
		System.out.println("lwk..."+new Date());
		brc.setBuffering(false);
		filename = defaultIfNull(trimToNull(filename), "product") + System.currentTimeMillis() +".csv";
		filename = "\"" + escapeURL(filename) + "\"";
		response.setHeader("Content-disposition", "attachment; filename=" + filename);
		response.setContentType("csv/plain");

	        PrintWriter out = response.getWriter();

	        for (int i = 0; i < 10; i++) {
	            out.flush(); // 立即提示用户下载

	            for (int j = 0; j < 10; j++) {
	                out.print(i);
	                if(j==9) continue;
	                out.print(",");
	            }
	            
	            out.println();

	        }
		
	}
}
