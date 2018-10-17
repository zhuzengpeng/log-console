package com.roowoo.log.modules.pie.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * Ip登陆过的用户数分布Entity
 * @author hhy
 * @version 2018-09-22
 */
public class LoginIpuserDis extends DataEntity<LoginIpuserDis> {
	
	private static final long serialVersionUID = 1L;
	private String count;		// 登陆次数
	private String num;		// ip数
	
	public LoginIpuserDis() {
		super();
	}

	public LoginIpuserDis(String id){
		super(id);
	}

	@Length(min=0, max=64, message="登陆次数长度必须介于 0 和 64 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@Length(min=0, max=64, message="ip数长度必须介于 0 和 64 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}