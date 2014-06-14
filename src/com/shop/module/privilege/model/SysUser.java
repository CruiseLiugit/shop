package com.shop.module.privilege.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统用户实体类
 * @author miaohanbin
 *
 */
public class SysUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;//用户ID，唯一编号,系统自动增长
	private String userCode;//用户唯一编码，编码唯一
	private String loginName;
	private String logPwd;
	private String userName;
	private String email;
	private String userPhone;
	private String userType;
	private String sellerCode;
	private Date createDate;
	private String status;
	
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
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLogPwd() {
		return logPwd;
	}
	public void setLogPwd(String logPwd) {
		this.logPwd = logPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSellerCode() {
		return sellerCode;
	}
	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
