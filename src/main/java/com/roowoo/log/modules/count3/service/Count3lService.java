package com.roowoo.log.modules.count3.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.count3.entity.Count3l;
import com.roowoo.log.modules.count3.dao.Count3lDao;

/**
 * count3Service
 * @author liujiale
 * @version 2018-09-25
 */
@Service
@Transactional(readOnly = true)
public class Count3lService extends CrudService<Count3lDao, Count3l> {

	@Override
	public Count3l get(String id) {
		return super.get(id);
	}

	@Override
	public List<Count3l> findList(Count3l count3l) {
		return super.findList(count3l);
	}

	@Override
	public Page<Count3l> findPage(Page<Count3l> page, Count3l count3l) {
		return super.findPage(page, count3l);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Count3l count3l) {
		super.save(count3l);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Count3l count3l) {
		super.delete(count3l);
	}
	
}