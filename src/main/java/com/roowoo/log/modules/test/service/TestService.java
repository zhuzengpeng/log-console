package com.roowoo.log.modules.test.service;

import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.test.dao.TestDao;
import com.roowoo.log.modules.test.entity.Test;
import org.springframework.stereotype.Service;

/**
 * 测试Service
 * @author ThinkGem
 * @version 2013-10-17
 */
@Service
public class TestService extends CrudService<TestDao, Test> {

}
