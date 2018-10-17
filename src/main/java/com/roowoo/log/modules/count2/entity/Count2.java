package com.roowoo.log.modules.count2.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * count2Entity
 * @author liu
 * @version 2018-09-27
 */
public class Count2 extends DataEntity<Count2> {
	
	private static final long serialVersionUID = 1L;
	private String departId;		// depart_id
	private String iPhoneload;		// way
	private String fingerprintload;		// successcount
	private String PCload;		// way
	private String faceload;		// successcount
	private Count2 parent;		// parent_id

	
	public Count2() {
		super();
	}

	public void setFaceload(String faceload) {
		this.faceload = faceload;
	}

	public String getFaceload() {
		return faceload;
	}

	public Count2(String id){
		super(id);
	}

	public void setPCload(String PCload) {
		this.PCload = PCload;
	}

	public String getPCload() {
		return PCload;
	}

	public void setFingerprintload(String fingerprintload) {
		this.fingerprintload = fingerprintload;
	}

	public String getFingerprintload() {
		return fingerprintload;
	}

	public void setiPhoneload(String iPhoneload) {
		this.iPhoneload = iPhoneload;
	}

	public String getiPhoneload() {
		return iPhoneload;
	}

	@Length(min=1, max=25, message="depart_id长度必须介于 1 和 25 之间")
	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}
	
	@JsonBackReference
	@NotNull(message="parent_id不能为空")
	public Count2 getParent() {
		return parent;
	}

	public void setParent(Count2 parent) {
		this.parent = parent;
	}
	

	
}