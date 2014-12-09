package com.boredou.mercury.web.write;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

public class JsonHttpWrite extends HttpWriter {

	private static final String CONTENT_TYPE = "application/json";

	public JsonHttpWrite(HttpServletResponse response, Object data) {
		super(response, data);
	}

	@Override
	protected String conver(Object data) {
		return JSONArray.toJSONString(data);
	}

	@Override
	protected String getContentType() {
		return CONTENT_TYPE;
	}

}
