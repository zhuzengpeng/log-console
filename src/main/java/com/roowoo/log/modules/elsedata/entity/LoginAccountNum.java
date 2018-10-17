package com.roowoo.log.modules.elsedata.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * 账户登陆次数统计Entity
 * @author hhy
 * @version 2018-09-22
 */
public class LoginAccountNum extends DataEntity<LoginAccountNum> {
	
	private static final long serialVersionUID = 1L;
	private String status;		// 状态
	private String sex;		// 性别
	private String count;		// 次数
	private String num;		// 账户数
	
	public LoginAccountNum() {
		super();
	}

	public LoginAccountNum(String id){
		super(id);
	}

	@Length(min=0, max=4, message="状态长度必须介于 0 和 4 之间")
	public String getStstus() {
		return status;
	}

	public void setStstus(String ststus) {
		this.status = ststus;
	}
	
	@Length(min=0, max=4, message="性别长度必须介于 0 和 4 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=64, message="次数长度必须介于 0 和 64 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@Length(min=0, max=64, message="账户数长度必须介于 0 和 64 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}