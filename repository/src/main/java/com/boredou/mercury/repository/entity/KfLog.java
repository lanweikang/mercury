package com.boredou.mercury.repository.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class KfLog implements Serializable{

	
	private static final long serialVersionUID = 5112431833291877034L;
	
	
	/**
	 * 添加日志ID 
	 */
	@Setter
	@Getter
	private Long kfLogId;
	
	/**
	 * 添加记录用户ID (当前登陆用户)
	 */
	@Setter
	@Getter
	private Long userId;
	
	
	/**
	 * 添加记录用户名
	 */
	@Setter
	@Getter
	private String userName;
	
	
	/**
	 * 记录生成时间 (系统生成)
	 */
	@Setter
	@Getter
	private Date createDate;
	
	
	/**	
	 * (0:白班,1:夜班)
	 */
	@Setter
	@Getter
	private String workStatus;
	
	
	/**	
	 * 备注
	 */
	@Setter
	@Getter
	private String remarks;
	
	/**	
	 * 逻辑删除字段
	 */
	@Setter
	@Getter
	private int delStatus;


}
