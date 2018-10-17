package com.roowoo.log.modules.sys.dao;

import com.roowoo.log.common.persistence.TreeDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.sys.entity.Area;

/**
 * 区域DAO接口
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {

}
