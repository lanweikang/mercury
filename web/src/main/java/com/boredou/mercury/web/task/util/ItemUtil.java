package com.boredou.mercury.web.task.util;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

import cn.weili.util.http.client.HttpClient;

public class ItemUtil {
	@Setter
	private static HttpClient hc;
	
}
