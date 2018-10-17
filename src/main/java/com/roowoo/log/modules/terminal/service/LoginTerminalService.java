package com.roowoo.log.modules.terminal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.service.CrudService;
import com.roowoo.log.modules.terminal.entity.LoginTerminal;
import com.roowoo.log.modules.terminal.dao.LoginTerminalDao;

/**
 * 终端登陆信息统计Service
 * @author hhy
 * @version 2018-09-26
 */
@Service
public class LoginTerminalService extends CrudService<LoginTerminalDao, LoginTerminal> {

	@Override
	public LoginTerminal get(String id) {
		return super.get(id);
	}

	@Override
	public List<LoginTerminal> findList(LoginTerminal loginTerminal) {
		return super.findList(loginTerminal);
	}

	@Override
	public Page<LoginTerminal> findPage(Page<LoginTerminal> page, LoginTerminal loginTerminal) {
		return super.findPage(page, loginTerminal);
	}

	@Override
	public void save(LoginTerminal loginTerminal) {
		super.save(loginTerminal);
	}

	@Override
	public void delete(LoginTerminal loginTerminal) {
		super.delete(loginTerminal);
	}
	
}