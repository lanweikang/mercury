package com.boredou.mercury.repository.entity;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

import com.boredou.mercury.repository.base.BaseDO;
@Data
public class AmazonItemInfoDO extends BaseDO {
	
	private String selfAsin;
	private String parentAsin;
	private String belongAsin;
	private String material;
	private int size;
	private String imgUrl;
	private double price;
	private int stock;
	private String seller;
	private int lastHttpCode;
	private String goodsType;
	
	public static void main(String[] args){
		StringBuilder sb =  new StringBuilder();
//		sb.append("456");
		if(StringUtils.isBlank(sb.toString())){
			System.out.println("is null");
		}else{
			System.out.println("is not");
		}
		System.out.println(sb.toString()+"000");
		if(sb !=null){
			System.out.println("is not");
		}else{
			System.out.println("is null");
		}
		
	}

}
