package com.roowoo.log.test.service;

import com.roowoo.log.common.service.TreeService;
import com.roowoo.log.common.utils.StringUtils;
import com.roowoo.log.test.dao.TestTreeDao;
import com.roowoo.log.test.entity.TestTree;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 树结构生成Service
 * @author ThinkGem
 * @version 2015-04-06
 */
@Service
public class TestTreeService extends TreeService<TestTreeDao, TestTree> {

	@Override
	public TestTree get(String id) {
		return super.get(id);
	}

	@Override
	public List<TestTree> findList(TestTree testTree) {
		if (StringUtils.isNotBlank(testTree.getParentIds())){
			testTree.setParentIds(","+testTree.getParentIds()+",");
		}
		return super.findList(testTree);
	}

	@Override
	public void save(TestTree testTree) {
		super.save(testTree);
	}

	@Override
	public void delete(TestTree testTree) {
		super.delete(testTree);
	}
	
}