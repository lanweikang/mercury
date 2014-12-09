package com.boredou.mercury.web.apps.module.screen.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.Role;
import com.boredou.mercury.repository.entity.User;
import com.boredou.mercury.repository.entity.UserRole;
import com.boredou.mercury.server.service.UserService;
import com.boredou.mercury.web.base.AbstractController;
import com.boredou.mercury.web.util.CommentUtil;
import com.boredou.mercury.web.write.JsonHttpWrite;

public class UserEdit extends AbstractController {
	
	@Autowired
	private UserService userService;

	/**
	 * 等同于execute
	 * 
	 * @param context
	 */
	public void doPerform(Context context,Navigator navigator) {
		System.out.println("doSearch:" + getParametersMap());
		try {
			String userName = getParametersMap().get("userName");
			System.out.println(userName);
			if (userName != null && !userName.equals("")) {
				User user = userService.getUserRoleByUserName(userName);
				if (user.getRoles().size() > 0) {
					Role userRole = user.getRoles().get(0);
					context.put("userRole", userRole);
				}
				context.put("user", user);
				List<Role> roleList = userService.getRoleList();
				context.put("roleList", roleList);
			}
		} catch (Exception e) {
			navigator.redirectToLocation("/500.htm");
		}
		
	}
	
}

