package com.boredou.mercury.web.apps.security;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import com.boredou.mercury.repository.entity.Permission;
import com.boredou.mercury.repository.entity.Role;
import com.boredou.mercury.repository.entity.User;
import com.boredou.mercury.server.service.UserService;

/**
 * Shiro权限认证
 * 
 * CopyRright (c) 2014: <般豆网络> Project: <mercury-web> File Name:
 * <ShiroDbRealm.java> Module ID: <(模块)类编号，可以引用系统设计中的类编号> Comments:
 * <对此类的描述，可以引用系统设计中的描述> JDK version used: <JDK1.6>
 * 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-26 上午9:43:04
 */

public class ShiroDbRealm extends AuthorizingRealm {

	public static final int INTERATIONS = 1024;
	
	private UserService userService;

	/**
	 * 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		token.setRememberMe(true);
		System.out.println("认证信息...");
		User user = userService.getUserByUserName(token.getUsername());
		if (user != null) {
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
					user.getUserName(), user.getPassword(), getName());
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = String.valueOf(principals);
		System.out.println("授权查询回调函数 userName" + userName);
		User user = userService.getUserRoleByUserName(userName);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Role role : user.getRoles()) {
				System.out.println("用户角色名" + role.getRoleName());
				// 基于Role的权限信息
				info.addRole(role.getRoleCode());
				List<Permission> permissionsList = role.getPermissions();
				// 加入权限认证
				for (Permission permission : permissionsList) {
					System.out.println("用户权限" + permission.getDescription() + "权限code" + permission.getCode());
					info.addStringPermission(permission.getCode());
				}
			}
			return info;
		} else {
			return null;
		}
	}
	
	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
