package com.boredou.mercury.repository.enums;

import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.PostMethod;


public enum Display {

	yes("是"),no("否");
	
	private String value;

	private Display(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public static void main(String[] args){
		String url = "http://www.amazon.com/s?ie=UTF8&page=1&rh=n%3A679337011%2Ck%3ADansko";
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod=new PostMethod(url);
		try {
//			postMethod.addParameter("ie", "UTF8");
//			postMethod.addParameter("page", "1");
//			postMethod.addParameter("rh", "n%3A679337011%2Ck%3ADansko");
			httpClient.executeMethod(postMethod);
			String responseMsg = postMethod.getResponseBodyAsString().trim();
			int recode = postMethod.getStatusCode();
			System.out.println(responseMsg+recode);
			
			 CookieSpec cookiespec = CookiePolicy.getDefaultSpec();  
		      Cookie[] cookies = httpClient.getState().getCookies();//cookiespec.match("*.", 80, "/" , false , httpClient.getState().getCookies());  
		      if (cookies.length == 0) {  
		         System.out.println( "None" );  
		      } else {  
		         for ( int i = 0; i < cookies.length; i++) {  
		            System.out.println(cookies[i].toString());  
		         }  
		      }
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
		}
	}
}
