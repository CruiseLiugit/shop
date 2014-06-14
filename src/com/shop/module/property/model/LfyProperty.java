package com.shop.module.property.model;

import java.util.Date;

/**
 * 属性model
 * @author Administrator
 *
 */
public class LfyProperty {
	private long id;
	private String propertyCode;
	private String propertyName;
	private String propertyType;
	private int propertyOrder;
	private String showName;
	private String status;
	private String chooseStatus;
	private Date createDate;
	private String createOpid;
	private String ischecked;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPropertyCode() {
		return propertyCode;
	}
	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public int getPropertyOrder() {
		return propertyOrder;
	}
	public void setPropertyOrder(int propertyOrder) {
		this.propertyOrder = propertyOrder;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChooseStatus() {
		return chooseStatus;
	}
	public void setChooseStatus(String chooseStatus) {
		this.chooseStatus = chooseStatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateOpid() {
		return createOpid;
	}
	public void setCreateOpid(String createOpid) {
		this.createOpid = createOpid;
	}
	public String getIschecked() {
		return ischecked;
	}
	public void setIschecked(String ischecked) {
		this.ischecked = ischecked;
	}
	
	
}
