package com.roowoo.log.modules.deptlog.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.deptlog.entity.DeptLogin;

import java.util.List;

/**
 * 部门登录DAO接口
 * @author zz
 * @version 2018-09-26
 */
@MyBatisDao
public interface DeptLoginDao extends CrudDao<DeptLogin> {


}