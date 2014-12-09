package com.boredou.mercury.web.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.service.requestcontext.parser.ParserRequestContext;
import com.alibaba.citrus.service.requestcontext.util.RequestContextUtil;

public abstract class AbstractController {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	protected HttpServletRequest request;

	public static final String SYS_PRE = "_";

	protected Map<String, String> getParametersMap() {
		ParserRequestContext parser = RequestContextUtil.findRequestContext(request, ParserRequestContext.class);
		return getParametersMap(parser, SYS_PRE);
	}

	protected static Map<String, String> getParametersMap(ParserRequestContext parser, String... prefixFilters) {
		ParameterParser params = parser.getParameters();
		Map<String, String> result = new HashMap<String, String>();
		for (String key : params.keySet()) {
			key = StringUtils.trimToEmpty(key);
			if (!StringUtils.startsWithAny(key, prefixFilters)) {
				result.put(key, StringUtils.trimToEmpty(params.getString(key)));
			}
		}
		return result;
	}
}
