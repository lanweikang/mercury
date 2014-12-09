package com.boredou.mercury.web.apps.module.screen.kflog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;
import com.boredou.mercury.repository.entity.KfLog;
import com.boredou.mercury.repository.entity.User;
import com.boredou.mercury.repository.util.Pagination;
import com.boredou.mercury.server.service.KfLogService;
import com.boredou.mercury.server.service.UserService;
import com.boredou.mercury.web.base.AbstractController;
import com.boredou.mercury.web.util.CommentUtil;
import com.boredou.mercury.web.write.JsonHttpWrite;

public class KflogList extends AbstractController {

	@Autowired
	private KfLogService kfLogService;

	@Autowired
	private UserService userService;

	/**
	 * 等同于execute
	 * 
	 * @param context
	 */
	public void doPerform(Context context) {

	}

	/**
	 * 查询分页
	 * 
	 * @param context
	 * @param pageIndex
	 * @param start
	 * @param limit
	 */
	public void doSearch(Context context, @Param("pageIndex") int pageIndex,
			@Param("start") int start, @Param("limit") int limit) {
		Pagination pagination = new Pagination();
		pagination.setPP(pageIndex);
		pagination.setPageSize(limit);
		KfLog kfLog = new KfLog();
		kfLog.setRemarks(getParametersMap().get("remarks"));
		kfLog.setWorkStatus(getParametersMap().get("workStatus"));
		String userId = getParametersMap().get("userId");
		if (userId != null && !"".equals(userId)) {
			kfLog.setUserId(Long.valueOf(userId));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<KfLog> kfLogList = kfLogService.getKfLogList(kfLog, pagination);
		map.put("rows", kfLogList);
		map.put("results", pagination.getTotalCount());
		JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, map);
		jsonHttpWrite.write();
		System.out.println("doSearch:" + getParametersMap());
	}
	

	/**
	 * 添加记录/修改记录
	 */
	public void doAddLog() {
		System.out.println("参数" + getParametersMap());
		JSONObject obj = new JSONObject();
		if (getParametersMap().get("createDate") != null
				&& !getParametersMap().get("createDate").equals("")
				&& getParametersMap().get("workStatus") != null
				&& !getParametersMap().get("workStatus").equals("")
				) {

			KfLog kfLog = new KfLog();
			// 获取充值号码
			String remarks = getParametersMap().get("remarks");
			kfLog.setRemarks(remarks);
			String workStatus = getParametersMap().get("workStatus");
			kfLog.setWorkStatus(workStatus);


			String createDate1 = getParametersMap().get("createDate");
			Date createDate = CommentUtil.parser(createDate1);
			kfLog.setCreateDate(createDate);
			// 获取当前用户及用户角色
			Subject subject = SecurityUtils.getSubject();
			// 检查用户角色是否为admin
			boolean isAdmin = subject.hasRole("administrator");
			// 获取当前登录用户
			String userName = (String) subject.getPrincipal();
			// 查询用户
			User user = userService.getUserByUserName(userName);
			if (user != null) {
				kfLog.setUserId(user.getUserId());
			}
			// 获取记录ID
			String kfLogId = getParametersMap().get("kfLogId");
			// 不为空表明此操作为修改
			if (kfLogId != null && !"".equals(kfLogId)) {
				// 查询要修改的记录
				KfLog kfLog1 = kfLogService.getKfLogById(Long.valueOf(kfLogId));
				// 判断当前登录用户是否为此订单记录添加者
				if (kfLog1.getUserId().equals(kfLog.getUserId())) {// 是
					// 验证用户角色
					if (isAdmin) {
						kfLog.setKfLogId(Long.valueOf(kfLogId));
						kfLogService.updateKfLog(kfLog);
						obj.put("success", true);
					} else {
						obj.put("error", "记录状态已修改您已无权修改此订单");
					}
				} else {// 否
						// 验证用户角色
					if (isAdmin) {
						kfLog.setKfLogId(Long.valueOf(kfLogId));
						kfLogService.updateKfLog(kfLog);
						obj.put("success", true);
					} else {
						obj.put("error", "对不起，您无权修改此订单");
					}

				}
			} else {// 否则为空就添加
				kfLog.setRemarks(remarks);
				kfLog.setWorkStatus(workStatus);
				kfLog.setCreateDate(createDate);
				// 添加记录
				kfLogService.addKfLog(kfLog);
				obj.put("success", true);

			}
		} else {
			obj.put("error", "参数错误");
		}
		JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, obj);
		jsonHttpWrite.write();
	}

	/**
	 * 删除客服日志记录
	 * 
	 * @param ids
	 */	
	public void doDelKfLog(@Param("ids") String ids) {
		System.out.println("---------------" + ids);
		String[] args = ids.split(",");
		JSONObject obj = new JSONObject();
		if (args.length == 0) {
			obj.put("success", false);
			JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, obj);
			jsonHttpWrite.write();
			System.out.println("1");
		      } else {
		    	  
		    	  KfLog kfLog = new KfLog();
		            // 获取当前用户及用户角色
					Subject subject = SecurityUtils.getSubject();
					// 检查用户角色是否为admin
					boolean isAdmin = subject.hasRole("administrator");	
					// 获取当前登录用户
					String userName = (String) subject.getPrincipal();
					// 查询用户
					User user = userService.getUserByUserName(userName);
					if (user != null) {
						kfLog.setUserId(user.getUserId());
					
						String kfLogId = args[0];	
						if(kfLogId == null){
							obj.put("success", false);
							JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, obj);
							jsonHttpWrite.write();
							System.out.println("2");
							System.out.println("----------" + kfLogId);
						}else
						 {
						 KfLog kfLog1 = kfLogService.getKfLogById(Long.valueOf(kfLogId));
				
						// 判断当前登录用户是否为此订单记录添加者
						if (kfLog1.getUserId().equals(kfLog.getUserId())) {// 是
							// 验证用户角色
							if (isAdmin) {    
								for (int i = 0; i < args.length; i++) {			    				    	 
									try {
										kfLogService.delKfLogById(Long.valueOf(args[i]));
									    } catch (Exception e) {
										e.printStackTrace();
									                          }
								                                           }	     
			                               obj.put("success", true);
			                               JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, obj);
			                               jsonHttpWrite.write();
			                               System.out.println("3");
							}else {
								obj.put("success", false);
								JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, obj);
								jsonHttpWrite.write();
								System.out.println("4");
								
					             }
							
						}else {// 否
							// 验证用户角色
							if (isAdmin) {    
								for (int i = 0; i < args.length; i++) {			    				    	 
									try {
										kfLogService.delKfLogById(Long.valueOf(args[i]));
									    } catch (Exception e) {
										e.printStackTrace();
									                          }
								          }	     
			                               obj.put("success", true);
			                               JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, obj);
			                               jsonHttpWrite.write();
			                               System.out.println("5");
							             }
							else {
								          obj.put("success", false);
								          JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, obj);
											jsonHttpWrite.write();
											System.out.println("6");
					             }							
						}
						
					}
						
		      }
					else {
				          obj.put("success", false);
				          JsonHttpWrite jsonHttpWrite = new JsonHttpWrite(response, obj);
							jsonHttpWrite.write();
							System.out.println("7");
					}
					
					
		}
	}
}
