package com.roowoo.log.modules.elsedata.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.elsedata.entity.LoginSexDis;

/**
 * 账号登陆性别分布情况DAO接口
 * @author hhy
 * @version 2018-09-27
 */
@MyBatisDao
public interface LoginSexDisDao extends CrudDao<LoginSexDis> {
	
}