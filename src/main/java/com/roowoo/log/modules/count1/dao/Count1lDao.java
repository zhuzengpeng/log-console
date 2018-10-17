package com.roowoo.log.modules.count1.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.count1.entity.Count1l;

import java.util.List;

/**
 * count1DAO接口
 * @author 刘家乐
 * @version 2018-09-22
 */
@MyBatisDao
public interface Count1lDao extends CrudDao<Count1l> {
    public abstract List<Count1l> findListByParentId();
    public abstract List<Count1l> findListByParentIdSon(String departid);

}