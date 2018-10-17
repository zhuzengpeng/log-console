package com.roowoo.log.modules.elsedata.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * 查看性别分布情况Entity
 * @author hhy
 * @version 2018-09-25
 */
public class LoginSexNum extends DataEntity<LoginSexNum> {
	
	private static final long serialVersionUID = 1L;
	private String city;		// 城市
	private Long womanNum;		// 女生数
	private Long manNum;		// 男生数
	private Float longitude;		// 经度
	private Float latitude;		// 纬度
	
	public LoginSexNum() {
		super();
	}

	public LoginSexNum(String id){
		super(id);
	}

	@Length(min=0, max=64, message="城市长度必须介于 0 和 64 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Long getWomanNum() {
		return womanNum;
	}

	public void setWomanNum(Long womanNum) {
		this.womanNum = womanNum;
	}
	
	public Long getManNum() {
		return manNum;
	}

	public void setManNum(Long manNum) {
		this.manNum = manNum;
	}
	
	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
}