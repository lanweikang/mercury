package com.boredou.mercury.repository.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户权限表
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-repository>
 * File Name:   <Permission.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-26 下午6:08:36
 */
public class Permission implements Serializable {

	private static final long serialVersionUID = -269353354012243869L;
	
	/**
	 * 权限id
	 */
	@Setter
	@Getter
	private Long permissionId;
	
	/**
	 * 权限代码
	 */
	@Setter
	@Getter
	private String code;
	
	/**
	 * 权限描述
	 */
	@Setter
	@Getter
	private String description;

}

