package com.roowoo.log.modules.count5.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.count5.entity.Count5l;
import com.roowoo.log.modules.count5.dao.Count5lDao;

/**
 * count5Service
 * @author liujiale
 * @version 2018-09-25
 */
@Service
@Transactional(readOnly = true)
public class Count5lService extends CrudService<Count5lDao, Count5l> {

	@Override
	public Count5l get(String id) {
		return super.get(id);
	}

	@Override
	public List<Count5l> findList(Count5l count5l) {
		return super.findList(count5l);
	}

	@Override
	public Page<Count5l> findPage(Page<Count5l> page, Count5l count5l) {
		return super.findPage(page, count5l);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Count5l count5l) {
		super.save(count5l);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Count5l count5l) {
		super.delete(count5l);
	}
	
}