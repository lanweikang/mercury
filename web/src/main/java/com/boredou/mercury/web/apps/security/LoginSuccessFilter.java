package com.boredou.mercury.web.apps.security;

import java.net.URLEncoder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * 登录成功后调用
 * 
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-web>
 * File Name:   <LoginSuccessFilter.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-27 上午11:43:55
 */
public class LoginSuccessFilter extends FormAuthenticationFilter{
	
	@Override
	public boolean onLoginSuccess(AuthenticationToken token,
			Subject currentUser, ServletRequest request, ServletResponse response)
			throws Exception {
		System.out.println("LoginSuccessFilter...");
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		currentUser = SecurityUtils.getSubject();
		String userName = (String)currentUser.getPrincipal();
		httpServletRequest.getSession().setAttribute("userName", userName);
		String uName = URLEncoder.encode(userName, "UTF-8");
		//loginName放入cookie
		Cookie cookie = new Cookie("userName",uName);
		cookie.setMaxAge(-1);
		cookie.setPath(httpServletRequest.getContextPath().length()>0?httpServletRequest.getContextPath() : "/");
		cookie.setSecure(false);
		httpServletResponse.addCookie(cookie);
		return super.onLoginSuccess(token, currentUser, request, response);
	}

}
