package com.boredou.mercury.web.task.util.datafetch;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.weili.util.StringUtil;
import cn.weili.util.http.client.HttpClient;
import cn.weili.util.http.client.SimpleHttpClient;
import cn.weili.util.http.param.RequestParams;
import cn.weili.util.http.result.ResponseResult;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.AmazonCategoryDO;
import com.boredou.mercury.repository.entity.AmazonItemDO;
import com.boredou.mercury.server.service.AmazonItemService;
import com.boredou.mercury.server.service.amazon.fileUtil;
import com.boredou.mercury.web.task.util.datafetch.expression.WatchHelp;
import com.boredou.mercury.web.util.Consts;

public class WatchFetch implements FetchMethod {
	@Resource(name = "poolHttpClient")
	private HttpClient hc;

	@Autowired
	AmazonItemService amazonItemService;

	private static int ijk=0;
	@Override
	public void doFetch(AmazonCategoryDO amazonCategoryDO, String goodsUrl) {

		System.out.println("lwk......"+"  WatchFetch" + "  -----------------  "+goodsUrl);

		String asin = goodsUrl.substring(goodsUrl.length()-10,goodsUrl.length());
		AmazonItemDO amazonItemDOResult = new AmazonItemDO();
		int code = -1 ;
		try{
			((SimpleHttpClient )hc).setConnectTimeout(90000);
			((SimpleHttpClient )hc).setReadTimeout(90000);
			((SimpleHttpClient )hc).setSoTimeout(90000);
			ResponseResult result = hc.execute(RequestParams.custom().setUrl(goodsUrl)
					.addHeader(Consts.CHEOME_USER_AGENT)
					.addHeader(Consts.ACCEPT)
					.setReadTimeout( 90000L )
					.build());
			code = result.getResultCode();
			String content = StringUtil.trimNotWithNull(result.getValue());
			amazonItemDOResult.setItemUrl(goodsUrl);
			amazonItemDOResult.setLastHttpCode(code);
			amazonItemDOResult.setBelongtoCategoryId(amazonCategoryDO.getId());
			amazonItemDOResult.setAsin(asin);


			//			if(ijk==0){
			//				System.out.println("lanweikang");
			//				fileUtil.writeToFile(content, "C:/Users/Administrator/Desktop/sk/log/log.txt");
			//			}

			//			pattItemName
			String itemName = WatchHelp.getItemName(content);
			amazonItemDOResult.setName(itemName);

			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("asin", asin );
			
			jsonObject.put("ModelNum", WatchHelp.getModelNum(content));

			jsonObject.put("stock", WatchHelp.getStock(content));

			jsonObject.put("price", WatchHelp.getPrice(content));

			jsonObject.put("seller", WatchHelp.getSeller(content));

			//材料
			JSONObject materialJsonObject = new JSONObject();
			materialJsonObject.put("Dial window material type", WatchHelp.getDialWindowType(content));
			materialJsonObject.put("Case Material", WatchHelp.getCaseMaterial(content));
			materialJsonObject.put("Band Material", WatchHelp.getBandMaterial(content));
			materialJsonObject.put("Band Color", WatchHelp.getBandColor(content));


			jsonObject.put("material", materialJsonObject);


			List<String> imgUrlList = WatchHelp.getImgUrls(content);

			jsonObject.put("imgUrls", imgUrlList);
			
			jsonObject.put("BrandSellerorCollectionName", WatchHelp.getBrandSellerorCollectionName(content));

			amazonItemDOResult.setWholeContent(jsonObject.toJSONString());


		}catch(Exception e){
			amazonItemDOResult.setLastHttpCode(ResponseResult.READ_TIMEOUT);
			amazonItemDOResult.setAsin(asin);
			e.getStackTrace();
		}finally{
			AmazonItemDO amazonItemDOParam = new AmazonItemDO();
			amazonItemDOParam.setAsin(asin);
			if(amazonItemService.getAmazonItemDOByAsinLimit1(amazonItemDOParam)==null){
				amazonItemService.addAmazonItemDO(amazonItemDOResult);
			}else{
				amazonItemService.updateAmazonItemDOByAsin(amazonItemDOResult);
			}
			System.out.println("lwk....code: "+code+"   "+goodsUrl+"....fetch!");
		}
		ijk++;
	}

}
