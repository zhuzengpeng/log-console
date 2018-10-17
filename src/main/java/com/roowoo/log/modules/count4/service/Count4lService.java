package com.roowoo.log.modules.count4.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.count4.entity.Count4l;
import com.roowoo.log.modules.count4.dao.Count4lDao;

/**
 * count4Service
 * @author liujaile
 * @version 2018-09-25
 */
@Service
@Transactional(readOnly = true)
public class Count4lService extends CrudService<Count4lDao, Count4l> {

	@Override
	public Count4l get(String id) {
		return super.get(id);
	}

	@Override
	public List<Count4l> findList(Count4l count4l) {
		return super.findList(count4l);
	}

	@Override
	public Page<Count4l> findPage(Page<Count4l> page, Count4l count4l) {
		return super.findPage(page, count4l);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Count4l count4l) {
		super.save(count4l);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Count4l count4l) {
		super.delete(count4l);
	}
	
}