package com.boredou.mercury.web.download;

import java.util.Map;

import lombok.Setter;


public class AutoSwitchDownload implements AmazonDownload{
	@Setter
	private static Map<String, AmazonDownload> downloadMap;

	@Override
	public void doDownload(String goodsType) {
		AmazonDownload amazonDownload = downloadMap.get(goodsType);
		amazonDownload.doDownload(goodsType);
	}
	
}
