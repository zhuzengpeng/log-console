package com.roowoo.log.modules.bar.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * 一个密码被错误输入的次数Entity
 * @author hhy
 * @version 2018-09-22
 */
public class LoginPwdNum extends DataEntity<LoginPwdNum> {
	
	private static final long serialVersionUID = 1L;
	private String count;		// 失败次数
	private String num;		// 密码数量
	
	public LoginPwdNum() {
		super();
	}

	public LoginPwdNum(String id){
		super(id);
	}

	@Length(min=0, max=64, message="失败次数长度必须介于 0 和 64 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@Length(min=0, max=64, message="密码数量长度必须介于 0 和 64 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}