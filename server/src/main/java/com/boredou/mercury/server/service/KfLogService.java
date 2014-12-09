package com.boredou.mercury.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boredou.mercury.repository.entity.KfLog;
import com.boredou.mercury.repository.util.Pagination;

@Service
public interface KfLogService {
	
	
	/**
	 * 根据ID查询日志记录
	 * @param refundRecordId
	 * @return
	 */
	public KfLog getKfLogById(Long kfLogId);
	
	
	/**
	 * 多条件查询日志记录
	 * @param rdfundRecord
	 * @return
	 */
	public List<KfLog> getKfLogList(KfLog kfLog, Pagination pagination);
	
	/**
	 * 更新日志
	 * @param KfLog
	 */
	public void updateKfLog(KfLog kfLog);
	
	

	/**
	 * 添加日志记录
	 * @param KfLog
	 */
	public void addKfLog(KfLog kfLog);

	
	/**
	 * 删除日志记录
	 * @param user
	 */
	public void delKfLogById(Long kfLogId);

}
