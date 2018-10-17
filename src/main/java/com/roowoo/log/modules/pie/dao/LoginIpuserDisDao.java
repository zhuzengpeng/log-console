package com.roowoo.log.modules.pie.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.pie.entity.LoginIpuserDis;

/**
 * Ip登陆过的用户数分布DAO接口
 * @author hhy
 * @version 2018-09-22
 */
@MyBatisDao
public interface LoginIpuserDisDao extends CrudDao<LoginIpuserDis> {
	
}