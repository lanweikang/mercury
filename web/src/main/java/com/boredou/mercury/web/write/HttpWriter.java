package com.boredou.mercury.web.write;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HttpWriter {

	private HttpServletResponse resp;

	private Object data;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public HttpWriter(HttpServletResponse response, Object data) {
		this.resp = response;
		this.data = data;
	}


	public void write() {
		PrintWriter out = null;
		resp.setContentType(getContentType());
		resp.setHeader("Cache-Control", "no-cache");
		try {
			out = resp.getWriter();
			out.write(conver(data).toCharArray());
			out.flush();
		} catch (IOException e) {
			logger.error("HttpWriter error:", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	protected abstract String conver(Object data);

	protected abstract String getContentType();

}
