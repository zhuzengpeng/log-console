package com.roowoo.log.modules.pie.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.pie.entity.LoginIpuserDis;
import com.roowoo.log.modules.pie.dao.LoginIpuserDisDao;

/**
 * Ip登陆过的用户数分布Service
 * @author hhy
 * @version 2018-09-22
 */
@Service
@Transactional(readOnly = true)
public class LoginIpuserDisService extends CrudService<LoginIpuserDisDao, LoginIpuserDis> {

	@Override
	public LoginIpuserDis get(String id) {
		return super.get(id);
	}

	@Override
	public List<LoginIpuserDis> findList(LoginIpuserDis loginIpuserDis) {
		return super.findList(loginIpuserDis);
	}

	@Override
	public Page<LoginIpuserDis> findPage(Page<LoginIpuserDis> page, LoginIpuserDis loginIpuserDis) {
		return super.findPage(page, loginIpuserDis);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(LoginIpuserDis loginIpuserDis) {
		super.save(loginIpuserDis);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(LoginIpuserDis loginIpuserDis) {
		super.delete(loginIpuserDis);
	}
	
}