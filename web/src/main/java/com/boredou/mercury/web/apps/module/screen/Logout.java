package com.boredou.mercury.web.apps.module.screen;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.alibaba.citrus.turbine.Navigator;

/**
 * 系统退出
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-web>
 * File Name:   <Logout.java>
 * Module ID:   <系统退出>
 * Comments:  <实现退出该系统功能>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-26 上午10:53:06
 */
public class Logout {
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 */
	public void execute(Navigator navigator, HttpServletRequest request,
			HttpServletResponse response) {
		
//		if (request.getSession().getAttribute("userName") != null) {
//			request.getSession().removeAttribute("userName");
//		}
		Cookie cookie = new Cookie("userName", null);
		cookie.setMaxAge(0);
		cookie.setPath(request.getContextPath().length() > 0 ? request
				.getContextPath() : "/");
		response.addCookie(cookie);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		navigator.redirectToLocation("login.htm");
	}

}

