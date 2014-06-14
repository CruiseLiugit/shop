package com.shop.module.privilege.model;

import java.util.Date;
/**
 * 用户角色实体类
 * @author miaohanbin
 *
 */
public class UserRole {
	private long id ;//唯一ID    
	private String userCode= " ";  //用户编码，关联用户表USER_CODE
	  
	private String roleCode= " ";//关联角色表ROLE_CODE
	  
	private Date createDate;//   创建时间
	  
	  
	private String status= "1";//  状态

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	 

}