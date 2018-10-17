package com.roowoo.log.modules.elsedata.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.elsedata.entity.LoginSexNum;

import java.util.List;

/**
 * 查看性别分布情况DAO接口
 * @author hhy
 * @version 2018-09-25
 */
@MyBatisDao
public interface LoginSexNumDao extends CrudDao<LoginSexNum> {

    List<LoginSexNum> findSexdata(LoginSexNum loginSexNum);
}