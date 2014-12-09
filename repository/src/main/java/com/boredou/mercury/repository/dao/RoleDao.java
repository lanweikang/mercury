package com.boredou.mercury.repository.dao;

import java.util.List;
import com.boredou.mercury.repository.base.SqlMapper;
import com.boredou.mercury.repository.entity.Role;

/**
 * 角色Dao层
 * CopyRright (c) 2014:   <般豆网络>
 * Project:     <mercury-repository>
 * File Name:   <RoleDao.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2014-4-1 下午2:03:59
 */
public interface RoleDao extends SqlMapper{
	
	/**
	 * 获取所有角色
	 * @return
	 */
	List<Role> queryRoleList();

}

