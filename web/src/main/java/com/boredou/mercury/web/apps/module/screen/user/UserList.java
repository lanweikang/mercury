package com.boredou.mercury.web.apps.module.screen.user;

import static cn.weili.util.SecurityUtil.md5;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.Role;
import com.boredou.mercury.repository.entity.User;
import com.boredou.mercury.repository.entity.UserRole;
import com.boredou.mercury.repository.util.Pagination;
import com.boredou.mercury.server.service.UserService;
import com.boredou.mercury.web.base.AbstractController;
import com.boredou.mercury.web.write.JsonHttpWrite;

public class UserList extends AbstractController {

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
		context.put("userName", userName);
		String userName1 = (String)request.getSession().getAttribute("userName");
		System.out.println("userName--------------------->" + userName1);
		// 获取所有用户角色
		List<Role> roleList = userService.getRoleList();
		context.put("roleList", roleList);
	}

	/**
	 * 多条件查询用户信息
	 * 
	 * @param context
	 * @param pageIndex
	 * @param start
	 * @param limit
	 */
	public void doSearch(Context context, @Param("pageIndex") int pageIndex,
			@Param("start") int start, @Param("limit") int limit) {

		System.out.println("doSearch:" + getParametersMap());
		User user = new User();
		if (getParametersMap().get("userId") != null
				&& !getParametersMap().get("userId").equals("")) {
			user.setUserId(Long.valueOf(getParametersMap().get("userId")));
		}
		user.setUserName(getParametersMap().get("userName"));
		user.setStartDate(getParametersMap().get("startDate"));
		user.setEndDate(getParametersMap().get("endDate"));
		Pagination pagination = new Pagination();
		pagination.setPP(pageIndex);
		pagination.setPageSize(limit);
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> userList = userService.getUserList(user, pagination);
		map.put("rows", userList);
		map.put("results", pagination.getTotalCount());
		JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, map);
		jsonHttpWrite.write();
	}
	
	/**
	 * 添加用户
	 */
	public void doAddUser(){
		System.out.println("参数" + getParametersMap());
		JSONObject obj = new JSONObject();
		if (getParametersMap().get("userName") != null && !getParametersMap().get("userName").equals("") &&
				getParametersMap().get("password") != null && !getParametersMap().get("password").equals("")&&
						getParametersMap().get("roleId") != null && !getParametersMap().get("roleId").equals("")) {
			User user = new User();
			user.setUserName(getParametersMap().get("userName"));
			user.setPassword(md5(getParametersMap().get("password")).trim());
			Role role = new Role();
			role.setRoleId(Long.valueOf(getParametersMap().get("roleId")));
			user.setRole(role);
			user = userService.addUser(user);
			if (user != null) {
				obj.put("success",true);
				JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response,obj);
				jsonHttpWrite.write();
			}
		}else{
			obj.put("error","参数错误");
			JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response,obj);
			jsonHttpWrite.write();
		}
	}
	
	/**
	 * 修改用户信息
	 * @param userId
	 * @param roleId
	 */
	public void doEditUser(@Param("userId")String userId, @Param("roleId")String roleId){
		System.out.println("参数" + getParametersMap());
		System.out.println("参数" + userId + "  " + roleId);
		JSONObject obj = new JSONObject();
		if (userId != null && roleId != null) {
			UserRole userRole = new UserRole();
			userRole.setUserId(Long.valueOf(userId));
			userRole.setRoleId(Long.valueOf(roleId));
			userService.updateUserRole(userRole);
			obj.put("success", true);
			JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response,obj);
			jsonHttpWrite.write();
		}else{
			obj.put("error", "参数错误");
			JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response,obj);
			jsonHttpWrite.write();
		}
	}
	
	/**
	 * 删除用户信息
	 * @param ids
	 */
	public void doDelUser(@Param("ids") String ids) {
		String[] args = ids.split(",");
		JSONObject obj = new JSONObject();
		if (args.length == 0) {
			obj.put("success",false);
			JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response,obj);
			jsonHttpWrite.write();
		} else {
			for (int i = 0; i < args.length; i++) {
				try {
					userService.delUserById(Long.valueOf(args[i]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			obj.put("success",true);
			JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response,obj);
			jsonHttpWrite.write();
		}
	}
}
