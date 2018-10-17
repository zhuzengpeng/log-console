package com.roowoo.log.modules.count5.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * count5Entity
 * @author liujiale
 * @version 2018-09-25
 */
public class Count5l extends DataEntity<Count5l> {
	
	private static final long serialVersionUID = 1L;
	private String loadcount;		// loadcount
	private String ip;		// ip
	
	public Count5l() {
		super();
	}

	public Count5l(String id){
		super(id);
	}

	@Length(min=1, max=10, message="loadcount长度必须介于 1 和 10 之间")
	public String getLoadcount() {
		return loadcount;
	}

	public void setLoadcount(String loadcount) {
		this.loadcount = loadcount;
	}
	
	@Length(min=1, max=25, message="ip长度必须介于 1 和 25 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}