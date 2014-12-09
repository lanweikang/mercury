/****************************************************************
文件名称:Role.java                                                
项目名称: mercury-repository                                              
模块名称:                                                          
功能说明:                                                          
系统版本: 1.0                                                   
开发人员: 王树辉                                                                                                                                                  
开发时间: 2014-3-21 下午2:25:48                                     
相关文档:                                                          
*****************************************************************/
package com.boredou.mercury.repository.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色
 * 
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-repository>
 * File Name:   <Role.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-21 下午2:26:25
 */
public class Role implements Serializable{

	private static final long serialVersionUID = -1823431098959263463L;
	
	/**
	 * 主键ID
	 */
	@Setter
	@Getter
	private Long roleId;
	
	/**
	 * 创建角色用户ID
	 */
	@Setter
	@Getter
	private Long createUserId;
	
	/**
	 * 角色名称
	 */
	@Setter
	@Getter
	private String roleName;
	
	/**
	 * 角色代码
	 */
	@Setter
	@Getter
	private String roleCode;
	
	/**
	 * 创建时间
	 */
	@Setter
	@Getter
	private Date createDate;
	
	@Setter
	@Getter
	private Permission permission;
	
	/**
	 * 权限列表
	 */
	@Setter
	@Getter
	private List<Permission> permissions;

}

