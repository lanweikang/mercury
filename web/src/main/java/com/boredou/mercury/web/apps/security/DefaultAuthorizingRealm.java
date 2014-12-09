package com.boredou.mercury.web.apps.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.server.user.query.UserQuery;

/**
 * 认证和授权类
 * 
 * @author panjunlin
 * 
 */
public class DefaultAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private UserQuery userQuery;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("========doGetAuthorizationInfo=======");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("========doGetAuthenticationInfo==============");
		return null;
	}

}
