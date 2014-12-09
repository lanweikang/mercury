package com.boredou.mercury.web.apps.module.screen.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.boredou.mercury.repository.entity.User;
import com.boredou.mercury.server.service.UserService;
import com.boredou.mercury.web.base.AbstractController;
import com.boredou.mercury.web.util.CommentUtil;
import com.boredou.mercury.web.write.JsonHttpWrite;

public class UserDetail extends AbstractController {
	
	@Autowired
	private UserService userService;

	/**
	 * 等同于execute
	 * 
	 * @param context
	 */
	public void doPerform(Context context, Navigator navigator) {
		System.out.println("doSearch:" + getParametersMap());
		try {
			Long userId = Long.valueOf(getParametersMap().get("userId"));
			System.out.println(userId);
			if (userId != null) {
				User user = userService.getUserByUserId(userId);
				context.put("user", user);
				String createDate = CommentUtil.longDateGB(user.getCreateDate());
				context.put("createDate", createDate);
			}
		} catch (Exception e) {
			navigator.redirectToLocation("/500.htm");
		}
	}

	/**
	 * 搜索执行方法，使用JsonHttpWrite打印对象
	 * 
	 * @param context
	 */
	public void doSearch(Context context) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> userList = userService.getUserList();
		map.put("rows", userList);
		map.put("results", userList.size());
		JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response,map);
		jsonHttpWrite.write();
		System.out.println("doSearch:" + getParametersMap());
//		map.put("rows", list);
//		map.put("results", 40);
//		HttpWriter writer = new JsonHttpWrite(response, map);
//		
//		writer.write();
	}
}


