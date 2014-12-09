package com.boredou.mercury.web.apps.module.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.boredou.mercury.web.base.AbstractController;
import com.boredou.mercury.web.form.LoginForm;
import com.boredou.mercury.web.form.UserForm;

public class UserAction extends AbstractController {

	public void doLogin(Context context, @FormGroup("loginForm") LoginForm form) {
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(form.createAuthenticationToken());
			// } catch (UnknownAccountException e) {
			//
			// } catch (IncorrectCredentialsException e) {
			// 
			// } catch (LockedAccountException e) {
			
		} catch (Exception e) {
			log.warn("", e);
		}
	}

	public void doSave(Context context, @FormGroup("UserForm") UserForm form) {
		
	}

	public void doUpdate(Context context, @FormGroup("UserForm") UserForm form) {
	}

}
