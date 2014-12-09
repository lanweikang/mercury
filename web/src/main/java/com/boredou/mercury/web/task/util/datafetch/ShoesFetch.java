package com.boredou.mercury.web.task.util.datafetch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemDO;
import com.boredou.mercury.server.service.AmazonItemService;
import com.boredou.mercury.web.util.AmazonSessionUtil;
import com.boredou.mercury.web.util.Consts;

import cn.weili.util.StringUtil;
import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.param.RequestParams;
import cn.weili.util.http.result.ResponseResult;
import lombok.Setter;

public class ShoesFetch implements FetchMethod {
	@Setter
	private HttpClient hc;
	@Autowired
	AmazonItemService amazonItemService;
	@Override
	public void fetch(AmazonCategoryDO amazonCategoryDO, String goodsUrl) {
		// TODO Auto-generated method stub
		System.out.println("lwk......"+"  ShoesFetch");
		System.out.println(goodsUrl);
		String asin = goodsUrl.substring(goodsUrl.length()-10,goodsUrl.length());
		AmazonItemDO amazonItemDOResult = new AmazonItemDO();
		int code = -1;
		try{
			ResponseResult result = hc.execute(RequestParams.custom().setUrl(goodsUrl).addHeader(Consts.CHEOME_USER_AGENT).build());
//			if(result.getRespHeaders().length == 0) return;
			code = result.getResultCode();
			String content = StringUtil.trimNotWithNull(result.getValue());
			amazonItemDOResult.setItemUrl(goodsUrl);
			amazonItemDOResult.setLastHttpCode(code);
			amazonItemDOResult.setBelongtoCategoryId(amazonCategoryDO.getId());
			amazonItemDOResult.setAsin(asin);
			
			StringBuilder WholeContent = new StringBuilder();
			
			Matcher mItemName = pattItemName.matcher(content);
			while(mItemName.find()){
				String itemName = mItemName.group(1);
				itemName = StringUtil.trimNotWithNull(itemName);
				amazonItemDOResult.setName(itemName);
				System.out.println("itemName:"+itemName);
				break;
			}
			
			String itemJson ="";
			Matcher mItemJson = pattItemJson.matcher(content);
			while(mItemJson.find()){
				itemJson = mItemJson.group(1);
				itemJson = StringUtil.trimNotWithNull(itemJson);
				System.out.println("itemJson:"+itemJson);
				break;
			}
			
			String sid=AmazonSessionUtil.getSid(hc);
			System.out.println(sid);
			
			WholeContent.append(sid);
			
			Matcher mItemSid = pattItemSid.matcher(content);
			while(mItemSid.find()){
				String itemSid = mItemSid.group(1);
				itemSid = StringUtil.trimNotWithNull(itemSid);
				System.out.println("itemSid:"+itemSid);
				WholeContent.append(" ,itemSid: "+itemSid);
				break;
			}
			
			if(StringUtils.isNotBlank(WholeContent)) amazonItemDOResult.setWholeContent(WholeContent.toString());
			
		}catch(Exception e){
			e.getStackTrace();
			amazonItemDOResult.setLastHttpCode(ResponseResult.READ_TIMEOUT);
			amazonItemDOResult.setAsin(asin);
		}finally{
			AmazonItemDO amazonItemDOParam = new AmazonItemDO();
			amazonItemDOParam.setAsin(asin);
			if(amazonItemService.getAmazonItemDOByAsinLimit1(amazonItemDOParam)==null){
				amazonItemService.addAmazonItemDO(amazonItemDOResult);
			}else{
				amazonItemService.updateAmazonItemDOByAsin(amazonItemDOResult);
			}
			System.out.println();
			System.out.println();
		}




	}


	//匹配item的name
	private static String expItemName ="<span id=\"productTitle\" class=\"a-size-large\">(.*?)</span>";
	private static Pattern pattItemName = Pattern.compile(expItemName);

	//匹配脚本中的json字符串
	private static String expItemJson ="<script type=\"a-state\" data-a-state=\"\\{&quot;key&quot;:&quot;twisterData&quot;\\}\">(.*?)</script>";
	private static Pattern pattItemJson = Pattern.compile(expItemJson);

	//sid
	private static String expItemSid ="ue_sid='(.*?)'";
	private static Pattern pattItemSid = Pattern.compile(expItemSid);

}
