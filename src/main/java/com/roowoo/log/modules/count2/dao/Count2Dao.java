package com.roowoo.log.modules.count2.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.count1.entity.Count1l;
import com.roowoo.log.modules.count2.entity.Count2;

import java.util.List;

/**
 * count2DAO接口
 * @author liu
 * @version 2018-09-27
 */
@MyBatisDao
public interface Count2Dao extends CrudDao<Count2> {
    public List<Count2> findListByParentId1();

    public List<Count2> findListByParentIdSon1(String departid);
}