package com.roowoo.log.modules.count6laccountmessage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.count6laccountmessage.entity.Count6lAccountMesssage;
import com.roowoo.log.modules.count6laccountmessage.dao.Count6lAccountMesssageDao;

/**
 * count6laccountmessageService
 * @author liujiale
 * @version 2018-09-26
 */
@Service
public class Count6lAccountMesssageService extends CrudService<Count6lAccountMesssageDao, Count6lAccountMesssage> {

	@Override
	public Count6lAccountMesssage get(String id) {
		return super.get(id);
	}

	@Override
	public List<Count6lAccountMesssage> findList(Count6lAccountMesssage count6lAccountMesssage) {
		return super.findList(count6lAccountMesssage);
	}

	@Override
	public Page<Count6lAccountMesssage> findPage(Page<Count6lAccountMesssage> page, Count6lAccountMesssage count6lAccountMesssage) {
		return super.findPage(page, count6lAccountMesssage);
	}

	@Override
	public void save(Count6lAccountMesssage count6lAccountMesssage) {
		super.save(count6lAccountMesssage);
	}

	@Override
	public void delete(Count6lAccountMesssage count6lAccountMesssage) {
		super.delete(count6lAccountMesssage);
	}
	
}