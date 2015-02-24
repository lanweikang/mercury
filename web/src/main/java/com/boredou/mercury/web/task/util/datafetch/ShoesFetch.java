package com.boredou.mercury.web.task.util.datafetch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemDO;
import com.boredou.mercury.server.service.AmazonItemService;
import com.boredou.mercury.web.task.util.datafetch.expression.ShoesHelp;
import com.boredou.mercury.web.util.AmazonSessionUtil;
import com.boredou.mercury.web.util.Consts;
import com.boredou.mercury.web.write.FileUtil;

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
	Log log = LogFactory.getLog(getClass());
	private static int kkk=0;
	@Override
	public void doFetch(AmazonCategoryDO amazonCategoryDO, String goodsUrl) {
		// TODO Auto-generated method stub
		System.out.println("lwk......"+"  ShoesFetch");
		System.out.println(goodsUrl);
//		String asin = goodsUrl.substring(goodsUrl.length()-10,goodsUrl.length());
		AmazonItemDO amazonItemDOResult = new AmazonItemDO();
		int code = -1;
		try{
			ResponseResult result = hc.execute(RequestParams.custom().setUrl(goodsUrl)
					.addHeader(Consts.CHEOME_USER_AGENT)
					.setReadTimeout( 50000L ).build());
			code = result.getResultCode();
			String content =result.getValue();
			String parentAsin = ShoesHelp.getParentAsin(content);
			
			String basePath = "C:\\Users\\Administrator\\Desktop\\logs\\";
			String filePath = basePath + parentAsin+".html";
			FileUtil.writeToFile(content, filePath, true);
			
			
			String itemName = ShoesHelp.getItemName(content);
			amazonItemDOResult.setItemUrl(goodsUrl);
			amazonItemDOResult.setLastHttpCode(code);
			amazonItemDOResult.setBelongtoCategoryId(amazonCategoryDO.getId());
			amazonItemDOResult.setAsin(parentAsin);
			amazonItemDOResult.setName(itemName);

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("asin", parentAsin);
			jsonObject.put("itemName", ShoesHelp.getItemName(content));

			//			List<String> materialList = ShoesHelp.getMaterials(content);
			jsonObject.put("materials", ShoesHelp.getMaterials(content));

			String itemJson = ShoesHelp.getItemJson(content);

			JSONObject ajaxjson = JSONObject.parseObject(itemJson);
			//			ShoesHelp

			//						String sid=AmazonSessionUtil.getSid(hc);

			if(StringUtils.isEmpty(itemJson)) return ;

			JSONObject jsonObject0 = JSONObject.parseObject(itemJson);
			JSONObject asinJson = JSONObject.parseObject(itemJson).getJSONObject("asin_variation_values");
			Map<String,JSONObject> asinVariationValuesMap = new HashMap<String, JSONObject>();
			List<String> asinList = new ArrayList<String>();
			for(String key:asinJson.keySet()){
				asinVariationValuesMap.put(key, (JSONObject)asinJson.get(key));
				asinList.add(key);
			}

			List<String> colorList = (List<String>) jsonObject0.getJSONObject("variation_values").get("color_name");
			List<String> sizeList = (List<String>) jsonObject0.getJSONObject("variation_values").get("size_name");

			System.out.println("ajax 查询次数:"+asinList.size()+" ,goodsUrl: "+goodsUrl);
			int i =0;
			Map<String,String> asinAndAjaxUrl = getColorAndSizeUrl(jsonObject0,asinVariationValuesMap);
			List<JSONObject> ajaxList = new ArrayList<JSONObject>();

			for (String asinAjax : asinList) {
				JSONObject perJson = new JSONObject();
				try{
					perJson.put("asin", asinAjax);
					ResponseResult ajaxResult = hc.execute(RequestParams.custom().setUrl(asinAndAjaxUrl.get(asinAjax))
							.addHeader(Consts.CHEOME_USER_AGENT)
							.setReadTimeout(90000L)
							.build());
					perJson.put("code", ajaxResult.getResultCode() );
					String ajaxResponse = ajaxResult.getValue();

					String basePath0 = "C:\\Users\\Administrator\\Desktop\\logs\\ajax2\\";
					String filePath0 = basePath0 + asinAjax+".html";
					FileUtil.writeToFile(ajaxResponse, filePath0, true);


					int colorNum = asinVariationValuesMap.get(asinAjax).getIntValue("color_name");
					int sizeNum = asinVariationValuesMap.get(asinAjax).getIntValue("size_name");

					colorList.get(colorNum);
					sizeList.get(sizeNum);

					perJson.put("color_name", colorList.get(colorNum));
					perJson.put("size_name", sizeList.get(sizeNum));

					String stocklll = ShoesHelp.getItemStock(ajaxResponse);

					perJson.put("stock", ShoesHelp.getItemStock(ajaxResponse) );					

					String pricelll = ShoesHelp.getItemPrice(ajaxResponse);

					i++;

					perJson.put("price", ShoesHelp.getItemPrice(ajaxResponse) );
					
					System.out.println(parentAsin+" --ajax请求"+stocklll+" , "+pricelll);
					
				}catch(Exception e1){
					System.out.println("ajax请求发生异常");
					perJson.put("error", "time out");
					e1.getStackTrace();
				}finally{
					ajaxList.add(perJson);
				}
			}
			jsonObject.put("childData", ajaxList);

			System.out.println("ajax 成功次数:" + i +" ,goodsUrl: "+goodsUrl);

			amazonItemDOResult.setWholeContent(jsonObject.toString());
			
			
			AmazonItemDO amazonItemDOParam = new AmazonItemDO();
			amazonItemDOParam.setAsin(parentAsin);
			if(amazonItemService.getAmazonItemDOByAsinLimit1(amazonItemDOParam)==null){
				amazonItemService.addAmazonItemDO(amazonItemDOResult);
			}else{
				amazonItemService.updateAmazonItemDOByAsin(amazonItemDOResult);
			}

		}catch(Exception e){
			e.getStackTrace();
//			amazonItemDOResult.setLastHttpCode(ResponseResult.READ_TIMEOUT);
//			amazonItemDOResult.setAsin(parentAsin);
		}finally{
//			AmazonItemDO amazonItemDOParam = new AmazonItemDO();
//			amazonItemDOParam.setAsin(parentAsin);
//			if(amazonItemService.getAmazonItemDOByAsinLimit1(amazonItemDOParam)==null){
//				amazonItemService.addAmazonItemDO(amazonItemDOResult);
//			}else{
//				amazonItemService.updateAmazonItemDOByAsin(amazonItemDOResult);
//			}
			System.out.println();
			System.out.println();
		}
	}

	//sid
	private static String expItemSid ="ue_sid='(.*?)'";
	private static Pattern pattItemSid = Pattern.compile(expItemSid); 

	private String getCompleteAjaxUrl(JSONObject jsonObject , String asin){
		return "http://www.amazon.com"+jsonObject.getJSONObject("contextMetaData").getJSONObject("full").get("AJAXUrl") +
				asin +"&isFlushing=2&id="+asin+"&mType=full&dpEnvironment=softlines"
				;
	}

	private Map<String,String> getColorAndSizeUrl( JSONObject jsonObject,Map<String,JSONObject> asinVariationValuesMap){
		Map<String,String> asinAndUrl = new HashMap<String, String>();
		for(String key:asinVariationValuesMap.keySet()){
			asinAndUrl.put(key, getCompleteAjaxUrl(jsonObject , key) );
		}
		return asinAndUrl;
	} 
}
