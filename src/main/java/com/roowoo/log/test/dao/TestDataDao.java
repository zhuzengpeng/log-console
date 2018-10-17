package com.roowoo.log.test.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.test.entity.TestData;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestDataDao extends CrudDao<TestData> {
	
}