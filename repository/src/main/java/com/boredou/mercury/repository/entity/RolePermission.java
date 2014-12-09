package com.boredou.mercury.repository.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色权限关联表
 * 
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-repository>
 * File Name:   <RolePermission.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-26 下午6:07:50
 */
public class RolePermission implements Serializable{
	
	private static final long serialVersionUID = 345349923961452985L;
	
	/**
	 * 主键ID
	 */
	@Setter
	@Getter
	private Long rolePermissionId;
	
	/**
	 * 权限ID
	 */
	@Setter
	@Getter
	private Long permissionId;
	
	/**
	 * 角色ID
	 */
	@Setter
	@Getter
	private Long roleId;
	
}
