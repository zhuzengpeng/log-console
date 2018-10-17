package com.roowoo.log.modules.account.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * 账户登录Entity
 * @author zz
 * @version 2018-09-22
 */
public class AccountLogin extends DataEntity<AccountLogin> {
	
	private static final long serialVersionUID = 1L;
	private String deptId;		// 部门id
	private String accountNumber;		// 账户数
	
	public AccountLogin() {
		super();
	}

	public AccountLogin(String id){
		super(id);
	}

	@Length(min=0, max=64, message="部门id长度必须介于 0 和 64 之间")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Length(min=0, max=255, message="账户数长度必须介于 0 和 255 之间")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}