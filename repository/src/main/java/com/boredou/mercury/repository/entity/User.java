package com.boredou.mercury.repository.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体
 * 
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-repository>
 * File Name:   <User.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-20 下午5:23:18
 */
public class User implements Serializable {

	private static final long serialVersionUID = -631803613265471136L;

	@Setter
	@Getter
	private Long userId;
	
	@Setter
	@Getter
	private String userName;
	
	@Setter
	@Getter
	private String password;
	
	@Setter
	@Getter
	private Date createDate;
	
	@Setter
	@Getter
	private int status;
	
	
	@Setter
	@Getter
	private Role role;
	
	/**
	 * 权限列表
	 */
	@Setter
	@Getter
	private List<Role> roles;
	
	@Setter
	@Getter
	private String startDate;
	
	@Setter
	@Getter
	private String endDate;

}
