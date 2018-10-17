package com.roowoo.log.modules.count6laccountmessage.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * count6laccountmessageEntity
 * @author liujiale
 * @version 2018-09-26
 */
public class Count6lAccountMesssage extends DataEntity<Count6lAccountMesssage> {
	
	private static final long serialVersionUID = 1L;
	private String account;		// account
	private String depart;		// depart
	private String loadDate;		// load_date
	private String loadDate1;		// load_date
	private String loadState;		// load_state
	private String sourceAddress;		// source_address
	private String targetAddress;		// target_address
	
	public Count6lAccountMesssage() {
		super();
	}

	public Count6lAccountMesssage(String id){
		super(id);
	}

	@Length(min=1, max=25, message="account长度必须介于 1 和 25 之间")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Length(min=1, max=25, message="depart长度必须介于 1 和 25 之间")
	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}
	
	@Length(min=1, max=25, message="load_date长度必须介于 1 和 25 之间")
	public String getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}
	
	@Length(min=1, max=25, message="load_state长度必须介于 1 和 25 之间")
	public String getLoadState() {
		return loadState;
	}

	public void setLoadState(String loadState) {
		this.loadState = loadState;
	}
	
	@Length(min=1, max=25, message="source_address长度必须介于 1 和 25 之间")
	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}
	
	@Length(min=1, max=25, message="target_address长度必须介于 1 和 25 之间")
	public String getTargetAddress() {
		return targetAddress;
	}

	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}

	public void setLoadDate1(String loadDate1) {
		this.loadDate1 = loadDate1;
	}

	public String getLoadDate1() {
		return loadDate1;
	}
}