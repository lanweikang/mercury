package com.boredou.mercury.repository.dao;

import java.util.List;

import com.boredou.mercury.repository.base.SqlMapper;
import com.boredou.mercury.repository.entity.UserRole;

/**
 * 用户角色关联表DAO
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-repository>
 * File Name:   <UserRoleDao.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-4-2 下午12:59:27
 */
public interface UserRoleDao extends SqlMapper{

	/**
	 * 添加用户角色
	 * @param userRole
	 */
	void addUserRole(UserRole userRole);
	
	/**
	 * 查询用户角色关系表
	 * userRoleId存在，userId和roleId为空，返回一个结果。
	 * userRoleId为空，userId或roleId存在则返回结果集。
	 * @param userRole
	 * @return
	 */
	List<UserRole> getUserRoleByInfo(UserRole userRole);
	
	/**
	 * 删除用户角色关联
	 * @param userRole
	 */
	void delUserRole(UserRole userRole);
	
}

