package com.roowoo.log.modules.deptlog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roowoo.log.modules.sys.entity.Menu;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.roowoo.log.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 部门登录Entity
 * @author zz
 * @version 2018-09-26
 */
public class DeptLogin extends DataEntity<DeptLogin> {
	
	private static final long serialVersionUID = 1L;
	private String state;		// 部门名
	private String deptId;		// 部门id
	private DeptLogin parent;		// 部门父级id
	private String productIp;		// 生产网地址ip
	private String sourceIp;		// 源地址ip
	private String status;		// 登录状态
	private String account;		// 登录账户
	private Date time;		// 登录时间
	private Date beginTime;		// 开始 登录时间
	private Date endTime;		// 结束 登录时间
	
	public DeptLogin() {
		super();
	}

	public DeptLogin(String id){
		super(id);
	}

	@Length(min=0, max=64, message="部门名长度必须介于 0 和 64 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=64, message="部门id长度必须介于 0 和 64 之间")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@JsonBackReference
    @NotNull
	public DeptLogin getParent() {
		return parent;
	}

	public void setParent(DeptLogin parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=64, message="生产网地址ip长度必须介于 0 和 64 之间")
	public String getProductIp() {
		return productIp;
	}

	public void setProductIp(String productIp) {
		this.productIp = productIp;
	}
	
	@Length(min=0, max=64, message="源地址ip长度必须介于 0 和 64 之间")
	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	
	@Length(min=0, max=64, message="登录状态长度必须介于 0 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=64, message="登录账户长度必须介于 0 和 64 之间")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



    @JsonIgnore
    public static void sortList(List<DeptLogin> list, List<DeptLogin> sourcelist, boolean cascade){
        for (int i=0; i<sourcelist.size(); i++){
            DeptLogin e = sourcelist.get(i);
            if (e.getDeptId()!=null && e.getParent()==null){
                list.add(e);
                if (cascade){
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j=0; j<sourcelist.size(); j++){
                        DeptLogin child = sourcelist.get(j);
                        if (child.getParent()!=null &&child.getDeptId()!=null
                                && child.getParent().equals(e.getDeptId())){
                            sortList(list, sourcelist, true);
                            break;
                        }
                    }
                }
            }
        }
    }


}