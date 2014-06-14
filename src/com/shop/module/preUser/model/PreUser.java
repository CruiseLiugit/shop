package com.shop.module.preUser.model;

import java.util.Date;

/**
 * 前台注册用户实体类
 * @author Administrator
 *
 */
public class PreUser {
	private long id;
	private String preUserCode;
	private String username;
	private String password;
	private Date createDate;
	private String status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPreUserCode() {
		return preUserCode;
	}
	public void setPreUserCode(String preUserCode) {
		this.preUserCode = preUserCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
