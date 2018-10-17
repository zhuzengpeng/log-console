package com.roowoo.log.test.dao;

import com.roowoo.log.common.persistence.TreeDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.test.entity.TestTree;

/**
 * 树结构生成DAO接口
 * @author ThinkGem
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}