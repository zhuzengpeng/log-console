package com.roowoo.log.modules.terminal.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.terminal.entity.LoginTerminal;

/**
 * 终端登陆信息统计DAO接口
 * @author hhy
 * @version 2018-09-26
 */
@MyBatisDao
public interface LoginTerminalDao extends CrudDao<LoginTerminal> {
	
}