package com.roowoo.log.modules.bar.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.bar.entity.LoginPwdNum;

/**
 * 一个密码被错误输入的次数DAO接口
 * @author hhy
 * @version 2018-09-22
 */
@MyBatisDao
public interface LoginPwdNumDao extends CrudDao<LoginPwdNum> {
	
}