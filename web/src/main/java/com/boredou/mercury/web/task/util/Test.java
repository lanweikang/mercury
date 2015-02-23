package com.boredou.mercury.web.task.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.methods.HttpGet;

import com.boredou.mercury.web.write.FileUtil;

public class Test {
	private static String searUrl = "http://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Dfashion-womens-shoes&field-keywords=Dansko";
	//匹配页面链接
	private static String expPageLink = "pagnLink.*href=\"(.*)\"";
	private static Pattern pattPageLink = Pattern.compile(expPageLink);

	//匹配总页面数量 
	/**
	 * >2</a></span>
	 * <span class="pagnRA"
	 */
	private static String expPageTotal = ">([\\d]+)(?:</a>)?</span>\\s*<span class=\"pagnRA\"";
	private static Pattern pattPageTotal = Pattern.compile(expPageTotal);
	public static void main(String[] args) throws HttpException, IOException {
		HttpMethod getMethod = new GetMethod(searUrl);
		getMethod.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.116 Safari/537.36");

		HttpClient hc = new HttpClient();
//		hc.getHttpConnectionManager().getParams().setConnectionTimeout(9000);
		hc.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		hc.getParams().setParameter("http.protocol.single-cookie-header",true);
		hc.getParams().setSoTimeout(120000);
		
		
		hc.executeMethod(getMethod);
		String rexp = getMethod.getResponseBodyAsString();
		
		String basePath = "C:\\Users\\Administrator\\Desktop\\logs\\";
		String filePath = basePath + 99999+".html";
		FileUtil.writeToFile(rexp, filePath, true);
		
		
		List<String> myPageList = new ArrayList<String>();
		int pageTotle=0;
		String pageLink="";
		Matcher mPageLink = pattPageLink.matcher(rexp);
		Matcher mPageTotle = pattPageTotal.matcher(rexp);
		//找到总页数
		while(mPageTotle.find()){
			pageTotle = Integer.parseInt(mPageTotle.group(1));
			break;
		}
		//找到某一个
		while(mPageLink.find()){
			pageLink = mPageLink.group(1);
			break;
		}
//		if(pageLink=="") return myPageList;
		for(int i=1;i<=pageTotle;i++){
//			String s = pageLink.replaceAll("ref=sr_pg_\\d*", "ref=sr_pg_"+i).replaceAll("page=\\d*", "page="+i);
//			pageLink
//			FileUtil.writeToFile("pageLink:123 -- ","C:/Users/Administrator/Desktop/df/test.txt" ,true);
			String s = pageLink.replaceAll("&amp;", "&").replaceAll("page=\\d*", "page="+i);
			String pageUrl = "http://www.amazon.com"+s;
			myPageList.add(pageUrl);
		}
		if(myPageList.size()==0)
			myPageList.add(searUrl) ;
		
		System.out.println("-------------------------------");
		for (String string : myPageList) {
			System.out.println(string);
		}
		System.out.println(getMethod.getQueryString());
		getMethod.releaseConnection();
		
	}
}
