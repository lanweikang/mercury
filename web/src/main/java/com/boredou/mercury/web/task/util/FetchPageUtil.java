package com.boredou.mercury.web.task.util;

import java.util.ArrayList;
import java.util.List;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;

import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.client.SimplePoolHttpClient;
import cn.weili.util.http.param.RequestParams;
import cn.weili.util.http.result.ResponseResult;

public class FetchPageUtil {
	@Setter
	private static HttpClient hc;
//	static{
//		if(hc==null)
//			hc=SimplePoolHttpClient.newHttpClient();
//	}
	private final static Logger logger = LoggerFactory.getLogger(FetchPageUtil.class);

	//匹配页面链接
	private static String expPageLink = "pagnLink.*href=\"(.*)\"";
	private static Pattern pattPageLink = Pattern.compile(expPageLink);

	//匹配总页面数量 
	/**
	 * >2</a></span>
	 * <span class="pagnRA"
	 */
	private static String expPageTotal = ">([\\d]+)[</a>]*</span>\\s*<span class=\"pagnRA\"";
	private static Pattern pattPageTotal = Pattern.compile(expPageTotal);

	public static List<String> getPageList(String goodsUrl){
		System.out.println("httpclient:"+hc);

		List<String> myPageList = new ArrayList<String>();
		String searchUrl = goodsUrl;

		ResponseResult result = hc.execute(RequestParams.custom().setUrl(searchUrl).build());

		if(result==null) return null;

		String rexp = result.getValue();

		int pageTotle=0;
		String pageLink="";
		Matcher mPageLink = pattPageLink.matcher(rexp);
		Matcher mPageTotle = pattPageTotal.matcher(rexp);
		//找到总页数
		while(mPageTotle.find()){
			pageTotle = Integer.parseInt(mPageTotle.group(1));

			logger.debug("共找到："+pageTotle+"页码");
			break;
		}
		//找到某一个
		while(mPageLink.find()){
			pageLink = mPageLink.group(1);
			logger.debug("pageLink:"+pageLink);
			break;
		}
		if(pageLink=="") return null;
		for(int i=1;i<=pageTotle;i++){
			String s = pageLink.replaceAll("ref=sr_pg_\\d*", "ref=sr_pg_"+i).replaceAll("page=\\d*", "page="+i);
			String pageUrl = "http://www.amazon.com"+s;
			myPageList.add(pageUrl);
		}
		return myPageList;
	}
}
