package com.roowoo.log.modules.elsedata.entity;

import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * 账号登陆性别分布情况Entity
 * @author hhy
 * @version 2018-09-27
 */
public class LoginSexDis extends DataEntity<LoginSexDis> {
	
	private static final long serialVersionUID = 1L;
	private String city;		// 城市
	private Long womanNum;		// 女生数
	private Long manNum;		// 男生数
	private String longitude;		// 经度
	private String latitude;		// 纬度
	private Long allNum;		// 男女总数
	
	public LoginSexDis() {
		super();
	}

	public LoginSexDis(String id){
		super(id);
	}

	@Length(min=1, max=64, message="城市长度必须介于 1 和 64 之间")
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
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public Long getAllNum() {
		return allNum;
	}

	public void setAllNum(Long allNum) {
		this.allNum = allNum;
	}
	
}