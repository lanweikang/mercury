package com.boredou.mercury.web.apps.module.screen.download;

import static com.alibaba.citrus.util.ObjectUtil.defaultIfNull;
import static com.alibaba.citrus.util.StringEscapeUtil.escapeURL;
import static org.apache.commons.lang.StringUtils.trimToNull;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.turbine.dataresolver.Param;
public class ItemDownload {
	@Autowired
    private HttpServletResponse response;

    @Autowired
    private BufferedRequestContext brc;
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
}
