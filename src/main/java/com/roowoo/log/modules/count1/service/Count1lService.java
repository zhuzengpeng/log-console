package com.roowoo.log.modules.count1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.count1.entity.Count1l;
import com.roowoo.log.modules.count1.dao.Count1lDao;

/**
 * count1Service
 * @author 刘家乐
 * @version 2018-09-22
 */
@Service
@Transactional(readOnly = true)
public class Count1lService extends CrudService<Count1lDao, Count1l> {
	@Autowired
	private Count1lDao count1lDao;

	@Override
	public Count1l get(String id) {
		return super.get(id);
	}

	@Override
	public List<Count1l> findList(Count1l count1l) {
		return super.findList(count1l);
	}

	@Override
	public Page<Count1l> findPage(Page<Count1l> page, Count1l count1l) {
		return super.findPage(page, count1l);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Count1l count1l) {
		super.save(count1l);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Count1l count1l) {
		super.delete(count1l);
	}


	public List<Count1l> findListByParentId() {
		return count1lDao.findListByParentId();
	}
}