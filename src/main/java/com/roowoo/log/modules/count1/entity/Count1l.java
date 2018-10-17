package com.roowoo.log.modules.count1.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * count1Entity
 * @author 刘家乐
 * @version 2018-09-22
 */
public class Count1l extends DataEntity<Count1l> {
	
	private static final long serialVersionUID = 1L;
	private String departId;		// depart_id
	private String failcount;		// failcount
	private String successcount;		// successcount
	
	public Count1l() {
		super();
	}

	public Count1l(String id){
		super(id);
	}

	@Length(min=0, max=255, message="depart_id长度必须介于 0 和 255 之间")
	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}
	
	@Length(min=0, max=255, message="failcount长度必须介于 0 和 255 之间")
	public String getFailcount() {
		return failcount;
	}

	public void setFailcount(String failcount) {
		this.failcount = failcount;
	}
	
	@Length(min=0, max=255, message="successcount长度必须介于 0 和 255 之间")
	public String getSuccesscount() {
		return successcount;
	}

	public void setSuccesscount(String successcount) {
		this.successcount = successcount;
	}
	
}