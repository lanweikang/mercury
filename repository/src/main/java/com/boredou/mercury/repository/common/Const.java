package com.boredou.mercury.repository.common;

import lombok.Setter;
import cn.weili.util.spring.SpringContextUtil;

public class Const {

	@Setter
	private String host;

	public static String getHost() {
		return SpringContextUtil.getBean(Const.class).host;
	}
}
