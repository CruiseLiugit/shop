package com.shop.module.privilege.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 菜单对象实体类
 * @author miaohanbin
 *
 */
public class Menus implements Serializable{ 
	private static final long serialVersionUID = 1L;
	private long id;
	private String menuCode;
	private String menuName;
	private String levelId;
	private String fmenuCode;
	private String engName;
	private String menuUrl;
	private Date createDate;
	private String status;
	private int sortValue;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getFmenuCode() {
		return fmenuCode;
	}
	public void setFmenuCode(String fmenuCode) {
		this.fmenuCode = fmenuCode;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
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
	public int getSortValue() {
		return sortValue;
	}
	public void setSortValue(int sortValue) {
		this.sortValue = sortValue;
	}
	
}
