package com.boredou.mercury.web.write;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;

public class DefaultHttpWrite extends HttpWriter {

	private static final String CONTENT_TYPE = "text/html";

	public DefaultHttpWrite(HttpServletResponse response, Object data) {
		super(response, data);
	}

	@Override
	protected String conver(Object data) {
		return ObjectUtils.toString(data);
	}

	@Override
	protected String getContentType() {
		return CONTENT_TYPE;
	}

}
