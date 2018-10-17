package com.roowoo.log.test.service;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.common.utils.StringUtils;
import com.roowoo.log.test.dao.TestDataChildDao;
import com.roowoo.log.test.dao.TestDataMainDao;
import com.roowoo.log.test.entity.TestDataChild;
import com.roowoo.log.test.entity.TestDataMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主子表生成Service
 * @author ThinkGem
 * @version 2015-04-06
 */
@Service
public class TestDataMainService extends CrudService<TestDataMainDao, TestDataMain> {

	@Autowired
	private TestDataChildDao testDataChildDao;

	@Override
	public TestDataMain get(String id) {
		TestDataMain testDataMain = super.get(id);
		testDataMain.setTestDataChildList(testDataChildDao.findList(new TestDataChild(testDataMain)));
		return testDataMain;
	}

	@Override
	public List<TestDataMain> findList(TestDataMain testDataMain) {
		return super.findList(testDataMain);
	}

	@Override
	public Page<TestDataMain> findPage(Page<TestDataMain> page, TestDataMain testDataMain) {
		return super.findPage(page, testDataMain);
	}

	@Override
	public void save(TestDataMain testDataMain) {
		super.save(testDataMain);
		for (TestDataChild testDataChild : testDataMain.getTestDataChildList()){
			if (testDataChild.getId() == null){
				continue;
			}
			if (TestDataChild.DEL_FLAG_NORMAL.equals(testDataChild.getDelFlag())){
				if (StringUtils.isBlank(testDataChild.getId())){
					testDataChild.setTestDataMain(testDataMain);
					testDataChild.preInsert();
					testDataChildDao.insert(testDataChild);
				}else{
					testDataChild.preUpdate();
					testDataChildDao.update(testDataChild);
				}
			}else{
				testDataChildDao.delete(testDataChild);
			}
		}
	}

	@Override
	public void delete(TestDataMain testDataMain) {
		super.delete(testDataMain);
		testDataChildDao.delete(new TestDataChild(testDataMain));
	}
	
}