/****************************************************************
文件名称:UserServiceImpl.java                                                
项目名称: mercury-server                                              
模块名称:                                                          
功能说明:                                                          
系统版本: 1.0                                                   
开发人员: 王树辉                                                                                                                                                  
开发时间: 2014-3-20 下午5:29:27                                     
相关文档:                                                          
*****************************************************************/
package com.boredou.mercury.server.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.boredou.mercury.repository.dao.AccountDao;
import com.boredou.mercury.repository.dao.RoleDao;
import com.boredou.mercury.repository.dao.UserDao;
import com.boredou.mercury.repository.dao.UserRoleDao;
import com.boredou.mercury.repository.entity.Role;
import com.boredou.mercury.repository.entity.User;
import com.boredou.mercury.repository.entity.UserRole;
import com.boredou.mercury.repository.util.Pagination;
import com.boredou.mercury.server.service.UserService;

/**
 * User服务实现类
 * 
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-server>
 * File Name:   <UserServiceImpl.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-20 下午5:29:31
 */
public class UserServiceImpl implements UserService{

private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}
	
	@Override
	public List<User> getUserList(User user, Pagination pagination) {
		int totalCount = userDao.queryUserCountByInfo(user);
		pagination.setTotalCount(totalCount);
		return userDao.queryUserListByInfo(user, pagination.getRowBounds());
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public void Test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserByUserId(Long userId) {
		return userDao.getUserByUserId(userId);
	}

	@Override
	public User getUserRoleByUserName(String userName) {
		return accountDao.getUserRoleByUserName(userName);
	}

	@Override
	public List<Role> getRoleList() {
		return roleDao.queryRoleList();
	}

	@Override
	public void delUserById(Long userId) {
		User user = new User();
		user.setUserId(userId);
		userDao.deleteUser(user);
	}

	@Override
	public User addUser(User user) {
		userDao.addUser(user);
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getUserId());
		userRole.setRoleId(user.getRole().getRoleId());
		userRoleDao.addUserRole(userRole);
		return user;
	}

	@Override
	public void updateUserRole(UserRole userRole) {
		// TODO 用户角色关系应为多对多，目前仅实现1对1
		// 此处应为2个结果集比较,删除老角色集与新角色集中不同的，保存新结果集。
		UserRole userRole1 = new UserRole();
		userRole1.setUserId(userRole.getUserId());
		List<UserRole> userRoleList = userRoleDao.getUserRoleByInfo(userRole1);
		for (UserRole userRole2 : userRoleList) {
			System.out.println(userRole2.getUserRoleId());
			userRoleDao.delUserRole(userRole2);
		}
		userRoleDao.addUserRole(userRole);
	}

}

