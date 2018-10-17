package com.roowoo.log.modules.bar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.bar.entity.LoginPwdNum;
import com.roowoo.log.modules.bar.dao.LoginPwdNumDao;

/**
 * 一个密码被错误输入的次数Service
 * @author hhy
 * @version 2018-09-22
 */
@Service
@Transactional(readOnly = true)
public class LoginPwdNumService extends CrudService<LoginPwdNumDao, LoginPwdNum> {

	@Override
	public LoginPwdNum get(String id) {
		return super.get(id);
	}

	@Override
	public List<LoginPwdNum> findList(LoginPwdNum loginPwdNum) {
		return super.findList(loginPwdNum);
	}

	@Override
	public Page<LoginPwdNum> findPage(Page<LoginPwdNum> page, LoginPwdNum loginPwdNum) {
		return super.findPage(page, loginPwdNum);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(LoginPwdNum loginPwdNum) {
		super.save(loginPwdNum);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(LoginPwdNum loginPwdNum) {
		super.delete(loginPwdNum);
	}
	
}