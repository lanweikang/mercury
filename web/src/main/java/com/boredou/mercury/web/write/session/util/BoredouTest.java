package com.boredou.mercury.web.write.session.util;

import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

import com.boredou.mercury.web.util.Consts;

import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.client.SimpleHttpClient;
import cn.weili.util.http.client.SimplePoolHttpClient;
import cn.weili.util.http.param.RequestParams;
import cn.weili.util.http.result.ResponseResult;

public class BoredouTest {
	
	public static void main(String[] args) {
		String baiduUrl = "http://www.baidu.com";
		HttpClient hc = SimplePoolHttpClient.newHttpClient();
		ResponseResult result = hc.execute(RequestParams.custom().setUrl(baiduUrl)
				.addHeader(Consts.CHEOME_USER_AGENT)
				.addHeader(Consts.ACCEPT)
				.setReadTimeout( 90000L )
				.build()
				);
		String resp = result.getValue();
		if(hc instanceof SimplePoolHttpClient){
			SimplePoolHttpClient  simpleHttpClient = (SimplePoolHttpClient) hc;
			BasicCookieStore basicCookieStore =   simpleHttpClient.getCookieStore();
			List<Cookie> cookies = basicCookieStore.getCookies();
			System.out.println("--------------------");
			System.out.println();
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName()+" , "+cookie.getValue());
			}
		}
				
		
	}

}
