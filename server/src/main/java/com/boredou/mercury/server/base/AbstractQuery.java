package com.boredou.mercury.server.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractQuery implements InitializingBean {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	protected void init() {
	}
}
