package com.roowoo.log.modules.sys.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.sys.entity.Dict;

import java.util.List;

/**
 * 字典DAO接口
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

    List<String> findTypeList(Dict dict);

}
