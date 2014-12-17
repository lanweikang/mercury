package com.boredou.mercury.web.write.session.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class PostTest {
	private static int count=0;
	public static void main(String[] args) {
		for (int i = 0; i < 200; i++) {
			main();
		}
	}
	public static void main() {
		HttpClient hc = new HttpClient();
		String yizhifuUrl = "https://b.bestpay.com.cn/bppf/login.do";
		String imgUrl = "https://b.bestpay.com.cn/bppf/vimage.do";
		HttpMethod method = new GetMethod(yizhifuUrl);
		int code = -1;
		Header[] headers =null;
		try {
			code = hc.executeMethod(method);
			System.out.println("code: "+code);
			headers = method.getResponseHeaders();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			method.releaseConnection();
		}
		System.out.println("-----------------------");
		for (Header header : headers) {
			System.out.println(header);
		}
		System.out.println("-----------------------");

		Cookie[] cookies = hc.getState().getCookies();
		String JSESSIONID ="";
		for (Cookie cookie : cookies) {
			if("JSESSIONID".equals(cookie.getName())){
				JSESSIONID = cookie.getValue();
			}
		}
		System.out.println(JSESSIONID);
		
		HttpMethod method1 = new GetMethod(imgUrl);
		try {
			method1.addRequestHeader("Host", "b.bestpay.com.cn");
			method1.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
			method1.addRequestHeader("Accept","image/png,image/*;q=0.8,*/*;q=0.5");
			method1.addRequestHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			method1.addRequestHeader("Accept-Encoding"," gzip, deflate");
			method1.addRequestHeader("Referer",yizhifuUrl);
			method1.addRequestHeader("Connection","keep-alive");
			method1.addRequestHeader("Cache-Control"," max-age=0");
			
			code = hc.executeMethod(method1);
			System.out.println("code: "+code);
			
			InputStream in = method1.getResponseBodyAsStream();
			
//			C:\Users\Administrator\Desktop\log
			File file =new File("C:/Users/Administrator/Desktop/yzm/"+ count++ +".jpeg");
			OutputStream os = null;
			int len = -1;
			os = new FileOutputStream(file);
			byte[] buffer = new byte[4*1024];
			while( (len=in.read(buffer)) !=-1){
				os.write(buffer, 0, len);
			}
			os.flush();
			
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			method.releaseConnection();
		}
		
		System.out.println("------cookies1-------");
		Cookie[] cookies1 = hc.getState().getCookies();
		String JSESSIONID1 ="";
		for (Cookie cookie : cookies1) {
			if("JSESSIONID".equals(cookie.getName())){
				JSESSIONID1 = cookie.getValue();
			}
		}
		System.out.println(JSESSIONID1);
		
	}

}
