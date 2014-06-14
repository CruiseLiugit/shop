package com.shop.module.property.model;

import java.util.Date;
/**
 * 
 * @author miaohanbin
 *
 */
public class LfyCategoryProperty {
	private long id;
	private String  categoryPropertyCode;
	private String 	categoryCode;
	private String propertyCode;
	private String status;
	private String createOpid;
	private Date createDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategoryPropertyCode() {
		return categoryPropertyCode;
	}
	public void setCategoryPropertyCode(String categoryPropertyCode) {
		this.categoryPropertyCode = categoryPropertyCode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getPropertyCode() {
		return propertyCode;
	}
	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateOpid() {
		return createOpid;
	}
	public void setCreateOpid(String createOpid) {
		this.createOpid = createOpid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
