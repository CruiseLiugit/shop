package com.shop.module.privilege.model;

import java.util.Date;
/**
 * 权限表
 * @author miaohanbin
 *
 */
public class Authority {
	private long id;//  唯一id
	private String authCode;//   权限编码	  
	private String isMenu;//  是否是菜单	  
	private String menuCode;//  资源编码	  
	private String modelCode;//  模型编码	  
	private Date   createDate;//   创建时间	  	  
	private String status="1";// 状态
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getaAuthCode() {
		return authCode;
	}

	public void setCode(String authCode) {
		this.authCode = authCode;
	}

	public String getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
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
