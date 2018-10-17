package com.roowoo.log.modules.count4.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * count4Entity
 * @author liujaile
 * @version 2018-09-25
 */
public class Count4l extends DataEntity<Count4l> {
	
	private static final long serialVersionUID = 1L;
	private String weekly;		// weekly
	private String loadfail;		// loadfail
	private String loadsuccess;		// loadsuccess
	
	public Count4l() {
		super();
	}

	public Count4l(String id){
		super(id);
	}

	@Length(min=0, max=50, message="weekly长度必须介于 0 和 50 之间")
	public String getWeekly() {
		return weekly;
	}

	public void setWeekly(String weekly) {
		this.weekly = weekly;
	}
	
	@Length(min=0, max=50, message="loadfail长度必须介于 0 和 50 之间")
	public String getLoadfail() {
		return loadfail;
	}

	public void setLoadfail(String loadfail) {
		this.loadfail = loadfail;
	}
	
	@Length(min=0, max=50, message="loadsuccess长度必须介于 0 和 50 之间")
	public String getLoadsuccess() {
		return loadsuccess;
	}

	public void setLoadsuccess(String loadsuccess) {
		this.loadsuccess = loadsuccess;
	}
	
}