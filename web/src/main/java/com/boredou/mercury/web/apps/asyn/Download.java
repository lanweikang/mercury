package com.boredou.mercury.web.apps.asyn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;


public class Download {
	@Autowired
	private HttpServletResponse response;
	public void execute() throws IOException{
		System.out.println("lwk..."+new Date());
		response.addHeader("Cache-Control", "no-cache");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write("I'm lanweikang");
		out.flush();
		
	}
}
