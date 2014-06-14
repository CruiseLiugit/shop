package com.shop.module.privilege.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 角色对象实体类
 * @author miaohanbin
 *
 */
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4388488263490875188L;
	private long id;// 唯一ID
	private String roleCode;// 角色编码
	private String roleName;// 角色名称
	private String description;// 角色描述
	private String isSystem="0";// 是否是系统产生 0否 1是 系统产生的不能删除。
	private String type="0";// 角色类型 0菜单角色，1数据角色
	private Date createDate;// 创建时间
	private String status = "1";// 状态

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
