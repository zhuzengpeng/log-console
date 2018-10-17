package com.roowoo.log.modules.sys.service;

import java.util.List;

import com.roowoo.log.modules.sys.dao.AreaDao;
import com.roowoo.log.modules.sys.entity.Area;
import com.roowoo.log.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import com.roowoo.log.common.service.TreeService;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Override
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Override
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
}
