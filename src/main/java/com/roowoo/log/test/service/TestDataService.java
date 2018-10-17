package com.roowoo.log.test.service;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.test.dao.TestDataDao;
import com.roowoo.log.test.entity.TestData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2015-04-06
 */
@Service
public class TestDataService extends CrudService<TestDataDao, TestData> {

	@Override
	public TestData get(String id) {
		return super.get(id);
	}

	@Override
	public List<TestData> findList(TestData testData) {
		return super.findList(testData);
	}

	@Override
	public Page<TestData> findPage(Page<TestData> page, TestData testData) {
		return super.findPage(page, testData);
	}

	@Override
	public void save(TestData testData) {
		super.save(testData);
	}

	@Override
	public void delete(TestData testData) {
		super.delete(testData);
	}
	
}