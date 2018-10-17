package com.roowoo.log.modules.account.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.account.entity.AccountLogin;
import com.roowoo.log.modules.account.dao.AccountLoginDao;

import javax.annotation.Resource;

/**
 * 账户登录Service
 * @author zz
 * @version 2018-09-22
 */
@Service
@Transactional(readOnly = true)
public class AccountLoginService extends CrudService<AccountLoginDao, AccountLogin> {

    @Resource
    private AccountLoginDao accountLoginDao;

	@Override
	public AccountLogin get(String id) {
		return super.get(id);
	}

	@Override
	public List<AccountLogin> findList(AccountLogin accountLogin) {
		return super.findList(accountLogin);
	}

	@Override
	public Page<AccountLogin> findPage(Page<AccountLogin> page, AccountLogin accountLogin) {
		return super.findPage(page, accountLogin);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(AccountLogin accountLogin) {
		super.save(accountLogin);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(AccountLogin accountLogin) {
		super.delete(accountLogin);
	}

    public List<Map<String, Object>> findDeptList() {
        return accountLoginDao.findDept();
    }


    public List<Map<String, Object>> findLoginNum() {
        return accountLoginDao.loginNum();
    }

    public List<Map<String, Object>> findLoginNum2() {
        return accountLoginDao.loginNum2();
    }

    public List<Map<String, Object>> findLoginNum3(String logdate) {
	    return accountLoginDao.loginNum3(logdate);
    }

    public List<Map<String, Object>> findLoginNum4(String logdate) {
        return accountLoginDao.loginNum4(logdate);
    }

    public List<Map<String, Object>> findLoginNum5() {
	    return accountLoginDao.loginNum5();
    }

    public List<Map<String, Object>> findLoginNum6() {
	    return accountLoginDao.loginNum6();
    }
}