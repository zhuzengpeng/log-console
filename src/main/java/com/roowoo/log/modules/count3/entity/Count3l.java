package com.roowoo.log.modules.count3.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * count3Entity
 * @author liujiale
 * @version 2018-09-25
 */
public class Count3l extends DataEntity<Count3l> {
	
	private static final long serialVersionUID = 1L;
	private String week;		// week
	private String failcount;		// failcount
	private String successcount;		// successcount
	
	public Count3l() {
		super();
	}

	public Count3l(String id){
		super(id);
	}

	@Length(min=1, max=50, message="week长度必须介于 1 和 50 之间")
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
	
	@Length(min=1, max=50, message="failcount长度必须介于 1 和 50 之间")
	public String getFailcount() {
		return failcount;
	}

	public void setFailcount(String failcount) {
		this.failcount = failcount;
	}
	
	@Length(min=1, max=50, message="successcount长度必须介于 1 和 50 之间")
	public String getSuccesscount() {
		return successcount;
	}

	public void setSuccesscount(String successcount) {
		this.successcount = successcount;
	}
	
}