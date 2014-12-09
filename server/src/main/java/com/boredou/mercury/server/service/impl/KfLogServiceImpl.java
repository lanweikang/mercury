package com.boredou.mercury.server.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.boredou.mercury.repository.dao.KfLogDao;
import com.boredou.mercury.repository.entity.KfLog;
import com.boredou.mercury.repository.entity.User;
import com.boredou.mercury.repository.util.Pagination;
import com.boredou.mercury.server.service.KfLogService;

public class KfLogServiceImpl implements KfLogService {
		
	@Autowired
	private KfLogDao kfLogDao;

	@Override
	public void updateKfLog(KfLog kfLog) {
		kfLogDao.updateKfLog(kfLog);
	}

	@Override
	public void addKfLog(KfLog kfLog) {
		kfLogDao.addKfLog(kfLog);

	}

	@Override
	public void delKfLogById(Long kfLogId) {
		KfLog kflog = new KfLog();
		kflog.setKfLogId(kfLogId);		
		kfLogDao.deleteKfLog(kflog);

	}

	@Override
	public KfLog getKfLogById(Long kfLogId) {
		if (kfLogId != null) {
			return kfLogDao.queryKfLogById(kfLogId);
		}
		return null;
	}

	@Override
	public List<KfLog> getKfLogList(KfLog kfLog, Pagination pagination) {
		int totalCount = kfLogDao.queryKfLogCount(kfLog);
		pagination.setTotalCount(totalCount);
		return kfLogDao.queryKfLogList(kfLog, pagination.getRowBounds());
	}

}
