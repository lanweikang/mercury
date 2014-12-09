package com.boredou.mercury.repository.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.boredou.mercury.repository.base.SqlMapper;
import com.boredou.mercury.repository.entity.KfLog;

public interface KfLogDao extends SqlMapper {
	
	
	/**
	 * 根据ID查询日记记录
	 * @param refundRecordId
	 * @return
	 */
	KfLog queryKfLogById(Long kfLogId);
	
	/**
	 * 多条件查询日记记录
	 * @param rdfundRecord
	 * @return
	 */
	List<KfLog> queryKfLogList(KfLog kfLog, RowBounds rowBounds);
	
	int queryKfLogCount(KfLog kfLog);
	
	/**
	 * 更新日志
	 * @param KfLog
	 */
	void updateKfLog(KfLog kfLog);
	

	/**
	 * 添加日志记录
	 * @param KfLog
	 */
	void addKfLog(KfLog kfLog);

	
	/**
	 * 删除日志记录
	 * @param user
	 */
	void deleteKfLog(KfLog kfLog);

}
