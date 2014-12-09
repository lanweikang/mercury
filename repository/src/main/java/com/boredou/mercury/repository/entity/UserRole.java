package com.boredou.mercury.repository.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色关联表
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-repository>
 * File Name:   <UserRole.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-4-2 下午12:52:11
 */
public class UserRole implements Serializable{

	private static final long serialVersionUID = -4529779408751009585L;

	/**
	 * 主键ID
	 */
	@Setter
	@Getter
	private Long userRoleId;
	
	/**
	 * 用户ID
	 */
	@Setter
	@Getter
	private Long userId;
	
	/**
	 * 角色ID
	 */
	@Setter
	@Getter
	private Long roleId;
}

