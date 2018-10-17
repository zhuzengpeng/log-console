package com.roowoo.log.modules.deptlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.deptlog.entity.DeptLogin;
import com.roowoo.log.modules.deptlog.dao.DeptLoginDao;

/**
 * 部门登录Service
 * @author zz
 * @version 2018-09-26
 */
@Service
public class DeptLoginService extends CrudService<DeptLoginDao, DeptLogin> {

    @Autowired
    private DeptLoginDao deptLoginDao;

	@Override
	public DeptLogin get(String id) {
		return super.get(id);
	}

	@Override
	public List<DeptLogin> findList(DeptLogin deptLogin) {
		return super.findList(deptLogin);
	}

	@Override
	public Page<DeptLogin> findPage(Page<DeptLogin> page, DeptLogin deptLogin) {
		return super.findPage(page, deptLogin);
	}

	@Override
	public void save(DeptLogin deptLogin) {
		super.save(deptLogin);
	}

	@Override
	public void delete(DeptLogin deptLogin) {
		super.delete(deptLogin);
	}

    public List<DeptLogin> findAll(DeptLogin entity) {
	    return deptLoginDao.findList(entity);
    }
}