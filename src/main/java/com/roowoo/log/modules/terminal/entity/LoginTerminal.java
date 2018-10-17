package com.roowoo.log.modules.terminal.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.roowoo.log.common.persistence.DataEntity;

/**
 * 终端登陆信息统计Entity
 * @author hhy
 * @version 2018-09-26
 */
public class LoginTerminal extends DataEntity<LoginTerminal> {
	
	private static final long serialVersionUID = 1L;
	private Date loginTime;		// 终端登录时间
	private String terminalIp;		// 终端IP
	private Long accountNumber;		// 登录账户数
	private Long visitNumber;		// 页面访问总数
	private Long manuVisitNumber;		// 生产网访问数
	private Long travelVisitNumber;		// 旅游网访问数
	private Long studyVisitNumber;		// 学习网访问数
	private Long winNumber;		// 访问成功数
	private Long failNumber;		// 访问失败数
	private Date beginLoginTime;		// 开始 终端登录时间
	private Date endLoginTime;		// 结束 终端登录时间
	
	public LoginTerminal() {
		super();
	}

	public LoginTerminal(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	@Length(min=0, max=100, message="终端IP长度必须介于 0 和 100 之间")
	public String getTerminalIp() {
		return terminalIp;
	}

	public void setTerminalIp(String terminalIp) {
		this.terminalIp = terminalIp;
	}
	
	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public Long getVisitNumber() {
		return visitNumber;
	}

	public void setVisitNumber(Long visitNumber) {
		this.visitNumber = visitNumber;
	}
	
	public Long getManuVisitNumber() {
		return manuVisitNumber;
	}

	public void setManuVisitNumber(Long manuVisitNumber) {
		this.manuVisitNumber = manuVisitNumber;
	}
	
	public Long getTravelVisitNumber() {
		return travelVisitNumber;
	}

	public void setTravelVisitNumber(Long travelVisitNumber) {
		this.travelVisitNumber = travelVisitNumber;
	}
	
	public Long getStudyVisitNumber() {
		return studyVisitNumber;
	}

	public void setStudyVisitNumber(Long studyVisitNumber) {
		this.studyVisitNumber = studyVisitNumber;
	}
	
	public Long getWinNumber() {
		return winNumber;
	}

	public void setWinNumber(Long winNumber) {
		this.winNumber = winNumber;
	}
	
	public Long getFailNumber() {
		return failNumber;
	}

	public void setFailNumber(Long failNumber) {
		this.failNumber = failNumber;
	}
	
	public Date getBeginLoginTime() {
		return beginLoginTime;
	}

	public void setBeginLoginTime(Date beginLoginTime) {
		this.beginLoginTime = beginLoginTime;
	}
	
	public Date getEndLoginTime() {
		return endLoginTime;
	}

	public void setEndLoginTime(Date endLoginTime) {
		this.endLoginTime = endLoginTime;
	}
		
}