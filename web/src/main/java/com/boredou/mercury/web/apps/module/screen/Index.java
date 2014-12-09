package com.boredou.mercury.web.apps.module.screen;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.boredou.mercury.repository.entity.Role;
import com.boredou.mercury.repository.entity.User;
import com.boredou.mercury.server.service.UserService;
import com.boredou.mercury.web.base.AbstractController;

/**
 * 首页
 * 
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-web>
 * File Name:   <Index.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-4-4 上午11:29:19
 */
public class Index extends AbstractController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 等同于execute
	 * 
	 * @param context
	 */
	public void doPerform(Context context, HttpServletRequest request) {
		
		// 获取当前用户及用户角色
		Subject subject = SecurityUtils.getSubject();
		// 获取当前登录用户
		String userName = (String)subject.getPrincipal();
		// 从session获取当前登录用户
//		String userName = (String)request.getSession().getAttribute("userName");
		User user = userService.getUserRoleByUserName(userName);
		user.setRole(user.getRoles().get(0));// 目前一个用户仅有一个角色
		context.put("user", user);
	}
}

