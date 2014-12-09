package com.boredou.mercury.web.apps.module.action;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.boredou.mercury.web.apps.security.LoginSuccessFilter;
import com.boredou.mercury.web.base.AbstractController;
import com.boredou.mercury.web.form.LoginForm;

/**
 * 用户登录
 * 
 * CopyRright (c) 2014: <般豆网络> 
 * Project: <mercury-web> File Name: <LoginAction.java>
 * Module ID: <(模块)类编号，可以引用系统设计中的类编号> 
 * Comments:<对此类的描述，可以引用系统设计中的描述> 
 * JDK version used: <JDK1.6>
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-3-24 下午3:09:37
 */
public class LoginAction extends AbstractController{
	
	public void doLogin(Context context, Navigator navigator, @FormGroup("loginForm") LoginForm form, 
			ServletRequest request, ServletResponse response) {
		
		log.info("登录 ...");
		// 进行登录验证  
		Subject subject = SecurityUtils.getSubject();
		try {
			AuthenticationToken token = form.createAuthenticationToken();
			subject.login(form.createAuthenticationToken());
			LoginSuccessFilter loginSuccessFilter = new LoginSuccessFilter();
			loginSuccessFilter.onLoginSuccess(token, subject, request, response);
			
		} catch (UnknownAccountException e) {
			context.put("errorMsg", "用户名不存在");
			log.info("用户名不存在");
		} catch (IncorrectCredentialsException e) {
			log.info("用户名或密码错误");
			context.put("errorMsg", "用户名或密码错误");
		} catch (LockedAccountException e) {
			log.info("用户已被锁定");
			context.put("errorMsg", "用户已被锁定");
		} catch (Exception e) {
			log.error("登录失败-未知错误...");
		}
	}
	
}
