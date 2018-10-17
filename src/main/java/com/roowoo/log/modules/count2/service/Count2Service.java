package com.roowoo.log.modules.count2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.count2.entity.Count2;
import com.roowoo.log.modules.count2.dao.Count2Dao;

/**
 * count2Service
 * @author liu
 * @version 2018-09-27
 */
@Service
public class Count2Service extends CrudService<Count2Dao, Count2> {
	@Autowired
	private  Count2Dao count2Dao;
	@Override
	public Count2 get(String id) {
		return super.get(id);
	}

	@Override
	public List<Count2> findList(Count2 count2) {
		return super.findList(count2);
	}

	@Override
	public Page<Count2> findPage(Page<Count2> page, Count2 count2) {
		return super.findPage(page, count2);
	}

	@Override
	public void save(Count2 count2) {
		super.save(count2);
	}

	@Override
	public void delete(Count2 count2) {
		super.delete(count2);
	}
	public  List<Count2> findListByParentId1(){
		return count2Dao.findListByParentId1();
	}

	public List<Count2> findListByParentIdSon1(String departid){ return count2Dao.findListByParentIdSon1(departid);}
	
}