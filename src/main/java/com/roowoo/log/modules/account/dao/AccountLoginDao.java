package com.roowoo.log.modules.account.dao;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.annotation.MyBatisDao;
import com.roowoo.log.modules.account.entity.AccountLogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 账户登录DAO接口
 * @author zz
 * @version 2018-09-22
 */
@MyBatisDao
public interface AccountLoginDao extends CrudDao<AccountLogin> {
    ArrayList<Map<String, Object>> findDept();


    List<Map<String, Object>> loginNum();

    List<Map<String, Object>> loginNum2();

    List<Map<String, Object>> loginNum3(String logdate);

    List<Map<String, Object>> loginNum4(String logdate);

    List<Map<String, Object>> loginNum5();

    List<Map<String, Object>> loginNum6();
}