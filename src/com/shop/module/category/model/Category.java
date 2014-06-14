package com.shop.module.category.model;

import java.util.Date;
import java.util.List;
/**
 * 产品分类model
 * @author miaohanbin
 *
 */
public class Category {
	private long id;
	private String categoryCode;
	private String categoryName;
	private String categoryPcode;
	private String createOpid;
	private Date createDate; 
	private String status;
	private int categoryOrder;
	private String showName;
	private String categoryType;
	private String categoryRootcode;
	private List<Category> childGoodsgorys;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryPcode() {
		return categoryPcode;
	}
	public void setCategoryPcode(String categoryPcode) {
		this.categoryPcode = categoryPcode;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCategoryOrder() {
		return categoryOrder;
	}
	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getCategoryRootcode() {
		return categoryRootcode;
	}
	public void setCategoryRootcode(String categoryRootcode) {
		this.categoryRootcode = categoryRootcode;
	}
	public List<Category> getChildGoodsgorys() {
		return childGoodsgorys;
	}
	public void setChildGoodsgorys(List<Category> childGoodsgorys) {
		this.childGoodsgorys = childGoodsgorys;
	}
	
}
