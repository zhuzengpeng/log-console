package com.roowoo.log.modules.elsedata.service;

import java.util.List;

import com.roowoo.log.modules.elsedata.dao.LoginSexDisDao;
import com.roowoo.log.modules.elsedata.dao.LoginSexNumDao;
import com.roowoo.log.modules.elsedata.entity.LoginSexDis;
import com.roowoo.log.modules.elsedata.entity.LoginSexNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.elsedata.entity.LoginAccountNum;
import com.roowoo.log.modules.elsedata.dao.LoginAccountNumDao;

/**
 * 账户登陆次数统计Service
 * @author hhy
 * @version 2018-09-22
 */
@Service
@Transactional(readOnly = true)
public class LoginAccountNumService extends CrudService<LoginAccountNumDao, LoginAccountNum> {

	@Autowired
	private LoginSexNumDao loginSexNumDao;

	@Autowired
	private LoginSexDisDao loginSexDisDao;

	@Override
	public LoginAccountNum get(String id) {
		return super.get(id);
	}

	@Override
	public List<LoginAccountNum> findList(LoginAccountNum loginAccountNum) {
		return super.findList(loginAccountNum);
	}

	@Override
	public Page<LoginAccountNum> findPage(Page<LoginAccountNum> page, LoginAccountNum loginAccountNum) {
		return super.findPage(page, loginAccountNum);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(LoginAccountNum loginAccountNum) {
		super.save(loginAccountNum);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(LoginAccountNum loginAccountNum) {
		super.delete(loginAccountNum);
	}

	public List<LoginSexNum> findSexdata(LoginSexNum loginSexNum) {
		return loginSexNumDao.findSexdata(loginSexNum);
	}

    public List<LoginSexDis> findAccountSexdata(LoginSexDis loginSexDis) {
		return loginSexDisDao.findList(loginSexDis);
    }
}