package com.roowoo.log.modules.sys.service;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.common.utils.DateUtils;
import com.roowoo.log.modules.sys.dao.LogDao;
import com.roowoo.log.modules.sys.entity.Log;
import org.springframework.stereotype.Service;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
public class LogService extends CrudService<LogDao, Log> {

	@Override
	public Page<Log> findPage(Page<Log> page, Log log) {
		
		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null){
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		
		return super.findPage(page, log);
		
	}
	
}
