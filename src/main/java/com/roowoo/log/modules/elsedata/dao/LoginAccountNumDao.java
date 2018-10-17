package com.roowoo.log.modules.elsedata.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.elsedata.entity.LoginAccountNum;

/**
 * 账户登陆次数统计DAO接口
 * @author hhy
 * @version 2018-09-22
 */
@MyBatisDao
public interface LoginAccountNumDao extends CrudDao<LoginAccountNum> {
	
}