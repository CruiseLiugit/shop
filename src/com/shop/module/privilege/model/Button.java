package com.shop.module.privilege.model;
/**
 * 按钮功能实体类
 * @author miaohanbin
 *
 */
public class Button {
	private long  id	;	
	private String  modelCode;	
	private String 	modelName;	
	private String 	imgName;	
	private String 	modelTitle;	
	private String 	createDate;	
	private String 	status="1";
	private int sortValue;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getModelTitle() {
		return modelTitle;
	}
	public void setModelTitle(String modelTitle) {
		this.modelTitle = modelTitle;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
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
