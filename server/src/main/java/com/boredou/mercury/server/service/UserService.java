/****************************************************************
文件名称: UserService.java                                                
项目名称: order_food                                              
模块名称: 用户服务层                                                         
功能说明: 用户服务层                                                         
系统版本: 1.0                                                   
开发人员: 王树辉                                                                                                                                                  
开发时间: 2014年3月11日 上午11:28:00                                     
相关文档:                                                          
*****************************************************************/
package com.boredou.mercury.server.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.boredou.mercury.repository.entity.Role;
import com.boredou.mercury.repository.entity.User;
import com.boredou.mercury.repository.entity.UserRole;
import com.boredou.mercury.repository.util.Pagination;

/**
 * User 服务层接口
 * 
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <order_food>
 * File Name:   <UserService.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-14 下午4:45:46
 */
@Service
public interface UserService {

	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> getUserList();
	
	/**
	 * 多条件查询用户（分页）
	 * @return
	 */
	public List<User> getUserList(User user, Pagination pagination);
	
	/**
	 * 根据用户名查询用户
	 * @param UserName
	 * @return
	 */
	public User getUserByUserName(String userName);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public User addUser(User user);
	
	/**
	 * 根据用户ID删除用户
	 * @param userId
	 */
	public void delUserById(Long userId);
	
	/**
	 * 根据用户ID查询用户
	 * @param UserName
	 * @return
	 */
	public User getUserByUserId(Long userId);
	
	/**
	 * 根据用户名查询用户权限列表
	 * @param userName
	 * @return
	 */
	public User getUserRoleByUserName(String userName);
	
	/**
	 * 获取所有用户角色
	 * @return
	 */
	public List<Role> getRoleList();
	
	/**
	 * 更新用户角色关系
	 * @param userRole
	 */
	public void updateUserRole(UserRole userRole);
	
	public void Test();
	
}

