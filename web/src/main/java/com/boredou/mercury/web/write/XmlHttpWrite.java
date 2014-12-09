package com.boredou.mercury.web.write;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.xstream.XStream;

public class XmlHttpWrite extends HttpWriter {

	private static final String CONTENT_TYPE = "text/xml";

	private String alias;

	public XmlHttpWrite(String alias, HttpServletResponse response, Object data) {
		super(response, data);
		this.alias = alias;
	}

	@Override
	protected String conver(Object data) {
		XStream xstream = null;
		try {
			xstream = new XStream();
			xstream.alias(alias, data.getClass());
			return xstream.toXML(data);
		} catch (Exception e) {
			logger.error("XmlHttpWrite conver error:",e);
			return StringUtils.EMPTY;
		} finally {
			if (xstream != null) {
				xstream = null;
			}
		}
	}

	@Override
	protected String getContentType() {
		return CONTENT_TYPE;
	}

}
