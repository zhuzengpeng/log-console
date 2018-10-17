package com.roowoo.log.modules.sys.service;

import java.util.List;

import com.roowoo.log.modules.sys.utils.DictUtils;
import com.roowoo.log.common.utils.CacheUtils;
import com.roowoo.log.modules.sys.dao.DictDao;
import com.roowoo.log.modules.sys.entity.Dict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roowoo.log.common.service.CrudService;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Override
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Override
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

}
